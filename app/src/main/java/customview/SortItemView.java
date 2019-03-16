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
import view.SecondCategoryActivity;

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
                    case "medicalBook_huangdi_activity":
                        Intent intent5 = new Intent(context, ChineseMedicineActivity.class);
                        intent5.putExtra("sortType", "medicalBook_huangdi");
                        intent5.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent5);
                        break;
                    case "medicalBook_bencao_activity":
                        Intent intent6 = new Intent(context, ChineseMedicineActivity.class);
                        intent6.putExtra("sortType", "medicalBook_bencao");
                        intent6.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent6);
                        break;
                    case "medicalBook_tangtou_activity":
                        Intent intent7 = new Intent(context, ChineseMedicineActivity.class);
                        intent7.putExtra("sortType", "medicalBook_tangtou");
                        intent7.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent7);
                        break;
                    case "medicalBook_shanghan_activity":
                        Intent intent8 = new Intent(context, ChineseMedicineActivity.class);
                        intent8.putExtra("sortType", "medicalBook_shanghan");
                        intent8.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent8);
                        break;
                    case "medicalBook_nanjing_activity":
                        Intent intent9 = new Intent(context, ChineseMedicineActivity.class);
                        intent9.putExtra("sortType", "medicalBook_nanjing");
                        intent9.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent9);
                        break;
                    case "medicalBook_yixue_activity":
                        Intent intent10 = new Intent(context, ChineseMedicineActivity.class);
                        intent10.putExtra("sortType", "medicalBook_yixue");
                        intent10.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent10);
                        break;
                    case "medicalBook_pinghu_activity":
                        Intent intent11 = new Intent(context, ChineseMedicineActivity.class);
                        intent11.putExtra("sortType", "medicalBook_pinghu");
                        intent11.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent11);
                        break;
                    case "medicalBook_jingkui_activity":
                        Intent intent12 = new Intent(context, ChineseMedicineActivity.class);
                        intent12.putExtra("sortType", "medicalBook_jingkui");
                        intent12.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent12);
                        break;
                    case "medicalBook_qianjing_activity":
                        Intent intent13 = new Intent(context, ChineseMedicineActivity.class);
                        intent13.putExtra("sortType", "medicalBook_qianjing");
                        intent13.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent13);
                        break;
                    case "medicalBook_wenbing_activity":
                        Intent intent14 = new Intent(context, ChineseMedicineActivity.class);
                        intent14.putExtra("sortType", "medicalBook_wenbing");
                        intent14.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent14);
                        break;
                    case "medicalBook_shennong_activity":
                        Intent intent15 = new Intent(context, ChineseMedicineActivity.class);
                        intent15.putExtra("sortType", "medicalBook_shennong");
                        intent15.putExtra("type", tvType.getText().toString());
                        context.startActivity(intent15);
                        break;
                }

            }
        });
    }


}
