package customview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.SecondCategoryActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import view.ChineseMedicineActivity;

public class SortItemView extends RelativeLayout {


    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ly)
    LinearLayout ly;
    @BindView(R.id.tv_number)
    TextView tvNumber;

    private Context context;

    public SortItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.sortitemview_layout, this);
        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SortItemView);
        String type = typedArray.getString(R.styleable.SortItemView_type);
        String number = typedArray.getString(R.styleable.SortItemView_number);
        String turnInActivity=typedArray.getString(R.styleable.SortItemView_turnInactivity);
        setData(type, number);
        if(turnInActivity==null)
        {
            turnInActivity="SecondCategoryActivity";
        }//没有跳转参数默认跳转二级分类列表
        setOnClickListener(turnInActivity);
        typedArray.recycle();
    }

    public void setData(String type, String number) {
        if(type!=null)
        {
            tvType.setText(type);
        }
        if(tvNumber!=null)
        {
            tvNumber.setText(number);
        }
    }

    public void setOnClickListener(final String turnInActivity)
    {
        ly.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (turnInActivity)
                {
                    case "ChineseMedicineActivity":
                        Intent intent = new Intent(context, ChineseMedicineActivity.class);
                        intent.putExtra("sortType","chineseMedicine");
                        intent.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent);
                        break;
                    case "SecondCategoryActivity":
                        Intent intent2 = new Intent(context, SecondCategoryActivity.class);
                        intent2.putExtra("firstCategory", tvType.getText().toString());
                        context.startActivity(intent2);
                        break;
                    case "acuPointActivity":
                        Intent intent3 = new Intent(context, ChineseMedicineActivity.class);
                        intent3.putExtra("sortType", "acuPoint");
                        intent3.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent3);
                        break;
                    case "prescriptionActivity":
                        Intent intent4 = new Intent(context, ChineseMedicineActivity.class);
                        intent4.putExtra("sortType", "prescription");
                        intent4.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent4);
                        break;
                }

            }
        });
    }


}
