package com.flexath.notesmyself

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var showDividers:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("Notes Myself", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers",true)
        switchOne.isChecked = showDividers

        switchOne.setOnCheckedChangeListener { _, isChecked ->
            showDividers = isChecked
        }
    }

    override fun onPause() {
        super.onPause()

        val prefs = getSharedPreferences("Notes Myself",Context.MODE_PRIVATE)
        val editor = prefs.edit()

        editor.putBoolean("dividers",showDividers)
        editor.apply()
    }
}