package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnlargePhotoActivity extends AppCompatActivity {

        @BindView(R.id.photoView)
        PhotoView photoView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_enlarge_photo);
            ButterKnife.bind(this);
            Glide.with(this).load(getIntent().getStringExtra("imageUrl")).crossFade(500).into(photoView);
            ImmersionBar.with(this).init();
        }

    @OnClick(R.id.photoView)
    public void onViewClicked() {
        finish();
    }
}
