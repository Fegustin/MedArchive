package com.example.medarchive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.medarchive.NetworkConnection
import com.example.medarchive.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_auth.*


class AuthFragment : Fragment() {

    companion object {
        private const val RC_SIGN_IN = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Проверка зашел ли пользователь на свой акк
        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            val action = FavoriteFragmentDirections.actionGlobalFavoriteFragment()
            view.findNavController().navigate(action)
        }
        // ----------- //

        // Login and registration

        // Мониторинг подключения к инернету
        val networkConnection = activity?.applicationContext?.let { NetworkConnection(it) }
        networkConnection?.observe(viewLifecycleOwner, Observer { isConnected ->
            buttonAuth.isClickable = if (isConnected) {
                buttonAuth.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorPrimary, null))
                true
            } else {
                buttonAuth.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorGray, null))
                false
            }
        })
        // ------ //

        buttonAuth.setOnClickListener {
            val providers = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setTheme(R.style.AppTheme)
                    .build(),
                RC_SIGN_IN
            )
        }

        imageViewHint.setOnClickListener { dialogHint() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_SIGN_IN -> {
                val response = IdpResponse.fromResultIntent(data)

                if (resultCode == Activity.RESULT_OK) {

                    val user = FirebaseAuth.getInstance().currentUser

                    if (user != null) {
                        if (!user.isEmailVerified) {
                            user.sendEmailVerification()
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(activity, getString(R.string.сonfirm_email), Toast.LENGTH_SHORT)
                                            .show()
                                        activity?.let {
                                            AuthUI.getInstance()
                                                .signOut(it)
                                        }
                                    } else {
                                        Toast.makeText(activity, getString(R.string.сonfirm_email), Toast.LENGTH_SHORT)
                                            .show()
                                        activity?.let {
                                            AuthUI.getInstance()
                                                .signOut(it)
                                        }
                                    }
                                }
                        } else {
                            view?.findNavController()?.navigate(R.id.action_global_favoriteFragment)
                        }
                    }

                } else {
                    Toast.makeText(activity, "Ошибка авторизации", Toast.LENGTH_SHORT)
                        .show()
                    Log.i("errorAuth", "Ошибка: ${response?.error}")
                }

            }

        }
    }

    private fun dialogHint() {
        MaterialAlertDialogBuilder(activity)
            .setTitle(resources.getString(R.string.hintAlertTitle))
            .setMessage(resources.getString(R.string.hintAlertMessage))
            .show()
    }
}