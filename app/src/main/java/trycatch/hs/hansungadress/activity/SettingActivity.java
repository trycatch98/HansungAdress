package trycatch.hs.hansungadress.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import trycatch.hs.hansungadress.R;
import trycatch.hs.hansungadress.util.Util;

/**
 * Created by trycatch on 2018. 1. 12..
 */

public class SettingActivity extends AppCompatActivity {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.logout)
    TextView logout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.sky_blue));
        }

        ButterKnife.bind(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.getInstance(getApplicationContext()).clearCookie();
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                finishAffinity();
            }
        });
    }
}
