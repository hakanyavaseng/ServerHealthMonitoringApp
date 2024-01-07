package vtys.group.serverhealth

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.auth.GoogleToken
import vtys.group.serverhealth.auth.LoginData
import vtys.group.serverhealth.auth.LoginService

class LoginActivity : AppCompatActivity() {

    private lateinit var gso : GoogleSignInOptions
    private lateinit var gsc : GoogleSignInClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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

        val account : GoogleSignInAccount?= GoogleSignIn.getLastSignedInAccount(this)
        if(account != null){
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1)
        {
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {

                task.getResult(ApiException::class.java)
                goToHome(task.getResult(ApiException::class.java))


            }catch (e : ApiException){
                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }



        }

    }

    private fun goToHome(account: GoogleSignInAccount){

        val idToken = account.idToken

        sendTokenToApi(idToken)

        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }

    private fun sendTokenToApi(idToken: String?) {
        if (idToken != null) {
            // You can now include this token in your API request
            val retrofit = Retrofit.Builder()
                .baseUrl("https://your-api-url.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val loginService = retrofit.create(LoginService::class.java)

            val googleToken = GoogleToken(idToken)
            val call: Call<Void> = loginService.googleLogin(googleToken)

            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Successful API call
                        // You can handle success as needed
                    } else {
                        // API call failed
                        // Handle failure as needed
                        println("API call failed: ${response.message()}")
                        response.errorBody()?.string()?.let {
                            println(it)
                        }
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // Handle failure
                    println("Network error: ${t.message}")
                    t.printStackTrace()
                }
            })
        } else {
            // Handle the case where idToken is null
            println("Google Sign-In token is null")
        }
    }


}
