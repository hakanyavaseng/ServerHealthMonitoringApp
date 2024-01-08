package vtys.group.serverhealth.fileops

import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.apache.poi.ss.usermodel.WorkbookFactory
import vtys.group.serverhealth.adapter.HealthDataRecyclerAdapter
import vtys.group.serverhealth.adapter.InterruptsAdapter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ExcelExport(private val context: Context) {

    fun exportInterruptsToXls(recyclerViewDetail: RecyclerView) {
        val interruptsAdapter = recyclerViewDetail.adapter as? InterruptsAdapter
        val interruptsDataList = interruptsAdapter?.getInterruptsList()

        if (interruptsDataList != null && interruptsDataList.isNotEmpty()) {
            val workbook = WorkbookFactory.create(true)
            val sheet = workbook.createSheet("InterruptData")

            createHeaders(sheet, arrayOf("Interrupt ID", "Date", "Time", "Status", "Server Name"))

            addDataToSheet(interruptsDataList, sheet) { interrupt, row ->
                row.createCell(0).setCellValue(interrupt.interruptid.toDouble())
                row.createCell(1).setCellValue(interrupt.interruptdate ?: "N/A")
                row.createCell(2).setCellValue(interrupt.interrupttime ?: "N/A")
                row.createCell(3).setCellValue(interrupt.interruptstatus)
                row.createCell(4).setCellValue(interrupt.serverid.servername ?: "N/A")
            }

            saveWorkbookToFile(workbook, "InterruptData")
        } else {
            showToast("No data to export")
        }
    }

    fun exportHealthDataToXls(recyclerViewDetail: RecyclerView) {
        val healthDataAdapter = recyclerViewDetail.adapter as? HealthDataRecyclerAdapter
        val healthDataList = healthDataAdapter?.getHealthdataList()

        if (healthDataList != null && healthDataList.isNotEmpty()) {
            val workbook = WorkbookFactory.create(true)
            val sheet = workbook.createSheet("HealthData")

            createHeaders(
                sheet,
                arrayOf(
                    "Health ID", "Date", "CPU Usage", "RAM Usage", "Storage Usage",
                    "Server Temp", "Ambient Temp", "Energy Usage", "Heartbeat", "Server Name"
                )
            )

            addDataToSheet(healthDataList, sheet) { healthData, row ->
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

            saveWorkbookToFile(workbook, "HealthData")
        } else {
            showToast("No data to export")
        }
    }

    private fun createHeaders(sheet: org.apache.poi.ss.usermodel.Sheet, headers: Array<String>) {
        val headerRow = sheet.createRow(0)
        headers.forEachIndexed { index, header ->
            headerRow.createCell(index).setCellValue(header)
        }
    }

    private fun <T> addDataToSheet(dataList: List<T>, sheet: org.apache.poi.ss.usermodel.Sheet, addData: (T, org.apache.poi.ss.usermodel.Row) -> Unit) {
        for ((index, data) in dataList.withIndex()) {
            val row = sheet.createRow(index + 1)
            addData(data, row)
        }
    }

    private fun saveWorkbookToFile(workbook: org.apache.poi.ss.usermodel.Workbook, fileNamePrefix: String) {
        val xlsFileName = "$fileNamePrefix${System.currentTimeMillis()}.xls"
        val xlsFilePath = File(
            ContextCompat.getExternalFilesDirs(context, Environment.DIRECTORY_DOWNLOADS)[0],
            xlsFileName
        ).absolutePath

        try {
            FileOutputStream(xlsFilePath).use { fileOut ->
                workbook.write(fileOut)
            }
            showToast("Data exported to $xlsFilePath")
        } catch (e: IOException) {
            e.printStackTrace()
            showToast("Error exporting data: ${e.message}")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}