package com.flexath.notesmyself

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //private var tempNote = Note()

    private val noteList = ArrayList<Note>()
    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val newDialog = NewNoteDialog()
            newDialog.show(supportFragmentManager,"123")

            recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

            val layout_manager = LinearLayoutManager(applicationContext)
            recyclerView!!.layoutManager = layout_manager
            recyclerView!!.itemAnimator = DefaultItemAnimator()
            recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

            adapter = NoteAdapter(this,noteList)
            recyclerView!!.adapter = adapter
        }
    }

    fun createNewNote(n : Note) {
        //tempNote = n
        noteList.add(n)

        // lets our adapter know that a new note has been added.
        adapter!!.notifyDataSetChanged()
    }

    fun showNote(adapterPosition: Int) {
        val dialog = ShowNoteDialog()
        dialog.showSelectedNote(noteList[adapterPosition])
        dialog.show(supportFragmentManager,"")
    }
}