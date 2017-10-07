package com.und.repository

import com.und.model.EmailTemplate
import org.springframework.data.jpa.repository.JpaRepository

interface EmailTemplateRepository : JpaRepository<EmailTemplate, Long> {
    fun findByClientID(clientID: Long = 1): List<EmailTemplate>
}