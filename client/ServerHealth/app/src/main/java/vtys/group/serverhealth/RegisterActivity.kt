package vtys.group.serverhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.auth.LoginService
import vtys.group.serverhealth.auth.RegistrationData

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val backToLoginButton = findViewById<Button>(R.id.btn_back_to_login)

        backToLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val intent = Intent(this, ValidationActivity::class.java)

        val btnRegister = findViewById<Button>(R.id.btn_registration_page)
        btnRegister.setOnClickListener {

            //TODO Kayıt olma işlemleri
            val retrofit = Retrofit.Builder()
                .baseUrl("https://serverhealth.azurewebsites.net") // Remove extra trailing slash
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val loginService = retrofit.create(LoginService::class.java)

            val txtUsername = findViewById<EditText>(R.id.username_input_register)
            val txtPassword = findViewById<EditText>(R.id.username_password)
            val txtMail = findViewById<EditText>(R.id.mail_input)

            val registrationData = RegistrationData(
                username = txtUsername.text.toString(),
                password = txtPassword.text.toString(),
                useremail = txtMail.text.toString()
            )

            val call: Call<Void> = loginService.registerUser(registrationData)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Successful registration
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Simulate a delay of 2 seconds
                        Handler(Looper.getMainLooper()).postDelayed({
                            sendData()
                            finish()
                        }, 2000)
                    } else {
                        // Registration failed
                        // Handle failure as needed
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration failed: ${response.message()}",
                            Toast.LENGTH_LONG
                        ).show()

                        // Log the network failure to console
                        response.errorBody()?.string()?.let {
                            println(it)
                        }
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle failure
                    Toast.makeText(
                        this@RegisterActivity,
                        "Network error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                    t.printStackTrace()
                }
            })
        }
    }

    fun sendData() {
        val etUsername = findViewById<EditText>(R.id.username_input_register)
        val sended_username = etUsername.text.toString()

        val intent = Intent(this, ValidationActivity::class.java)
        intent.putExtra("USERNAME", sended_username)
        startActivity(intent)
    }

}