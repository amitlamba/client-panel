package com.und.model

import javax.persistence.*

@Entity
@Table(name = "CLIENT_VERIFICATION")
class ClientVerification {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "client_verification_id_seq")
    @SequenceGenerator(name = "client_verification_id_seq", sequenceName = "client_verification_id_seq", allocationSize = 1)
    var id: Long? = null
}