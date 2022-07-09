package com.flexath.notesmyself

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val mainActivity: MainActivity,private val noteList: List<Note>)
                    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note = noteList[position]
        holder.itemTitle.text = note.title
        holder.itemDescription.text = note.description
        when {
            note.idea -> holder.itemStatus.text = mainActivity.resources.getString(R.string.new_note_idea)
            note.important -> holder.itemStatus.text = mainActivity.resources.getString(R.string.new_note_important)
            note.todo -> holder.itemStatus.text = mainActivity.resources.getString(R.string.new_note_todo)
            else -> holder.itemStatus.text = mainActivity.resources.getString(R.string.blank_text)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view),View.OnClickListener{
        val itemStatus:TextView
        val itemTitle:TextView
        val itemDescription:TextView

        init {
            itemStatus = view.findViewById(R.id.textViewStatus)
            itemTitle = view.findViewById(R.id.textViewTitle)
            itemDescription = view.findViewById(R.id.textViewDescription)
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mainActivity.showNote(adapterPosition)
        }
    }

}