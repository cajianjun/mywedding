package com.cjj.wedding;  
  
import org.springframework.boot.context.properties.ConfigurationProperties;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.Configuration;  
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;  
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;  
import springfox.documentation.spring.web.plugins.Docket;  
import springfox.documentation.swagger2.annotations.EnableSwagger2;  
  

@Configuration  
@EnableSwagger2  
@ConfigurationProperties  

public class Swagger2Config {  
  
    @Bean  
    public Docket createRestApi() {  
//    	ParameterBuilder tokenPar = new ParameterBuilder();  
//        List<Parameter> pars = new ArrayList<Parameter>();  
//        tokenPar.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();  
//        pars.add(tokenPar.build());  
    	
        return new Docket(DocumentationType.SWAGGER_2)  
//                .apiInfo(apiInfo())  
//                .groupName("test")  
//                .genericModelSubstitutes(DeferredResult.class)  
//                .useDefaultResponseMessages(false)  
//                .forCodeGeneration(true)  
//                .select()  
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))  
//                .paths(PathSelectors.any())  
//                .build()
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cjj"))
                .paths(PathSelectors.any())
                .build();
//                .globalOperationParameters(pars);
    }  
  
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("cjj")  
                .description("查看最新接口变化日志,点see more！！")  
                .contact(new Contact("JEFF DEAN", "/patent/admin/es/log", "11111@qq.com"))  
                .version("0.0.2")  
                .license("cjj")  
                .licenseUrl("baidu.com")  
                .build();  
    }  
}  