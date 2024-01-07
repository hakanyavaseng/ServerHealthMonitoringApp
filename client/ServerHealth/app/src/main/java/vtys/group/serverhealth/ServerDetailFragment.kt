package vtys.group.serverhealth


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vtys.group.serverhealth.adapter.HealthDataRecyclerAdapter
import vtys.group.serverhealth.adapter.InterruptsAdapter
import vtys.group.serverhealth.viewmodel.ServerDetailViewModel

class ServerDetailFragment : Fragment() {
    private lateinit var viewModel: ServerDetailViewModel
    private lateinit var recyclerViewDetail: RecyclerView


    private val serverDetailAdapter = HealthDataRecyclerAdapter(emptyList())
    private val interruptsAdapter = InterruptsAdapter(emptyList())
    //val serverDetailViewModel= ServerDetailViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_server_detail, container, false)

        val serverId = arguments?.getInt("serverId", -1) ?: -1
        val serverName = arguments?.getString("serverName", "")
        val serverIp = arguments?.getString("serverIp", "")
        val serverOs = arguments?.getString("serverOs", "")
        val serverRam = arguments?.getInt("serverRam", 0) ?: 0
        val serverStorageType = arguments?.getInt("serverStorageType", 0) ?: 0
        val serverStorageCapacity = arguments?.getInt("serverStorageCapacity", 0) ?: 0
        val hospitalName = arguments?.getString("hospitalName", "")
        val cityName = arguments?.getString("cityName", "")


        // Update UI elements with server details
        view.findViewById<TextView>(R.id.serverNameTextView).text = "$serverName"
        view.findViewById<TextView>(R.id.serverIpTextView).text = "IP: $serverIp"
        view.findViewById<TextView>(R.id.serverOsTextView).text = "OS: $serverOs"
        view.findViewById<TextView>(R.id.serverRamTextView).text = "RAM: $serverRam"
        view.findViewById<TextView>(R.id.serverStorageTypeTextView).text =
            "Storage Type: $serverStorageType"
        view.findViewById<TextView>(R.id.serverStorageCapacityTextView).text =
            "Storage Capacity: $serverStorageCapacity"
        view.findViewById<TextView>(R.id.hospitalNameTextView).text = "$hospitalName"
        view.findViewById<TextView>(R.id.cityNameTextView).text = "$cityName"
        recyclerViewDetail = view.findViewById(R.id.recyclerViewDetail)

        //Listing elements
        val radioWeek = view.findViewById<RadioButton>(R.id.weeklyRadio)
        val radioMonth = view.findViewById<RadioButton>(R.id.monthlyRadio)
        val radioYear = view.findViewById<RadioButton>(R.id.yearlyRadio)

        val btnHealth = view.findViewById<Button>(R.id.btnHealth)
        val btnInterrupt = view.findViewById<Button>(R.id.btnInterrupt)
        val btnReport = view.findViewById<Button>(R.id.btnReport)

        btnHealth.setOnClickListener{
            if(radioWeek.isChecked){
                viewModel.refreshDataWeek(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = serverDetailAdapter
                observeLiveData()
            }
            else if(radioMonth.isChecked){
                viewModel.refreshDataMonth(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = serverDetailAdapter
                observeLiveData()
            }
            else if(radioYear.isChecked){
                viewModel.refreshDataYear(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = serverDetailAdapter
                observeLiveData()
            }
        }

        btnInterrupt.setOnClickListener{
            if(radioWeek.isChecked)
            {
                viewModel.refreshInterruptWeek(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            }
            else if(radioMonth.isChecked)
            {
                viewModel.refreshInterruptMonth(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            }
            else if(radioYear.isChecked)
            {
                viewModel.refreshInterruptYear(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            }

        }








        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ServerDetailViewModel::class.java]

    }

    private fun observeLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)
        val errorMessage = requireView().findViewById<TextView>(R.id.errorMessage)

        viewModel.healthData.observe(viewLifecycleOwner, Observer { healthData ->
            healthData?.let { healthDataList ->
                recyclerViewDetail.visibility = View.VISIBLE
                serverDetailAdapter.setData(healthDataList)
            }
        })

        viewModel.healthDataError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                errorMessage.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.healthDataLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                errorMessage.visibility = if (it) View.GONE else View.VISIBLE
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })


    }

    private fun observeInterruptsLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)
        val errorMessage = requireView().findViewById<TextView>(R.id.errorMessage)

        viewModel.interruptData.observe(viewLifecycleOwner, Observer { interruptsData ->
            interruptsData?.let { interruptsList ->
                recyclerViewDetail.visibility = View.VISIBLE
                interruptsAdapter.setData(interruptsList)
            }
        })

        viewModel.interruptDataError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                errorMessage.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.interruptDataLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                errorMessage.visibility = if (it) View.GONE else View.VISIBLE
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }
}
