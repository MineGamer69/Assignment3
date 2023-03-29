package com.example.assignment3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ShareActionProvider
import androidx.core.view.MenuItemCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val menuBar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(menuBar)

        val navhostCtrl = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navhostCtrl.navController

        val build = AppBarConfiguration.Builder(navController.graph)

        val toolbarConfig = build.build()

        menuBar.setupWithNavController(navController, toolbarConfig)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuID = item.itemId
        return when (menuID) {
            R.id.action_settings -> {
                navController.navigate(R.id.settingsFragment)
                true
            }
            R.id.action_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Sharing our movie name here!")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)

                // Get the ShareActionProvider
                val shareActionProvider =
                    MenuItemCompat.getActionProvider(item) as ShareActionProvider?

                // Set the ShareIntent to the ShareActionProvider
                shareActionProvider?.setShareIntent(shareIntent)

                return true
            }
            else -> return NavigationUI.onNavDestinationSelected(item, navController)
        }
    }

}