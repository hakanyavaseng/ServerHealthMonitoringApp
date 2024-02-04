package vtys.group.serverhealth

import Home
import Add
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import vtys.group.serverhealth.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var googleSignInClient: GoogleSignInClient

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        // Add any other necessary options
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)


        // Initialize the GoogleSignInClient
        googleSignInClient = GoogleSignIn.getClient(this, gso)



        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> replaceFragment(Home())
                R.id.navigation_favourite -> replaceFragment(Favourites())
                R.id.navigation_refresh -> replaceFragment(Add())
                R.id.navigation_notification -> replaceFragment(Notifications())
                R.id.navigation_profile -> replaceFragment(Profile())
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    // Provide access to the GoogleSignInClient
    fun getGoogleSignInClient(): GoogleSignInClient {
        return googleSignInClient
    }
}

/*val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
      bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
          when (menuItem.itemId) {
              R.id.navigation_item1 -> {
                  // Item 1 ile ilgili işlemler
                  true
              }
              R.id.navigation_item2 -> {
                  // Item 2 ile ilgili işlemler
                  true
              }
              R.id.navigation_item3 -> {
                  // Item 3 ile ilgili işlemler
                  true
              }
              R.id.navigation_item4 -> {
                  // Item 4 ile ilgili işlemler
                  true
              }
              R.id.navigation_item5 -> {
                  // Item 5 ile ilgili işlemler
                  true
              }

              // Diğer öğeleri ekleyin
              else -> false
          }
      }
*/

