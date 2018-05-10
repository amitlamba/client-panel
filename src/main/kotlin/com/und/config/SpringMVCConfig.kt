package com.und.config


import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.JstlView


/**
 *
 * @author amit
 */
@Configuration
class SpringMVCConfig : WebMvcConfigurer {


    @Bean
    fun getInternalResourceViewResolver(): InternalResourceViewResolver {
        val resolver = InternalResourceViewResolver()
        resolver.setPrefix("/WEB-INF/views/")
        resolver.setSuffix(".jsp")
        resolver.setViewClass(JstlView::class.java)
        return resolver
    }


    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }


    @Bean
    fun corsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("*")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        val bean = FilterRegistrationBean(CorsFilter(source))
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
        return CorsFilter(source)
    }


}
