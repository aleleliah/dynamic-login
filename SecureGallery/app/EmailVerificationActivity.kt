package com.fyp2.securegallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.fyp2.securegallery.network.generateVerificationCode
import com.fyp2.securegallery.network.sendVerificationEmail
import com.fyp2.securegallery.util.Storage
import com.google.android.material.snackbar.Snackbar

class EmailVerificationActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtVerificationCode: EditText
    private lateinit var btnSendCode: Button
    private lateinit var btnVerifyCode: Button
    private var verificationCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_email_verification)

        edtEmail = findViewById(R.id.edtEmailInput)
        edtVerificationCode = findViewById(R.id.edtVerificationCodeInput)
        btnSendCode = findViewById(R.id.btnSendCode)
        btnVerifyCode = findViewById(R.id.btnVerifyCode)

        btnSendCode.setOnClickListener {
            val email = edtEmail.text.toString()
            if (email.isNotEmpty()) {
                verificationCode = generateVerificationCode()
                sendVerificationEmail(to = email, code = verificationCode)
                Snackbar.make(it, "Verification code sent to your email.", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(it, "Email cannot be empty.", Snackbar.LENGTH_SHORT).show()
            }
        }

        btnVerifyCode.setOnClickListener {
            val inputCode = edtVerificationCode.text.toString()
            if (inputCode == verificationCode) {
                // Proceed to login page
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(it, "Invalid verification code.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
