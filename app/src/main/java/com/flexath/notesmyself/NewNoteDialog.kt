package com.flexath.notesmyself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class NewNoteDialog:DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.new_note_dialog,null)

        val editTitle = dialogView.findViewById<EditText>(R.id.edTitle)
        val editDescription = dialogView.findViewById<EditText>(R.id.edDescription)
        val checkIdea = dialogView.findViewById<CheckBox>(R.id.chIdea)
        val checkTodo = dialogView.findViewById<CheckBox>(R.id.chTodo)
        val checkImportant = dialogView.findViewById<CheckBox>(R.id.chImportant)
        val buttonOk = dialogView.findViewById<Button>(R.id.btnOk)
        val buttonCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        builder.setView(dialogView).setMessage("")

        buttonOk.setOnClickListener {

            val notes = Note()

            notes.title = editTitle.text.toString()
            notes.description = editDescription.text.toString()
            notes.idea = checkIdea.isChecked
            notes.todo = checkTodo.isChecked
            notes.important = checkImportant.isChecked

            val callingActivity = activity as MainActivity?
            callingActivity!!.createNewNote(notes)

            dismiss()
        }

        buttonCancel.setOnClickListener {
            dismiss()
        }

        return builder.create()
    }
}