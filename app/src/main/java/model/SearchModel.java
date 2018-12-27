package model;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;

import java.util.ArrayList;
import java.util.List;

import bean.ChineseMedicine;
import bean.HotSearch;
import bean.SearchHistory;
import contract.SearchContract;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchModel implements SearchContract.SearchModel {


    @Override
    public void getHotSearchList(final SearchContract.onGetHotSearchListListner listner) {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) {
                List<HotSearch> list=MyApplication.getDaoSession().getHotSearchDao().loadAll();
                List<String> hotSearchList=new ArrayList<>();
                for(int i=0;i<list.size();i++)
                {
                    hotSearchList.add(list.get(i).getName());
                }
                e.onNext(hotSearchList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {
                 listner.onGetHotSearchListSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                 listner.onGetHotSearchListFailure("获取热门搜索列表失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getHistorySearchList(final SearchContract.onGetHistorySearchListListner listner) {

        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) {
                List<SearchHistory> list=MyApplication.getDaoSession().getSearchHistoryDao().loadAll();
                List<String> historyList=new ArrayList<>();
                for(int i=0;i<list.size();i++)
                {
                    historyList.add(list.get(i).getHistory());
                }
                e.onNext(historyList);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> value) {
                       listner.onGetHistorySearchListSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                       listner.onGetHistorySearchListFailure("获取历史搜索列表失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
