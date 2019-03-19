//package trycatch.hs.hansungadress.ui.login;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.Html;
//import android.text.TextWatcher;
//import android.view.KeyEvent;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import trycatch.hs.hansungadress.R;
//import trycatch.hs.hansungadress.event.LoginEvent;
//import trycatch.hs.hansungadress.ui.MainActivity;
//import trycatch.hs.hansungadress.util.ApiManager;
//import trycatch.hs.hansungadress.util.Util;
//
//public class LoginFragment extends Fragment {
//    @BindView(R.id.et_id)
//    EditText et_id;
//
//    @BindView(R.id.et_pw)
//    EditText et_pw;
//
//    @BindView(R.id.dummy)
//    EditText dummy;
//
//    /*@BindView(R.id.user_permission_btn)
//    Button user_permission_btn;*/
//
//    @BindView(R.id.start_btn)
//    Button start_btn;
//
//    @BindView(R.id.guide_msg_agree)
//    CheckBox guide_msg_agree;
//
//    @BindView(R.id.guide_msg)
//    TextView guide_msg;
//
//    /*private int PERMISSION_CODE = 100;
//    private boolean isPermissionAgree = false;*/
//    private Util util;
//    private InputMethodManager imm;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_login);
//
//        ButterKnife.bind(this);
//
//        util = Util.getInstance(getApplicationContext());
//
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//        if(!util.getCookie().equals("")){
//            //ApiManager.getInstance(getApplicationContext()).login(util.getId(), util.getPw());
//            startActivity(new Intent(LoginFragment.this, MainActivity.class));
//            finish();
//        }
//
//        et_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    changeColor(et_id, false);
//                    //checkField();
//                }
//                else{
//                    if(!et_id.getText().toString().equals("")){
//                        changeColor(et_id, true);
//                        //checkField();
//                    }
//                }
//            }
//        });
//
//        et_id.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                switch (actionId) {
//                    case EditorInfo.IME_ACTION_NEXT:
//                        if(!et_id.getText().toString().equals("")) {
//                            changeColor(et_id, true);
//                        }
//                        et_pw.requestFocus();
//                        //checkField();
//                        break;
//                    default:
//                        // 기본 엔터키 동작
//                        return false;
//                }
//                return true;
//            }
//        });
//
//        et_id.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
//                if(et_id.getText().toString().equals("")){
//                    changeColor(et_id, false);
//                    //checkField();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        et_pw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    changeColor(et_pw, false);
//                    //checkField();
//                }
//                else{
//                    if(!et_pw.getText().toString().equals("")){
//                        changeColor(et_pw, true);
//                        //checkField();
//                    }
//                }
//            }
//        });
//
//        et_pw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                switch (actionId) {
//                    case EditorInfo.IME_ACTION_DONE:
//                        if(!et_pw.getText().toString().equals("")) {
//                            changeColor(et_pw, true);
//                        }
//                        else{
//                            changeColor(et_pw, false);
//                        }
//                        //checkField();
//                        dummy.requestFocus();
//                        imm.hideSoftInputFromWindow(et_pw.getWindowToken(), 0);
//                        break;
//                    default:
//                        // 기본 엔터키 동작
//                        return false;
//                }
//                return true;
//            }
//        });
//
//        et_pw.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
//                if(et_pw.getText().toString().equals("")){
//                    changeColor(et_pw, false);
//                    //checkField();
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        /*user_permission_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivityForResult(new Intent(LoginFragment.this, PermissionActivity.class), PERMISSION_CODE);
//            }
//        });*/
//
//        start_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id = et_id.getText().toString();
//                String pw = et_pw.getText().toString();
//                util.setId(id);
//                util.setPw(pw);
//
//                ApiManager.getInstance(getApplicationContext()).login(id, pw);
//            }
//        });
//
//        guide_msg_agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                dummy.requestFocus();
//                imm.hideSoftInputFromWindow(et_pw.getWindowToken(), 0);
//                changeStartBtn(b);
//            }
//        });
//
//        StringBuilder msg = new StringBuilder();
//        msg.append("한성대학교 교직원 연락처를 조회할 수 있습니다.<br>업무적인 용도 외에 사용을 금합니다.<br>")
//            .append("<font color=#000000><strong>타인의 개인정보 </strong></font><font color=#ff0000>유출 시</font><font color=#000000><strong> 개인정보 보호법에 따라<br>처벌</strong></font>")
//                .append(" 을 받을 수 있음을 알려드립니다.");
//        guide_msg.setText(Html.fromHtml(msg.toString()));
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
//    /*@Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == PERMISSION_CODE){
//            if(resultCode == Activity.RESULT_OK){
//                user_permission_btn.setBackgroundColor(getResources().getColor(R.color.sky_blue));
//                user_permission_btn.setTextColor(getResources().getColor(R.color.white));
//                isPermissionAgree = data.getBooleanExtra("isAgree", false);
//                dummy.requestFocus();
//            }
//        }
//
//        checkField();
//    }*/
//
//    /*public void checkField(){
//        if(!et_id.getText().toString().equals("")) {
//            if (!et_pw.getText().toString().equals("")) {
//                *//*if (isPermissionAgree) {
//                    changeStartBtn(true);
//                }
//                else{
//                    changeStartBtn(false);
//                }*//*
//                changeStartBtn(true);
//            }
//            else {
//                changeStartBtn(false);
//            }
//        }
//        else {
//            changeStartBtn(false);
//        }
//    }*/
//
//    public void changeStartBtn(boolean flag){
//        if(flag){
//            start_btn.setEnabled(true);
//            start_btn.setBackgroundColor(getResources().getColor(R.color.sky_blue));
//            start_btn.setText(getString(R.string.start_msg));
//        }
//        else{
//            start_btn.setEnabled(false);
//            start_btn.setBackgroundColor(getResources().getColor(R.color.gray));
//            start_btn.setText("");
//        }
//    }
//
//    public void changeColor(EditText view, boolean flag){
//        if(flag){
//            view.setBackgroundResource(R.drawable.skyblue_selector);
//            view.setTextColor(Color.parseColor("#ffffff"));
//        }
//        else{
//            view.setBackgroundResource(R.drawable.gray_selector);
//            view.setTextColor(Color.parseColor("#000000"));
//        }
//    }
//
//    @Subscribe
//    public void loginEvent(LoginEvent loginEvent){
//        if(loginEvent.isLogin()) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
//        else {
//            Toast.makeText(this, "아이디와 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
//        }
//    }
//}
