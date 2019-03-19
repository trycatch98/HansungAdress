//package trycatch.hs.hansungadress.ui;
//
//import android.content.ClipData;
//import android.content.ClipboardManager;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import androidx.annotation.Nullable;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import trycatch.hs.hansungadress.R;
//import trycatch.hs.hansungadress.activity.SettingActivity;
//import trycatch.hs.hansungadress.adapter.FavoriteAdapter;
//import trycatch.hs.hansungadress.adapter.SearchAdapter;
//import trycatch.hs.hansungadress.event.SearchEvent;
//import trycatch.hs.hansungadress.model.AddressModel;
//import trycatch.hs.hansungadress.model.FavoriteModel;
//import trycatch.hs.hansungadress.util.ApiManager;
//import trycatch.hs.hansungadress.util.Util;
//
///**
// * Created by trycatch on 2017. 11. 30..
// */
//
//public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.setting)
//    ImageView setting;
//
//    @BindView(R.id.et_search)
//    EditText et_search;
//
//    @BindView(R.id.clear_btn)
//    ImageView clear_btn;
//
//    @BindView(R.id.search_cancel_btn)
//    Button search_cancel_btn;
//
//    @BindView(R.id.search_layout)
//    LinearLayout search_layout;
//
//    @BindView(R.id.favorite_layout)
//    LinearLayout favorite_layout;
//
//    @BindView(R.id.search_result_layout)
//    LinearLayout search_result_layout;
//
//    @BindView(R.id.number_of_result)
//    TextView number_of_result;
//
//    @BindView(R.id.search_result_list)
//    ListView search_result_list;
//
//    @BindView(R.id.favorite_list)
//    ListView favorite_list;
//
//    @BindView(R.id.no_favorite)
//    TextView no_favorite;
//
//    private SearchAdapter searchAdapter;
//    private FavoriteAdapter favoriteAdapter;
//    private ArrayList<AddressModel> items;
//    private FavoriteModel favoriteItems;
//    private InputMethodManager imm;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ButterKnife.bind(this);
//
//        Locale.setDefault(Locale.KOREA);
//
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        Window window = getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(getResources().getColor(R.color.sky_blue));
//        }
//
//        setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, SettingActivity.class));
//            }
//        });
//
//        favoriteItems = Util.getInstance(getApplicationContext()).getFavorite();
//        favoriteAdapter = new FavoriteAdapter(getApplicationContext(), favoriteItems.getAddressModels());
//        favorite_list.setAdapter(favoriteAdapter);
//        favorite_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
//                View dialogView = getLayoutInflater().inflate(R.layout.address_info_dialog, null);
//                ImageView address_img = (ImageView) dialogView.findViewById(R.id.address_img);
//                TextView address_name = (TextView) dialogView.findViewById(R.id.address_name);
//                TextView address_department = (TextView) dialogView.findViewById(R.id.address_department);
//                TextView address_position = (TextView) dialogView.findViewById(R.id.address_position);
//                TextView office_number = (TextView) dialogView.findViewById(R.id.office_number);
//                TextView office_number_copy = (TextView) dialogView.findViewById(R.id.office_number_copy);
//                TextView phone_number = (TextView) dialogView.findViewById(R.id.phone_number);
//                TextView phone_number_copy = (TextView) dialogView.findViewById(R.id.phone_number_copy);
//                TextView email = (TextView) dialogView.findViewById(R.id.email);
//                TextView cancel_btn = (TextView) dialogView.findViewById(R.id.cancel_btn);
//                final ImageView address_favorite = (ImageView) dialogView.findViewById(R.id.address_favorite);
//
//                final AddressModel item = favoriteItems.getAddressModels().get(i);
//
//                Glide.with(getApplicationContext()).load("https://smart.hansung.ac.kr/profile_image?u=" + item.getPhoto().substring(item.getPhoto().length() - 6, item.getPhoto().length())).into(address_img);
//                address_name.setText(item.getName());
//                address_department.setText(item.getDepartment());
//                address_position.setText(item.getPosition());
//                office_number.setText(item.getOffice() + " " + getString(R.string.call));
//                phone_number.setText(item.getPhone() + " " + getString(R.string.call));
//                email.setText(item.getEmail() + " " + getString(R.string.email));
//                cancel_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                if(item.isFavorite())
//                    Glide.with(getApplicationContext()).load(R.drawable.favorite_on).into(address_favorite);
//
//                address_favorite.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(!item.isFavorite()) {
//                            Glide.with(getApplicationContext()).load(R.drawable.favorite_on).into(address_favorite);
//                            item.setFavorite(true);
//                            favoriteItems.addAddress(item);
//                            favoriteAdapter.setItems(favoriteItems.getAddressModels());
//                            searchAdapter.notifyDataSetChanged();
//                        }
//                        else{
//                            Glide.with(getApplicationContext()).load(R.drawable.favorite_off).into(address_favorite);
//                            item.setFavorite(false);
//                            favoriteItems.getAddressModels().remove(item);
//                            favoriteAdapter.setItems(favoriteItems.getAddressModels());
//                            searchAdapter.notifyDataSetChanged();
//                        }
//                        if(favoriteItems.getAddressModels().size() > 0){
//                            no_favorite.setVisibility(View.INVISIBLE);
//                            favorite_list.setVisibility(View.VISIBLE);
//                        }
//                        else{
//                            no_favorite.setVisibility(View.VISIBLE);
//                            favorite_list.setVisibility(View.INVISIBLE);
//                        }
//                        Util.getInstance(getApplicationContext()).setFavorite(favoriteItems);
//                    }
//                });
//
//                office_number.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getOffice(), null)));
//                    }
//                });
//
//                office_number_copy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clipData = ClipData.newPlainText("office", item.getOffice());
//                        clipboardManager.setPrimaryClip(clipData);
//                        Toast.makeText(getApplicationContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                phone_number.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getPhone(), null)));
//                    }
//                });
//
//                phone_number_copy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clipData = ClipData.newPlainText("phone", item.getPhone());
//                        clipboardManager.setPrimaryClip(clipData);
//                        Toast.makeText(getApplicationContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                email.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{item.getEmail()});
//                        intent.setType("text/plain");
//                        startActivity(Intent.createChooser(intent, "Choose Email Client"));
//                    }
//                });
//
//                bottomSheetDialog.setContentView(dialogView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        if(favoriteItems.getAddressModels().size() > 0){
//            no_favorite.setVisibility(View.INVISIBLE);
//            favorite_list.setVisibility(View.VISIBLE);
//        }
//        else{
//            no_favorite.setVisibility(View.VISIBLE);
//            favorite_list.setVisibility(View.INVISIBLE);
//        }
//
//        searchAdapter = new SearchAdapter(getApplicationContext(), new ArrayList<AddressModel>());
//        search_result_list.setAdapter(searchAdapter);
//        search_result_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
//                View dialogView = getLayoutInflater().inflate(R.layout.address_info_dialog, null);
//                ImageView address_img = (ImageView) dialogView.findViewById(R.id.address_img);
//                TextView address_name = (TextView) dialogView.findViewById(R.id.address_name);
//                TextView address_department = (TextView) dialogView.findViewById(R.id.address_department);
//                TextView address_position = (TextView) dialogView.findViewById(R.id.address_position);
//                TextView office_number = (TextView) dialogView.findViewById(R.id.office_number);
//                TextView office_number_copy = (TextView) dialogView.findViewById(R.id.office_number_copy);
//                TextView phone_number = (TextView) dialogView.findViewById(R.id.phone_number);
//                TextView phone_number_copy = (TextView) dialogView.findViewById(R.id.phone_number_copy);
//                TextView email = (TextView) dialogView.findViewById(R.id.email);
//                TextView cancel_btn = (TextView) dialogView.findViewById(R.id.cancel_btn);
//                final ImageView address_favorite = (ImageView) dialogView.findViewById(R.id.address_favorite);
//
//                final AddressModel item = items.get(i);
//
//                Glide.with(getApplicationContext()).load("https://smart.hansung.ac.kr/profile_image?u=" + item.getPhoto().substring(item.getPhoto().length() - 6, item.getPhoto().length())).into(address_img);
//                address_name.setText(item.getName());
//                address_department.setText(item.getDepartment());
//                address_position.setText(item.getPosition());
//                office_number.setText(item.getOffice() + " " + getString(R.string.call));
//                phone_number.setText(item.getPhone() + " " + getString(R.string.call));
//                email.setText(item.getEmail() + " " + getString(R.string.email));
//                cancel_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                if(item.isFavorite())
//                    Glide.with(getApplicationContext()).load(R.drawable.favorite_on).into(address_favorite);
//
//                address_favorite.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(!item.isFavorite()) {
//                            Glide.with(getApplicationContext()).load(R.drawable.favorite_on).into(address_favorite);
//                            item.setFavorite(true);
//                            favoriteItems.addAddress(item);
//                            favoriteAdapter.setItems(favoriteItems.getAddressModels());
//                            searchAdapter.setItems(items);
//                        }
//                        else{
//                            Glide.with(getApplicationContext()).load(R.drawable.favorite_off).into(address_favorite);
//                            item.setFavorite(false);
//                            for(AddressModel model : favoriteItems.getAddressModels())
//                                if(model.getPhone().equals(item.getPhone()))
//                                    favoriteItems.getAddressModels().remove(model);
//                            favoriteAdapter.setItems(favoriteItems.getAddressModels());
//                            searchAdapter.setItems(items);
//                        }
//                        if(favoriteItems.getAddressModels().size() > 0){
//                            no_favorite.setVisibility(View.INVISIBLE);
//                            favorite_list.setVisibility(View.VISIBLE);
//                        }
//                        else{
//                            no_favorite.setVisibility(View.VISIBLE);
//                            favorite_list.setVisibility(View.INVISIBLE);
//                        }
//                        Util.getInstance(getApplicationContext()).setFavorite(favoriteItems);
//                    }
//                });
//
//                office_number.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getOffice(), null)));
//                    }
//                });
//
//                office_number_copy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clipData = ClipData.newPlainText("office", item.getOffice());
//                        clipboardManager.setPrimaryClip(clipData);
//                        Toast.makeText(getApplicationContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//                phone_number.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", item.getPhone(), null)));
//                    }
//                });
//
//                phone_number_copy.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
//                        ClipData clipData = ClipData.newPlainText("phone", item.getPhone());
//                        clipboardManager.setPrimaryClip(clipData);
//                        Toast.makeText(getApplicationContext(), "복사되었습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                email.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(Intent.ACTION_SEND);
//                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{item.getEmail()});
//                        intent.setType("text/plain");
//                        startActivity(Intent.createChooser(intent, "Choose Email Client"));
//                    }
//                });
//
//                bottomSheetDialog.setContentView(dialogView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        et_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    search_result_layout.setVisibility(View.VISIBLE);
//                    favorite_layout.setVisibility(View.INVISIBLE);
//                    search_layout.setBackgroundResource(R.drawable.white_radius);
//                    et_search.setTextColor(getResources().getColor(R.color.black));
//                    clear_btn.setVisibility(View.VISIBLE);
//                    search_cancel_btn.setVisibility(View.VISIBLE);
//                }
//                else{
//                    if(et_search.getText().toString().equals("")) {
//                        search_result_layout.setVisibility(View.INVISIBLE);
//                        favorite_layout.setVisibility(View.VISIBLE);
//                        search_layout.setBackgroundResource(R.drawable.gray_radius);
//                        clear_btn.setVisibility(View.INVISIBLE);
//                        search_cancel_btn.setVisibility(View.INVISIBLE);
//                    }
//                }
//            }
//        });
//
//        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                switch (actionId) {
//                    case EditorInfo.IME_ACTION_SEARCH:
//                        et_search.clearFocus();
//                        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);
//                        if(!et_search.getText().toString().equals("")){
//                            ApiManager.getInstance(getApplicationContext()).search(et_search.getText().toString());
//                            search_layout.setBackgroundResource(R.drawable.skyblue_radius);
//                            et_search.setTextColor(getResources().getColor(R.color.white));
//                            clear_btn.setVisibility(View.INVISIBLE);
//                        }
//                        break;
//                    default:
//                        // 기본 엔터키 동작
//                        return false;
//                }
//                return false;
//            }
//        });
//
//        search_cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                et_search.setText("");
//                et_search.clearFocus();
//                search_result_layout.setVisibility(View.INVISIBLE);
//                favorite_layout.setVisibility(View.VISIBLE);
//                search_layout.setBackgroundResource(R.drawable.gray_radius);
//                search_cancel_btn.setVisibility(View.INVISIBLE);
//                searchAdapter.setItems(new ArrayList<AddressModel>());
//                number_of_result.setText("0");
//            }
//        });
//
//        clear_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                et_search.setText("");
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe
//    public void SearchEvent(SearchEvent event){
//        if(event.isSuccess()){
//            items = event.getItems();
//            for(int i = 0; i < items.size(); i++){
//                for(AddressModel item: favoriteItems.getAddressModels()) {
//                    if (items.get(i).getPhone().equals(item.getPhone())){
//                        items.get(i).setFavorite(true);
//                    }
//                }
//            }
//            searchAdapter.setItems(items);
//            number_of_result.setText(items.size() + "");
//        }
//    }
//}
