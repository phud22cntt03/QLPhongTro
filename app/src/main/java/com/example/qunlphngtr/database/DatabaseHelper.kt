package com.example.qunlphngtr.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.qunlphngtr.model.Room

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QuanLyPhongTro.db"
        private const val DATABASE_VERSION = 2  // tăng version để update cột mới

        const val TABLE_ROOM = "rooms"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_PRICE = "price"
        const val COLUMN_AREA = "area"
        const val COLUMN_STATUS = "status"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_IMAGE_URI = "imageUri"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createRoomTable = """
            CREATE TABLE $TABLE_ROOM (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_PRICE REAL,
                $COLUMN_AREA REAL,
                $COLUMN_STATUS TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_IMAGE_URI TEXT
            )
        """.trimIndent()
        db.execSQL(createRoomTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ROOM")
        onCreate(db)
    }

    // Insert Room (có thêm ảnh)
    fun insertRoom(
        name: String,
        price: Double,
        area: Double,
        status: String,
        description: String,
        imageUri: String? = null
    ): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_PRICE, price)
            put(COLUMN_AREA, area)
            put(COLUMN_STATUS, status)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_IMAGE_URI, imageUri)
        }
        val result = db.insert(TABLE_ROOM, null, values)
        db.close()
        return result
    }

    // Lấy tất cả phòng
    fun getAllRooms(): List<Room> {
        val roomList = mutableListOf<Room>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_ROOM", null)

        if (cursor.moveToFirst()) {
            do {
                val room = Room(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)),
                    area = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AREA)),
                    status = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STATUS)),
                    description = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)),
                    imageUri = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_URI))
                )
                roomList.add(room)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return roomList
    }
}
