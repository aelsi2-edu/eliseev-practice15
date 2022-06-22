package com.aelsi2.practice15

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.aelsi2.practice15.utils.debouncers.OnClickDebouncer

class SignInActivity : FragmentActivity(), View.OnClickListener {
    private lateinit var usernameTextField : EditText
    private lateinit var passwordTextField : EditText
    private lateinit var signInButton : View
    private val onClickDebouncer = OnClickDebouncer(this, 500)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        initViews()
    }
    private fun initViews() {
        usernameTextField = findViewById<LoginTextField>(R.id.login_field).editText
        passwordTextField = findViewById<LoginTextField>(R.id.password_field).editText
        signInButton = findViewById(R.id.sign_in_button)
        signInButton.setOnClickListener(onClickDebouncer)
    }
    private fun onSignInRequest() {
        if (usernameTextField.text.isEmpty() || passwordTextField.text.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_field, Toast.LENGTH_LONG).show()
            return
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    override fun onClick(view: View?) {
        when (view) {
            signInButton -> {
                onSignInRequest()
            }
        }
    }
}