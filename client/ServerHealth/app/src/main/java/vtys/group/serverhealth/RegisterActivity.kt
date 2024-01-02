package vtys.group.serverhealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val backtologinButton=findViewById<Button>(R.id.btn_back_to_login)

        backtologinButton.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish() }
        val btn_register=findViewById<Button>(R.id.btn_register)
        btn_register.setOnClickListener {
            val intent= Intent(this,ValidationActivity::class.java)
            startActivity(intent)
            finish()


        }}}