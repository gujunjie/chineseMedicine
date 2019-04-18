package view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abc.chinesemedicine.R;
import com.gyf.barlibrary.ImmersionBar;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImmersionBar.with(this).init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
