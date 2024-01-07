package vtys.group.serverhealth.create

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import vtys.group.serverhealth.data.CityDataModel
import vtys.group.serverhealth.data.HospitalDataModel
import vtys.group.serverhealth.data.ServerDataModel
import vtys.group.serverhealth.data.ServerDataModelWithIntHospitalId

interface CreateService {

    @POST("/api/servers/create")
    fun addServer(@Body serverData: ServerDataModelWithIntHospitalId): Call<Void>

    @POST("/hospitals/create")
    fun addHospital(@Body hospitalData: HospitalDataModel): Call<Void>

    @GET("/hospitals/getall")
    fun getHospitals(): Call<List<HospitalDataModel>>

    @GET("/locations/getall")
    fun getCities(): Call<List<CityDataModel>>
}
