package com.example.restareareview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.restareareview.MyDBHelper.Companion.TABLE_NAME
import kotlinx.android.synthetic.main.activity_my_menu.*
import kotlinx.android.synthetic.main.custom_action_bar_layout.*

class MyMenuActivity : AppCompatActivity() {
    lateinit var myDBHelper: MyDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_menu)
        setActionBar()
        init()
        getAllRecord()
    }

    private fun setActionBar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.custom_action_bar_layout)

        val view = supportActionBar!!.customView
        val homeImageButton = view.findViewById<ImageButton>(R.id.home)
        homeImageButton.setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK))
        }

        val questionImageButton = view.findViewById<ImageButton>(R.id.mail)
        questionImageButton.setOnClickListener {
            val questionIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jackoss@naver.com", null))
            questionIntent.putExtra(Intent.EXTRA_SUBJECT, "")
            questionIntent.putExtra(Intent.EXTRA_TEXT, "")
            startActivity(Intent.createChooser(questionIntent, ""))
        }

        title_text.text = "즐겨찾기 목록"
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