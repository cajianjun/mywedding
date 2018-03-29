package com.weiwei.patent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBEntityFactory {

	private String packageOutPath = "com.cjj.entitys";
	private String packageOutPath2 = "com.cjj.entitys";
	private static String dbname = "wedding";
	private String[] colnames;
	private String[] colTypes;
	private boolean util = false;
	private boolean decimal = false;
	String lineSeparator = System.getProperty("line.separator", "\n");  

	public static void main(String[] args) {
		new DBEntityFactory();
	}
	
	
	
	private  List<String> getTables(Connection conn) throws SQLException {  
		List<String> tableNames = new ArrayList<String>();
        DatabaseMetaData dbMetData = conn.getMetaData();  
        // mysql convertDatabaseCharsetType null  
        ResultSet rs = dbMetData.getTables(null,  
        		dbname, null,  
                new String[] { "TABLE", "VIEW" });  
  
        while (rs.next()) {  
            if (rs.getString(4) != null  
                    && (rs.getString(4).equalsIgnoreCase("TABLE") || rs  
                            .getString(4).equalsIgnoreCase("VIEW"))) {  
                String tableName = rs.getString(3).toLowerCase();  
                tableNames.add(tableName) ;
            }  
        }  
        return tableNames;
    }  
	
	public DBEntityFactory() {
		List<String> ignoreList = new ArrayList<String>();
		ignoreList.add("oauth_access_token");
		ignoreList.add("oauth_approvals");
		ignoreList.add("oauth_client_details");
		ignoreList.add("oauth_client_token");
		ignoreList.add("oauth_code");
		ignoreList.add("oauth_refresh_token");
		ignoreList.add("user_info");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://106.14.203.125:3306/" + dbname + "?autoReconnect=true&useSSL=false", "root", "caojianjun666");
			List<String> tbNames = getTables(con);
			for(String tbName:tbNames) {
					if(ignoreList.indexOf(tbName) > -1) {
						continue;
					}
					PreparedStatement pStemt = con.prepareStatement("select * from " + dbname + "." + tbName);
					ResultSetMetaData rsmd = pStemt.getMetaData();
					int size = rsmd.getColumnCount();
					colnames = new String[size];
					colTypes = new String[size];
					for (int i = 0; i < size; i++) {
						colnames[i] = rsmd.getColumnName(i + 1);
						colTypes[i] = rsmd.getColumnTypeName(i + 1);
						if (colTypes[i].equalsIgnoreCase("datetime")) {
							util = true;
						}
						if (colTypes[i].equalsIgnoreCase("decimal")) {
							decimal = true;
						}
	//				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
	//					sql = true;
	//				}
					}
					String content = parse(colnames, colTypes,tbName);
					File directory = new File("");
					String outputPath = directory.getAbsolutePath() + "/src/main/java/" + this.packageOutPath.replace(".", "/") + "/"
							+ initcap(name2Tuofeng(tbName)) + "Entity.java";
					createIfNotExist(outputPath);
					FileWriter fw = new FileWriter(outputPath);
					
					
					PrintWriter pw = new PrintWriter(fw);
					pw.print(content);
					pw.flush();
					pw.close();
					System.out.println(outputPath + "done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createIfNotExist(String s ) {
		File f = new File(s);
		if(!f.exists()) {
			try {
				f.createNewFile();
				System.out.println("create new file success,path=" + s);
			} catch (IOException e) {
				System.out.println("create new file failed,path=" + s);
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 涓嬪垝绾块鏍肩殑瀛楃涓茶浆鍖栨垚椹煎嘲鍛藉悕鐨勫瓧绗︿覆渚嬪锛歵able_user ->tableUser
	 * @param s
	 * @return
	 */
	private static String name2Tuofeng(String s) {
		String[] ss = s.split("_");
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for(String shortS :ss) {
			if(i == 0) {
				i ++;
				sb.append(shortS);
				continue;
			}
			shortS = initcap(shortS);
			sb.append(shortS);
		}
		return sb.toString();
	}
	
	/**
	 * 鏈熬杩炴帴缁撴潫绗�
	 * @param sb
	 * @param content
	 */
	private void appendL(StringBuilder sb,String content) {
		sb.append(content).append(lineSeparator);
	}

	private String parse(String[] colnames, String[] colTypes,String tablename) {
		StringBuilder sb = new StringBuilder();
		appendL(sb, "package " + this.packageOutPath2 + ";");
		if (util) {
			appendL(sb,"import java.util.Date;");
		}
		if(decimal) {
			appendL(sb,"import java.math.BigDecimal;");
		}
		appendL(sb,"public class " + initcap(name2Tuofeng(tablename)) + "Entity{");
		for (int i = 0; i < colnames.length; i++) {
			appendL(sb,"	private " + sqlType2JavaType(colTypes[i]) + " " + name2Tuofeng(colnames[i]) + ";");
		}
		appendL(sb,"	public " + initcap(name2Tuofeng(tablename)) + "Entity(){}");
		sb.append("	public " + initcap(name2Tuofeng(tablename)) + "Entity(");
		for (int j = 0; j < colnames.length; j++) {
			sb.append(sqlType2JavaType(colTypes[j]) + " " + name2Tuofeng(colnames[j]));
			if (j != colnames.length - 1)
				sb.append(",");
		}
		appendL(sb,"){");
		appendL(sb,"		this();");
		for (int s = 0; s < colnames.length; s++) {
			appendL(sb,"		this." + name2Tuofeng(colnames[s]) + "=" + name2Tuofeng(colnames[s]) + ";");
		}
		appendL(sb,"	}");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("	public void set" + initcap(name2Tuofeng(colnames[i])) 
			+ "(" + sqlType2JavaType(colTypes[i]) + " " + name2Tuofeng(colnames[i])
					+ "){");
			sb.append("this." + name2Tuofeng(colnames[i]) + "=" + name2Tuofeng(colnames[i]) + ";");
			sb.append("}");
			appendL(sb,"");
			sb.append("	public " + sqlType2JavaType(colTypes[i]) + " get" + initcap(name2Tuofeng(colnames[i])) + "(){");
			sb.append("return " + name2Tuofeng(colnames[i]) + ";");
			sb.append("}");
			appendL(sb,"");
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 棣栧瓧姣嶅ぇ鍐�
	 * @param str
	 * @return
	 */
	private static String initcap(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private String sqlType2JavaType(String sqlType) {
		
		sqlType = sqlType.replace("UNSIGNED","");
		sqlType = sqlType.trim();
		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "Long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "Float";
		} else if(sqlType.equalsIgnoreCase("decimal")){
			return "BigDecimal";
		} else if (sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "Double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}else {
			System.out.println("sqlType=" + sqlType + ",cant be recogized");
		}
		return null;
	}


}