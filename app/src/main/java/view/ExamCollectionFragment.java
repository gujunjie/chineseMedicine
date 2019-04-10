package view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.CollectionDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;

import java.util.ArrayList;
import java.util.List;

import adapter.ExamCollectionRecyclerViewAdapter;
import bean.Collection;
import bean.Examination;
import bean.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;

public class ExamCollectionFragment extends Fragment {


    private TextView tv_collectionNumber;
    private TextView tv_showNothing;

    private RecyclerView rv_collection;

    private List<Collection> examCollectionList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.collectionfragement_layout,container,false);

        initFindViewById(view);

         getAndSetCollectionData();
        return view;
    }


    public void initFindViewById(View view)
    {
        tv_collectionNumber=(TextView)view.findViewById(R.id.tv_collectionNumber);
        tv_showNothing=(TextView)view.findViewById(R.id.tv_showNothing);
        rv_collection=(RecyclerView)view.findViewById(R.id.rv_collection);
    }


    public void getAndSetCollectionData()
    {
        Observable.create(new ObservableOnSubscribe<List<Examination>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Examination>> e) {
                User user= DataBaseUtil.getUser(getActivity());

                CollectionDao collectionDao= MyApplication.getDaoSession().getCollectionDao();

                ExaminationDao examinationDao=MyApplication.getDaoSession().getExaminationDao();

                examCollectionList=collectionDao.queryBuilder().where(CollectionDao.Properties.UserId.eq(user.getId())).where(CollectionDao.Properties.LearningOrExam.eq("exam")).list();

                List<Examination> examinationList=new ArrayList<>();
                for(int i=0;i<examCollectionList.size();i++)
                {
                    Examination examination=examinationDao.queryBuilder().where(ExaminationDao.Properties.SortType.eq(examCollectionList.get(i).getSortType())).where(ExaminationDao.Properties.Id.eq(examCollectionList.get(i).getOriginId())).list().get(0);
                    examinationList.add(examination);
                }

                e.onNext(examinationList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Examination>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Examination> value) {

                        if(value.size()==0)
                        {
                            tv_showNothing.setVisibility(View.VISIBLE);
                        }else
                        {
                            tv_collectionNumber.setText("全部收藏("+value.size()+")");

                            ExamCollectionRecyclerViewAdapter adapter=new ExamCollectionRecyclerViewAdapter(getActivity(),value);

                            StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);

                            rv_collection.setLayoutManager(manager);
                            rv_collection.setAdapter(adapter);
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });





    }


}
