import com.example.barber.Login.Customer
import com.example.barber.Login.LoginReq
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CustomerAPI {
    @GET("allcus")
    fun retrieveCustomer(): Call<List<Customer>>

    @FormUrlEncoded
    @POST("signup")
    fun signup(
        @Field("Cus_Username") cus_username: String,
        @Field("Cus_Password") cus_password: String,
        @Field("Cus_Email") cus_email: String,
        @Field("Cus_Name") cus_name: String,
        @Field("Cus_Age") cus_age: Int,
        @Field("Cus_Gender") cus_gender: String,
        @Field("Cus_Tel") cus_tel: String,
    ): Call<Customer>

    @POST("login")
    fun CusLogin(
        @Body loginReq: LoginReq
    ): Call<Customer>


    companion object{
        fun create(): CustomerAPI{
            val cusClient: CustomerAPI = Retrofit.Builder()
                .baseUrl("http://192.168.1.39:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CustomerAPI::class.java)
            return cusClient
        }
        fun getRetrofit(): Retrofit {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.39:3000/")  // nodeJs 10.0.2.2:3000
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit
        }
        fun getUserService(): CustomerAPI {
            val userService: CustomerAPI = getRetrofit().create(CustomerAPI::class.java)
            return userService
        }
    }

}