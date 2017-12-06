//package com.und.security.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.ComponentScan
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.web.cors.CorsConfiguration
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource
//import org.springframework.web.filter.CorsFilter
//import org.springframework.web.servlet.config.annotation.CorsRegistry
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@ComponentScan("com.und")
//class WebSecurityConfig2 : WebSecurityConfig() {
//
//    //    @Bean
////    fun corsFilter(): CorsFilter {
////        val source = UrlBasedCorsConfigurationSource()
////        val config = CorsConfiguration()
////        config.addAllowedOrigin("*")
////        config.addAllowedHeader("*")
////        config.addAllowedMethod("*")
////        config.addExposedHeader("Authorization")
////        config.addExposedHeader("Content-Type")
////        source.registerCorsConfiguration("/**", config)
////        return CorsFilter(source)
////    }
//
////    fun addCorsMappings1(registry: CorsRegistry) {
////        registry.addMapping("/**")
////                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
////    }
//}