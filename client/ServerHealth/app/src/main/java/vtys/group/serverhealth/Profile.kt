package vtys.group.serverhealth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
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
        val img = view.findViewById<ImageView>(R.id.profileImageView)
        val greeting = view.findViewById<TextView>(R.id.greetingTextView)
        val email = view.findViewById<TextView>(R.id.userEmailTextViewProfile)

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


        greeting.text = "Hello, " + account?.displayName
        email.text = account?.email

        val imageUri = account?.photoUrl
        if (account != null && account.getPhotoUrl() != null) {
            // Load the image using Glide
            Glide.with(this)
                .load(account.getPhotoUrl())
                .into(img);
        } else {
            // If the photoUrl is null, you can set a default image or handle it accordingly.
            img.setImageResource(R.drawable.profile);
        }


        // Inflate the layout for this fragment
        return view
    }


}