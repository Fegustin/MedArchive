package com.example.medarchive.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.medarchive.R
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    private val RC_SIGN_IN: Int = 1001

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null) {
            if (user.isEmailVerified) view.findNavController().navigate(R.id.action_authFragment_to_listOfItemsFragment)
        }


        // Login and registration
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
                            view?.findNavController()?.navigate(R.id.action_authFragment_to_listOfItemsFragment)
                        }
                    }

                } else {
                    // Позже заменить на вывод в лог
                    Toast.makeText(activity, "Ошибка: ${response?.error}", Toast.LENGTH_SHORT)
                        .show()
                }

            }

        }
    }
}