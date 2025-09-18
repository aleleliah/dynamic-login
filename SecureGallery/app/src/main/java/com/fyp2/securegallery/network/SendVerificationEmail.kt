package com.fyp2.securegallery.network

import android.os.AsyncTask
import org.json.JSONObject
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.random.Random

fun generateVerificationCode(): String {
    val code = Random.nextInt(100000, 1000000) // Generates a random number between 100000 and 999999
    return code.toString() // Return the code as a string
}

fun sendVerificationEmail(to: String, code: String) {
    val url = URL("https://fabmatchtrack.online/market/sendVerificationEmailSecure.php") // Replace with actual URL

    // Prepare data to send in POST request
    val postData = "to=$to&code=$code"

    // Start async task to send request
    SendVerificationEmail().execute(url, postData)
}

class SendVerificationEmail : AsyncTask<Any, Void, String>() {

    override fun doInBackground(vararg params: Any?): String {
        val url = params[0] as URL
        val postData = params[1] as String
        var response = ""

        try {
            // Open connection to the API URL
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")

            // Send the POST data
            val outputStream = DataOutputStream(connection.outputStream)
            outputStream.writeBytes(postData)
            outputStream.flush()
            outputStream.close()

            // Get the response
            val inputStream = InputStreamReader(connection.inputStream)
            val bufferedReader = inputStream.readLines()
            response = bufferedReader.joinToString("\n")

            inputStream.close()

        } catch (e: Exception) {
            e.printStackTrace()
            response = "Error: ${e.message}"
        }

        return response
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        if (result != null) {
            try {
                // Parse the response from the server
                val jsonResponse = JSONObject(result)
                val resultValue = jsonResponse.getString("result")

                if (resultValue == "success") {
                    // Handle success case
                    println("Verification email sent successfully")
                } else {
                    // Handle failure case
                    println("Failed to send verification email")
                }
            } catch (e: Exception) {
                println("Error parsing response: ${e.message}")
            }
        }
    }

}