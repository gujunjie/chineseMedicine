package customview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import view.SortActivity;

public class IconView extends LinearLayout {


    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ly_iconView)
    LinearLayout lyIconView;

    private Context context;

    public IconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        LayoutInflater.from(context).inflate(R.layout.iconview_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconView);
        int icon = typedArray.getResourceId(R.styleable.IconView_icon, 0);
        String name = typedArray.getString(R.styleable.IconView_name);

        setData(icon, name);//设置数据

        setOnClickListener(name);//加事件侦听
        typedArray.recycle();
    }

    public void setOnClickListener(final String name)
    {
        lyIconView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (name)
                {
                    case "黄帝内经":
                        Intent intent=new Intent(context, SortActivity.class);
                        intent.putExtra("sortType","medicalBookFirstCategory_huangdi");
                        context.startActivity(intent);
                        break;
                    case "本草纲目":
                        Intent intent2=new Intent(context, SortActivity.class);
                        intent2.putExtra("sortType","medicalBookFirstCategory_bencao");
                        context.startActivity(intent2);
                        break;
                    case "难经":
                        Intent intent3=new Intent(context, SortActivity.class);
                        intent3.putExtra("sortType","medicalBookFirstCategory_nanjing");
                        context.startActivity(intent3);
                        break;
                    case "金匮要略":
                        Intent intent4=new Intent(context, SortActivity.class);
                        intent4.putExtra("sortType","medicalBookFirstCategory_jinkui");
                        context.startActivity(intent4);
                        break;
                    case "千金要方":
                        Intent intent5=new Intent(context, SortActivity.class);
                        intent5.putExtra("sortType","medicalBookFirstCategory_qianjing");
                        context.startActivity(intent5);
                        break;
                    case "神农本草经":
                        Intent intent6=new Intent(context, SortActivity.class);
                        intent6.putExtra("sortType","medicalBookFirstCategory_shennong");
                        context.startActivity(intent6);
                        break;
                    case "汤头歌诀":
                        Intent intent7=new Intent(context, SortActivity.class);
                        intent7.putExtra("sortType","medicalBookFirstCategory_tangtou");
                        context.startActivity(intent7);
                        break;
                    case "伤寒论":
                        Intent intent8=new Intent(context, SortActivity.class);
                        intent8.putExtra("sortType","medicalBookFirstCategory_shanghan");
                        context.startActivity(intent8);
                        break;
                    case "濒湖脉学":
                        Intent intent9=new Intent(context, SortActivity.class);
                        intent9.putExtra("sortType","medicalBookFirstCategory_pinghu");
                        context.startActivity(intent9);
                        break;
                    case "温病条辨":
                        Intent intent10=new Intent(context, SortActivity.class);
                        intent10.putExtra("sortType","medicalBookFirstCategory_wenbing");
                        context.startActivity(intent10);
                        break;
                    case "医学三字经":
                        Intent intent11=new Intent(context, SortActivity.class);
                        intent11.putExtra("sortType","medicalBookFirstCategory_yixue");
                        context.startActivity(intent11);
                        break;
                    case "医宗金鉴":
                        Intent intent12=new Intent(context, SortActivity.class);
                        intent12.putExtra("sortType","medicalBookFirstCategory_yizong");
                        context.startActivity(intent12);
                        break;
                }
            }
        });
    }


    public void setData(int icon, String name) {
        if (icon != 0) {
            ivIcon.setImageResource(icon);
        }
        if (name != null) {
            tvName.setText(name);
        }
    }
}
