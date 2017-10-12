package com.und.security.model

class EmailMessage(
        val from: String,
        val to: String,
        var subject: String = "",
        var body: String = ""

) {

    fun buildBody(builder: (user: User, arguments: Map<String, String>) -> String): String {
        return ""
    }
}