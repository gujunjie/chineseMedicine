package view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.ErrorExaminationDao;
import com.example.abc.chinesemedicine.greendao.UserDao;

import java.util.ArrayList;
import java.util.List;

import adapter.ErrorExamRecyclerViewAdapter;
import bean.ErrorExamSyllabus;
import bean.ErrorExamination;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import customview.MyTitleBar;
import util.DataBaseUtil;

public class ErrorExaminationListActivity extends AppCompatActivity {

    @BindView(R.id.tv_errorNumber)
    TextView tvErrorNumber;
    @BindView(R.id.rv_errorExamination)
    RecyclerView rvErrorExamination;
    @BindView(R.id.errorExam_titleBar)
    MyTitleBar errorExamTitleBar;
    @BindView(R.id.ll_bottomSheet)
    LinearLayout llBottomSheet;
    @BindView(R.id.tv_mask)
    TextView tvMask;

    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_examination_list);
        ButterKnife.bind(this);


        initUI();
    }

    public void initUI() {
        errorExamTitleBar.getActivityForFinish(this);


        //获取当前登录的user
        User user = DataBaseUtil.getUser(this);
        ErrorExaminationDao dao = MyApplication.getDaoSession().getErrorExaminationDao();

        //获取当前用户的所有错题列表
        List<ErrorExamination> errorExaminationList = dao.queryBuilder().where(ErrorExaminationDao.Properties.UserId.eq(user.getId())).list();

        //显示所有错题个数
        tvErrorNumber.setText(errorExaminationList.size() + "");


        //配置中药1错题类型的基本信息
        List<ErrorExamSyllabus> list = new ArrayList<>();
        ErrorExamSyllabus syllabus1 = new ErrorExamSyllabus();
        syllabus1.setSortType("中药学专业知识(一)");
        syllabus1.setIcon(R.drawable.number1);
        syllabus1.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "中药学专业知识(一)"));


        ErrorExamSyllabus syllabus2 = new ErrorExamSyllabus();
        syllabus2.setSortType("中药学专业知识(二)");
        syllabus2.setIcon(R.drawable.number2);
        syllabus2.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "中药学专业知识(二)"));

        ErrorExamSyllabus syllabus3 = new ErrorExamSyllabus();
        syllabus3.setSortType("中药学综合知识与技能");
        syllabus3.setIcon(R.drawable.number3);
        syllabus3.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "中药学综合知识与技能"));

        ErrorExamSyllabus syllabus4 = new ErrorExamSyllabus();
        syllabus4.setSortType("药学专业知识(一)");
        syllabus4.setIcon(R.drawable.number4);
        syllabus4.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "药学专业知识(一)"));

        ErrorExamSyllabus syllabus5 = new ErrorExamSyllabus();
        syllabus5.setSortType("药学专业知识(二)");
        syllabus5.setIcon(R.drawable.number5);
        syllabus5.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "药学专业知识(二)"));

        ErrorExamSyllabus syllabus6 = new ErrorExamSyllabus();
        syllabus6.setSortType("药学综合知识与技能");
        syllabus6.setIcon(R.drawable.number6);
        syllabus6.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "药学综合知识与技能"));

        ErrorExamSyllabus syllabus7 = new ErrorExamSyllabus();
        syllabus7.setSortType("药事管理与法规");
        syllabus7.setIcon(R.drawable.number7);
        syllabus7.setErrorNumber(getEverySortTypeErrorExamNumber(errorExaminationList, "药事管理与法规"));

        list.add(syllabus1);
        list.add(syllabus2);
        list.add(syllabus3);
        list.add(syllabus4);
        list.add(syllabus5);
        list.add(syllabus6);
        list.add(syllabus7);


        //所有错题信息recyclerview显示
        ErrorExamRecyclerViewAdapter adapter = new ErrorExamRecyclerViewAdapter(list, this);

        rvErrorExamination.setLayoutManager(new LinearLayoutManager(this));
        rvErrorExamination.setAdapter(adapter);

        //配置底部上拉视图
        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    tvMask.setVisibility(View.GONE);
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


    }

    //计算每一种类型错题的数目
    public int getEverySortTypeErrorExamNumber(List<ErrorExamination> list, String sortType) {
        int number = 0;

        //匹配到相同类型就数字加1
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSortType().equals(sortType)) {
                number++;
            }
        }
        return number;
    }


    @OnClick({R.id.tv_mask, R.id.tv_remove,R.id.tv_never, R.id.tv_five, R.id.tv_six, R.id.tv_seven, R.id.tv_eight, R.id.tv_nine, R.id.tv_ten})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_mask:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                tvMask.setVisibility(View.GONE);
                break;
            case R.id.tv_remove:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                tvMask.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_never:
                saveRightTimesForRemoveSetting(-1);
                break;
            case R.id.tv_five:
                saveRightTimesForRemoveSetting(5);
                break;
            case R.id.tv_six:
                saveRightTimesForRemoveSetting(6);
                break;
            case R.id.tv_seven:
                saveRightTimesForRemoveSetting(7);
                break;
            case R.id.tv_eight:
                saveRightTimesForRemoveSetting(8);
                break;
            case R.id.tv_nine:
                saveRightTimesForRemoveSetting(9);
                break;
            case R.id.tv_ten:
                saveRightTimesForRemoveSetting(10);
                break;
        }
    }

  public void saveRightTimesForRemoveSetting(int number)
  {

      UserDao dao=MyApplication.getDaoSession().getUserDao();

      User user=DataBaseUtil.getUser(this);

      user.setRightTimesForRemove(number);//设置用户设定的连续做对自动删除次数

      dao.update(user);

      //为-1就永不删除
      if(number==-1)
      {
          Snackbar.make(tvErrorNumber,"已设置不删除",Snackbar.LENGTH_LONG).setAction("好的", new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
          }).show();
      }else
      {
          Snackbar.make(tvErrorNumber,"已设置连续做对删除次数 "+number,Snackbar.LENGTH_LONG).setAction("好的", new View.OnClickListener() {
              @Override
              public void onClick(View v) {

              }
          }).show();
      }//其他情况按数字决定

      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      tvMask.setVisibility(View.GONE);//选择完成后底部视图还原归位

  }

}
