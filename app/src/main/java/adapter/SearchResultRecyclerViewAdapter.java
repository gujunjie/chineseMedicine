package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.ExamSearchResultActivity;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.ExaminationDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;

import java.util.List;

import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Examination;
import bean.MedicalBook;
import bean.Prescription;
import bean.SearchResult;
import view.ChineseMedicineDataActivity;

public class SearchResultRecyclerViewAdapter extends RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<SearchResult> list;

    public SearchResultRecyclerViewAdapter(Context context,List<SearchResult> list)
    {
        this.context=context;
        this.list=list;
    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView image;
        TextView name;
        TextView sortType;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            image=(ImageView)itemView.findViewById(R.id.iv_image);
            name=(TextView)itemView.findViewById(R.id.tv_name);
            sortType=(TextView)itemView.findViewById(R.id.tv_sortType);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_searchresult_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchResult result=list.get(holder.getAdapterPosition());
                switch (result.getSortType())
                {
                    case "中药":
                        List<ChineseMedicine> medicineList=MyApplication.getDaoSession().getChineseMedicineDao().queryBuilder().where(ChineseMedicineDao.Properties.Name.eq(result.getName())).list();
                        Intent intent=new Intent(context, ChineseMedicineDataActivity.class);
                        intent.putExtra("sortType","chineseMedicine");
                        intent.putExtra("object",medicineList.get(0));
                        context.startActivity(intent);
                        break;
                    case "中成药":
                        List<ChinesePatentDrug> drugList=MyApplication.getDaoSession().getChinesePatentDrugDao().queryBuilder().where(ChinesePatentDrugDao.Properties.Name.eq(result.getName())).list();
                        Intent intent2=new Intent(context, ChineseMedicineDataActivity.class);
                        intent2.putExtra("sortType","chinesePatentDrug");
                        intent2.putExtra("object",drugList.get(0));
                        context.startActivity(intent2);
                        break;
                    case "穴位":
                        List<AcuPoint> pointList=MyApplication.getDaoSession().getAcuPointDao().queryBuilder().where(AcuPointDao.Properties.Name.eq(result.getName())).list();
                        Intent intent3=new Intent(context, ChineseMedicineDataActivity.class);
                        intent3.putExtra("sortType","acuPoint");
                        intent3.putExtra("object",pointList.get(0));
                        context.startActivity(intent3);
                        break;
                    case "方剂":
                        List<Prescription> prescriptionList=MyApplication.getDaoSession().getPrescriptionDao().queryBuilder().where(PrescriptionDao.Properties.Name.eq(result.getName())).list();
                        Intent intent4=new Intent(context, ChineseMedicineDataActivity.class);
                        intent4.putExtra("sortType","prescription");
                        intent4.putExtra("object",prescriptionList.get(0));
                        context.startActivity(intent4);
                        break;
                    case "中药学专业知识(一)":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "中药学专业知识(二)":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "中药学综合知识与技能":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "药学专业知识(一)":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "药学专业知识(二)":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "药学综合知识与技能":
                        turnInExamSearchResultActivity(result);
                        break;
                    case "药事管理与法规":
                        turnInExamSearchResultActivity(result);
                        break;
                        default:
                            List<MedicalBook> bookList=MyApplication.getDaoSession().getMedicalBookDao().queryBuilder().where(MedicalBookDao.Properties.Name.eq(result.getName())).list();
                            Intent intent5=new Intent(context, ChineseMedicineDataActivity.class);
                            intent5.putExtra("sortType","medicalBook");
                            intent5.putExtra("object",bookList.get(0));
                            context.startActivity(intent5);

                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         SearchResult result=list.get(position);
        Glide.with(context).load(result.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.image);
        holder.name.setText(result.getName());
        holder.sortType.setText("标签分类 : "+result.getSortType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void turnInExamSearchResultActivity(SearchResult result)
    {
        List<Examination> examinationList=MyApplication.getDaoSession().getExaminationDao().queryBuilder().where(ExaminationDao.Properties.Title.eq(result.getName())).list();
        Intent intent=new Intent(context, ExamSearchResultActivity.class);
        intent.putExtra("exam",examinationList.get(0));
        context.startActivity(intent);
    }



}
