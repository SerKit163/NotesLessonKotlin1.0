package ru.netology

import ru.netology.notes.Notes
import ru.netology.notes.Service
import ru.netology.notes.comments.Comment

fun main() {

    val service = Service()

    val note1 = Notes(title = "Заголовок #1", text = "Текст #1")
    val note2 = Notes(title = "Заголовок #2", text = "Текст #2")
    val note3 = Notes(title = "Заголовок #3", text = "Текст #3")
    val note4 = Notes(title = "Заголовок #4", text = "Текст #4")

    val note2Edit = Notes(id = 2, title = "Заголовок #22", text = "Текст #22")
//    val note3Edit = Notes(id = 3, title = "Заголовок #33", text = "Текст #33")

    service.add(note1)
    service.add(note2)
    service.add(note3)
    service.add(note4)

    service.edit(note2Edit)

//    service.delete(1)
    service.delete(3)

//    service.edit(note3Edit)


    val comment1 = Comment(noteId = 2, message = "Комментарий #1")
    val comment2 = Comment(noteId = 4, message = "Комментарий #2")
    val comment4 = Comment(noteId = 1, message = "Комментарий #4")

    val comment3 = Comment(id = 2, message = "Комментарий #3")

    service.createComment(comment1)
    service.createComment(comment2)
    service.createComment(comment4)

    service.editComment(comment3)

    service.deleteComment(1)
    service.deleteComment(2)
//    service.editComment(comment3)

    service.recoverComment(1)


    println("---- Заметки ----")
    println("-----------------")
    println(service.get())
    println("-- Комментарий --")
    println("----------------")
    println(service.getComments())

}