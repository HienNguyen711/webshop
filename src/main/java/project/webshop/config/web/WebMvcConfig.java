package project.webshop.config.web;

public class WebMvcConfig {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // For examples using Spring 4.1.0
//        if ((env.getProperty("resource.handler.conf")).equals("4.1.0")) {
//            registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(3600).resourceChain(true).addResolver(new GzipResourceResolver()).addResolver(new PathResourceResolver());
//            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/", "classpath:/other-resources/").setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//            registry.addResourceHandler("/files/**").addResourceLocations("file:/Users/Elena/").setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//            registry.addResourceHandler("/other-files/**").addResourceLocations("file:/Users/Elena/").setCachePeriod(3600).resourceChain(true).addResolver(new GzipResourceResolver());
//        }
//        // For examples using Spring 4.0.7
//        else if ((env.getProperty("resource.handler.conf")).equals("4.0.7")) {
//            registry.addResourceHandler("/resources/**").addResourceLocations("/", "/resources/", "classpath:/other-resources/");
//            registry.addResourceHandler("/files/**").addResourceLocations("file:/Users/Elena/");
//
//        }
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        registry.addInterceptor(localeChangeInterceptor);
//    }
//
//    @Bean
//    public ResourceUrlEncodingFilter resourceUrlEncodingFilter() {
//        ResourceUrlEncodingFilter filter = new ResourceUrlEncodingFilter();
//
//        return filter;
//    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
//        return cookieLocaleResolver;
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setCacheSeconds(0);
//        return messageSource;
//    }

}
