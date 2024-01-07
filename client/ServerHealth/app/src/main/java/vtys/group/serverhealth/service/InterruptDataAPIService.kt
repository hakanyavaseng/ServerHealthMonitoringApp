package vtys.group.serverhealth.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vtys.group.serverhealth.data.InterruptDataModel
import vtys.group.serverhealth.model.HealthDataModel

class InterruptDataAPIService {

    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://serverhealth.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val interruptDataAPI = retrofit.create(InterruptDataAPI::class.java)

    fun interruptDataYear(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return interruptDataAPI.healthDataYear(serverId)

    }
    fun interruptDataMonth(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return  interruptDataAPI.healthDataMonth(serverId)

    }
    fun interruptDataWeek(serverId: Int): retrofit2.Call<ArrayList<InterruptDataModel>> {
        return  interruptDataAPI.healthDataWeek(serverId)
    }

}