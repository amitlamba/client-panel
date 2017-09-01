package com.und


import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ClientPanelApplication

fun main(args: Array<String>) {

    SpringApplication.run(ClientPanelApplication::class.java, *args)
}