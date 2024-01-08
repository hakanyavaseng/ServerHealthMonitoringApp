package vtys.group.serverhealth


import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.poi.ss.usermodel.WorkbookFactory
import vtys.group.serverhealth.adapter.DailyReportAdapter
import vtys.group.serverhealth.adapter.HealthDataRecyclerAdapter
import vtys.group.serverhealth.adapter.InterruptsAdapter
import vtys.group.serverhealth.adapter.MonthlyReportAdapter
import vtys.group.serverhealth.viewmodel.ServerDetailViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ServerDetailFragment : Fragment() {
    private lateinit var viewModel: ServerDetailViewModel
    private lateinit var recyclerViewDetail: RecyclerView


    private val healthDataRecyclerAdapter = HealthDataRecyclerAdapter(emptyList())
    private val interruptsAdapter = InterruptsAdapter(emptyList())
    private val monthlyReportAdapter = MonthlyReportAdapter(emptyList())
    private val dailyReportAdapter = DailyReportAdapter(emptyList())
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
        val serverRam = arguments?.getString("serverRam", "") ?: 0
        val serverStorageType = arguments?.getString("serverStorageType", "") ?: 0
        val serverStorageCapacity = arguments?.getString("serverStorageCapacity", "") ?: 0
        val hospitalName = arguments?.getString("hospitalName", "")
        val cityName = arguments?.getString("cityName", "")


        // Update UI elements with server details
        view.findViewById<TextView>(R.id.serverNameTextView).text = "$serverName"
        view.findViewById<TextView>(R.id.serverIpTextView).text = "IP: $serverIp"
        view.findViewById<TextView>(R.id.serverOsTextView).text = "OS: $serverOs"
        view.findViewById<TextView>(R.id.serverRamTextView).text = "RAM: $serverRam"
        view.findViewById<TextView>(R.id.serverStorageTypeTextView).text =
            "$serverStorageType"
        view.findViewById<TextView>(R.id.serverStorageCapacityTextView).text =
            "Capacity: $serverStorageCapacity"
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
        val btnExport = view.findViewById<Button>(R.id.btnExport)

        btnHealth.setOnClickListener {
            if (radioWeek.isChecked) {
                viewModel.refreshDataWeek(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = healthDataRecyclerAdapter
                observeLiveData()
                healthDataRecyclerAdapter.clearData()

            } else if (radioMonth.isChecked) {
                viewModel.refreshDataMonth(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = healthDataRecyclerAdapter
                observeLiveData()
                healthDataRecyclerAdapter.clearData()

            } else if (radioYear.isChecked) {
                viewModel.refreshDataYear(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = healthDataRecyclerAdapter
                observeLiveData()
                healthDataRecyclerAdapter.clearData()

            }
        }

        btnInterrupt.setOnClickListener {
            if (radioWeek.isChecked) {
                viewModel.refreshInterruptWeek(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            } else if (radioMonth.isChecked) {
                viewModel.refreshInterruptMonth(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            } else if (radioYear.isChecked) {
                viewModel.refreshInterruptYear(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = interruptsAdapter
                observeInterruptsLiveData()
            }

        }

        btnReport.setOnClickListener {


            if (radioYear.isChecked) {
                viewModel.refreshMonthlyReport(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = monthlyReportAdapter
                observeMonthlyReportLiveData()
            } else if (radioWeek.isChecked) {
                viewModel.refreshDailyReport(serverId = serverId)
                recyclerViewDetail.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewDetail.adapter = dailyReportAdapter
                observeDailyReportLiveData()

            } else {
                //TODO DO NOTHING
            }
        }

        btnExport.setOnClickListener {
            if (recyclerViewDetail.adapter == healthDataRecyclerAdapter)
                exportHealthDataToXls(requireContext(), recyclerViewDetail)
            else if (recyclerViewDetail.adapter == interruptsAdapter)
                exportInterruptsToXls()
        }








        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ServerDetailViewModel::class.java]

    }

    private fun observeLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)

        viewModel.healthData.observe(viewLifecycleOwner, Observer { healthData ->
            healthData?.let { healthDataList ->
                if (healthDataList.isNotEmpty()) {
                    // Show data
                    recyclerViewDetail.visibility = View.VISIBLE
                    healthDataRecyclerAdapter.setData(healthDataList)
                } else {
                    // No data available
                    recyclerViewDetail.visibility = View.GONE
                }
            }
        })

        viewModel.healthDataError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
            }
        })

        viewModel.healthDataLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }

    private fun observeInterruptsLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)

        viewModel.interruptData.observe(viewLifecycleOwner, Observer { interruptsData ->
            interruptsData?.let { interruptsList ->
                recyclerViewDetail.visibility = View.VISIBLE
                interruptsAdapter.setData(interruptsList)
            }
        })

        viewModel.interruptDataError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
            }
        })

        viewModel.interruptDataLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }

    private fun observeMonthlyReportLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)

        viewModel.monthlyReportData.observe(viewLifecycleOwner, Observer { monthlyReportData ->
            monthlyReportData?.let { monthlyReportList ->
                recyclerViewDetail.visibility = View.VISIBLE
                monthlyReportAdapter.setData(monthlyReportList)
            }
        })

        viewModel.monthlyReportError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
            }
        })

        viewModel.monthlyReportLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }

    private fun observeDailyReportLiveData() {
        val recyclerViewDetail: RecyclerView = requireView().findViewById(R.id.recyclerViewDetail)

        viewModel.dailyReportData.observe(viewLifecycleOwner, Observer { dailyReportData ->
            dailyReportData?.let { dailyReportList ->
                recyclerViewDetail.visibility = View.VISIBLE
                dailyReportAdapter.setData(dailyReportList)
            }
        })

        viewModel.dailyReportError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
            }
        })

        viewModel.dailyReportLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                recyclerViewDetail.visibility = if (it) View.GONE else View.VISIBLE
            }
        })
    }

    private fun exportInterruptsToXls() {
        val interruptsAdapter = recyclerViewDetail.adapter as? InterruptsAdapter
        val interruptsDataList = interruptsAdapter?.getInterruptsList()

        if (interruptsDataList != null && interruptsDataList.isNotEmpty()) {
            val workbook = WorkbookFactory.create(true)
            val sheet = workbook.createSheet("InterruptData")

            // Add headers
            val headerRow = sheet.createRow(0)
            headerRow.createCell(0).setCellValue("Interrupt ID")
            headerRow.createCell(1).setCellValue("Date")
            headerRow.createCell(2).setCellValue("Time")
            headerRow.createCell(3).setCellValue("Status")
            headerRow.createCell(4).setCellValue("Server Name")

            // Add data
            for ((index, interrupt) in interruptsDataList.withIndex()) {
                val row = sheet.createRow(index + 1)
                row.createCell(0).setCellValue(interrupt.interruptid.toDouble())
                row.createCell(1).setCellValue(interrupt.interruptdate ?: "N/A")
                row.createCell(2).setCellValue(interrupt.interrupttime ?: "N/A")
                row.createCell(3).setCellValue(interrupt.interruptstatus)
                row.createCell(4).setCellValue(interrupt.serverid.servername ?: "N/A")
            }

            // Save the workbook to a file
            val xlsFileName = "InterruptData_${System.currentTimeMillis()}.xls"
            val xlsFilePath = File(
                requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                xlsFileName
            ).absolutePath

            try {
                val fileOut = FileOutputStream(xlsFilePath)
                workbook.write(fileOut)
                fileOut.close()
                showToast("Data exported to $xlsFilePath")
            } catch (e: IOException) {
                e.printStackTrace()
                showToast("Error exporting data")
            }
        } else {
            showToast("No data to export")
        }
    }

    private fun exportHealthDataToXls(context: Context, recyclerViewDetail: RecyclerView) {
        val healthDataAdapter = recyclerViewDetail.adapter as? HealthDataRecyclerAdapter
        val healthDataList = healthDataAdapter?.getHealthdataList()

        if (healthDataList != null && healthDataList.isNotEmpty()) {
            val workbook = WorkbookFactory.create(true)
            val sheet = workbook.createSheet("HealthData")

            // Add headers
            val headerRow = sheet.createRow(0)
            headerRow.createCell(0).setCellValue("Health ID")
            headerRow.createCell(1).setCellValue("Date")
            headerRow.createCell(2).setCellValue("CPU Usage")
            headerRow.createCell(3).setCellValue("RAM Usage")
            headerRow.createCell(4).setCellValue("Storage Usage")
            headerRow.createCell(5).setCellValue("Server Temp")
            headerRow.createCell(6).setCellValue("Ambient Temp")
            headerRow.createCell(7).setCellValue("Energy Usage")
            headerRow.createCell(8).setCellValue("Heartbeat")
            headerRow.createCell(9).setCellValue("Server Name")

            // Add data
            for ((index, healthData) in healthDataList.withIndex()) {
                val row = sheet.createRow(index + 1)
                row.createCell(0).setCellValue(healthData.dataId.toDouble())
                row.createCell(1).setCellValue(healthData.dataDateTime ?: "N/A")
                row.createCell(2).setCellValue(healthData.dataCpuUsage.toDouble())
                row.createCell(3).setCellValue(healthData.dataRamUsage.toDouble())
                row.createCell(4).setCellValue(healthData.dataStorageUsage.toDouble())
                row.createCell(5).setCellValue(healthData.dataServerTemp.toDouble())
                row.createCell(6).setCellValue(healthData.dataAmbientTemp.toDouble())
                row.createCell(7).setCellValue(healthData.dataEnergyUsage.toDouble())
                row.createCell(8).setCellValue(if (healthData.dataHeartBeat) 1.0 else 0.0)
                row.createCell(9).setCellValue(healthData.serverId.servername ?: "N/A")
            }

            // Save the workbook to a file
            val xlsFileName = "HealthData_${System.currentTimeMillis()}.xls"
            val xlsFilePath = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
                xlsFileName
            ).absolutePath

            try {
                val fileOut = FileOutputStream(xlsFilePath)
                workbook.write(fileOut)
                fileOut.close()
                showToast(context, "Data exported to $xlsFilePath")
            } catch (e: IOException) {
                e.printStackTrace()
                showToast(context, "Error exporting data")
            }
        } else {
            showToast(context, "No data to export")
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
