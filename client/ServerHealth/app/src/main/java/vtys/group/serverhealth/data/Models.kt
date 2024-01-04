data class ServerDataModel(
    val serverId: Int,
    val serverName: String,
    val serverIp: String,
    val serverOs: String,
    val serverRam: Int,
    val serverStorageType: Int,
    val serverStorageCapacity: Int,
    val hospitalId: HospitalDataModel
)

data class HospitalDataModel(
    val hospitalId: Int,
    val hospitalName: String,
    val cityId: CityDataModel
)

data class CityDataModel(
    val cityId: Int,
    val cityName: String
)