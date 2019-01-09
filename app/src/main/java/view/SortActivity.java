package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.abc.chinesemedicine.R;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class SortActivity extends AppCompatActivity {

    private View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater  mInflater = LayoutInflater.from(this);


        String sortType = getIntent().getStringExtra("sortType");
        switch (sortType) {
            case "pingyin":
                contentView= mInflater.inflate(R.layout.activity_pingyinsort,null);
                setContentView(contentView);
                MyTitleBar pingyinTitlebar=contentView.findViewById(R.id.pingyin_titlebar);
                pingyinTitlebar.getActivityForFinish(this);
                break;
            case "chinesePatentDrugFirstCategory":
                contentView= mInflater.inflate(R.layout.activity_chinesepatentdrugfirstcategory,null);
                setContentView(contentView);
                MyTitleBar drugfirstcategoryTitlebar=contentView.findViewById(R.id.drugfirstcategory_titlebar);
                drugfirstcategoryTitlebar.getActivityForFinish(this);
                break;
            case "acuPoint":
                contentView= mInflater.inflate(R.layout.activity_acupoint,null);
                setContentView(contentView);
                MyTitleBar acuPointTitlebar=contentView.findViewById(R.id.acuPoint_titBar);
                acuPointTitlebar.getActivityForFinish(this);
                break;
            case "prescription":
                contentView= mInflater.inflate(R.layout.activity_prescription,null);
                setContentView(contentView);
                MyTitleBar prescriptionTitleBar=contentView.findViewById(R.id.prescription_titleBar);
                prescriptionTitleBar.getActivityForFinish(this);
                break;
            case "medicalBook":
                contentView= mInflater.inflate(R.layout.activity_medicalbook,null);
                setContentView(contentView);
                MyTitleBar medicalBookTitleBar=contentView.findViewById(R.id.medicalBook_titleBar);
                medicalBookTitleBar.getActivityForFinish(this);
                break;
        }


        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();




    }




}
