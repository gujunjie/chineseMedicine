package customview;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abc.chinesemedicine.LearningActivity;
import com.example.abc.chinesemedicine.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class StudyView extends LinearLayout {


    @BindView(R.id.tv_studyName)
    TextView tvStudyName;//标题名
    @BindView(R.id.tv_studyData)
    TextView tvStudyData;//描述数据
    @BindView(R.id.iv_studyImage1)
    ImageView ivStudyImage1;//小图片1
    @BindView(R.id.iv_studyImage2)
    ImageView ivStudyImage2;//小图片2
    @BindView(R.id.iv_studyImage3)
    ImageView ivStudyImage3;//小图片3
    @BindView(R.id.iv_studyImage4)
    ImageView ivStudyImage4;//小图片4
    @BindView(R.id.tv_difficultyNumber)
    TextView tvDifficultyNumber;//难度系数
    @BindView(R.id.tv_progressNumber)
    TextView tvProgressNumber;//学习进度
    @BindView(R.id.iv_mainImage)
    CircleImageView ivMainImage;//主图

    private Context context;

    private String studyName;

    public StudyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context=context;

        LayoutInflater.from(context).inflate(R.layout.studyview_layout, this);
        ButterKnife.bind(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StudyView);

        studyName = typedArray.getString(R.styleable.StudyView_studyName);
        String studyData = typedArray.getString(R.styleable.StudyView_studyData);
        String difficultyNumber = typedArray.getString(R.styleable.StudyView_difficultyNumber);
        int mainImage = typedArray.getResourceId(R.styleable.StudyView_mainImage, 0);
        int studyImage1 = typedArray.getResourceId(R.styleable.StudyView_studyImage1, 0);
        int studyImage2 = typedArray.getResourceId(R.styleable.StudyView_studyImage2, 0);
        int studyImage3 = typedArray.getResourceId(R.styleable.StudyView_studyImage3, 0);
        int studyImage4 = typedArray.getResourceId(R.styleable.StudyView_studyImage4, 0);

        setData(studyName, studyData, difficultyNumber, mainImage, studyImage1, studyImage2, studyImage3, studyImage4);
        typedArray.recycle();


    }

    public void setData(String studyName, String studyData, String difficultyNumber, int mainImage, int studyImage1, int studyImage2, int studyImage3, int studyImage4) {
        tvStudyName.setText(studyName);
        tvStudyData.setText(studyData);
        tvDifficultyNumber.setText(difficultyNumber);
        ivMainImage.setImageResource(mainImage);
        ivStudyImage1.setImageResource(studyImage1);
        ivStudyImage2.setImageResource(studyImage2);
        ivStudyImage3.setImageResource(studyImage3);
        ivStudyImage4.setImageResource(studyImage4);
    }

    public void setProgress(String progress) {
        tvProgressNumber.setText("学习进度：" + progress + "%");
    }



    @OnClick(R.id.btn_study)
    public void onViewClicked() {
        Intent intent=new Intent(context, LearningActivity.class);
        intent.putExtra("sortType",studyName);
        context.startActivity(intent);
    }
}
