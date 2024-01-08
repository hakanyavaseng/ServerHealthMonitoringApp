package vtys.group.serverhealth.fileops

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.opencsv.CSVReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vtys.group.serverhealth.model.CityDataModel
import vtys.group.serverhealth.model.HospitalDataModel
import vtys.group.serverhealth.model.ServerDataModelWithIntHospitalId
import vtys.group.serverhealth.service.impl.CreateService
import java.io.InputStreamReader

class ServerDataManager(
    private val createService: CreateService,
    private val context: Context
) {

    suspend fun addServer(
        serverName: String,
        serverIP: String,
        selectedOS: String,
        serverRAM: String,
        selectedStorageType: String,
        serverStorageCapacity: String,
        selectedHospitalId: Int
    ) {
        val serverData = ServerDataModelWithIntHospitalId(
            0,
            serverName,
            serverIP,
            selectedOS,
            serverRAM,
            selectedStorageType,
            serverStorageCapacity,
            selectedHospitalId
        )

        try {
            val response = createService.addServer(serverData).execute()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Server added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Server could not be added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
        }
    }

    suspend fun addHospital(
        hospitalName: String,
        cityId: CityDataModel
    ) {
        val hospitalData = HospitalDataModel(
            0,
            hospitalName,
            cityId
        )

        try {
            val response = createService.addHospital(hospitalData).execute()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Hospital added successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Hospital could not be added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
        }
    }

    suspend fun importServersFromCSV(fileUri: Uri) {
        try {
            val inputStream = context.contentResolver.openInputStream(fileUri)
            val reader = CSVReader(InputStreamReader(inputStream))

            // Skip the header row if your CSV file has one
            reader.readNext()

            // Iterate through rows
            var nextLine: Array<String>?
            while (reader.readNext().also { nextLine = it } != null) {
                // Process each column in the row
                val serverName = nextLine?.get(0)
                val serverIP = nextLine?.get(1)
                val serverOS = nextLine?.get(2)
                val serverRAM = nextLine?.get(3)?.toIntOrNull()
                val serverStorageType = nextLine?.get(4)
                val serverStorageCapacity = nextLine?.get(5)?.toIntOrNull()
                val hospitalID = nextLine?.get(6)?.toIntOrNull()

                addServer(
                    serverName ?: "",
                    serverIP ?: "",
                    serverOS ?: "",
                    serverRAM?.toString() ?: "",
                    serverStorageType ?: "",
                    serverStorageCapacity?.toString() ?: "",
                    hospitalID ?: 0
                )
            }

            // Close the CSV reader
            reader.close()
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Error reading the CSV file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    suspend fun importHospitalsFromCSV(fileUri: Uri) {
        try {
            val inputStream = context.contentResolver.openInputStream(fileUri)
            val reader = CSVReader(InputStreamReader(inputStream))

            // Skip the header row if your CSV file has one
            reader.readNext()

            // Iterate through rows
            var nextLine: Array<String>?
            while (reader.readNext().also { nextLine = it } != null) {
                // Process each column in the row
                val hospitalName = nextLine?.get(0)
                val cityIdString = nextLine?.get(1)

                if (hospitalName != null && cityIdString != null) {
                    // Convert the city ID from String to Int
                    val cityId = cityIdString.toIntOrNull()

                    if (cityId != null) {
                        addHospital(hospitalName, CityDataModel(cityId, ""))
                    }
                }
            }

            // Close the CSV reader
            reader.close()
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Error reading the CSV file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val PICK_FILE_REQUEST_CODE = 1

        fun openFilePicker(activity: Activity) {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"  // Set the MIME type based on the type of files you want to pick
            activity.startActivityForResult(intent, PICK_FILE_REQUEST_CODE)
        }
    }
}