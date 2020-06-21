package com.example.restareareview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.restareareview.MyDBHelper.Companion.TABLE_NAME
import kotlinx.android.synthetic.main.activity_my_menu.*

class MyMenuActivity : AppCompatActivity() {
    lateinit var myDBHelper: MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_menu)
        init()
        getAllRecord()
    }

    fun init(){
        myDBHelper = MyDBHelper(this)
        insertbtn.setOnClickListener {
            val loadName = loadNameEdit.text.toString()
            val restName = restNameEdit.text.toString()
            val menuName = menuNameEdit.text.toString()
            val mydata = MyData(0, loadName, restName, menuName)
            val result = myDBHelper.insertData(mydata)
            if(result){
                Toast.makeText(this, "DB INSERT SUCCESS", Toast.LENGTH_SHORT).show()
                getAllRecord()
            }else{
                Toast.makeText(this, "DB INSERT FAILED", Toast.LENGTH_SHORT).show()
            }
        }

        deletebtn.setOnClickListener {

        }

        updatebtn.setOnClickListener {

        }

        findbtn.setOnClickListener {

        }

    }
    fun getAllRecord(){
        myDBHelper.getAllRecord()
    }

}