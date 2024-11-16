package com.iua.app.domain.model

import com.iua.app.data.local.entity.EventEntity
import com.iua.app.data.remote.dto.EventDTO

data class EventsModel(
    var id: Long ,
    var title: String,
    var description: String,
    var image: String = "",
    var subtitle: String = "",
    var isFavorite: Boolean = false
)

// API (DTO) -> Modelo de Dominio
fun EventDTO.toEventsModel(): EventsModel {
    //return EventsModel(title = this.title, description = this.description)
    return EventsModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle
    )
}

// Entity (Room) -> Modelo de Dominio
fun EventEntity.toEventsModel(): EventsModel {
    return EventsModel(
        id = this.id,
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle,
        isFavorite = this.isFavorite
    )
}

//// Modelo de Dominio -> API (DTO)
//fun EventsModel.toEventDTO(): EventDTO {
//    return EventDTO(id = 0, title = this.title, description = this.description) // Ajusta ID según necesidad
//}


// Modelo de Dominio -> Entity (Room)
fun EventsModel.toEventsEntity(): EventEntity {
    return EventEntity(
        id = this.id.toLong(),
        title = this.title,
        description = this.description,
        image = this.image,
        subtitle = this.subtitle,
        isFavorite = this.isFavorite
    )
}