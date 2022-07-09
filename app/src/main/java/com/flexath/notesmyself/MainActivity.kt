package com.flexath.notesmyself

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //private var tempNote = Note()

    private var noteList = ArrayList<Note>()
    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null
    private var showDividers:Boolean = false

    private var mSerializer: JSONSerializer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            val newDialog = NewNoteDialog()
            newDialog.show(supportFragmentManager,"123")

            mSerializer = JSONSerializer("NoteToSelf.json", applicationContext)
            try {
                noteList = mSerializer!!.load()
            } catch (e: Exception) {
                noteList = ArrayList()
                Log.e("Error loading notes: ", "", e)
            }

            recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

            val layoutManager = LinearLayoutManager(applicationContext)
            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.itemAnimator = DefaultItemAnimator()
            //recyclerView!!.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))

            adapter = NoteAdapter(this,noteList)
            recyclerView!!.adapter = adapter
        }
    }

    private fun saveNotes() {
        try {
            mSerializer!!.save(this.noteList)
        } catch (e: Exception) {
            Log.e("Error Saving Notes", "", e)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.icSetting -> {
                Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,SettingsActivity::class.java)
                startActivity(myIntent)
            }
            R.id.icFavourite -> Toast.makeText(this,"Favourite",Toast.LENGTH_SHORT).show()
            R.id.icFeedback -> Toast.makeText(this,"Feedback",Toast.LENGTH_SHORT).show()
            R.id.icExit -> finish()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        saveNotes()
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("Notes Myself", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers", true)

        if (showDividers)
            recyclerView?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        else {
                recyclerView?.removeItemDecorationAt(0)
        }
    }
}