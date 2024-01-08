import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import vtys.group.serverhealth.R
import vtys.group.serverhealth.ServerDetailFragment
import vtys.group.serverhealth.model.CityDataModel
import vtys.group.serverhealth.model.HospitalDataModel
import vtys.group.serverhealth.adapter.ServerAdapter
import vtys.group.serverhealth.model.ServerDataModel
import vtys.group.serverhealth.service.impl.RetrofitService

class Home : Fragment(), ServerAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ServerAdapter
    private val retrofitService = RetrofitService()
    private val retrofit = retrofitService.getRetrofit()

    private val getAllApi = retrofit.baseUrl().toString() + "api/servers/getall"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)




        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = ServerAdapter(emptyList(), this)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fetchDataFromApi()

        // Set up SearchView
        val searchView: SearchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })

        return view
    }

    override fun onItemClick(server: ServerDataModel) {
        // Handle item click here
        openServerDetailFragment(server)
    }

    private fun openServerDetailFragment(server: ServerDataModel) {
        val serverDetailFragment = ServerDetailFragment()

        // Pass data to ServerDetailFragment using arguments
        val args = Bundle()
        args.putInt("serverId", server.serverid)
        args.putString("serverName", server.servername)
        args.putString("serverIp", server.serverip)
        args.putString("serverOs", server.serveros)
        args.putString("serverRam", server.server_ram)
        args.putString("serverStorageType", server.serverStorageType)
        args.putString("serverStorageCapacity", server.serverStorageCapacity)
        args.putString("hospitalName", server.hospitalid.hospitalname)
        args.putString("cityName", server.hospitalid.cityid.cityname)

        serverDetailFragment.arguments = args

        // Replace current fragment with ServerDetailFragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, serverDetailFragment)
            .addToBackStack(null) // Optional: Adds the transaction to the back stack
            .commit()
    }


    private fun fetchDataFromApi() {
        val reqQueue: RequestQueue =
            Volley.newRequestQueue(requireContext())  // Assuming you are inside a Fragment, use requireContext() instead of this
        val requestJson = JsonArrayRequest(Request.Method.GET, getAllApi, null, { jsonArray ->
            Log.d("Volley Example", jsonArray.toString())

            val serverList = ArrayList<ServerDataModel>()

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)

                val server = ServerDataModel(
                    jsonObject.getInt("serverid"),
                    jsonObject.getString("servername"),
                    jsonObject.getString("serverip"),
                    jsonObject.getString("serveros"),
                    jsonObject.getString("server_ram"),
                    jsonObject.getString("serverStorageType"),
                    jsonObject.getString("serverStorageCapacity"),
                    extractHospital(jsonObject.getJSONObject("hostpitalid"))
                )

                serverList.add(server)
            }

            // Now, you can update the adapter with the obtained serverList
            adapter.setData(serverList)
        }, { error ->
            // Handle error here
            Log.e("Volley Example", "Error fetching data: ${error.message}")
        })

        reqQueue.add(requestJson)
    }

    private fun extractHospital(hospitalObject: JSONObject): HospitalDataModel {
        return HospitalDataModel(
            hospitalObject.getInt("hospitalid"),
            hospitalObject.getString("hospitalname"),
            extractCity(hospitalObject.getJSONObject("cityid"))
        )
    }

    private fun extractCity(cityObject: JSONObject): CityDataModel {
        return CityDataModel(
            cityObject.getInt("cityid"),
            cityObject.getString("cityname")
        )
    }


}