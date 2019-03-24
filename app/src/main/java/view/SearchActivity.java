package view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import contract.SearchContract;
import presenter.SearchPresenter;
import util.DataBaseUtil;


public class SearchActivity extends BaseActivity<SearchContract.SearchView, SearchPresenter> implements SearchContract.SearchView {


    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.tfl_searchHistory)
    TagFlowLayout tflSearchHistory;
    @BindView(R.id.tfl_popularSearch)
    TagFlowLayout tflPopularSearch;
    @BindView(R.id.ly_history)
    LinearLayout lyHistory;


    private SearchPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();

        initAutoCompleteTextView(DataBaseUtil.getSearchTipsList());//获取搜索建议

        presenter.getHotSearchList();//获取热门搜索


    }

    @Override
    public SearchPresenter createPresenter() {
        presenter = new SearchPresenter(this);
        return presenter;
    }


    @Override
    public void initAutoCompleteTextView(List<String> list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setDropDownVerticalOffset(8);//设置提示列表的垂直偏移量
        //设置搜索提示

        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //保存搜索记录
                String input = v.getText().toString().trim();
                if (!input.equals("")) {
                    DataBaseUtil.saveHistorySearch(input);
                    turnInSearchResultActivity(input);
                } else {
                    Snackbar.make(autoCompleteTextView,"搜索内容不能为空",Snackbar.LENGTH_SHORT).setAction("好的", new View.OnClickListener() {
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
                String tips = autoCompleteTextView.getText().toString().trim();
                DataBaseUtil.saveHistorySearch(tips);
                turnInSearchResultActivity(tips);
            }
        });//搜索建议选择事件

    }


    @SuppressWarnings("unchecked")
    @Override
    public void initHotSearchTag(final List<String> list) {
        tflPopularSearch.setAdapter(new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tag = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tag_layout, tflPopularSearch, false);
                tag.setText(o.toString());
                return tag;
            }
        });

        tflPopularSearch.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String tag = list.get(position);
                DataBaseUtil.saveHistorySearch(tag);
                turnInSearchResultActivity(tag);
                return true;
            }
        });
    }

    @Override
    public void showGetHotSearchListErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initHistorySearchTag(final List<String> list) {
        if (list.size() != 0) {
            lyHistory.setVisibility(View.VISIBLE);
            tflSearchHistory.setAdapter(new TagAdapter(list) {
                @Override
                public View getView(FlowLayout parent, int position, Object o) {
                    TextView tag = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.tag_layout, tflSearchHistory, false);
                    tag.setText(o.toString());
                    return tag;
                }
            });
        } else {
            lyHistory.setVisibility(View.GONE);
        }

        tflSearchHistory.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                turnInSearchResultActivity(list.get(position));
                return true;
            }
        });
    }

    @Override
    public void showGetHistorySearchListErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.getHistorySearchList();  //获取搜索历史
    }


    public void turnInSearchResultActivity(String searchKeyWord) {
        Intent intent = new Intent(this, SearchResultActivity.class);
        intent.putExtra("searchKeyWord", searchKeyWord);
        startActivity(intent);

    }


    @OnClick({R.id.btn_scanning, R.id.tv_deleteAllHistory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scanning:
                Intent intent=new Intent(this, ScanningActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_deleteAllHistory:
                AlertDialog.Builder dialog = new AlertDialog.Builder(SearchActivity.this);
                dialog.setMessage("确认清除全部历史记录吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApplication.getDaoSession().getSearchHistoryDao().deleteAll();
                        lyHistory.setVisibility(View.GONE);//删除所有记录并设置不可见
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
