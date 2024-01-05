package vtys.group.serverhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.auth.LoginData
import vtys.group.serverhealth.auth.LoginService

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val intentLogin =Intent(this,HomeActivity::class.java)

        val registerButton=findViewById<Button>(R.id.btn_register)

        registerButton.setOnClickListener {
            val intentRegister = Intent(this,RegisterActivity::class.java)
            startActivity(intentRegister)
            finish()
        }
        val loginButton=findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener{

            val txtLoginUsername = findViewById<EditText>(R.id.username_input)
            val txtLoginPassword = findViewById<EditText>(R.id.username_password)

            val loginData = LoginData(
                username = txtLoginUsername.text.toString(),
                password = txtLoginPassword.text.toString()
            )

            val retrofit = Retrofit.Builder()
                .baseUrl("https://serverhealth.azurewebsites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val loginService = retrofit.create(LoginService::class.java)

            val callLogin: Call<Void> = loginService.loginUser(loginData)

            callLogin.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Successful login
                        Toast.makeText(
                            this@LoginActivity,
                            "Login successful",
                            Toast.LENGTH_SHORT
                        ).show()


                        startActivity(intentLogin)
                        finish()
                        // Proceed with further actions if needed
                    } else {
                        // Login failed
                        // Handle failure as needed
                        Toast.makeText(
                            this@LoginActivity,
                            "Login failed: ${response.message()}",
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
                        this@LoginActivity,
                        "Network error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    t.printStackTrace()
                }
            })
        }
        }
}
