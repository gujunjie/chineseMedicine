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
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556126562810&di=ad2d1418eee7deee7bd3dabaea04bd7d&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012ccc5b5fc4e8a801215c8f8867e1.jpg%401280w_1l_2o_100sh.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556126858846&di=784de6df5f140dbef92249804693eb5b&imgtype=0&src=http%3A%2F%2Fs15.sinaimg.cn%2Fmw690%2F006VT6H5zy7dI7NDko69e%26690");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1556117509&di=d60125aba926035ccf9e872bc5fc613b&src=http://i0.hdslb.com/bfs/article/8d3e695c16dc0b48e5aeb22dbf5be14303386d2e.jpg");
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
