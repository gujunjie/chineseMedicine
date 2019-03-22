package view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.EnlargePhotoActivity;
import com.example.abc.chinesemedicine.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import bean.MessageEvent;


public class LearningFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_learningPicture;

    private TextView tv_learningText;

    private String imageUrl;

    private String data;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.learningfragment_layout,container,false);

        iv_learningPicture=(ImageView)view.findViewById(R.id.iv_learningPicture);
        tv_learningText=(TextView)view.findViewById(R.id.tv_learningText);

        initUI();

        return view;
    }

    public void initUI()
    {
        Glide.with(getActivity()).load(imageUrl).placeholder(R.color.colorPlaceHolder).into(iv_learningPicture);
        tv_learningText.setText(data);

        iv_learningPicture.setOnClickListener(this);//给图片加入时间侦听,放大事件
    }



    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void setData(String imageUrl, String data)
    {
        this.data=data;
        this.imageUrl=imageUrl;
    }

    public  void setTextSize(int size)
    {

        switch (size)
        {
            case 0:
                tv_learningText.setTextSize(21);
                break;
            case 1:
                tv_learningText.setTextSize(19);
                break;
            case 2:
                tv_learningText.setTextSize(17);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        setTextSize(event.getTextSize());
       }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), EnlargePhotoActivity.class);
        intent.putExtra("imageUrl",imageUrl);
        startActivity(intent);
    }
}


