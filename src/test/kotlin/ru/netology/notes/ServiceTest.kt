package ru.netology.notes

import org.junit.Test

import org.junit.Assert.*
import ru.netology.exception.CommentFoundException
import ru.netology.exception.CommentNotFoundException
import ru.netology.exception.NoteNotFoundException
import ru.netology.notes.comments.Comment

class ServiceTest {
    private val service = Service()

    @Test
    fun add() {
        val note = service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        assertEquals(note.id, 1)
    }

    @Test
    fun edit() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val edit = service.edit(Notes(id = 1, title = "Edit Заметка #1", text = "Edit Текст #1"))
        assertTrue(edit)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val edit = service.edit(Notes(id = 11, title = "Edit Заметка #1", text = "Edit Текст #1"))
        assertEquals(edit, Notes(id = 11, title = "Edit Заметка #1", text = "Edit Текст #1"))
    }

    @Test
    fun delete() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val delete = service.delete(1)
        assertTrue(delete)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val delete = service.delete(11)
        assertEquals(delete, null)
    }

    @Test
    fun get() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val get = service.get()
        assertEquals(get, "Notes(id=1, title=Заметка #1, text=Текст #1, removeNotes=false)\n")
    }

    @Test
    fun createComment() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val comment = service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        assertTrue(comment)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        val comment = service.createComment(Comment(noteId = 11, message = "Комментарий #1"))
        assertEquals(comment, Comment(noteId = 11, message = "Комментарий #1"))
    }

    @Test
    fun editComment() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        val commentEdit = service.editComment(Comment(id = 1, noteId = 1, message = "Edit Комментарий #1"))
        assertTrue(commentEdit)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        val commentEdit = service.editComment(Comment(id = 11, noteId = 1, message = "Edit Комментарий #1"))
        assertEquals(commentEdit, Comment(id = 11, noteId = 1, message = "Edit Комментарий #1"))
    }

    @Test
    fun deleteComment() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        val deleteComment = service.deleteComment(1)
        assertTrue(deleteComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        val deleteComment = service.deleteComment(11)
        assertEquals(deleteComment, Comment(noteId = 1, message = "Комментарий #1"))
    }

    @Test
    fun recoverComment() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        service.deleteComment(1)
        val recoverComment = service.recoverComment(1)
        assertTrue(recoverComment)
    }

    @Test(expected = CommentNotFoundException::class)
    fun recoverCommentDeleteThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
//        service.deleteComment(11)
        val recoverComment = service.recoverComment(11)
        assertEquals(recoverComment, Comment(noteId = 1, message = "Комментарий #1"))
    }

    @Test(expected = CommentNotFoundException::class)
    fun recoverCommentThrow() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        service.deleteComment(11)
        val recoverComment = service.recoverComment(1)
        assertEquals(recoverComment, Comment(noteId = 1, message = "Комментарий #1"))
    }

    @Test(expected = CommentFoundException::class)
    fun recoverCommentThrowComment() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
//        service.deleteComment(1)
        val recoverComment = service.recoverComment(1)
        assertEquals(recoverComment, Comment(id=1, noteId = 1, message = "Комментарий #1"))
    }

    @Test
    fun getComments() {
        service.add(Notes(title = "Заметка #1", text = "Текст #1"))
        service.createComment(Comment(noteId = 1, message = "Комментарий #1"))
        val getComment = service.getComments()
        assertEquals(getComment, "Comment(id=1, noteId=1, replyTo=0, message=Комментарий #1, removeComment=false)\n")
    }


}