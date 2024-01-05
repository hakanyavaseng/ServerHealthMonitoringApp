package vtys.group.serverhealth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


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
        val hospitalName = arguments?.getString("hospitalName", "")
        val cityName = arguments?.getString("cityName", "")


        // Use the retrieved data to populate your UI elements
        // For example, update TextViews with server details
        val serverNameTextView: TextView = view.findViewById(R.id.serverNameTextView)
        val serverIpTextView: TextView = view.findViewById(R.id.serverIpTextView)
        val serverOsTextView: TextView = view.findViewById(R.id.serverOsTextView)
        val serverRamTextView: TextView = view.findViewById(R.id.serverRamTextView)
        val serverStorageTypeTextView: TextView = view.findViewById(R.id.serverStorageTypeTextView)
        val serverStorageCapacityTextView: TextView = view.findViewById(R.id.serverStorageCapacityTextView)
        val hospitalNameTextView: TextView = view.findViewById(R.id.hospitalNameTextView)
        val cityNameTextView: TextView = view.findViewById(R.id.cityNameTextView)




        serverNameTextView.text = "$serverName"
        serverIpTextView.text = "IP: $serverIp"
        serverOsTextView.text = "OS: $serverOs"
        serverRamTextView.text = "RAM: $serverRam"
        serverStorageTypeTextView.text = "Storage Type: $serverStorageType"
        serverStorageCapacityTextView.text = "Storage Capacity: $serverStorageCapacity"
        hospitalNameTextView.text = "$hospitalName"
        cityNameTextView.text = "$cityName"



        return view

    }
}




