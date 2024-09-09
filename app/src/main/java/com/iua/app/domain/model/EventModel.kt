package com.iua.app.domain.model

import com.iua.app.data.local.entity.EventsEntity


data class EventsModel(
    var name: String,
    var description: String,
)

fun EventsModel.toEventsEntity(): EventsEntity{
    return EventsEntity(
        name = this.name,
        description = this.description
    )
}