package com.und.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView


/**
 *
 * @author amit
 */
@Configuration
//@ComponentScan(basePackages = arrayOf("com.und"))
class SpringMVCConfig : WebMvcConfigurerAdapter() {

    @Bean
    open fun  getInternalResourceViewResolver() : InternalResourceViewResolver {
        val resolver  =  InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView::class.java);
        return resolver;
    }

    override fun configureDefaultServletHandling(configurer:DefaultServletHandlerConfigurer){
        configurer.enable();
    }

}
