package com.example.studentsapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Lesson 5
        // TODO: Step 1 - Setup project for nav graphs ✅
        // TODO: Step 2 - Setup nav_graph.xml and add Destinations ✅
        // TODO: Step 3 - Setup MainActivity with nav host and nav controller ✅
        // TODO: Step 4 - Setup toolbar ✅
        // TODO: Step 5 - Add Bottom nav bar ✅

        val toolBar: Toolbar = findViewById(R.id.toolbar)
//        toolBar.setBackgroundColor(Color.parseColor("#333333"))
        setSupportActionBar(toolBar)

        val navHostController =
            supportFragmentManager.findFragmentById((R.id.main_nav_fragment)) as? NavHostFragment
        navController = navHostController?.navController
        navController?.let {
            NavigationUI.setupActionBarWithNavController(
                activity = this,
                navController = it,
            )
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_bar)
        navController?.let { NavigationUI.setupWithNavController(bottomNavigationView, it) }

//        val db = Firebase.firestore
//
//        // Create a new user with a first and last name
//        val user = hashMapOf(
//            "first" to "Ada",
//            "last" to "Lovelace",
//            "born" to 1815,
//        )
//
//        // Add a new document with a generated ID
//        db.collection("users").add(user).addOnSuccessListener { documentReference ->
//                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }.addOnFailureListener { e ->
//                Log.w("TAG", "Error adding document", e)
//            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController?.popBackStack()
                true
            }

            else -> {
                navController?.let { NavigationUI.onNavDestinationSelected(item, it) }
                true
            }
        }
    }

}