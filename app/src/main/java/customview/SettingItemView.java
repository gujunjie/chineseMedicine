package customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingItemView extends RelativeLayout {


    @BindView(R.id.iv_itemIcon)
    ImageView ivItemIcon;
    @BindView(R.id.tv_itemTitle)
    TextView tvItemTitle;
    @BindView(R.id.tv_itemDescribe)
    TextView tvItemDescribe;


    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.settingitemview_layout, this);
        ButterKnife.bind(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        int itemIcon = typedArray.getResourceId(R.styleable.SettingItemView_itemIcon,0);
        String itemTitle = typedArray.getString(R.styleable.SettingItemView_itemTitle);
        String itemDescribe = typedArray.getString(R.styleable.SettingItemView_itemDescribe);

         setData(itemIcon,itemTitle,itemDescribe);
        typedArray.recycle();
    }

    public void setData(int itemIcon,String itemTitle,String itemDescribe)
    {
        if(itemIcon!=0)
        {
            ivItemIcon.setImageResource(itemIcon);
        }
        if(itemTitle!=null)
        {
            tvItemTitle.setText(itemTitle);
        }
        if(itemDescribe!=null)
        {
            tvItemDescribe.setText(itemDescribe);
        }
    }


}
