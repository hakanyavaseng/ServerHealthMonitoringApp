package vtys.group.serverhealth.viewmodel

import android.util.Log
import androidx.annotation.ReturnThis
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vtys.group.serverhealth.data.InterruptDataModel
import vtys.group.serverhealth.model.HealthDataModel
import vtys.group.serverhealth.service.HealthDataAPI
import vtys.group.serverhealth.service.HealthDataAPIService
import vtys.group.serverhealth.service.InterruptDataAPIService

private val Any.isSuccessful: Boolean
    get() {
        return true
    }

class ServerDetailViewModel : ViewModel() {

    val healthData = MutableLiveData<ArrayList<HealthDataModel>>()
    val healthDataError = MutableLiveData<Boolean>()
    val healthDataLoading = MutableLiveData<Boolean>()

    val interruptData = MutableLiveData<ArrayList<InterruptDataModel>>()
    val interruptDataError = MutableLiveData<Boolean>()
    val interruptDataLoading = MutableLiveData<Boolean>()

    private val healthDataAPIService = HealthDataAPIService()
    private val interruptDataAPIService = InterruptDataAPIService()


    fun refreshDataMonth(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataMonth(serverId).enqueue(object : Callback<ArrayList<HealthDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<HealthDataModel>>,
                response: Response<ArrayList<HealthDataModel>>
            ) {
                if (response.isSuccessful) {
                    healthData.value = response.body()
                    healthDataError.value = false
                    healthDataLoading.value = false
                } else {
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                healthDataError.value = true
                healthDataLoading.value = false
            }
        })
    }
    fun refreshDataWeek(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataWeek(serverId).enqueue(object : Callback<ArrayList<HealthDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<HealthDataModel>>,
                response: Response<ArrayList<HealthDataModel>>
            ) {
                if (response.isSuccessful) {
                    healthData.value = response.body()
                    healthDataError.value = false
                    healthDataLoading.value = false
                } else {
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                healthDataError.value = true
                healthDataLoading.value = false
            }
        })
    }
    fun refreshDataYear(serverId: Int) {
        healthDataLoading.value = true
        healthDataAPIService.healthDataYear(serverId).enqueue(object : Callback<ArrayList<HealthDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<HealthDataModel>>,
                response: Response<ArrayList<HealthDataModel>>
            ) {
                if (response.isSuccessful) {
                    healthData.value = response.body()
                    healthDataError.value = false
                    healthDataLoading.value = false
                } else {
                    healthDataError.value = true
                    healthDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<HealthDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                healthDataError.value = true
                healthDataLoading.value = false
            }
        })
    }

    fun refreshInterruptMonth(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataMonth(serverId).enqueue(object : Callback<ArrayList<InterruptDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<InterruptDataModel>>,
                response: Response<ArrayList<InterruptDataModel>>
            ) {
                if (response.isSuccessful) {
                    interruptData.value = response.body()
                    interruptDataError.value = false
                    interruptDataLoading.value = false
                } else {
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                interruptDataError.value = true
                interruptDataLoading.value = false
            }
        })
    }
    fun refreshInterruptWeek(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataWeek(serverId).enqueue(object : Callback<ArrayList<InterruptDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<InterruptDataModel>>,
                response: Response<ArrayList<InterruptDataModel>>
            ) {
                if (response.isSuccessful) {
                    interruptData.value = response.body()
                    interruptDataError.value = false
                    interruptDataLoading.value = false
                } else {
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                interruptDataError.value = true
                interruptDataLoading.value = false
            }
        })
    }
    fun refreshInterruptYear(serverId: Int) {
        interruptDataLoading.value = true
        interruptDataAPIService.interruptDataYear(serverId).enqueue(object : Callback<ArrayList<InterruptDataModel>> {
            override fun onResponse(
                call: Call<ArrayList<InterruptDataModel>>,
                response: Response<ArrayList<InterruptDataModel>>
            ) {
                if (response.isSuccessful) {
                    interruptData.value = response.body()
                    interruptDataError.value = false
                    interruptDataLoading.value = false
                } else {
                    interruptDataError.value = true
                    interruptDataLoading.value = false
                }
            }

            override fun onFailure(call: Call<ArrayList<InterruptDataModel>>, t: Throwable) {
                Log.e("ServerDetailViewModel", t.message.toString())
                interruptDataError.value = true
                interruptDataLoading.value = false
            }
        })
    }

}

