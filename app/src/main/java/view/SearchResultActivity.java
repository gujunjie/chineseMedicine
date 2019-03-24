package view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import adapter.SearchResultRecyclerViewAdapter;
import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Examination;
import bean.MedicalBook;
import bean.Prescription;
import bean.SearchResult;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;
import util.SoftKeyboardUtil;

public class SearchResultActivity extends AppCompatActivity {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.tv_showNothing)
    TextView tvShowNothing;
    @BindView(R.id.rv_searchResult)
    RecyclerView rvSearchResult;
    @BindView(R.id.tv_showResultCount)
    TextView tvShowResultCount;

    private String searchKeyWord;

    private List<SearchResult> resultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();

        initSearchBar();//将关键字填入搜索栏

        search(searchKeyWord);

    }

    public void initSearchBar() {
        searchKeyWord = getIntent().getStringExtra("searchKeyWord");
        autoCompleteTextView.setText(searchKeyWord);//搜索栏键入搜索关键字
        autoCompleteTextView.clearFocus();//移除焦点
        autoCompleteTextView.setImeOptions(3);//重新获取焦点时软键盘变为搜索按钮

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, DataBaseUtil.getSearchTipsList());
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setDropDownVerticalOffset(8);//设置提示列表的垂直偏移量

        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //保存搜索记录
                String input=v.getText().toString().trim();
                if(!input.equals(""))
                {
                    autoCompleteTextView.clearFocus();
                    DataBaseUtil.saveHistorySearch(input);
                    resultList.clear();//清除上一次的搜索结果
                    search(input);//重新搜索
                    SoftKeyboardUtil.hideSoftKeyboard(SearchResultActivity.this);//搜索完毕隐藏键盘
                    autoCompleteTextView.clearFocus();//移除焦点
                }else
                {
                    Snackbar.make(tvShowResultCount,"搜索内容不能为空",Snackbar.LENGTH_SHORT).setAction("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();

                }
                return true;
            }
        });//软键盘搜索事件

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //保存搜索记录
                String tips=autoCompleteTextView.getText().toString().trim();
                DataBaseUtil.saveHistorySearch(tips);
                resultList.clear();//清除上一次的搜索结果
                search(tips);//重新搜索
                autoCompleteTextView.clearFocus();//移除焦点
                SoftKeyboardUtil.hideSoftKeyboard(SearchResultActivity.this);//搜索完毕隐藏键盘
            }
        });//搜索建议选择事件
    }

    public void search(final String searchKeyWord) {
        Observable.create(new ObservableOnSubscribe<List<SearchResult>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchResult>> e) {
                List<ChineseMedicine> chineseMedicinelist = MyApplication.getDaoSession().getChineseMedicineDao().queryBuilder().where(ChineseMedicineDao.Properties.Name.like("%" + searchKeyWord + "%")).list();
                if (chineseMedicinelist.size() != 0) {
                    for (int i = 0; i < chineseMedicinelist.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(chineseMedicinelist.get(i).getName());
                        result.setImageUrl(chineseMedicinelist.get(i).getMedicineImageUrl());
                        result.setSortType("中药");
                        resultList.add(result);
                    }
                }

                List<ChinesePatentDrug> drugList = MyApplication.getDaoSession().getChinesePatentDrugDao().queryBuilder().where(ChinesePatentDrugDao.Properties.Name.like("%" + searchKeyWord + "%")).list();
                if (drugList.size() != 0) {
                    for (int i = 0; i < drugList.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(drugList.get(i).getName());
                        result.setImageUrl(drugList.get(i).getImageUrl());
                        result.setSortType("中成药");
                        resultList.add(result);
                    }
                }
                List<AcuPoint> pointList=MyApplication.getDaoSession().getAcuPointDao().queryBuilder().where(AcuPointDao.Properties.Name.like("%" + searchKeyWord + "%")).list();
                if (pointList.size() != 0) {
                    for (int i = 0; i < pointList.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(pointList.get(i).getName());
                        result.setImageUrl(pointList.get(i).getImageUrl());
                        result.setSortType("穴位");
                        resultList.add(result);
                    }
                }

                List<Prescription> prescriptionList=MyApplication.getDaoSession().getPrescriptionDao().queryBuilder().where(PrescriptionDao.Properties.Name.like("%" + searchKeyWord + "%")).list();
                if (prescriptionList.size() != 0) {
                    for (int i = 0; i < prescriptionList.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(prescriptionList.get(i).getName());
                        result.setImageUrl(prescriptionList.get(i).getImageUrl());
                        result.setSortType("方剂");
                        resultList.add(result);
                    }
                }

                List<MedicalBook> bookList=MyApplication.getDaoSession().getMedicalBookDao().queryBuilder().where(MedicalBookDao.Properties.Name.like("%" + searchKeyWord + "%")).list();
                if (bookList.size() != 0) {
                    for (int i = 0; i < bookList.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(bookList.get(i).getName());
                        result.setImageUrl(bookList.get(i).getImageUrl());
                        result.setSortType(bookList.get(i).getBookName());
                        resultList.add(result);
                    }
                }

                List<Examination> examinationList=MyApplication.getDaoSession().getExaminationDao().queryBuilder().where(ExaminationDao.Properties.Title.like("%" + searchKeyWord + "%")).list();
                if (examinationList.size() != 0) {
                    for (int i = 0; i < examinationList.size(); i++) {
                        SearchResult result = new SearchResult();
                        result.setName(examinationList.get(i).getTitle());
                        result.setImageUrl(examinationList.get(i).getImageUrl());
                        result.setSortType(examinationList.get(i).getSortType());
                        resultList.add(result);
                    }
                }

                e.onNext(resultList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SearchResult> value) {
                        if (resultList.size() != 0) {

                            rvSearchResult.setVisibility(View.VISIBLE);
                            tvShowNothing.setVisibility(View.GONE);//搜索结果不为空就结果栏可见，为空提示不可见
                            SearchResultRecyclerViewAdapter adapter = new SearchResultRecyclerViewAdapter(SearchResultActivity.this, value);
                            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            rvSearchResult.setAdapter(adapter);
                            rvSearchResult.setLayoutManager(manager);
                            tvShowResultCount.setText("全部结果"+"("+value.size()+")");//给结果栏填充数据

                        } else {
                            tvShowNothing.setVisibility(View.VISIBLE);
                            rvSearchResult.setVisibility(View.GONE);
                            tvShowResultCount.setText("全部结果"+"(0)");//搜索结果为空，就为空提示可见，结果栏不可见
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }


    @OnClick(R.id.tv_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
