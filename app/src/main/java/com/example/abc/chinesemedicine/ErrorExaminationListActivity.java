package com.example.abc.chinesemedicine;

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

        User user = DataBaseUtil.getUser(this);
        ErrorExaminationDao dao = MyApplication.getDaoSession().getErrorExaminationDao();
        List<ErrorExamination> errorExaminationList = dao.queryBuilder().where(ErrorExaminationDao.Properties.UserId.eq(user.getId())).list();

        tvErrorNumber.setText(errorExaminationList.size() + "");


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

        ErrorExamRecyclerViewAdapter adapter = new ErrorExamRecyclerViewAdapter(list, this);

        rvErrorExamination.setLayoutManager(new LinearLayoutManager(this));
        rvErrorExamination.setAdapter(adapter);

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

      user.setRightTimesForRemove(number);

      dao.update(user);

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
      }

      bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
      tvMask.setVisibility(View.GONE);

  }

}
