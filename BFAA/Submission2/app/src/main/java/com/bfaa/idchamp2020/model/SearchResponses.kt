package com.bfaa.idchamp2020.model

data class SearchResponses(
    val incomplete_results: Boolean,
    val items: List<UserGithub>,
    val total_count: Int
)