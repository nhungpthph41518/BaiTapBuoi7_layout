package and103.ph41518.lab1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterSanpham extends BaseAdapter {

    Context context;
    List<SanphamModel> sanphamModels;

    public AdapterSanpham(Context context, List<SanphamModel> sanphamModels) {
        this.context = context;
        this.sanphamModels = sanphamModels;
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

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_sp, viewGroup, false);

        TextView tvID = (TextView) rowView.findViewById(R.id.tvId);
        ImageView imgAvatar = (ImageView) rowView.findViewById(R.id.imgAvatatr);
        TextView tvName = (TextView) rowView.findViewById(R.id.tvName);

        TextView tvSoluong = (TextView) rowView.findViewById(R.id.tvSoluong);

        TextView tvTonkho = (TextView) rowView.findViewById(R.id.tvTonkho);

        TextView tvGia = (TextView) rowView.findViewById(R.id.tvGia);

//        String imageUrl = mList.get(position).getThumbnailUrl();
//        Picasso.get().load(imageUrl).into(imgAvatar);
////        imgAvatar.setImageResource(imageId[position]);
        tvName.setText(String.valueOf(sanphamModels.get(position).getTen()));

        tvSoluong.setText(String.valueOf(sanphamModels.get(position).getSoluong()));

        tvTonkho.setText(String.valueOf(sanphamModels.get(position).isTonkho()));

        tvGia.setText(String.valueOf(sanphamModels.get(position).getGia()));

        return rowView;
    }
}
