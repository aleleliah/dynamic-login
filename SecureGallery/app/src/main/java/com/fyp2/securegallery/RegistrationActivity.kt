package com.fyp2.securegallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fyp2.securegallery.model.User
import com.fyp2.securegallery.network.generateVerificationCode
import com.fyp2.securegallery.network.sendVerificationEmail
import com.fyp2.securegallery.util.Storage
import com.google.android.material.snackbar.Snackbar

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)

        val edtPin = findViewById<EditText>(R.id.edtPinInput)
        val edtOtp = findViewById<EditText>(R.id.edtOtpInput)
        val edtConfirmPin = findViewById<EditText>(R.id.edtConfirmPinInput)
        val btnVerifyEmail = findViewById<Button>(R.id.btnVerifyEmail)
        val edtEmail = findViewById<EditText>(R.id.edtEmailInput)
        val rootView = findViewById<View>(android.R.id.content)
        val btnDiscard = findViewById<Button>(R.id.btnDiscard)
        val btnConfirm = findViewById<Button>(R.id.btnConfirm)
        var code = ""

        btnConfirm.setOnClickListener {

            if(edtOtp.text.toString().isEmpty()){
                Snackbar.make(rootView, "Verification code cannot be empty!", Snackbar.LENGTH_SHORT)
                    .show()
            } else if(edtOtp.text.toString() != code){
                Snackbar.make(rootView, "Invalid verification code", Snackbar.LENGTH_SHORT)
                    .show()
            } else if(edtPin.text.toString() != edtConfirmPin.text.toString()){
                Snackbar.make(rootView, "Pin does not match!", Snackbar.LENGTH_SHORT)
                    .show()
            } else if(edtPin.text.toString().isEmpty()){
                Snackbar.make(rootView, "Pin cannot be empty!", Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Storage().saveUser(
                    User(
                        pin = edtPin.text.toString(),
                        email = edtEmail.text.toString()
                    )
                )
                Snackbar.make(rootView, "You may proceed to login", Snackbar.LENGTH_SHORT)
                    .show()
                Storage().saveExist(exist = true)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btnDiscard.setOnClickListener {
            finish()
        }

        btnVerifyEmail.setOnClickListener {
            code = generateVerificationCode()

            if(edtEmail.text.toString().isNotEmpty()){
                sendVerificationEmail(to = edtEmail.text.toString(), code = code)
                Snackbar.make(rootView, "Please check your email for verification code, including the spam folder", Snackbar.LENGTH_SHORT)
                    .show()
            } else{
                Snackbar.make(rootView, "Email cannot be empty", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}