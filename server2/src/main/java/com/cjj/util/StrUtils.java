package com.cjj.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caojianjun
 *	字符串处理工具类
 */
public class StrUtils {
	/**
	 * 判断字符串是否为空
	 * null，空字符串，空格字符串都返回true
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if(s == null || s.trim().equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * isEmpty相反
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}
	
	
	/**
	 * 将["xxx","xxx","xxx"...]形式的"["和"]"删除
	 * 
	 * @param str
	 * @return
	 */
	public static String manipulateStringlists(String str) {
		if (isEmpty(str)) {
			return str;
		}
		String newstr = str.replace("[", "");
		return newstr.replace("]", "");
	}
	
	public static String removeCNandPoint(String apid) {
		apid = apid.replace("CN", "");
		apid = apid.replace(".", "");
		return apid;
	}
	
	
      /**
       * 使用md5的算法进行加密
       */
      public static String md5(String plainText) {
         byte[] secretBytes = null;
         try {
             secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
         String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
         // 如果生成数字未满32位，需要前面补0
         for (int i = 0; i < 32 - md5code.length(); i++) {
             md5code = "0" + md5code;
         }
         return md5code;
     }
	 
  	/**
  	 * 传入Integer返回专利类型
  	 * @param s
  	 * @return String 
  	 */
	public static String getChinesePatentType(Integer dbtype) {
		switch (dbtype) {
		case 1:
			return "发明专利";
		case 2:
			return "实用新型";
		case 3:
			return "外观专利";
		default:
			break;
		}
		return "";
	}
	
	public static boolean check(String str, String regex) {
		boolean flag = false;
		try {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(str);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static String genUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * 在commonProperties中完成赋值
	 */
	public static String prefix = "";
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhh");
	{
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0800"));
	}
	
	/**
	 * 根据时间生成随机订单号
	 * @return
	 */
	public static String genOrderNum(String lastorderNum) {
		if(isEmpty(lastorderNum) || lastorderNum.length() != 12) {
			lastorderNum = "000000000000";
		}
		//channel 1代表网站2代表app,暂时只用1
		String channel = "1";
		String orderTime = channel + dateFormat.format(new Date());
		if(orderTime.equals(lastorderNum.substring(0,lastorderNum.length()-3))) {
			int index = 1000 + Integer.valueOf(lastorderNum.substring(lastorderNum.length()-4));
			index ++;
			return orderTime + (index + "").substring(1);
		}else {
			return orderTime + "000";
		}
	}
	
	/**
	 * 根据时间生成随机支付订单号
	 * @return
	 */
	public static String genPayNum() {
		// TODO 暂时只用时间戳，功能完成后再加
		String orderNum = String.valueOf(System.currentTimeMillis()) + (Math.round((Math.random() + 1) * 1000000) + "").substring(1);
		return orderNum;
	}

	public static String arr2String(String[] ss) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(String s :ss) {
			if(!isEmpty(s)) {
				sb.append(s.trim()).append(",");
			}
		}
		String finalStr = sb.toString();
		finalStr = finalStr.substring(0,finalStr.length() -1) + "]";
		return finalStr;
	}
	public static String list2String(List<String> ss) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(String s :ss) {
			if(!isEmpty(s)) {
				sb.append(s.trim()).append(",");
			}
		}
		String finalStr = sb.toString();
		finalStr = finalStr.substring(0,finalStr.length() -1) + "]";
		return finalStr;
	}
	public static List<String> string2List(String s){
		List<String> returnList = new ArrayList<String>();
		s = s.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] ss = s.split(",");
		for(String tmpS:ss) {
			if(!isEmpty(tmpS) && !"null".equals(tmpS.trim())) {
				returnList.add(tmpS.trim());
			}
		}
		return returnList;
	}
	public static String[] string2Arr(String s) {
		return string2List(s).toArray(new String[] {});
	}
	
	
	/**去掉两边的[]
	 * @param s
	 * @return
	 */
	public static String removeOuters(String s) {
		if(s == null) {
			return "";
		}
		if(s.startsWith("[")) {
			s = s.substring(1);
		}
		if(s.endsWith("]")) {
			s = s.substring(0,s.length() -1);
		}
		return s;
	}

