package com.flexath.notesmyself

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tempNote = Note()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonShowNote = findViewById<Button>(R.id.btnShowNote)

        buttonShowNote.setOnClickListener {
            val showDialog = ShowNoteDialog()
            showDialog.showSelectedNote(tempNote)
            showDialog.show(supportFragmentManager,"")
        }

        fab.setOnClickListener {
            val newDialog = NewNoteDialog()
            newDialog.show(supportFragmentManager,"123")
        }
    }

    fun createNewNote(n : Note) {
        tempNote = n
    }
}