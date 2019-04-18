package customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import view.ExamActivity;

public class MyTitleBarWhite extends LinearLayout {


    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private boolean needToSaveData;

    private Activity activity;

    public MyTitleBarWhite(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        LayoutInflater.from(context).inflate(R.layout.mytitlebarwhite_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBarWhite);
        String title = typedArray.getString(R.styleable.MyTitleBarWhite_whiteTitle);
        setTitle(title);
        typedArray.recycle();
    }

    public void setTitle(String title) {
        if (title != null) {
            tvTitle.setText(title);
        }

    }

    public void getActivityForFinish(Activity activity,boolean needToSaveData) {
        this.activity = activity;
        this.needToSaveData=needToSaveData;

    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        activity.finish();
        if(needToSaveData)
        {
            ExamActivity examActivity=(ExamActivity)activity;
            examActivity.saveLastExamData();
        }
    }
}
