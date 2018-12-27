package presenter;

import java.util.List;

import base.BasePresenter;
import contract.SearchContract;
import model.SearchModel;

public class SearchPresenter extends BasePresenter<SearchContract.SearchView> implements SearchContract.SearchPresenter,
         SearchContract.onGetHotSearchListListner
                , SearchContract.onGetHistorySearchListListner{

    private SearchContract.SearchView searchView;

    private SearchContract.SearchModel searchModel;

    public SearchPresenter(SearchContract.SearchView searchView)
    {
        this.searchModel=new SearchModel();
        this.searchView=searchView;
    }



    @Override
    public void getHotSearchList() {
        searchModel.getHotSearchList(this);
    }

    @Override
    public void getHistorySearchList() {
        searchModel.getHistorySearchList(this);
    }



    @Override
    public void onGetHotSearchListFailure(String errorMessage) {
        searchView.showGetHotSearchListErrorMessage(errorMessage);
    }

    @Override
    public void onGetHotSearchListSuccess(List<String> list) {
         searchView.initHotSearchTag(list);
    }

    @Override
    public void onGetHistorySearchListSuccess(List<String> list) {
        searchView.initHistorySearchTag(list);
    }

    @Override
    public void onGetHistorySearchListFailure(String errorMessage) {
        searchView.showGetHistorySearchListErrorMessage(errorMessage);
    }
}
