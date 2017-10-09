package com.und.security.model

import org.hibernate.annotations.ColumnDefault

import java.util.Date
import javax.persistence.*

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "APPUSER")
class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    var id: Long? = null

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CLIENT_ID")
    var client: Client? = null

    @Column(name = "user_type")
    @NotNull
    var userType :Int = 1

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    lateinit var username: String

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    lateinit var password: String

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    lateinit var firstname: String

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    lateinit var lastname: String

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    lateinit var email: String

    @Column(name = "ENABLED")
    @NotNull
    var enabled: Boolean = false

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    lateinit var lastPasswordResetDate: Date

    @Column(name = "SECRET", length = 50)
    @NotNull
    @ColumnDefault("'mySecret'")
    @Size(min = 4, max = 50)
    lateinit var clientSecret: String

    @Column(name = "KEY", length = 50)
    @Size(min = 4, max = 50)
    var key: String? = null

    @Column(name = "PHONE", length = 15)
    @Size(min = 10, max = 15)
    var mobile: String? = null

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITY", joinColumns = arrayOf(JoinColumn(name = "USER_ID", referencedColumnName = "ID")), inverseJoinColumns = arrayOf(JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")))
    var authorities: List<Authority> = arrayListOf()
}