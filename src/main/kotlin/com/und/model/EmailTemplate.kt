package com.und.model

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Null

@Entity
@Table(name = "email_template")
class EmailTemplate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "email_template_seq")
    @SequenceGenerator(name = "email_template_seq", sequenceName = "email_template_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "email_template_body")
    @NotNull
    lateinit var emailTemplateBody: String

    @Column(name = "email_template_subject")
    lateinit var emailTemplateSubject: String

    @Column(name = "parent_id")
    @Null
    var parentID: Long? = null

    @Column(name = "from")
    @NotNull
    lateinit var from: String

    //TODO See how to use Enum here
    @Column(name = "message_type") //Promotional or Transactional
    @NotNull
    var messageType: Short? = null

    @Column(name = "tags")
    var tags: String? = null

    fun setMessageType(emailMessageType: EmailMessageType) {
        this.messageType = emailMessageType.value
    }

    fun getMessageType() : EmailMessageType {
        return EmailMessageType.fromValue(this.messageType!!)!!
    }
}

