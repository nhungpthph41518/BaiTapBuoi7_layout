package and103.ph41518.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.checkerframework.checker.units.qual.C;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView lvMain;
    List<SanphamModel> sanphamModels;
    AdapterSanpham adapterSanpham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = findViewById(R.id.lvDanhSach);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<List<SanphamModel>> call = apiService.getSanphams();

        call.enqueue(new Callback<List<SanphamModel>>() {
            @Override
            public void onResponse(Call<List<SanphamModel>> call, Response<List<SanphamModel>> response) {
                if (response.isSuccessful()) {
                    sanphamModels = response.body();

                    adapterSanpham = new AdapterSanpham(getApplicationContext(), sanphamModels);

                    lvMain.setAdapter(adapterSanpham);
                }
            }

            @Override
            public void onFailure(Call<List<SanphamModel>> call, Throwable t) {
                Log.e("Main", t.getMessage());
            }
        });
    }
}