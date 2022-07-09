package com.flexath.notesmyself

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*
import java.util.ArrayList

class JSONSerializer (
    private var filename: String,
    private var context: Context
        ) {

    @Throws(IOException::class, JSONException::class)
    fun save(notes:List<Note>) {
        val jsonArray = JSONArray()
        for(n in notes) {
            jsonArray.put(n.convertToJSON())

            var writer: Writer? = null
            try{
                val out = context.openFileOutput(filename,Context.MODE_PRIVATE)
                writer = OutputStreamWriter(out)
                writer.write(jsonArray.toString())
            }finally {
                if(writer != null){
                    writer.close()
                }
            }
        }
    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Note> {
        val noteList = ArrayList<Note>()
        var reader: BufferedReader? = null

        try {

            val `in` = context.openFileInput(filename)
            reader = BufferedReader(InputStreamReader(`in`))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }

            val jArray = JSONTokener(jsonString.toString()).nextValue() as JSONArray

            for (i in 0 until jArray.length()) {
                noteList.add(Note(jArray.getJSONObject(i)))
            }
        } catch (e: FileNotFoundException) {
            // we will ignore this one, since it happens
            // when we start fresh. You could add a log here.
        } finally {// This will always run
            reader!!.close()
        }

        return noteList
    }
}