//package trycatch.hs.hansungadress.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import trycatch.hs.hansungadress.R;
//import trycatch.hs.hansungadress.model.AddressModel;
//
///**
// * Created by trycatch on 2017. 12. 2..
// */
//
//public class FavoriteAdapter extends BaseAdapter {
//    private Context mContext;
//    private LayoutInflater inflater;
//    private ArrayList<AddressModel> items;
//
//    public FavoriteAdapter(Context mContext, ArrayList<AddressModel> items) {
//        this.mContext = mContext;
//        this.items = items;
//        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @Override
//    public int getCount() {
//        return items.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return items.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    public void setItems(ArrayList<AddressModel> items) {
//        this.items = items;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public View getView(int i, View convertView, ViewGroup viewGroup) {
//        View view = null;
//        ViewHolder viewHolder = null;
//
//        if(convertView == null){
//            view = inflater.inflate(R.layout.search_item, null);
//            viewHolder = new ViewHolder(view);
//        }
//        else{
//            view = convertView;
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        AddressModel item = items.get(i);
//
//        viewHolder.search_item_department.setText(item.getDepartment());
//        viewHolder.search_item_position.setText(item.getPosition());
//        viewHolder.search_item_name.setText(item.getName());
//        viewHolder.search_item_phone.setText(item.getPhone());
//        if(item.isFavorite())
//            Glide.with(mContext).load(R.drawable.favorite_on).into(viewHolder.search_item_favorite);
//
//        view.setTag(viewHolder);
//
//        return view;
//    }
//
//
//    class ViewHolder {
//        @BindView(R.id.search_item_department)
//        TextView search_item_department;
//
//        @BindView(R.id.search_item_position)
//        TextView search_item_position;
//
//        @BindView(R.id.search_item_name)
//        TextView search_item_name;
//
//        @BindView(R.id.search_item_phone)
//        TextView search_item_phone;
//
//        @BindView(R.id.search_item_favorite)
//        ImageView search_item_favorite;
//
//        public ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//}
