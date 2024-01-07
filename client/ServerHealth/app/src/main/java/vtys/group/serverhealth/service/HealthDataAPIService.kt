package vtys.group.serverhealth.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.model.HealthDataModel

class HealthDataAPIService {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serverhealth.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val healthDataAPI = retrofit.create(HealthDataAPI::class.java)

    fun healthDataYear(serverId: Int): retrofit2.Call<ArrayList<HealthDataModel>> {
        return healthDataAPI.healthDataYear(serverId)
    }
    fun healthDataMonth(serverId: Int): retrofit2.Call<ArrayList<HealthDataModel>> {
        return healthDataAPI.healthDataMonth(serverId)
    }
    fun healthDataWeek(serverId: Int): retrofit2.Call<ArrayList<HealthDataModel>> {
        return healthDataAPI.healthDataWeek(serverId)
    }
    fun healthDataById(serverId: Int): retrofit2.Call<ArrayList<HealthDataModel>> {
        return healthDataAPI.healthDataById(serverId)
    }

}