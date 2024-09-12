package me.quyen.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafCustomizationConfig {
    /**
     * As a result, when a controller method returns a view name,
     * Thymeleaf will look for it in 4 different locations respectively:
     * 1. "/templates/templatelocation/"
     * 2. "/templates/templatelocation/other/"
     * 3. "/templates/templatelocation/another/"
     * 4. "/templates/"
     */
//	@Bean
//	public org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
//	firstTemplateResolver() {
//		org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver templateResolver
//				= new org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver();
//		templateResolver.setPrefix("classpath:/templates/templatelocation/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
//		templateResolver.setCharacterEncoding("UTF-8");
//		templateResolver.setOrder(0);
//		templateResolver.setCheckExistence(true);
//		return templateResolver;
//	}
//
//	@Bean
//	public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver secondTemplateResolver() {
//		org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
//				= new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("templates/templatelocation/other/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
//		templateResolver.setCharacterEncoding("UTF-8");
//		templateResolver.setOrder(1);
//		templateResolver.setCheckExistence(true);
//		return templateResolver;
//	}
//
//	@Bean
//	public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver thirdTemplateResolver() {
//	org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
//			= new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
//		org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
//				= new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
//		templateResolver.setPrefix("templates/templatelocation/another/");
//		templateResolver.setSuffix(".html");
//		templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
//		templateResolver.setCharacterEncoding("UTF-8");
//		templateResolver.setOrder(2);
//		templateResolver.setCheckExistence(true);
//		return templateResolver;
//	}

    @Bean
    public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
    startupWebsiteTemplateResolver() {
        org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
                = new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/startup-website-template/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(3);
        templateResolver.setCheckExistence(true);
        return templateResolver;
    }

    @Bean
    public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver realEstateTemplateResolver() {
        org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
                = new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/real-estate/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(4);
        templateResolver.setCheckExistence(true);
        return templateResolver;
    }

    @Bean
    public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver educationTemplateResolver() {
        org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
                = new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/education/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(5);
        templateResolver.setCheckExistence(true);
        return templateResolver;
    }
    @Bean
    public org.thymeleaf.templateresolver.ClassLoaderTemplateResolver electroSellTemplateResolver() {
        org.thymeleaf.templateresolver.ClassLoaderTemplateResolver templateResolver
                = new org.thymeleaf.templateresolver.ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/electro-sell/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(org.thymeleaf.templatemode.TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(6);
        templateResolver.setCheckExistence(true);
        return templateResolver;
    }
}
