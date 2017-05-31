package com.example.vipshah.roomplay

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface BookDao {

    @get:Query("SELECT * FROM book")
    val allBooks: List<Book>

    @Query("SELECT * FROM BOOK WHERE edition == :arg0")
    fun withSpecificEdition(edition: Int): List<Book>

    @Query("SELECT * FROM BOOK WHERE author IN (:arg0)")
    fun loadAllByAuthorNames(authors: Array<String>): List<Book>

    @Insert
    fun insert(vararg books: Book): LongArray

    @Delete
    fun delete(book: Book): Int
}
