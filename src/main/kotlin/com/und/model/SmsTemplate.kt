package com.und.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.validator.constraints.Email
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

@Entity
@Table(name = "sms_template")
class SmsTemplate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sms_template_id_seq")
    @SequenceGenerator(name = "sms_template_id_seq", sequenceName = "sms_template_id_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "appuser_id")
    @NotNull
    var appuserID: Long? = null

    @Column(name = "sms_template_body")
    @NotNull
    lateinit var smsTemplateBody: String

    @Column(name = "parent_id")
    @Null
    var parentID: Long? = null

    @Email
    @Column(name = "from_user")
    @NotNull
    lateinit var from: String

    @Column(name = "message_type") //Promotional or Transactional
    @NotNull
    @Enumerated(EnumType.STRING)
    var messageType: EmailMessageType? = null

    @Column(name = "tags")
    var tags: String? = null

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    lateinit var status: Status
}

