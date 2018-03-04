package com.und.model.jpa

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

    @Column(name = "name")
    @NotNull
    var name: String = ""

    @Column(name = "type")
    @NotNull
    var type: String = ""

    @Column(name = "data")
    @NotNull
    var data: String = "{}"


}