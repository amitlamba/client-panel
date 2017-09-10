package com.und


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = arrayOf("com.und"))
class ClientPanelApplication

fun main(args: Array<String>) {

    SpringApplication.run(ClientPanelApplication::class.java, *args)
}