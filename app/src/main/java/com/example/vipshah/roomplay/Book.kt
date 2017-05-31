package com.example.vipshah.roomplay

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Book(@ColumnInfo(name = COLUMN_TITLE) var title: String? = null
                , @ColumnInfo(name = COLUMN_EDITION) var edition: Int = 0
                , @ColumnInfo(name = COLUMN_AUTHOR) var author: String? = null) {

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    companion object {
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_EDITION = "edition"
        private const val COLUMN_AUTHOR = "author"
    }
}
