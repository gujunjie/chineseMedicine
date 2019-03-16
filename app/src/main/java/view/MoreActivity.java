package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.abc.chinesemedicine.R;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import view.MoreFragment;

public class MoreActivity extends AppCompatActivity {

    @BindView(R.id.stl_more)
    SlidingTabLayout stlMore;
    @BindView(R.id.vp_more)
    ViewPager vpMore;

    private List<Fragment> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);

        initUI();


    }

    public void initUI()
    {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();

        MoreFragment healthFragment=new MoreFragment();
        MoreFragment medicineDietFragment=new MoreFragment();
        MoreFragment prescriptionFragment=new MoreFragment();

        Bundle bundle1=new Bundle();
        bundle1.putString("sortType","health");
        healthFragment.setArguments(bundle1);

        Bundle bundle2=new Bundle();
        bundle2.putString("sortType","medicineDiet");
        medicineDietFragment.setArguments(bundle2);

        Bundle bundle3=new Bundle();
        bundle3.putString("sortType","prescription");
        prescriptionFragment.setArguments(bundle3);

        list.add(healthFragment);
        list.add(medicineDietFragment);
        list.add(prescriptionFragment);

        String[] title={"养生","药膳","药方"};

        MyFragmentPageAdapter adapter=new MyFragmentPageAdapter(getSupportFragmentManager(),list);

        vpMore.setAdapter(adapter);
        //vpMore.setOffscreenPageLimit(2);

        stlMore.setViewPager(vpMore,title);

        vpMore.setCurrentItem(getIntent().getIntExtra("sortType",0));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
