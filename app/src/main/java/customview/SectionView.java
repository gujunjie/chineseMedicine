package customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionView extends LinearLayout {


    @BindView(R.id.iv_section)
    ImageView ivSection;
    @BindView(R.id.tv_sectionText)
    TextView tvSectionText;
    @BindView(R.id.ll_sectionItem)
    LinearLayout llSectionItem;

    public SectionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.sectionview_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SectionView);
        int section = typedArray.getResourceId(R.styleable.SectionView_section, 0);
        setSection(section);
        typedArray.recycle();
    }

    public void setSection(int section) {
        if (section != 0) {
            ivSection.setImageResource(section);
        }

    }

    public void setSectionText(String sectionText)
    {
        tvSectionText.setText(sectionText);
    }
    public void setSectionTextColor(int color)
    {
        tvSectionText.setTextColor(color);
    }


}
