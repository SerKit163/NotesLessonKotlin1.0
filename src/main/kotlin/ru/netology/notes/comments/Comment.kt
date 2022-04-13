package ru.netology.notes.comments

data class Comment(
    val id: Int = 0,
    val noteId: Int = 0,
    val replyTo: Int = 0,
    val message: String? = null,
    val removeComment: Boolean = false
)