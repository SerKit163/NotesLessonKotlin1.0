package ru.netology.notes

import ru.netology.exception.CommentFoundException
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.NoteNotFoundException
import ru.netology.notes.comments.Comment

class Service {

    private val notes = mutableListOf<Notes>()
    private val comments = mutableListOf<Comment>()

    private var idPost: Int = 0
    private var idComment: Int = 0


    fun add(note: Notes): Notes {
        idPost++
        notes.add(note.copy(id = idPost))

        return notes.last()
    }

    fun edit(note: Notes): Boolean {

        notes.forEach { i ->
            if (note.id == i.id && !i.removeNotes) {
                notes[i.id - 1] = note.copy(title = note.title, text = note.text)
                return true
            }
        }
        throw NoteNotFoundException("(edit) No such id: ${note.id}")
    }

    fun delete(id: Int): Boolean {
        notes.forEach { i ->
            if (id == i.id && !i.removeNotes) {
                notes[i.id - 1] = notes[i.id - 1].copy(removeNotes = true)
                return true
            }
        }
        throw NoteNotFoundException("(delete) No such id: $id")
    }

    fun createComment(comment: Comment): Boolean {
        idComment++

        notes.forEach { i ->
            if (comment.noteId == i.id && !i.removeNotes) {
                comments.add(comment.copy(id = idComment))
                return true
            }
        }
        throw NoteNotFoundException("(createComment) No such id: ${comment.noteId}")
    }

    fun editComment(comment: Comment): Boolean {
        comments.forEach { i ->
            if (comment.id == i.id && !i.removeComment) {
                comments[i.id - 1] = comment.copy(message = comment.message)
                return true
            }
        }
        throw CommentNotFoundException("(editComment) No such id: ${comment.id}")
    }

    fun deleteComment(id: Int): Boolean {
        comments.forEach { i ->
            if (id == i.id && !i.removeComment) {
                comments[i.id - 1] = comments[i.id - 1].copy(removeComment = true)
                return true
            }
        }
        throw CommentNotFoundException("(deleteComment) No such id: $id")
    }

    fun recoverComment(id: Int): Boolean {
        comments.forEach { i ->
            if (id == i.id && i.removeComment) {
                comments[i.id - 1] = comments[i.id - 1].copy(removeComment = false)
                return true
            }

            if (id == i.id) {
                throw CommentFoundException("(deleteComment) A comment with this id:$id has not been deleted")
            }
        }
        throw CommentNotFoundException("(recoverComment) No such id: $id")
    }

    fun get(): String {
        val sb = StringBuilder()

        for (i in notes) {
            if (!i.removeNotes) {
                sb
                    .append(i)
                    .append("\n")
            }
        }
        return sb.toString()
    }

    fun getComments(): String {
        val sb = StringBuilder()

        for (i in comments) {
            if (!i.removeComment) {
                sb
                    .append(i)
                    .append("\n")
            }
        }
        return sb.toString()
    }

//    fun getCommentsListAll(): List<Comment> {
//        return comments
//    }

//    fun getNotesListAll() : List<Notes> {
//        return notes
//    }

//    private val notesMap = mutableMapOf<Notes, Comment?>()

//    fun createMap() {
//        notes.forEach { n ->
//            comments.forEach { c ->
//                if (n.id == c.noteId) {
//                    notesMap[n] = c
//                }
//            }
//        }
//    }
//
//    fun getNotesMap() {
//        println(notesMap)
//    }

}