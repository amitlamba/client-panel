package com.und.model.jpa

import com.und.model.Status
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import kotlin.collections.HashMap

@Entity
@Table(name = "service_provider_credentials")
class ServiceProviderCredentials {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "service_provider_credentials_id_seq")
    @SequenceGenerator(name = "service_provider_credentials_id_seq", sequenceName = "service_provider_credentials_id_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "appuser_id")
    @NotNull
    var appuserID: Long? = null

    @Column(name = "service_provider_type")
    @NotNull
    lateinit var serviceProviderType: String

    @Column(name = "service_provider")
    @NotNull
    lateinit var serviceProvider: String

    @Column(name = "url")
    lateinit var url: String

    @Column(name = "port")
    var port: Int? = null

    @Column(name = "username")
    lateinit var username: String

    @Column(name = "password")
    lateinit var password: String

    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    lateinit var dateCreated: Date

    @Column(name = "date_modified")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    lateinit var dateModified: Date

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    lateinit var status: Status

    @Column(name = "credentials")
//    @NotNull
    var credentialsMap: HashMap<String, String> = HashMap<String, String>();
}

