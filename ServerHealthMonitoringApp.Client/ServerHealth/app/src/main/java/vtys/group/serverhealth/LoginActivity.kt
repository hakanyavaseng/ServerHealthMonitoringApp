package vtys.group.serverhealth

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.service.impl.GoogleUser
import vtys.group.serverhealth.service.impl.LoginData
import vtys.group.serverhealth.service.impl.LoginService
import vtys.group.serverhealth.service.impl.RetrofitService

class LoginActivity : AppCompatActivity() {

    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofitService = RetrofitService()
        val intentLogin = Intent(this, HomeActivity::class.java)

        val registerButton = findViewById<Button>(R.id.btn_register)
        registerButton.setOnClickListener {
            val intentRegister = Intent(this, RegisterActivity::class.java)
            startActivity(intentRegister)
            finish()
        }
        val loginButton = findViewById<Button>(R.id.btn_login)



        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        gsc = GoogleSignIn.getClient(this, gso)

        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)

        if (account != null) {
            intentLogin.putExtra("ID_TOKEN", account.idToken)
            startActivity(intentLogin)
            finish()
        }


        val btnGoogleLogin = findViewById<Button>(R.id.btnGoogleLogin)

        btnGoogleLogin.setOnClickListener {

            val signInIntent = gsc.signInIntent
            startActivityForResult(signInIntent, 1)


        }



        loginButton.setOnClickListener {

            val txtLoginUsername = findViewById<EditText>(R.id.username_input)
            val txtLoginPassword = findViewById<EditText>(R.id.username_password)

            val loginData = LoginData(
                username = txtLoginUsername.text.toString(),
                password = txtLoginPassword.text.toString()
            )

            val retrofit = retrofitService.getRetrofit()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                task.getResult(ApiException::class.java)
                goToHome(task.getResult(ApiException::class.java))


            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }


        }

    }

    private fun goToHome(account: GoogleSignInAccount) {

        val email = account.email
        sendUserToApi(email)

        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }

    private fun sendUserToApi(email: String?) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.0.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginService = retrofit.create(LoginService::class.java)

        val GoogleUser = GoogleUser(
            email = email.toString()
        )

        val call: Call<Void> = loginService.googleLogin(GoogleUser)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {

                    Toast.makeText(
                        this@LoginActivity,
                        "Google Login successful.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    Log.d(TAG, "onResponse: ${response.message()}")

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Handle failure
                println("Network error: ${t.message}")
                t.printStackTrace()
            }
        })

    }


}
