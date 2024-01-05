package vtys.group.serverhealth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ServerDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_server_detail, container, false)

        // Retrieve data from arguments
        val serverId = arguments?.getInt("serverId", -1) ?: -1
        val serverName = arguments?.getString("serverName", "")
        val serverIp = arguments?.getString("serverIp", "")
        val serverOs = arguments?.getString("serverOs", "")
        val serverRam = arguments?.getInt("serverRam", 0) ?: 0
        val serverStorageType = arguments?.getInt("serverStorageType", 0) ?: 0
        val serverStorageCapacity = arguments?.getInt("serverStorageCapacity", 0) ?: 0

        // Use the retrieved data to populate your UI elements
        // For example, update TextViews with server details
        val serverNameTextView = view.findViewById<TextView>(R.id.serverNameTextViewServerDetail)


        serverNameTextView.text = "Name: $serverName"


        return view
    }
}