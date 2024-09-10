package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MySQliteHelper(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ROLLNUMEBR_COL + " TEXT PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " INTEGER," +
                CGPA_COL + " REAL" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun getAllStudents(): List<Student> {
        val db: SQLiteDatabase = this.readableDatabase
        val projection = arrayOf(
            Companion.ROLLNUMEBR_COL,
            Companion.NAME_COl,
            Companion.AGE_COL,
            Companion.CGPA_COL
        )
        val cursor: Cursor = db.query(
            Companion.TABLE_NAME,  // The table to query
            projection,  // The columns to return
            null,  // The columns for the WHERE clause (null = all rows)
            null,  // The values for the WHERE clause (null = all rows)
            null,  // don't group the rows
            null,  // don't filter by row groups
            null   // The sort order
        )
        val studentList = mutableListOf<Student>()
        while (cursor.moveToNext()) {
            val rollnumber = cursor.getString(cursor.getColumnIndexOrThrow(Companion.ROLLNUMEBR_COL))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Companion.NAME_COl))
            val age = cursor.getInt(cursor.getColumnIndexOrThrow(Companion.AGE_COL))
            val cgpa = cursor.getDouble(cursor.getColumnIndexOrThrow(Companion.CGPA_COL))
            val student = Student(rollnumber, name, age, cgpa)
            studentList.add(student)
        }
        cursor.close()
        db.close()
        return studentList
    }

    fun insertStudent(student: Student): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(ROLLNUMEBR_COL, student.rollNumber)
            put(NAME_COl, student.name)
            put(AGE_COL, student.age)
            put(CGPA_COL, student.cgpa)
        }
        val id = db.insert(Companion.TABLE_NAME, null, values)
        db.close()
        return id
    }

    companion object {
        private const val DATABASE_NAME = " STUDENT_DATABASE"
        private const val DATABASE_VERSION = 1
        val TABLE_NAME = "gfg_table"
        val ROLLNUMEBR_COL = "rollnumber"
        val NAME_COl = "name"
        val AGE_COL = "age"
        val CGPA_COL = "cgpa"


        fun getDatabaseName(): String {
            return DATABASE_NAME
        }

        fun getDatabaseVersion(): Int {
            return DATABASE_VERSION
        }
    }
}