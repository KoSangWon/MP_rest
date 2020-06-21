package com.example.restareareview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
            var result = myDBHelper.deleteProduct(loadIdEdit.text.toString())
            if(result){
                Toast.makeText(this, "DELETE SUCCESS", Toast.LENGTH_SHORT).show()
                getAllRecord()
            }else{
                Toast.makeText(this, "DELETE FAILED", Toast.LENGTH_SHORT).show()
            }
        }

        updatebtn.setOnClickListener {
            val loadId = loadIdEdit.text.toString().toInt()
            val loadName = loadNameEdit.text.toString()
            val restName = restNameEdit.text.toString()
            val menuName = menuNameEdit.text.toString()
            val data = MyData(loadId, loadName, restName, menuName)
            val result = myDBHelper.updateProduct(data)
            if(result){
                Toast.makeText(this, "UPDATE SUCCESS", Toast.LENGTH_SHORT).show()
                getAllRecord()
            }else{
                Toast.makeText(this, "UPDATE FAILED", Toast.LENGTH_SHORT).show()
            }
        }

        findbtn.setOnClickListener {
            val name = restNameEdit.text.toString()
            val result = myDBHelper.findProduct(name)
            if(result){
                Toast.makeText(this, "RECORD FOUND", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "NO MATCH FOUND", Toast.LENGTH_SHORT).show()
            }
        }

        testSql.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val name = s.toString()
                val result = myDBHelper.findProduct2(name)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //TODO("Not yet implemented")
            }
        })

    }

    fun getAllRecord(){
        myDBHelper.getAllRecord()
    }

}