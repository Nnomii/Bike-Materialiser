package com.example.bikematerialiser

import android.content.ContentValues
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import at.connyduck.sparkbutton.SparkButton
import at.connyduck.sparkbutton.SparkEventListener
import com.google.android.material.navigation.NavigationView

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
            findViewById(R.id.textMessage))
        texts.forEach {
            it.animate().apply {
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

        // Get the last 9 locations from the database
        val cursor = database.rawQuery("SELECT * FROM locations ORDER BY id DESC LIMIT 9", null)
        val history = mutableListOf<Pair<String, String>>()
        var location = 1
        while (cursor.moveToNext()) {
            if (location == 1) {
                val lastLocation = SpannableString("The last location of your bike is \n" +
                        cursor.getString(1) +
                        "\nat " +
                        cursor.getString(2))
                lastLocation.setSpan(StyleSpan(Typeface.ITALIC), 0, lastLocation.length, 0)
                texts[1].text = lastLocation
            }
            history.add(Pair(cursor.getString(1), cursor.getString(2)))
            location += 1
        }
        cursor.close()

        // Add and history drawers and buttons
        val drawerHistory = findViewById<DrawerLayout>(R.id.drawerHistory)
        val drawerAdd = findViewById<DrawerLayout>(R.id.drawerAdd)
        val buttonHistory = findViewById<SparkButton>(R.id.buttonHistory)
        val buttonAdd = findViewById<SparkButton>(R.id.buttonAdd)

        // Prevent swiping for drawer opening, we have buttons for that!
        drawerHistory.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        drawerAdd.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        // Make the buttons open the drawers
        buttonHistory.setEventListener { _, _ ->
            drawerHistory.openDrawer(GravityCompat.END)
            true
        }
        buttonAdd.setEventListener { _, _ ->
            drawerAdd.openDrawer(GravityCompat.START)
            true
        }

        // Populate the add location drawer
        populateAddDrawer(history)
    }

    /**
     * Updates the locations in the add location drawer.
     * It stores the last 9 locations used for easy access.
     */
    private fun populateAddDrawer(history: MutableList<Pair<String, String>>) {
        var viewNumber = 1
        history.forEach {
            // TODO: Find view
            viewNumber += 1
        }
    }
}
