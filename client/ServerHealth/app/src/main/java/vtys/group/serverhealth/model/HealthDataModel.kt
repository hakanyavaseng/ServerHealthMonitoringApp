package vtys.group.serverhealth.model

import com.google.gson.annotations.SerializedName
import vtys.group.serverhealth.data.ServerDataModel

// Import ServerDataModel if it's in a different package
data class HealthDataModel(
    @SerializedName("dataid")
    val dataId: Int,
    @SerializedName("datadatetime")
    val dataDateTime: String,
    @SerializedName("datacpuusage")
    val dataCpuUsage: Int,
    @SerializedName("dataramusage")
    val dataRamUsage: Int,
    @SerializedName("datastorageusage")
    val dataStorageUsage: Int,
    @SerializedName("dataservertemp")
    val dataServerTemp: Int,
    @SerializedName("dataambienttemp")
    val dataAmbientTemp: Int,
    @SerializedName("dataenergyusage")
    val dataEnergyUsage: Int,
    @SerializedName("dataheartbeat")
    val dataHeartBeat: Boolean,
    @SerializedName("serverid")
    val serverId: ServerDataModel
)