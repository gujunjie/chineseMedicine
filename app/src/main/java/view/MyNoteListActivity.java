package view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.NoteDao;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import adapter.NoteRecyclerViewAdapter;
import bean.Note;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import util.SharePreferenceUtil;

public class MyNoteListActivity extends AppCompatActivity {


    @BindView(R.id.rv_myNote)
    RecyclerView rvMyNote;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_allSelected)
    Button btnAllSelected;
    @BindView(R.id.tv_showNothing)
    TextView tvShowNothing;
    @BindView(R.id.btn_edit)
    Button btnEdit;

    private NoteRecyclerViewAdapter adapter;

    private boolean isShowCheckBox;

    private boolean isAllChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note_list);
        ButterKnife.bind(this);

        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserNote();
    }

    public void getUserNote()
    {
        String userName = SharePreferenceUtil.getLoginAccount(this);
        List<Note> list = MyApplication.getDaoSession().getNoteDao().queryBuilder().where(NoteDao.Properties.UserName.eq(userName)).list();

        if (list.size() == 0) {
            tvShowNothing.setVisibility(View.VISIBLE);
            btnEdit.setClickable(false);

        } else {
            tvShowNothing.setVisibility(View.GONE);
            btnEdit.setClickable(true);
            List<Boolean> isCheckedList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                isCheckedList.add(false);
            }
            adapter = new NoteRecyclerViewAdapter(list, isCheckedList, this);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            rvMyNote.setAdapter(adapter);
            rvMyNote.setLayoutManager(manager);
        }
    }

    public void initUI() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();


    }


    @OnClick({R.id.btn_back, R.id.btn_edit, R.id.btn_delete, R.id.btn_allSelected})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_edit:
                if (isShowCheckBox) {
                    isShowCheckBox = false;
                    btnAllSelected.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.GONE);

                } else {
                    isShowCheckBox = true;
                    btnAllSelected.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                }
                adapter.showCheckBox(isShowCheckBox);
                break;
            case R.id.btn_delete:

                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(this);
                normalDialog.setMessage("是否删除所选的笔记？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean isDeleteSuccess = adapter.deleteCheckedItem();
                                if (isDeleteSuccess) {
                                    btnAllSelected.setVisibility(View.GONE);
                                    btnDelete.setVisibility(View.GONE);
                                    adapter.showCheckBox(false);
                                }
                                if(adapter.getItemCount()==0)
                                {
                                    tvShowNothing.setVisibility(View.VISIBLE);
                                    btnEdit.setClickable(false);
                                }
                            }
                        });
                normalDialog.setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do

                            }
                        });
                // 显示
                normalDialog.show();
                break;
            case R.id.btn_allSelected:
                if (isAllChecked) {
                    isAllChecked = false;
                } else {
                    isAllChecked = true;

                }
                adapter.setAllCheckedOrAllUnChecked(isAllChecked);


                break;
        }
    }
}
