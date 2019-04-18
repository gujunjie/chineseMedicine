package view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.NoteDao;

import bean.Note;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import util.TimeUtil;

public class EditNoteActivity extends AppCompatActivity {

    @BindView(R.id.et_noteTitle)
    EditText etNoteTitle;
    @BindView(R.id.et_noteText)
    EditText etNoteText;

    private String noteTitle;

    private String noteText;

    private Note note;

    private boolean isSaveChange=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        ButterKnife.bind(this);

        initUI();


    }

    public void initUI() {
        note = getIntent().getParcelableExtra("note");
        noteTitle = note.getTitle();
        noteText = note.getNoteText();
        etNoteTitle.setText(noteTitle);
        etNoteText.setText(noteText);

    }

    @OnClick({R.id.btn_back, R.id.tv_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                tipsSaveChange();
                break;
            case R.id.tv_finish:

                if(!noteTitle.equals(etNoteTitle.getText().toString().trim())||!noteText.equals(etNoteText.getText().toString().trim()))
                {
                    if(etNoteTitle.getText().toString().trim().equals(""))
                    {
                        new SweetAlertDialog(EditNoteActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("笔记标题不可为空！")
                                .setContentText("给你的笔记取个标题吧")
                                .setConfirmText("好的")
                                .show();
                    }else
                    {
                        new SweetAlertDialog(EditNoteActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("保存成功！")
                                .setContentText("已保存对当前笔记的更改")
                                .setConfirmText("好的")
                                .show();
                        saveChange();
                        isSaveChange=true;
                    }
                }else{
                    new SweetAlertDialog(EditNoteActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("笔记尚未编辑更改！")
                            .setContentText("写下您此刻的想法")
                            .setConfirmText("好的")
                            .show();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
       tipsSaveChange();
    }

    public void saveChange()
    {

        NoteDao dao= MyApplication.getDaoSession().getNoteDao();
        note.setTitle(etNoteTitle.getText().toString().trim());
        note.setNoteText(etNoteText.getText().toString().trim());
        note.setTime(TimeUtil.getSystemTime());
        dao.update(note);
    }

    public void tipsSaveChange()
    {   if(isSaveChange)
        {
         finish();
        }else
         {
           if(!noteTitle.equals(etNoteTitle.getText().toString().trim())||!noteText.equals(etNoteText.getText().toString().trim()))
           {
               new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                       .setTitleText("是否保存对当前笔记的更改？")
                       .setConfirmButton("保存", new SweetAlertDialog.OnSweetClickListener() {
                           @Override
                           public void onClick(SweetAlertDialog sweetAlertDialog) {

                               if(etNoteTitle.getText().toString().trim().equals(""))
                               {

                                   sweetAlertDialog.setTitleText("笔记标题不可为空！");
                                   sweetAlertDialog.setContentText("给你的笔记取个标题吧");
                                   sweetAlertDialog.setConfirmText("好的");
                                   sweetAlertDialog.showCancelButton(false);
                                   sweetAlertDialog.setConfirmClickListener(null);

                               }else {
                                   sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                   sweetAlertDialog.setTitleText("保存成功！");
                                   sweetAlertDialog.setContentText("已保存对当前笔记的更改");
                                   sweetAlertDialog.setConfirmText("好的");
                                   sweetAlertDialog.showCancelButton(false);
                                   sweetAlertDialog.setConfirmClickListener(null);

                                   saveChange();
                                   isSaveChange=true;
                               }

                           }
                       })
                       .setCancelButton("取消", new SweetAlertDialog.OnSweetClickListener() {
                           @Override
                           public void onClick(SweetAlertDialog sweetAlertDialog) {
                               finish();
                           }
                       })
                       .show();

           }else
           {
               finish();
           }
       }
      }

}
