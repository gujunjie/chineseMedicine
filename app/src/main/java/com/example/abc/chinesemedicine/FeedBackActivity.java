package com.example.abc.chinesemedicine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;

public class FeedBackActivity extends AppCompatActivity {

    @BindView(R.id.feedBack_titleBar)
    MyTitleBar feedBackTitleBar;
    @BindView(R.id.et_emailText)
    EditText etEmailText;
    @BindView(R.id.et_userEmailAddress)
    EditText etUserEmailAddress;

    private String emailText;
    private String userEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);

        feedBackTitleBar.getActivityForFinish(this);
                     //你的邮箱密码或授权码
    }

    @OnClick(R.id.btn_sendEmail)
    public void onViewClicked() {

        emailText=etEmailText.getText().toString().trim();
        userEmailAddress=etUserEmailAddress.getText().toString().trim();

        if(emailText.equals(""))
        {
            Snackbar.make(etEmailText,"写点东西再提交吧",Snackbar.LENGTH_SHORT)
                    .setAction("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }else if(userEmailAddress.equals("")){
            Snackbar.make(etEmailText,"请留下您的邮箱地址，方便我们做出反馈",Snackbar.LENGTH_SHORT)
                    .setAction("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
        else
        {
            Uri uri = Uri.parse("mailto:571095488@qq.com");
            String[] email = {userEmailAddress};
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra(Intent.EXTRA_CC, email); // 抄送人
            intent.putExtra(Intent.EXTRA_SUBJECT, "用户反馈建议"); // 主题
            intent.putExtra(Intent.EXTRA_TEXT,emailText); // 正文
            startActivity(Intent.createChooser(intent, "请选择邮件类应用"));
        }


    }
}
