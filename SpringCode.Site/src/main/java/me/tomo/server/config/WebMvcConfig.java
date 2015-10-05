package me.tomo.server.config;

import me.tomo.server.interceptors.ExecuteTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by usr0200379 on 15/10/01.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"me.tomo"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new InternalResourceViewResolver();
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/views/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExecuteTimeInterceptor());
//        registry.addInterceptor(new TransactionInterceptor()).addPathPatterns("/person/save/*");
    }

}
