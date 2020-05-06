package com.github.amin.drawer.animation

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setNavigationOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }

        navController = Navigation.findNavController(this, R.id.host_fragment)
        navigation_view.setupWithNavController(navController)
        navigation_view.isSelected

        setupDrawerAnimation()
    }

    private fun setupDrawerAnimation() {
        val scaleControl = 6f

        drawer_layout.setScrimColor(Color.TRANSPARENT)
        drawer_layout.elevation = 0f
        drawer_layout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val slideX = drawerView.width * slideOffset
                container.translationX = slideX
                container.scaleY = 1 - (slideOffset/scaleControl)
            }

            override fun onDrawerStateChanged(newState: Int) {}
            override fun onDrawerClosed(drawerView: View) {}
            override fun onDrawerOpened(drawerView: View) {}
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        drawer_layout.closeDrawer(GravityCompat.START)
    }
}