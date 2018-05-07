package com.und.repository.mongo

interface EventCustomRepository {
    fun usersFromEvent(query: String, clientId: Long): List<String>

}