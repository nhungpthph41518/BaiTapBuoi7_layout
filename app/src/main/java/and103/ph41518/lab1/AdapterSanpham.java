package and103.ph41518.lab1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterSanpham extends BaseAdapter {

    Context context;
    List<SanphamModel> sanphamModels;
    APIService apiService;
    MainActivity main;

    public AdapterSanpham(Context context, List<SanphamModel> sanphamModels, MainActivity main) {
        this.context = context;
        this.sanphamModels = sanphamModels;
        this.main = main;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(APIService.class);

    }

    @Override
    public int getCount() {
        return sanphamModels.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    private OnDeleteClickListener onDeleteClickListener;

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_sanpham, viewGroup, false);

        TextView tvID = (TextView) rowView.findViewById(R.id.tvId);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatatr);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);

        TextView tvSoluong = (TextView) rowView.findViewById(R.id.tvSoluong);

        TextView tvGia = (TextView) rowView.findViewById(R.id.tvGia);

        Button btnXoa = rowView.findViewById(R.id.btnXoa);
        Button btnChinhSua = rowView.findViewById(R.id.btnChinhSua);

//        sửa
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChinhSua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        main.openUpdateDialog(sanphamModels.get(position));
                    }
                });
            }
        });


//        xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            }
        });


//        String imageUrl = mList.get(position).getThumbnailUrl();
//        Picasso.get().load(imageUrl).into(imgAvatar);
////        imgAvatar.setImageResource(imageId[position]);
        tvName.setText(String.valueOf(sanphamModels.get(position).getTen()));

        tvSoluong.setText(String.valueOf(sanphamModels.get(position).getSoluong()));


        tvGia.setText(String.valueOf(sanphamModels.get(position).getGia()));

        return rowView;
    }
}
