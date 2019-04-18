package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abc.chinesemedicine.R;

import customview.SettingItemView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment {


    private SettingItemView siv_about;

    private SettingItemView siv_feedBack;

    private SettingItemView siv_userData;

    private SettingItemView siv_studyTimeLine;

    private SettingItemView siv_setting;

    private CircleImageView userIcon;

    private Button btn_setting;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.userfragment_layout,container,false);
        initFindViewById(view);
        setListener();
        return view;
    }

    public void initFindViewById(View view)
    {
        userIcon=(CircleImageView)view.findViewById(R.id.iv_userIcon);
        siv_about=(SettingItemView) view.findViewById(R.id.siv_about);
        siv_feedBack=(SettingItemView) view.findViewById(R.id.siv_feedBack);
        siv_userData=(SettingItemView) view.findViewById(R.id.siv_userData);
        siv_studyTimeLine=(SettingItemView) view.findViewById(R.id.siv_studyTimeLine);
        siv_setting=(SettingItemView) view.findViewById(R.id.siv_setting);
        btn_setting=(Button)view.findViewById(R.id.btn_setting);
    }

    public void setListener()
    {

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        siv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        siv_feedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), FeedBackActivity.class);
                startActivity(intent);
            }
        });
        siv_userData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), UserDataActivity.class);
                startActivity(intent);
            }
        });
        siv_studyTimeLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), StudyTimeLineActivity.class);
                startActivity(intent);
            }
        });

        siv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
