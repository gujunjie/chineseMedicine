package view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.abc.chinesemedicine.R;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPageAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import customview.MyTitleBar;
import view.ExamCollectionFragment;
import view.LearningCollectionFragment;

public class CollectionListActivity extends AppCompatActivity {

    @BindView(R.id.collection_titleBar)
    MyTitleBar collectionTitleBar;
    @BindView(R.id.stl_collectSortType)
    SlidingTabLayout stlCollectSortType;
    @BindView(R.id.vp_collection)
    ViewPager vpCollection;

    private List<Fragment> list=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list);
        ButterKnife.bind(this);

        initUI();
    }


    public void initUI() {

        collectionTitleBar.getActivityForFinish(this);


        LearningCollectionFragment learningCollectionFragment=new LearningCollectionFragment();
        ExamCollectionFragment examCollectionFragment=new ExamCollectionFragment();


        list.add(learningCollectionFragment);
        list.add(examCollectionFragment);


        String[] title={"章节","试题"};

        MyFragmentPageAdapter adapter=new MyFragmentPageAdapter(getSupportFragmentManager(),list);

        vpCollection.setAdapter(adapter);

        stlCollectSortType.setViewPager(vpCollection,title);
    }


}
