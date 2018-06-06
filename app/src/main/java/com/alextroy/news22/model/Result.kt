package com.alextroy.news22.model


data class Result(
        val id: String,
        val type: String,
        val sectionId: String,
        val sectionName: String,
        val webPublicationDate: String,
        val webTitle: String,
        val webUrl: String,
        val apiUrl: String,
        val fields: Fields,
        val isHosted: Boolean,
        val pillarId: String,
        val pillarName: String
)