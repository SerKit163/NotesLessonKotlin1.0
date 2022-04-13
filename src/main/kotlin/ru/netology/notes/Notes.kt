package ru.netology.notes

import ru.netology.notes.comments.Comment

data class Notes(
    val id: Int = 0,
    val title: String? = null,
    val text: String? = null,
    val removeNotes: Boolean = false
)