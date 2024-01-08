package vtys.group.serverhealth.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://serverhealth.azurewebsites.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit;
    }

}