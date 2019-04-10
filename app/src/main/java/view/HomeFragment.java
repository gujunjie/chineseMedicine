package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.abc.chinesemedicine.GlideImageLoader;
import com.example.abc.chinesemedicine.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import customview.TuiJianView;

public class HomeFragment extends Fragment implements View.OnClickListener {


    private Banner banner;

    private List<String> list;

    private SearchView searchView;

    private TuiJianView health_tuijian;

    private TuiJianView medicineDiet_tuijian;

    private TuiJianView prescription_tuijian;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_layout, container, false);


        initFindViewById(view);//获取控件实例

        initSearchBar();//初始化搜索栏

        initBanner();//初始化轮播图

        initTuiJian();//初始化推荐栏
        return view;
    }

    public void initFindViewById(View view)
    {
        banner = (Banner) view.findViewById(R.id.banner);
        TextView tv_chineseMedicine = (TextView) view.findViewById(R.id.tv_chineseMedicine);
        tv_chineseMedicine.setOnClickListener(this);

        TextView tv_chinesepatentdrug = (TextView) view.findViewById(R.id.tv_chinesepatentdrug);
        tv_chinesepatentdrug.setOnClickListener(this);

        TextView tv_acuPoint = (TextView) view.findViewById(R.id.tv_acuPoint);
        tv_acuPoint.setOnClickListener(this);

        TextView tv_prescription = (TextView) view.findViewById(R.id.tv_prescription);
        tv_prescription.setOnClickListener(this);

        TextView tv_medicalBook = (TextView) view.findViewById(R.id.tv_medicalBook);
        tv_medicalBook.setOnClickListener(this);

        Button btn_scanning=(Button)view.findViewById(R.id.btn_scanning);
        btn_scanning.setOnClickListener(this);


        searchView = (SearchView) view.findViewById(R.id.searchView);
        health_tuijian = (TuiJianView) view.findViewById(R.id.health_tuijian);
        medicineDiet_tuijian = (TuiJianView) view.findViewById(R.id.medicineDiet_tuijian);
        prescription_tuijian = (TuiJianView) view.findViewById(R.id.prescription_tuijian);
    }

    @Override
    public void onResume() {
        super.onResume();
        searchView.clearFocus();
    }

    public void initSearchBar() {
        //去掉searchview的下划线
        searchView.findViewById(android.support.v7.appcompat.R.id.search_plate).setBackground(null);
        searchView.findViewById(android.support.v7.appcompat.R.id.submit_area).setBackground(null);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void initBanner() {

        list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544430961&di=54b013cc19fbf79fd0b8c1ecca026f71&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F6d81800a19d8bc3e47599fde898ba61ea8d34555.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544430994&di=61afb9905da2c640a206fc044c678ae2&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F503d269759ee3d6d29d11fdf48166d224f4ade8b.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544431043&di=e88cb4a835d9247a6d3f760defa13024&imgtype=jpg&er=1&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F6159252dd42a28341517241e50b5c9ea15cebf75.jpg");
        banner.setImageLoader(new GlideImageLoader()).setBannerStyle(BannerConfig.NOT_INDICATOR).setDelayTime(2500).setImages(list).start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent=new Intent(getActivity(),MoreActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initTuiJian() {
        health_tuijian.setRecyclerViewData(getActivity(), "health", 3);
        medicineDiet_tuijian.setRecyclerViewData(getActivity(), "medicineDiet", 3);
        prescription_tuijian.setRecyclerViewData(getActivity(), "prescription", 3);

        health_tuijian.setTvMoreOnClickListener(getActivity(), 0);
        medicineDiet_tuijian.setTvMoreOnClickListener(getActivity(), 1);
        prescription_tuijian.setTvMoreOnClickListener(getActivity(), 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_chineseMedicine:
                Intent intent = new Intent(getActivity(), SortActivity.class);
                intent.putExtra("sortType","pingyin");
                startActivity(intent);
                break;
                case R.id.btn_scanning:
                Intent intent2 = new Intent(getActivity(), ScanningActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_chinesepatentdrug:
                Intent intent3 = new Intent(getActivity(), SortActivity.class);
                intent3.putExtra("sortType","chinesePatentDrugFirstCategory");
                startActivity(intent3);
                break;
            case R.id.tv_acuPoint:
                Intent intent4 = new Intent(getActivity(), SortActivity.class);
                intent4.putExtra("sortType","acuPoint");
                startActivity(intent4);
                break;
            case R.id.tv_prescription:
                Intent intent5 = new Intent(getActivity(), SortActivity.class);
                intent5.putExtra("sortType","prescription");
                startActivity(intent5);
                break;
            case  R.id.tv_medicalBook:
                Intent intent6 = new Intent(getActivity(), SortActivity.class);
                intent6.putExtra("sortType","medicalBook");
                startActivity(intent6);
                break;
        }
    }





}
