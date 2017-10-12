package com.und.security.model

import com.und.model.ClientVerification
import org.hibernate.annotations.ColumnDefault
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.collections.ArrayList

@Entity
@Table(name = "CLIENT")
class Client {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "client_id_seq")
    @SequenceGenerator(name = "client_id_seq", sequenceName = "client_id_seq", allocationSize = 1)
    var id: Long? = null

    //TODO CREATE STATE COLUMN

    @Column(name = "NAME", length = 255)
    @NotNull
    @Size(min = 4, max = 255)
    lateinit var name: String

    @Column(name = "EMAIL", length = 255, unique = true)
    @NotNull
    @Size(min = 4, max = 255)
    lateinit var email: String

    @Column(name = "PHONE", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    var phone: String? = null

    @Column(name = "email_verified")
    @NotNull
    var emailVerified: Boolean = false

    @Column(name = "phone_verified")
    @NotNull
    var phoneVerified: Boolean = false

    @Column(name = "address")
    var address: String? = null

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    lateinit var dateCreated: Date

    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    lateinit var dateModified: Date

    @OneToOne(mappedBy = "client",
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
     var clientVerification: ClientVerification = ClientVerification()
      set(value)  {
          field = value
          field.client = this
      }

    @OneToMany(mappedBy = "client",
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
    var users = mutableListOf<User>()

    fun addUser(user: User) {
        users.add(user)
        user.client = this
    }

    fun removeUser(user: User) {
        users.remove(user)
        user.client = null

    }

}