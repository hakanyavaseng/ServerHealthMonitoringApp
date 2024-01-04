package vtys.group.serverhealth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.auth.LoginService
import vtys.group.serverhealth.auth.VerificationData

class ValidationActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        val receivedUsername = intent.getStringExtra("USERNAME")

        val intent= Intent(this,LoginActivity::class.java)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val loginService = retrofit.create(LoginService::class.java)



        val btnContinue=findViewById<Button>(R.id.btn_validation)
        btnContinue.setOnClickListener {

            val txtBox1 = findViewById<EditText>(R.id.val_input_index0)
            val txtBox2 = findViewById<EditText>(R.id.val_input_index1)
            val txtBox3 = findViewById<EditText>(R.id.val_input_index2)
            val txtBox4 = findViewById<EditText>(R.id.val_input_index3)

            val entered_verification_code = txtBox1.text.toString() + txtBox2.text.toString() + txtBox3.text.toString() + txtBox4.text.toString()


            val verificationData = VerificationData(
                username = receivedUsername.toString(),
                verificationCode = entered_verification_code
            )

            val callVerify: Call<Void> = loginService.verifyUser(verificationData)

            callVerify.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // Verification successful
                        Toast.makeText(
                            this@ValidationActivity,
                            "Verification successful",
                            Toast.LENGTH_SHORT
                        ).show()

                        Handler(Looper.getMainLooper()).postDelayed({
                            startActivity(intent)
                            finish()
                        }, 2000)
                        // Proceed with further actions if needed
                    } else {
                        // Verification failed
                        // Handle failure as needed
                        Toast.makeText(
                            this@ValidationActivity,
                            "Verification failed: ${response.message()}",
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
                        this@ValidationActivity,
                        "Network error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()

                    t.printStackTrace()
                }
            })











    }
}}