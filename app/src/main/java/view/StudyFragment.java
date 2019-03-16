package view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.UserDao;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.ImmersionFragment;

import java.text.DecimalFormat;
import java.util.List;

import bean.LearningProgress;
import bean.User;
import customview.StudyView;
import util.DataBaseUtil;
import util.SharePreferenceUtil;

public class StudyFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.studyfragment_layout,container,false);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        initUI(view);
    }

    public void initUI(View view)
    {
        StudyView sv_chineseMedicine=(StudyView)view.findViewById(R.id.sv_chineseMedicine);
        StudyView sv_prescription=(StudyView)view.findViewById(R.id.sv_prescription);
        StudyView sv_chinesepatentdrug=(StudyView)view.findViewById(R.id.sv_chinesepatentdrug);
        StudyView sv_medicalBook=(StudyView)view.findViewById(R.id.sv_medicalBook);
        StudyView sv_acuPoint=(StudyView)view.findViewById(R.id.sv_acuPoint);

        List<LearningProgress> list= DataBaseUtil.getLearningProgressList(getActivity());
        DecimalFormat df = new DecimalFormat("0.0");

        sv_chineseMedicine.setProgress(df.format(list.get(0).getLastLearningPercent()));
        sv_prescription.setProgress(df.format(list.get(1).getLastLearningPercent()));
        sv_chinesepatentdrug.setProgress(df.format(list.get(2).getLastLearningPercent()));
        sv_medicalBook.setProgress(df.format(list.get(3).getLastLearningPercent()));
        sv_acuPoint.setProgress(df.format(list.get(4).getLastLearningPercent()));


    }//更新学习进度


}
