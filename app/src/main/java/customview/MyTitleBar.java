package customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTitleBar extends RelativeLayout {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    Button btnBack;

    private Activity activity;

    public MyTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.mytitlebar_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBar);
        String title = typedArray.getString(R.styleable.MyTitleBar_title);
        setTitle(title);
        typedArray.recycle();
    }

    public void setTitle(String title) {
        if(title!=null)
        {
            tvTitle.setText(title);
        }

    }

    public void getActivityForFinish(Activity activity) {
        this.activity=activity;
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        activity.finish();
    }
}
