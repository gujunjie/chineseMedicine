package contract;

import java.util.List;

public interface SearchContract {


    interface SearchModel {

        void getHotSearchList(onGetHotSearchListListner listner);

        void getHistorySearchList(onGetHistorySearchListListner listner);

    }

    interface SearchView {

        void initAutoCompleteTextView(List<String> list);//初始化搜索栏


        void initHotSearchTag(List<String> list);//初始化热门搜索栏

        void showGetHotSearchListErrorMessage(String message);

        void initHistorySearchTag(List<String> list);//初始化历史搜索栏

        void showGetHistorySearchListErrorMessage(String message);
    }

    interface SearchPresenter {


        void getHotSearchList();//获取热门搜索列表

        void getHistorySearchList();//获取历史搜索列表

    }


    interface onGetHotSearchListListner
    {
        void onGetHotSearchListSuccess(List<String> list);
        void onGetHotSearchListFailure(String errorMessage);
    }

    interface onGetHistorySearchListListner
    {
        void onGetHistorySearchListSuccess(List<String> list);
        void onGetHistorySearchListFailure(String errorMessage);
    }


}
