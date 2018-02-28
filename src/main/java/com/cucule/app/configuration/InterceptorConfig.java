package com.cucule.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cucule.app.Interceptor.ClientAuthInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    ClientAuthInterceptor cuculeHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO pathちゃんと明示するべき
        registry.addInterceptor(this.cuculeHandlerInterceptor).addPathPatterns("/**");
    }
}
