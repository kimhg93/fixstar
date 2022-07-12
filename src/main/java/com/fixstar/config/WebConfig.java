package com.fixstar.config;

import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.fixstar.Interceptor.SessionInterceptor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.fixstar.*"})
public class WebConfig implements WebMvcConfigurer {

    // resource 위치 설정 ex)css, js ....
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**");
//    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

    // json MessageConverter
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        Jackson2ObjectMapperBuilder builder =
                new Jackson2ObjectMapperBuilder()
                        .indentOutput(true)
                        .dateFormat(new SimpleDateFormat("yyyy-mm-dd"))
                        .modulesToInstall(new ParameterNamesModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(builder.build());
        converter.setSupportedMediaTypes(
                Arrays.asList(
                        new MediaType("application", "json", Charset.forName("UTF-8"))
                )
        );
        return converter;
    }

    // String MessageConverter
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(
                Arrays.asList(
                        new MediaType("text", "html", Charset.forName("UTF-8"))
                )
        );
        return converter;
    }

    // MessageConverter 적용
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
        converters.add(stringHttpMessageConverter());
    }

    // Interceptor 설정
    @Bean
    public SessionInterceptor sessionInterceptor() {
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor())
                .excludePathPatterns("/**");
    }

    // contenttype 설정
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_ATOM_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    //fileUpload
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxInMemorySize(50000000); // 50MB
        resolver.setMaxUploadSize(100000000); // 100MB
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

}
