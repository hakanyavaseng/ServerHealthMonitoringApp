package vtys.group.serverhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val registerButton=findViewById<Button>(R.id.btn_register)

        registerButton.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }








}}