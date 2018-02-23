package trycatch.hs.hansungadress.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import trycatch.hs.hansungadress.R;

/**
 * Created by trycatch on 2017. 11. 27..
 */

public class PermissionActivity extends AppCompatActivity {
    @BindView(R.id.agree_btn)
    Button agree_btn;

    @BindView(R.id.refuse_btn)
    Button refuse_btn;

    @BindView(R.id.personal)
    TextView personal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        ButterKnife.bind(this);

        agree_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("isAgree", true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        refuse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://www.hansung.ac.kr/web/www/personal");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
