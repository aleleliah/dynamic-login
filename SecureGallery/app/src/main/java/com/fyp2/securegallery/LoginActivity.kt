package com.fyp2.securegallery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fyp2.securegallery.util.Storage
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var pinCircles: List<View>
    private val enteredPin = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(!Storage().getExist()){
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            finish()
        }

        pinCircles = listOf(
            findViewById(R.id.circle1),
            findViewById(R.id.circle2),
            findViewById(R.id.circle3),
            findViewById(R.id.circle4)
        )

        setupKeypad()
    }

    private fun setupKeypad() {
        val buttonIds = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        // Create a list of numbers 0-9 and shuffle it
        val numbers = (0..9).toMutableList()
        numbers.shuffle()

        for (i in buttonIds.indices) {
            val btn = findViewById<Button>(buttonIds[i])
            val number = numbers[i]
            btn.text = number.toString()
            btn.setOnClickListener {
                if (enteredPin.length < 4) {
                    enteredPin.append(btn.text.toString())
                    updatePinCircles()
                    if (enteredPin.length == 4) {
                        validatePin(enteredPin.toString())
                    }
                }
            }
        }

        findViewById<View>(R.id.btnDelete).setOnClickListener {
            if (enteredPin.isNotEmpty()) {
                enteredPin.deleteCharAt(enteredPin.length - 1)
                updatePinCircles()
            }
        }
    }

    private fun updatePinCircles() {
        for (i in pinCircles.indices) {
            if (i < enteredPin.length) {
                pinCircles[i].setBackgroundResource(R.drawable.pin_circle_filled)
            } else {
                pinCircles[i].setBackgroundResource(R.drawable.pin_circle_unfilled)
            }
        }
    }

    private fun validatePin(pin: String) {
        val savedPin = Storage().getUser().pin
        if (pin == savedPin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            enteredPin.clear()
            updatePinCircles()
            val rootView = findViewById<View>(android.R.id.content)
            Snackbar.make(rootView, "Incorrect PIN. Please try again.", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}
