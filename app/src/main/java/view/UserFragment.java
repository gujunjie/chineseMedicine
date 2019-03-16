package view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.chinesemedicine.AboutActivity;
import com.example.abc.chinesemedicine.LoginActivity;
import com.example.abc.chinesemedicine.R;

import customview.SettingItemView;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserFragment extends Fragment {


    private SettingItemView siv_about;

    private CircleImageView userIcon;


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
    }
}
