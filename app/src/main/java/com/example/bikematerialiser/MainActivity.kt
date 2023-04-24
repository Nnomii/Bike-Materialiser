package com.example.bikematerialiser

import android.content.ContentValues
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    /**
     * Called once, once the app is launched.
     * @param savedInstanceState Saved instance state for the application.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Rotates the title text in 3d space so that it aligns with the background wall
        // texts[0] is the title text and texts[1] is the location message text
        val texts = arrayOf(findViewById<TextView>(R.id.textTitle),
            findViewById<TextView>(R.id.textMessage))
        texts.forEach {
            text -> text.animate().apply {
                duration = 0
                rotationYBy(9f)
            }.start()
        }

        // This database stores the bike location data
        val database = LocationDatabase(applicationContext).readableDatabase
//        val cv = ContentValues()
//        cv.put("location", "Stieltjesweg")
//        cv.put("datetime", "19:19 19/05/2000")
//        database.insert("locations", null, cv)

        // Get the last location from the database and show it in the location message text
        val cursor = database.rawQuery("SELECT * FROM locations ORDER BY id DESC LIMIT 1", null)
        if (cursor.moveToNext()) {
            val lastLocation = SpannableString("The last location of your bike is \n" +
                    cursor.getString(1) +
                    "\nat " +
                    cursor.getString(2))
            lastLocation.setSpan(StyleSpan(Typeface.ITALIC), 0, lastLocation.length, 0)
            texts[1].text = lastLocation
        }
        cursor.close()
    }
}