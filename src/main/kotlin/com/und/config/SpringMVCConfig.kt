package com.und.config


import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
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

/*    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val source = ResourceBundleMessageSource()
        source.setBasenames("i18/users", "i18/errormsg")
        source.setUseCodeAsDefaultMessage(true)
        return source
    }*/

    @Bean
    fun taskScheduler(): TaskScheduler {
        return ConcurrentTaskScheduler() //single threaded by default
    }

    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
        configurer.enable()
    }

//    @Bean
//    fun corsFilter(): FilterRegistrationBean<*> {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        config.addAllowedOrigin("http://localhost:4200")
//        config.addAllowedHeader("*")
//        config.addAllowedMethod("*")
//        source.registerCorsConfiguration("/**", config)
//        val bean = FilterRegistrationBean(CorsFilter(source))
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE)
//        return bean
//    }

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


//    @Bean
//    fun corsConfigurationSource(): CorsConfigurationSource {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
////        config.addAllowedOrigin("http://localhost:4200")
//        config.allowedOrigins = listOf("*")
//        config.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
//        config.allowedMethods = listOf("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH")
////        config.addExposedHeader("authorization")
////        config.addExposedHeader("Content-Type")
//        source.registerCorsConfiguration("/**", config)
//        return source
//    }

}
