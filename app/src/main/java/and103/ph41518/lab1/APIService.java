package and103.ph41518.lab1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    String DOMAIN = "http://10.82.0.103:3000/";

    @GET("/api/list")
    Call<List<SanphamModel>> getSanphams();

    @POST("/api/addsp")
    Call<SanphamModel> addSanpham();
}
