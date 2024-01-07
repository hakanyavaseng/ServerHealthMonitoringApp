package vtys.group.serverhealth.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService{
    @POST("/api/auth/register")
    fun registerUser(@Body registrationData: RegistrationData): Call<Void>

    @POST("/api/auth/verify")
    fun verifyUser(@Body verificationData: VerificationData): Call<Void>

    @POST("/api/auth/login")
    fun loginUser(@Body loginData: LoginData): Call<Void>

    @POST("/api/auth/googlelogin")
    fun googleLogin(@Body googleToken : GoogleToken): Call<Void>

}

data class RegistrationData(
    val username: String,
    val password: String,
    val useremail: String
)

data class VerificationData(
    val username: String,
    val verificationCode: String
)

data class LoginData(
    val username: String,
    val password: String
)
data class GoogleToken(
    val idToken: String
)