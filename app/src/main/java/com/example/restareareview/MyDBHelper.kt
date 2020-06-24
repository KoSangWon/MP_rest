package com.example.restareareview

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.view.Gravity
import android.widget.TableRow
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_my_menu.*

class MyDBHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object{
        val DB_VERSION = 1
        val DB_NAME = "mydb.db"
        val TABLE_NAME = "restareas"
        val LOADID = "loadid"
        val LOADNAME = "loadname"
        val RESTNAME = "restname"
        val MENUNAME = "menuname"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val create_table = "create table if not exists " + TABLE_NAME + "(" +
                LOADID + " integer primary key autoincrement, " +
                LOADNAME + " text, " +
                RESTNAME + " text, " +
                MENUNAME + " text)"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop_table = "drop table if exists " + TABLE_NAME
        db?.execSQL(drop_table)
        onCreate(db)
    }

    fun insertData(data: MyData):Boolean{
        val values = ContentValues()
        values.put(LOADNAME, data.loadName)
        values.put(RESTNAME, data.restName)
        values.put(MENUNAME, data.menuName)
        val db = this.writableDatabase
        if(db.insert(TABLE_NAME, null, values) > 0){
            val activity = context as MyMenuActivity
            activity.loadIdEdit.setText("")
            activity.loadNameEdit.setText("")
            activity.restNameEdit.setText("")
            activity.menuNameEdit.setText("")
            db.close()
            return true
        }else{
            db.close()
            return false
        }
    }

    fun updateProduct(data: MyData):Boolean{
        val strsql = "select * from " + TABLE_NAME + " where " + LOADID + " = \'" + data.loadId + "\'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.moveToFirst()){
            val values = ContentValues()
            values.put(LOADNAME, data.loadName)
            values.put(RESTNAME, data.restName)
            values.put(MENUNAME, data.menuName)
            db.update(TABLE_NAME, values, LOADID+"=?", arrayOf(data.loadId.toString()))
            val activity = context as MyMenuActivity
            activity.loadIdEdit.setText("")
            activity.loadNameEdit.setText("")
            activity.restNameEdit.setText("")
            activity.menuNameEdit.setText("")
            cursor.close()
            db.close()
            return true
        }
        cursor.close()
        db.close()
        return false
    }

    fun deleteProduct(loadid: String):Boolean{
        val strsql = "select * from " + TABLE_NAME + " where " + LOADID + " = \'" + loadid + "\'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.moveToFirst()){
            db.delete(TABLE_NAME, LOADID+"=?", arrayOf(loadid))
            val activity = context as MyMenuActivity
            activity.loadIdEdit.setText("")
            activity.loadNameEdit.setText("")
            activity.restNameEdit.setText("")
            activity.menuNameEdit.setText("")
            cursor.close()
            db.close()
            return true
        }
        cursor.close()
        db.close()
        return false
    }

    fun findProduct(restName: String):Boolean{
        val strsql = "select * from " + TABLE_NAME + " where " + RESTNAME + " = \'" + restName + "\'"
        val db = this.readableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.count != 0){
            showRecord(cursor)
            cursor.close()
            db.close()
            return true;
        }
        cursor.close()
        db.close()
        return false;
    }

    //select * from product where pname like '김%'
    fun findProduct2(restName: String):Boolean{
        val strsql = "select * from " + TABLE_NAME + " where " + RESTNAME + " LIKE \'" + restName + "%\'"
        val db = this.readableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.count != 0){
            showRecord(cursor)
            cursor.close()
            db.close()
            return true;
        }
        cursor.close()
        db.close()
        return false;
    }


    fun getAllRecord(){
        val strsql = "select * from " + TABLE_NAME
        val db = this.readableDatabase
        val cursor = db.rawQuery(strsql, null)
        if(cursor.count != 0){
            showRecord(cursor)
        }
        cursor.close()
        db.close()
    }

    fun showRecord(cursor: Cursor){
        cursor.moveToFirst()
        val count = cursor.columnCount
        val recordcount = cursor.count
        val activity = context as MyMenuActivity
        activity.tableLayout.removeAllViewsInLayout()
        // 컬럼 타이틀 만들기
        val tablerow = TableRow(activity)
        val rowParam = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, count.toFloat())
        tablerow.layoutParams = rowParam
        val viewParam = TableRow.LayoutParams(0, 100, 1f)
        for(i in 0 until count){
            val textView = TextView(activity)
            textView.layoutParams = viewParam
            textView.text = cursor.getColumnName(i)
            textView.setBackgroundColor(Color.BLACK)
            textView.textSize = 18.0f
            textView.setTextColor(Color.BLACK)
            textView.fontFeatureSettings = "@drawable/lottedream"
            textView.gravity = Gravity.CENTER
        }
        activity.tableLayout.addView(tablerow)
        // 실제 레코드 읽어오기
        do{
            val row = TableRow(activity)
            row.layoutParams = rowParam

            row.setOnClickListener {
                for(i in 0 until count){
                    val txtView = row.getChildAt(i) as TextView
                    when(txtView.tag){
                        0 -> activity.loadIdEdit.setText(txtView.text)
                        1 -> activity.loadNameEdit.setText(txtView.text)
                        2 -> activity.restNameEdit.setText(txtView.text)
                        3 -> activity.menuNameEdit.setText(txtView.text)
                    }
                }
            }


            for(i in 0 until count){
                val textView = TextView(activity)
                textView.layoutParams = viewParam
                textView.text = cursor.getString(i)
                textView.textSize = 13.0f
                textView.setTag(i)
                row.addView(textView)
            }
            activity.tableLayout.addView(row)
        }while (cursor.moveToNext())
    }

}