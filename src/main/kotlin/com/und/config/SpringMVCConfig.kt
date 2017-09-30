package com.und.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
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

    @Bean
    fun  messageSource() : ResourceBundleMessageSource {
        val source = ResourceBundleMessageSource();
        source.setBasenames("i18/users", "i18/errormsg");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }


    override fun configureDefaultServletHandling(configurer:DefaultServletHandlerConfigurer){
        configurer.enable();
    }

}
