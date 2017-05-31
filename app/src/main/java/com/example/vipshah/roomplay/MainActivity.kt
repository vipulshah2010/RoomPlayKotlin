package com.example.vipshah.roomplay

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    private var bookDatabase: BookDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ButterKnife.bind(this)

        bookDatabase = Room.databaseBuilder(applicationContext, BookDatabase::class.java, "database-name").build()
    }

    @OnClick(R.id.insertButton)
    internal fun insert() {
        object : Thread() {
            override fun run() {

                val book1 = Book(author = "James Smith", edition = 1, title = "Advance Android")
                val book2 = Book(author = "John Johnson", edition = 2, title = "Advance Kotlin")
                val book3 = Book(author = "Robert Williams", edition = 3, title = "Advance RxJava")

                val keys = bookDatabase!!.bookDao().insert(book1, book2, book3)

                runOnUiThread {
                    for (key in keys) {
                        Toast.makeText(this@MainActivity, "Inserted with id:" + key, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }.start()
    }

    @OnClick(R.id.readButton)
    internal fun read() {
        object : Thread() {
            override fun run() {
                val books = bookDatabase!!.bookDao().withSpecificEdition(edition = 1)
                // val books = bookDatabase!!.bookDao().loadAllByAuthorNames(authors = arrayOf("Robert Williams"))
                // val books = bookDatabase!!.bookDao().allBooks

                if (books.isNotEmpty()) {
                    for (book in books) {
                        runOnUiThread { Toast.makeText(this@MainActivity, book.toString(), Toast.LENGTH_SHORT).show() }
                    }
                }
            }
        }.start()
    }

    @OnClick(R.id.updateButton)
    internal fun update() {

    }

    @OnClick(R.id.deleteButton)
    internal fun delete() {
        object : Thread() {
            override fun run() {
                val books = bookDatabase!!.bookDao().withSpecificEdition(edition = 1)

                if (books.isNotEmpty()) {
                    val book = books.get(0)
                    val deletedBookId = bookDatabase!!.bookDao().delete(book)

                    runOnUiThread {
                        if (deletedBookId != -1) {
                            Toast.makeText(this@MainActivity, "Book with Id $deletedBookId deleted successfully!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "Deletion Failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }.start()
    }
}
