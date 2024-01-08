package vtys.group.serverhealth.service.impl

import vtys.group.serverhealth.model.HealthDataModel
import vtys.group.serverhealth.service.HealthDataAPI

class HealthDataAPIService {

    private val retrofitService: RetrofitService = RetrofitService()
    val retrofit = retrofitService.getRetrofit()

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