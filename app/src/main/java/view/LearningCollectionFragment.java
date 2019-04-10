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

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.CollectionDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;

import java.util.ArrayList;
import java.util.List;

import adapter.ExamCollectionRecyclerViewAdapter;
import adapter.LearningCollectionRecyclerViewAdapter;
import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Collection;
import bean.Examination;
import bean.MedicalBook;
import bean.Prescription;
import bean.SearchResult;
import bean.User;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;

public class LearningCollectionFragment extends Fragment {


    private TextView tv_collectionNumber;
    private TextView tv_showNothing;

    private RecyclerView rv_collection;

    private List<Collection> learningCollectionList;


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
        Observable.create(new ObservableOnSubscribe<List<SearchResult>>() {
            @Override
            public void subscribe(ObservableEmitter<List<SearchResult>> e) {
                User user= DataBaseUtil.getUser(getActivity());

                CollectionDao collectionDao= MyApplication.getDaoSession().getCollectionDao();

                learningCollectionList=collectionDao.queryBuilder().where(CollectionDao.Properties.UserId.eq(user.getId())).where(CollectionDao.Properties.LearningOrExam.eq("learning")).list();

                 List<SearchResult> list=new ArrayList<>();

                 for(int i=0;i<learningCollectionList.size();i++)
                 {
                     switch (learningCollectionList.get(i).getSortType())
                     {
                         case "中药学":
                             ChineseMedicineDao medicineDao=MyApplication.getDaoSession().getChineseMedicineDao();
                             ChineseMedicine medicine=medicineDao.queryBuilder().where(ChineseMedicineDao.Properties.Id.eq(learningCollectionList.get(i).getOriginId())).list().get(0);
                             SearchResult result1=new SearchResult();
                             result1.setImageUrl(medicine.getMedicineImageUrl());
                             result1.setName(medicine.getName());
                             result1.setSortType("中药学");
                             result1.setData(medicine.getData());
                             list.add(result1);
                             break;
                         case "方剂学":
                             PrescriptionDao prescriptionDao=MyApplication.getDaoSession().getPrescriptionDao();
                             Prescription prescription=prescriptionDao.queryBuilder().where(PrescriptionDao.Properties.Id.eq(learningCollectionList.get(i).getOriginId())).list().get(0);
                             SearchResult result2=new SearchResult();
                             result2.setImageUrl(prescription.getImageUrl());
                             result2.setName(prescription.getName());
                             result2.setSortType("方剂学");
                             result2.setData(prescription.getData());
                             list.add(result2);
                             break;
                         case "中成药":
                             ChinesePatentDrugDao chinesePatentDrugDao=MyApplication.getDaoSession().getChinesePatentDrugDao();
                             ChinesePatentDrug drug=chinesePatentDrugDao.queryBuilder().where(ChinesePatentDrugDao.Properties.Id.eq(learningCollectionList.get(i).getOriginId())).list().get(0);
                             SearchResult result3=new SearchResult();
                             result3.setImageUrl(drug.getImageUrl());
                             result3.setName(drug.getName());
                             result3.setSortType("中成药");
                             result3.setData(drug.getData());
                             list.add(result3);
                             break;
                         case "针灸学":
                             AcuPointDao acuPointDao=MyApplication.getDaoSession().getAcuPointDao();
                             AcuPoint acuPoint=acuPointDao.queryBuilder().where(AcuPointDao.Properties.Id.eq(learningCollectionList.get(i).getOriginId())).list().get(0);
                             SearchResult result4=new SearchResult();
                             result4.setImageUrl(acuPoint.getImageUrl());
                             result4.setName(acuPoint.getName());
                             result4.setSortType("针灸学");
                             result4.setData(acuPoint.getData());
                             list.add(result4);
                             break;
                         default:
                             MedicalBookDao medicalBookDao=MyApplication.getDaoSession().getMedicalBookDao();
                             MedicalBook medicalBook=medicalBookDao.queryBuilder().where(MedicalBookDao.Properties.Id.eq(learningCollectionList.get(i).getOriginId())).list().get(0);
                             SearchResult result5=new SearchResult();
                             result5.setImageUrl(medicalBook.getImageUrl());
                             result5.setName(medicalBook.getName());
                             result5.setSortType(medicalBook.getBookName());
                             result5.setData(medicalBook.getData());
                             list.add(result5);
                     }
                 }
                 e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<SearchResult> value) {

                        if(value.size()==0)
                        {
                            tv_showNothing.setVisibility(View.VISIBLE);
                        }else
                        {
                            tv_collectionNumber.setText("全部收藏("+value.size()+")");

                            LearningCollectionRecyclerViewAdapter adapter=new LearningCollectionRecyclerViewAdapter(getActivity(),value);

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
