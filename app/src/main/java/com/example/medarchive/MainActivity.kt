package com.example.medarchive

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.medarchive.swipe.OnSwipeTouchListener
import com.example.medarchive.ui.AuthFragmentDirections
import com.example.medarchive.ui.FavoriteFragmentDirections
import com.example.medarchive.ui.ListOfItemsFragmentDirections
import com.example.medarchive.ui.SubjectsFragmentDirections
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.drawer_header.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var listener: NavController.OnDestinationChangedListener

    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Настройка navigation component
        setSupportActionBar(main_toolbar)

        navController = findNavController(R.id.nav_host_fragment)
        navigationView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.favoriteFragment,
                R.id.listOfItemsFragment,
                R.id.subjectsFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setNavigationItemSelectedListener(this)
        // ------------------ //

        listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            // Скрытие нав-бара и тул-бара в определенных фрагментах
            when (destination.id) {
                R.id.authFragment -> {
                    main_toolbar?.visibility = View.GONE
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                else -> {
                    main_toolbar?.visibility = View.VISIBLE
                    drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
                }
            }
        }

        user = FirebaseAuth.getInstance().currentUser

        if (user == null) {
            val action = AuthFragmentDirections.actionGlobalAuthFragment()
            navController.navigate(action)
        }

        drawerLayout.setOnTouchListener(object : OnSwipeTouchListener(this@MainActivity) {
            override fun onSwipeLeft() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    updateUserNameAndEmail()
                }
            }

            override fun onSwipeRight() {
                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.openDrawer(GravityCompat.START)
                    updateUserNameAndEmail()
                }
            }
        })
    }

    private fun updateUserNameAndEmail() {
        user?.let {
            textViewUserName?.text = it.displayName
            textViewEmail?.text = it.email
        }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        when (item.itemId) {
            R.id.oneCourse -> {
                val action = SubjectsFragmentDirections.actionGlobalSubjectsFragment2()
                navController.navigate(action)
                return true
            }
            R.id.twoCourse -> {

            }
            R.id.threeCourse -> {

            }
            R.id.fourCourse -> {

            }
            R.id.fiveCourse -> {

            }
            R.id.sixCourse -> {

            }
            R.id.favorite -> {
                val action = FavoriteFragmentDirections.actionGlobalFavoriteFragment()
                navController.navigate(action)
                return true
            }
            R.id.logOut -> {
                // Выход с аккаунта и переход на страницу авторизации
                AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Вы вышли с аккаунта", Toast.LENGTH_SHORT)
                            .show()
                        val action = AuthFragmentDirections.actionGlobalAuthFragment()
                        navController.navigate(action)
                    }
                return true
            }
        }
        return true
    }

    override fun onBackPressed() {
        // Закрытие нав-бара при нажатии кнопки назад
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        updateUserNameAndEmail()

        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}