package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.PointerIcon;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.R;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.gyf.barlibrary.ImmersionBar;

import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Prescription;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChineseMedicineDataActivity extends AppCompatActivity {

    @BindView(R.id.iv_medicinePhoto)
    ImageView ivMedicinePhoto;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tv_medicineData)
    TextView tvMedicineData;
    @BindView(R.id.fam_menu)
    FloatingActionsMenu famMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_medicine_data);
        ButterKnife.bind(this);

        ImmersionBar.with(this).init();

        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();


    }

    public void initView()
    {
        String sortType=getIntent().getStringExtra("sortType");
        switch (sortType)
        {
            case "chineseMedicine":
                ChineseMedicine medicine = getIntent().getParcelableExtra("object");
                collapsingToolbar.setTitle(medicine.getName());
                Glide.with(this).load(medicine.getMedicineImageUrl()).into(ivMedicinePhoto);
                tvMedicineData.setText(medicine.getData());
                break;
            case "chinesePatentDrug":
                ChinesePatentDrug drug=getIntent().getParcelableExtra("object");
                collapsingToolbar.setTitle(drug.getName());
                Glide.with(this).load(drug.getImageUrl()).into(ivMedicinePhoto);
                tvMedicineData.setText(drug.getData());
                break;
            case "acuPoint":
                AcuPoint point=getIntent().getParcelableExtra("object");
                collapsingToolbar.setTitle(point.getName());
                Glide.with(this).load(point.getImageUrl()).into(ivMedicinePhoto);
                tvMedicineData.setText(point.getData());
                break;

            case "prescription":
                Prescription prescription=getIntent().getParcelableExtra("object");
                collapsingToolbar.setTitle(prescription.getName());
                Glide.with(this).load(prescription.getImageUrl()).into(ivMedicinePhoto);
                tvMedicineData.setText(prescription.getData());
                break;


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.fab_huge, R.id.fab_large, R.id.fab_normal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab_huge:
                tvMedicineData.setTextSize(21);
                break;
            case R.id.fab_large:
                tvMedicineData.setTextSize(19);
                break;
            case R.id.fab_normal:
                tvMedicineData.setTextSize(17);
                break;
        }
    }
}
