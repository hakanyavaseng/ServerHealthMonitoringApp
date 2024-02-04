package vtys.group.serverhealth.service.impl

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import vtys.group.serverhealth.model.CityDataModel
import vtys.group.serverhealth.model.HospitalDataModel
import vtys.group.serverhealth.model.ServerDataModelWithIntHospitalId

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
