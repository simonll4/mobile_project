package com.iua.app.domain.model

import com.iua.app.data.local.entity.EventEntity
import com.iua.app.data.remote.dto.EventDTO
import java.util.Date

data class EventModel(
    var id: Long,
    var title: String,
    var description: String,
    var image: String = "",
    var subtitle: String = "",
    var isFavorite: Boolean = false,
    var date: Date
)

// API (DTO) -> Modelo de Dominio
fun EventDTO.toEventsModel(): EventModel {
    return EventModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle,
        date = this.getDateAsDate()
    )
}

// Modelo de Dominio -> Entity (Room)
fun EventModel.toEventsEntity(): EventEntity {
    return EventEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle,
        isFavorite = this.isFavorite,
        date = this.date
    )
}

//// Entity (Room) -> Modelo de Dominio
fun EventEntity.toEventsModel(): EventModel {
    return EventModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle,
        isFavorite = this.isFavorite,
        date = this.date
    )
}

//
////// Modelo de Dominio -> API (DTO)
////fun EventsModel.toEventDTO(): EventDTO {
////    return EventDTO(id = 0, title = this.title, description = this.description)
////}
