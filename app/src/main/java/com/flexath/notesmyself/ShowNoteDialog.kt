package com.flexath.notesmyself

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment


class ShowNoteDialog : DialogFragment() {

    private var showNote:Note? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(this.requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.show_note_dialog,null)

        val title = dialogView.findViewById<TextView>(R.id.title)
        val description = dialogView.findViewById<TextView>(R.id.description)
        val button = dialogView.findViewById<Button>(R.id.button)

        title.text = showNote!!.title
        description.text = showNote!!.description

        builder.setView(dialogView).setMessage("Your Note")

        button.setOnClickListener {
            dismiss()
        }
        return builder.create()
    }

    fun showSelectedNote(n : Note) {
        showNote = n
    }
}