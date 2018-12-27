package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import adapter.AcuPointRecyclerViewAdapter;
import adapter.ChineseMedicineRecyclerViewAdapter;
import adapter.ChinesePatentDrugRecyclerViewAdapter;
import adapter.PrescriptionRecyclerViewAdapter;
import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Prescription;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;

public class ChineseMedicineActivity extends AppCompatActivity {


    @BindView(R.id.titlebar)
    MyTitleBar titlebar;
    @BindView(R.id.rv_chineseMedicine)
    RecyclerView rvChineseMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese_medicine);
        ButterKnife.bind(this);

        ImmersionBar.with(this).statusBarColor(R.color.colorAccent).fitsSystemWindows(true).init();
        //沉浸式
        titlebar.getActivityForFinish(this);

        initUI();
    }

    public void initUI() {
        Intent intent=getIntent();
        String sortType=intent.getStringExtra("sortType");
        String type=intent.getStringExtra("type");
        titlebar.setTitle(type);
        switch (sortType)
        {
            case "chineseMedicine":

                ChineseMedicineDao dao= MyApplication.getDaoSession().getChineseMedicineDao();

                List<ChineseMedicine> list=dao.queryBuilder().where(ChineseMedicineDao.Properties.SortType.eq(type)).list();

                ChineseMedicineRecyclerViewAdapter adapter=new ChineseMedicineRecyclerViewAdapter(this,list);

                LinearLayoutManager manager=new LinearLayoutManager(this);

                rvChineseMedicine.setAdapter(adapter);
                rvChineseMedicine.setLayoutManager(manager);
                break;
            case "chinesePatentDrug":
                ChinesePatentDrugDao dao1=MyApplication.getDaoSession().getChinesePatentDrugDao();

                List<ChinesePatentDrug> list1=dao1.queryBuilder().where(ChinesePatentDrugDao.Properties.SecondCategoryName.eq(type)).list();

                ChinesePatentDrugRecyclerViewAdapter adapter1=new ChinesePatentDrugRecyclerViewAdapter(this,list1);
                LinearLayoutManager manager1=new LinearLayoutManager(this);

                rvChineseMedicine.setAdapter(adapter1);
                rvChineseMedicine.setLayoutManager(manager1);
                break;
            case "acuPoint":
                AcuPointDao dao2= MyApplication.getDaoSession().getAcuPointDao();
                List<AcuPoint> list2=dao2.queryBuilder().where(AcuPointDao.Properties.SortType.eq(type)).list();

                AcuPointRecyclerViewAdapter adapter2=new AcuPointRecyclerViewAdapter(this,list2);
                LinearLayoutManager manager2=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter2);
                rvChineseMedicine.setLayoutManager(manager2);
                break;

            case "prescription":
                PrescriptionDao dao3=MyApplication.getDaoSession().getPrescriptionDao();
                List<Prescription> list3=dao3.queryBuilder().where(PrescriptionDao.Properties.SortType.eq(type)).list();
                PrescriptionRecyclerViewAdapter adapter3=new PrescriptionRecyclerViewAdapter(this,list3);
                LinearLayoutManager manager3=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter3);
                rvChineseMedicine.setLayoutManager(manager3);
                break;
        }


    }
}
