package com.example.abc.chinesemedicine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abc.chinesemedicine.greendao.LearningProgressDao;
import com.example.abc.chinesemedicine.greendao.NoteDao;
import com.example.abc.chinesemedicine.greendao.UserDao;
import com.gyf.barlibrary.ImmersionBar;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import adapter.MyFragmentPageAdapter;
import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.LearningProgress;
import bean.MedicalBook;
import bean.MessageEvent;
import bean.Note;
import bean.Prescription;
import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import util.DataBaseUtil;
import util.SharePreferenceUtil;
import util.TimeUtil;
import view.LearningFragment;

public class LearningActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//标题栏
    @BindView(R.id.vp_learning)
    ViewPager vpLearning;//viewpager
    @BindView(R.id.tv_progress)
    TextView tvProgress;//学习进度
    @BindView(R.id.tv_allNumber)
    TextView tvAllNumber;//学习总条目
    @BindView(R.id.tv_currentNumber)
    TextView tvCurrentNumber;//当前学习条目
    @BindView(R.id.btn_collect)
    Button btnCollect;
    @BindView(R.id.tv_collectionText)
    TextView tvCollectionText;
    @BindView(R.id.tfl_allItem)
    TagFlowLayout tflAllItem;//所有item流式布局
    @BindView(R.id.slidingUpPanel)
    SlidingUpPanelLayout slidingUpPanel;



    private String sortType;//学习分类



    private boolean isCollected = false;//是否收藏

    private List<ChineseMedicine> medicineList;

    private List<Prescription> prescriptionList;

    private List<ChinesePatentDrug> chinesePatentDrugList;

    private List<MedicalBook> medicalBookList;

    private List<AcuPoint> acuPointList;

    private int currentLearningPosition = 0;//当前学习的位置

    private float currentLearningPercent = 0;//当前学习的百分比

    private String noteText="";//笔记内容

    private String noteTitle="";//笔记标题

    private EditText et_noteText;//笔记文字输入控件

    private EditText et_NoteTitle;//笔记标题输入控件



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);
        ButterKnife.bind(this);

        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor(R.color.colorAccent).init();

        initUI();


    }


    @SuppressWarnings("unchecked")
    public void initAllItemFlowLayout(int itemNumber) {

        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < itemNumber; i++) {
            list.add(i + 1);
        }


        tflAllItem.setAdapter(new TagAdapter(list) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tag = (TextView) LayoutInflater.from(LearningActivity.this).inflate(R.layout.allitemtag_layout, tflAllItem, false);
                tag.setText(o.toString());
                return tag;
            }
        });

        tflAllItem.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                int tag = list.get(position);
                vpLearning.setCurrentItem(tag - 1, true);
                slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                currentLearningPosition = position;
                return true;
            }
        });


    }

    public void initUI() {

        sortType = getIntent().getStringExtra("sortType");


        switch (sortType) {
            case "中药学":
                handleChineseMedicine();

                break;
            case "方剂学":
                handlePrescription();

                break;
            case "针灸学":
                handleAcuPoint();

                break;
            case "中成药":
                handleChinesePatentDrug();

                break;
            case "经典医书":
                handleMedicalBook();

                break;
        }


    }


    public void handleChineseMedicine() {

        Observable.create(new ObservableOnSubscribe<List<ChineseMedicine>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChineseMedicine>> e) {
                List<ChineseMedicine> list = MyApplication.getDaoSession().getChineseMedicineDao().loadAll();
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChineseMedicine>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ChineseMedicine> value) {

                        medicineList = value;
                        tvTitle.setText(value.get(0).getName());

                        List<Fragment> list = new ArrayList<>();


                        for (int i = 0; i < value.size(); i++) {
                            LearningFragment fragment = new LearningFragment();
                            fragment.setData(value.get(i).getMedicineImageUrl(), value.get(i).getData());
                            list.add(fragment);
                        }

                        vpLearning.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), list));
                        tvAllNumber.setText("/" + value.size());
                        loadLastLearningData();
                        initAllItemFlowLayout(value.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


        vpLearning.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(medicineList.get(position).getName());
                tvCurrentNumber.setText(position + 1 + "");
                currentLearningPercent = (float) (position + 1) / medicineList.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0");//格式化数值，返回String类型,保留一位小数
                tvProgress.setText("进度：" + df.format(currentLearningPercent) + " %");
                currentLearningPosition = position;
                if(noteText.equals(""))
                {

                }else
                {
                    showTipSaveLastItemNoteDialog();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void handlePrescription() {
        Observable.create(new ObservableOnSubscribe<List<Prescription>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Prescription>> e) {
                List<Prescription> list = MyApplication.getDaoSession().getPrescriptionDao().loadAll();
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Prescription>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Prescription> value) {

                        prescriptionList = value;
                        tvTitle.setText(value.get(0).getName());

                        List<Fragment> list = new ArrayList<>();


                        for (int i = 0; i < value.size(); i++) {
                            LearningFragment fragment = new LearningFragment();
                            fragment.setData(value.get(i).getImageUrl(), value.get(i).getData());
                            list.add(fragment);
                        }

                        vpLearning.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), list));
                        tvAllNumber.setText("/" + value.size());
                        loadLastLearningData();
                        initAllItemFlowLayout(value.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


        vpLearning.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(prescriptionList.get(position).getName());
                tvCurrentNumber.setText(position + 1 + "");
                currentLearningPercent = (float) (position + 1) / prescriptionList.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0");//格式化数值，返回String类型,保留一位小数
                tvProgress.setText("进度：" + df.format(currentLearningPercent) + " %");
                currentLearningPosition = position;
                if(noteText.equals(""))
                {

                }else
                {
                    showTipSaveLastItemNoteDialog();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void handleChinesePatentDrug() {
        Observable.create(new ObservableOnSubscribe<List<ChinesePatentDrug>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ChinesePatentDrug>> e) {
                List<ChinesePatentDrug> list = MyApplication.getDaoSession().getChinesePatentDrugDao().loadAll();
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChinesePatentDrug>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ChinesePatentDrug> value) {

                        chinesePatentDrugList = value;
                        tvTitle.setText(value.get(0).getName());

                        List<Fragment> list = new ArrayList<>();


                        for (int i = 0; i < value.size(); i++) {
                            LearningFragment fragment = new LearningFragment();
                            fragment.setData(value.get(i).getImageUrl(), value.get(i).getData());
                            list.add(fragment);
                        }

                        vpLearning.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), list));
                        tvAllNumber.setText("/" + value.size());
                        loadLastLearningData();
                        initAllItemFlowLayout(value.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


        vpLearning.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(chinesePatentDrugList.get(position).getName());
                tvCurrentNumber.setText(position + 1 + "");
                currentLearningPercent = (float) (position + 1) / chinesePatentDrugList.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0");//格式化数值，返回String类型,保留一位小数
                tvProgress.setText("进度：" + df.format(currentLearningPercent) + " %");
                currentLearningPosition = position;
                if(noteText.equals(""))
                {

                }else
                {
                    showTipSaveLastItemNoteDialog();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void handleAcuPoint() {
        Observable.create(new ObservableOnSubscribe<List<AcuPoint>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AcuPoint>> e) {
                List<AcuPoint> list = MyApplication.getDaoSession().getAcuPointDao().loadAll();
                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AcuPoint>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<AcuPoint> value) {

                        acuPointList = value;
                        tvTitle.setText(value.get(0).getName());

                        List<Fragment> list = new ArrayList<>();


                        for (int i = 0; i < value.size(); i++) {
                            LearningFragment fragment = new LearningFragment();
                            fragment.setData(value.get(i).getImageUrl(), value.get(i).getData());
                            list.add(fragment);
                        }

                        vpLearning.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), list));
                        tvAllNumber.setText("/" + value.size());
                        loadLastLearningData();
                        initAllItemFlowLayout(value.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


        vpLearning.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(acuPointList.get(position).getName());
                tvCurrentNumber.setText(position + 1 + "");
                currentLearningPercent = (float) (position + 1) / acuPointList.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0");//格式化数值，返回String类型,保留一位小数
                tvProgress.setText("进度：" + df.format(currentLearningPercent) + " %");
                currentLearningPosition = position;

                if(noteText.equals(""))
                {

                }else
                {
                    showTipSaveLastItemNoteDialog();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void handleMedicalBook() {
        Observable.create(new ObservableOnSubscribe<List<MedicalBook>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MedicalBook>> e) {

                List<MedicalBook> list = MyApplication.getDaoSession().getMedicalBookDao().loadAll();


                e.onNext(list);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MedicalBook>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MedicalBook> value) {
                        medicalBookList = value;

                        tvTitle.setText(value.get(0).getName());

                        List<Fragment> list = new ArrayList<>();


                        for (int i = 0; i < value.size(); i++) {
                            LearningFragment fragment = new LearningFragment();
                            fragment.setData(value.get(i).getImageUrl(), value.get(i).getData());
                            list.add(fragment);
                        }

                        vpLearning.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(), list));
                        tvAllNumber.setText("/" + value.size());
                        loadLastLearningData();
                        initAllItemFlowLayout(value.size());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                    }
                });


        vpLearning.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvTitle.setText(medicalBookList.get(position).getName());
                tvCurrentNumber.setText(position + 1 + "");
                currentLearningPercent = (float) (position + 1) / medicalBookList.size() * 100;
                DecimalFormat df = new DecimalFormat("0.0");//格式化数值，返回String类型,保留一位小数
                tvProgress.setText("进度：" + df.format(currentLearningPercent) + " %");
                currentLearningPosition = position;
                if(noteText.equals(""))
                {

                }else
                {
                    showTipSaveLastItemNoteDialog();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void saveLastLearningData() {
        String account = SharePreferenceUtil.getLoginAccount(this);

        UserDao dao = MyApplication.getDaoSession().getUserDao();
        LearningProgressDao dao1 = MyApplication.getDaoSession().getLearningProgressDao();
        List<User> list = dao.queryBuilder().where(UserDao.Properties.Name.eq(account)).list();
        User user = list.get(0);
        List<LearningProgress> progressList = user.getList();
        LearningProgress progress = null;
        for (int i = 0; i < progressList.size(); i++) {
            if (progressList.get(i).getLearningSubject().equals(sortType)) {
                progress = progressList.get(i);
                break;
            }
        }

        progress.setLastLearningPosition(currentLearningPosition);
        progress.setLastLearningPercent(currentLearningPercent);

        user.setLastestLearningSubject(sortType);
        user.setLastestLearningItem(tvTitle.getText().toString().trim());

        dao1.update(progress);
        dao.update(user);


    }//保存最近学习数据

    public void loadLastLearningData() {

        List<LearningProgress> progressList = DataBaseUtil.getLearningProgressList(this);
        for (int i = 0; i < progressList.size(); i++) {
            if (progressList.get(i).getLearningSubject().equals(sortType)) {
                LearningProgress progress = progressList.get(i);
                vpLearning.setCurrentItem(progress.getLastLearningPosition());
                break;
            }
        }


    }//加载最近学习数据

    @Override
    public void onBackPressed() {

        saveLastLearningData();//保存数据

        if(noteText.equals(""))
        {
            finish();
        }else
        {
            showTipSaveNoteDialog();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_back, R.id.btn_ChangeTextSize, R.id.btn_takeNote, R.id.btn_collect, R.id.tv_collectionText, R.id.rl_titleBar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                saveLastLearningData();//保存数据

                if(noteText.equals(""))
                {
                    finish();
                }else
                {
                    showTipSaveNoteDialog();
                }
                break;
            case R.id.btn_ChangeTextSize:
                showChangeTextDialog();
                break;
            case R.id.btn_takeNote:

                showTakeNotesDialog();

                break;
            case R.id.btn_collect:
                if (!isCollected) {
                    isCollected = true;
                    btnCollect.setBackgroundResource(R.mipmap.collection_pressed);
                    tvCollectionText.setText("已收藏");
                } else {
                    isCollected = false;
                    btnCollect.setBackgroundResource(R.mipmap.collection_normal);
                    tvCollectionText.setText("收藏");
                }
                break;
            case R.id.tv_collectionText:
                if (!isCollected) {
                    isCollected = true;
                    btnCollect.setBackgroundResource(R.mipmap.collection_pressed);
                    tvCollectionText.setText("已收藏");
                } else {
                    isCollected = false;
                    btnCollect.setBackgroundResource(R.mipmap.collection_normal);
                    tvCollectionText.setText("收藏");
                }
                break;
            case R.id.rl_titleBar:
                slidingUpPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                break;
        }
    }

    public void showChangeTextDialog() {
        final String[] items = {"超大", "大", "正常"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(LearningActivity.this);
        listDialog.setIcon(R.mipmap.textsize2);
        listDialog.setTitle("调整字体大小");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // which 下标从0开始

                EventBus.getDefault().post(new MessageEvent(which));

            }
        });
        listDialog.show();
    }

    public void showTakeNotesDialog() {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(LearningActivity.this);
        final View dialogView = LayoutInflater.from(LearningActivity.this).inflate(R.layout.dialog_layout, null);
        et_noteText = (EditText) dialogView.findViewById(R.id.et_noteText);
        et_noteText.setText(noteText);//加载草稿

        et_NoteTitle = (EditText) dialogView.findViewById(R.id.et_NoteTitle);
        if(noteTitle.equals(""))
        {
            et_NoteTitle.setText(tvTitle.getText().toString());
        }else
        {
            et_NoteTitle.setText(noteTitle);
        }

        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                noteText=et_noteText.getText().toString().trim();
                noteTitle=et_NoteTitle.getText().toString().trim();
                if (noteText.equals("")) {

                    new SweetAlertDialog(LearningActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("您还没有写任何东西呢")
                            .setContentText("写点笔记再保存吧！")
                            .setConfirmText("好的")
                            .show();
                } else if(noteTitle.equals("")){

                    new SweetAlertDialog(LearningActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("标题不可为空哦")
                            .setContentText("给你的笔记取个标题吧！")
                            .setConfirmText("好的")
                            .show();


                }else
                {
                    saveNote();

                    new SweetAlertDialog(LearningActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("保存成功!")
                            .setContentText("已存放至我的笔记")
                            .setConfirmText("好的")
                            .show();//提示保存成功
                }

            }
        });
        customizeDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                noteText = et_noteText.getText().toString().trim();//保存草稿

                noteTitle=et_NoteTitle.getText().toString().trim();


            }
        });
        customizeDialog.setCancelable(false);
        customizeDialog.show();
    }

    public void showTipSaveLastItemNoteDialog()
    {
        SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);

        sweetAlertDialog.setTitleText("温馨提示")
        .setContentText("是否保存上个章节的笔记？")
        .setConfirmText("保存")
        .setCancelText("取消")
        .setConfirmButton("保存", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("保存成功!");
                        sweetAlertDialog.setContentText("已存放至我的笔记");
                        sweetAlertDialog.showCancelButton(false);
                        sweetAlertDialog.setConfirmText("好的");
                        sweetAlertDialog.setConfirmClickListener(null);
                        saveNote();
                    }
                })

                .setCancelButton("取消", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        noteText="";//清空笔记
                        noteTitle="";
                    }
                })

                .show();
    }


    public void showTipSaveNoteDialog()
    {
        SweetAlertDialog sweetAlertDialog=new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);

        sweetAlertDialog.setTitleText("温馨提示")
                .setContentText("是否保存当前页面笔记？")
                .setConfirmText("保存")
                .setCancelText("取消")
                .setConfirmButton("保存", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        sweetAlertDialog.setTitleText("保存成功!");
                        sweetAlertDialog.setContentText("已存放至我的笔记");
                        sweetAlertDialog.showCancelButton(false);
                        sweetAlertDialog.setConfirmText("好的");
                        sweetAlertDialog.setConfirmClickListener(null);
                        saveNote();
                    }
                })

                .setCancelButton("取消", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.cancel();
                        noteText="";//清空笔记
                        noteTitle="";
                    }
                })

                .show();
    }


    public void saveNote()
    {
        NoteDao dao = MyApplication.getDaoSession().getNoteDao();
        Note note = new Note();
        note.setNoteText(noteText);
        note.setSubject(sortType);
        note.setTitle(noteTitle);
        note.setUserName(SharePreferenceUtil.getLoginAccount(LearningActivity.this));
        note.setTime(TimeUtil.getSystemTime());
        dao.insert(note);
        noteText="";//笔记清空
        noteTitle="";
    }




}
