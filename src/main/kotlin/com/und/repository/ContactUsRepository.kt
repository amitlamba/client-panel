package com.und.repository

import com.und.model.jpa.ContactUs
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactUsRepository: JpaRepository<ContactUs,Long>{


}