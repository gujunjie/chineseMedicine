package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;
import com.gyf.barlibrary.ImmersionBar;

import java.util.List;

import adapter.AcuPointRecyclerViewAdapter;
import adapter.ChineseMedicineRecyclerViewAdapter;
import adapter.ChinesePatentDrugRecyclerViewAdapter;
import adapter.MedicalBookRecyclerViewAdapter;
import adapter.PrescriptionRecyclerViewAdapter;
import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.MedicalBook;
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
            case "medicalBook_huangdi":
                MedicalBookDao dao4=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list4=dao4.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("黄帝内经")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter4=new MedicalBookRecyclerViewAdapter(this,list4);
                LinearLayoutManager manager4=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter4);
                rvChineseMedicine.setLayoutManager(manager4);
                break;
            case "medicalBook_bencao":
                MedicalBookDao dao5=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list5=dao5.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("本草纲目")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter5=new MedicalBookRecyclerViewAdapter(this,list5);
                LinearLayoutManager manager5=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter5);
                rvChineseMedicine.setLayoutManager(manager5);
                break;
            case "medicalBook_tangtou":
                MedicalBookDao dao6=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list6=dao6.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("汤头歌诀")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter6=new MedicalBookRecyclerViewAdapter(this,list6);
                LinearLayoutManager manager6=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter6);
                rvChineseMedicine.setLayoutManager(manager6);
                break;
            case "medicalBook_shanghan":
                MedicalBookDao dao7=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list7=dao7.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("伤寒论")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter7=new MedicalBookRecyclerViewAdapter(this,list7);
                LinearLayoutManager manager7=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter7);
                rvChineseMedicine.setLayoutManager(manager7);
                break;
            case "medicalBook_nanjing":
                MedicalBookDao dao8=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list8=dao8.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("难经")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter8=new MedicalBookRecyclerViewAdapter(this,list8);
                LinearLayoutManager manager8=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter8);
                rvChineseMedicine.setLayoutManager(manager8);
                break;
            case "medicalBook_yixue":
                MedicalBookDao dao9=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list9=dao9.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("医学三字经")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter9=new MedicalBookRecyclerViewAdapter(this,list9);
                LinearLayoutManager manager9=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter9);
                rvChineseMedicine.setLayoutManager(manager9);
                break;
            case "medicalBook_pinghu":
                MedicalBookDao dao10=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list10=dao10.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("濒湖脉学")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter10=new MedicalBookRecyclerViewAdapter(this,list10);
                LinearLayoutManager manager10=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter10);
                rvChineseMedicine.setLayoutManager(manager10);
                break;
            case "medicalBook_jingkui":
                MedicalBookDao dao11=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list11=dao11.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("金匮要略")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter11=new MedicalBookRecyclerViewAdapter(this,list11);
                LinearLayoutManager manager11=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter11);
                rvChineseMedicine.setLayoutManager(manager11);
                break;
            case "medicalBook_qianjing":
                MedicalBookDao dao12=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list12=dao12.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("千金要方")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter12=new MedicalBookRecyclerViewAdapter(this,list12);
                LinearLayoutManager manager12=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter12);
                rvChineseMedicine.setLayoutManager(manager12);
                break;
            case "medicalBook_wenbing":
                MedicalBookDao dao13=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list13=dao13.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("温病条辨")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter13=new MedicalBookRecyclerViewAdapter(this,list13);
                LinearLayoutManager manager13=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter13);
                rvChineseMedicine.setLayoutManager(manager13);
                break;
            case "medicalBook_shennong":
                MedicalBookDao dao14=MyApplication.getDaoSession().getMedicalBookDao();
                List<MedicalBook> list14=dao14.queryBuilder().where(MedicalBookDao.Properties.BookName.eq("神农本草经")).where(MedicalBookDao.Properties.SortType.eq(type)).list();
                MedicalBookRecyclerViewAdapter adapter14=new MedicalBookRecyclerViewAdapter(this,list14);
                LinearLayoutManager manager14=new LinearLayoutManager(this);
                rvChineseMedicine.setAdapter(adapter14);
                rvChineseMedicine.setLayoutManager(manager14);
                break;

        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
