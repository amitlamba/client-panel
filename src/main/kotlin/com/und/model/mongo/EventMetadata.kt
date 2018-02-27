package com.und.model.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "#{tenantProvider.getTenant()}_eventmetadata")
class EventMetadata(
        @field: Id var id: String? = "",
        val name: String,
        val properties: MutableList<Property>) {
}

@Document(collection = "#{tenantProvider.getTenant()}_userproperties")
class CommonMetadata(
        @field: Id var id: String? = "",
        val name: String,
        val properties: MutableList<Property>) {
}


class Property(
        val name: String,
        val options: MutableList<Any>
)