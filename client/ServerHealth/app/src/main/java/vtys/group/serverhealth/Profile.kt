package vtys.group.serverhealth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn


class Profile : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val homeActivity = requireActivity() as HomeActivity

        val account = GoogleSignIn.getLastSignedInAccount(homeActivity)

        // Call findViewById on the 'view' object
        val tv = view.findViewById<TextView>(R.id.UserEmailTextViewProfile)

        val btnLOGOUT = view.findViewById<TextView>(R.id.btnLogout)

        btnLOGOUT.setOnClickListener {
            // Access the GoogleSignInClient from the HomeActivity
            val googleSignInClient = homeActivity.getGoogleSignInClient()

            // Sign out the user
            googleSignInClient.signOut().addOnCompleteListener {
                // Optionally, navigate to the login screen or perform other actions after sign out
                // For example:
                val intent = Intent(homeActivity, LoginActivity::class.java)
                homeActivity.startActivity(intent)
                homeActivity.finish()
            }
        }

        tv.text = account?.email
        // Now you can use 'tv' to manipulate the TextView

        // Inflate the layout for this fragment
        return view
    }


}