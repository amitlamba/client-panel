package com.und.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "segment")
class Segment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "segment_id_seq")
    @SequenceGenerator(name = "segment_id_seq", sequenceName = "segment_id_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    var name: String = ""
    var type: String = ""
}