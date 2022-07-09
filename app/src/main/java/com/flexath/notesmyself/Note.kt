package com.flexath.notesmyself

import org.json.JSONException
import org.json.JSONObject

class Note(
    var title:String? = null,
    var description:String? = null,
    var idea:Boolean = false,
    var todo:Boolean = false,
    var important:Boolean = false ){

    private val JSON_TITLE = "title"
    private val JSON_DESCRIPTION = "description"
    private val JSON_IDEA = "idea"
    private val JSON_TODO = "todo"
    private val JSON_IMPORTANT = "important"

    @Throws(JSONException::class)
    constructor(jsonObj : JSONObject) : this() {
        title = jsonObj.getString(JSON_TITLE)
        description = jsonObj.getString(JSON_DESCRIPTION)
        idea = jsonObj.getBoolean(JSON_IDEA)
        todo = jsonObj.getBoolean(JSON_TODO)
        important = jsonObj.getBoolean(JSON_IMPORTANT)
    }

    @Throws(JSONException::class)
    fun convertToJSON() : JSONObject {
        val obj = JSONObject()
        obj.put(JSON_TITLE,title)
        obj.put(JSON_DESCRIPTION,description)
        obj.put(JSON_IDEA,idea)
        obj.put(JSON_TODO,todo)
        obj.put(JSON_IMPORTANT,important)
        return obj
    }


}
