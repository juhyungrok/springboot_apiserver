package org.zero.apiserver.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zero.apiserver.controller.formatter.LocalDateFormatter;



//Formatter를 이용한 LocalDate 처리

@Configuration
@Log4j2
public class CoustomServletConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new LocalDateFormatter());
        log.info("0000000000000000000");
        log.info("addformatter");
    }

    @Override
    //react와 연결하기 위해서 하는 법칙
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //모든 곳에서 접속 권한 허용
                .maxAge(500)
                .allowedMethods("GET","POST","DELETE","HEAD","OPTIONS");
    }
}