	/**
	 * 根据专利申请号获取专利类型
	 * 规则：以2003年为界限，之前的是前两位数字代表年份，之后前四位数字代表年份。2003年则部分是前两位算年份，部分前四位代表年份
	 * 
	 * @param aid
	 * @return
	 */
	public static int getPatentType(String aid) {
		if (aid.startsWith("CN")) {
			String year = aid.substring(2, 4);
			if ("20".equals(year)) {
				if (Integer.valueOf(aid.substring(6, 7)) == 8) {
					return 1;
				} else if (Integer.valueOf(aid.substring(6, 7)) == 9) {
					return 2;
				} else {
					return Integer.valueOf(aid.substring(6, 7));
				}
			} else {
				if (Integer.valueOf(aid.substring(4, 5)) == 8) {
					return 1;
				} else if (Integer.valueOf(aid.substring(4, 5)) == 9) {
					return 2;
				} else {
					return Integer.valueOf(aid.substring(4, 5));
				}
			}
		}
		return 1;
	}
	
	/**
	 * 根据aid获取专利的类型名称
	 * @param aid
	 * @return
	 */
	public static String  getPatentDBName(String aid) {
		int dbType = getPatentType(aid);
		switch (dbType) {
		case 1:
			return "FMZL";
		case 2:
			return "SYXX";
		case 3:
			return "WGZL";
		default:
			break;
		}
		return "FMZL";
	}
	
	/**根据int专利类型返回专利中文名称1.发明专利，2。实用新型，3.外观专利，4。发明授权
	 * @param type
	 * @return
	 */
	public static String getPatentCNNameByType(int type) {
		String result = "发明专利";
		switch (type) {
		case 1:
			
			break;
		case 2:
			result = "实用新型";
			break;
		case 3:
			result  = "外观专利";
			break;
		case 4:
			result = "发明授权";
			break;
		default:
			break;
		}
		return result;
	}
	
	/**
	 * 数组字符串用sep符号组合
	 * @param ss
	 * @param sep
	 * @return
	 */
	public static String joinArr(String[] ss,String sep) {
		if(ss == null || ss.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String s:ss) {
			sb.append(sep).append(s);
		}
		String returnS = sb.toString();
		return returnS;
	}
	/**
	 * 数组字符串用sep符号组合
	 * @param ss
	 * @param sep
	 * @return
	 */
	public static String joinArr(List<String> ss,String sep) {
		if(ss == null || ss.size() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(String s:ss) {
			sb.append(s).append(sep);
		}
		String returnS = sb.toString();
		return returnS.substring(0,returnS.length() - sep.length());
	}

	/**
	 * 根据数据库里存放的图片路径，拼接绝对图片网络地址
	 * 
	 * @param prefix
	 * @param path
	 * @return
	 */
	public static String getAbsolutePicUrl(String path) {

		return prefix + path;// path.replaceAll("\\\\", "/");
	}

	/**
	 * 验证手机号码
	 * 
	 * 移动号码段:134、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
	 * 联通号码段:130、131、132、136、145、155、156、176、185、186
	 * 电信号码段:133、153、173、177、180、181、189
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean PHONE(String phone) {
		if (!isEmpty(phone)) {
			String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[3,6-8])|(18[0-9]))\\d{8}$";
			return check(phone, regex);
		}
		return false;
	}

	/**
	 * 验证姓名格式
	 * 
	 * @param name
	 * @return
	 */
	public static boolean NAME(String name) {
		if (!isEmpty(name)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证地址格式
	 * 
	 * @param address
	 * @return
	 */
	public static boolean ADDRESS(String address) {
		if (!isEmpty(address)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证银行卡格式
	 * 
	 * @param bankCard
	 * @return
	 */
	public static boolean BANK_CARD(String bankCard) {
		if (!isEmpty(bankCard)) {
			return true;
		}
		return false;
	}

	/**
	 * 验证电子邮件格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean EMAIL(String email) {
		if (!isEmpty(email)) {
			String regex = "^[a-zA-Z0-9_\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}$";
			return check(email, regex);
		}
		return false;
	}

	public static boolean EMPTY(String s) {
		return isEmpty(s);
	}
	
	
}
