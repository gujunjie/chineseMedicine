package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.AcuPointDao;
import com.example.abc.chinesemedicine.greendao.ChineseMedicineDao;
import com.example.abc.chinesemedicine.greendao.ChinesePatentDrugDao;
import com.example.abc.chinesemedicine.greendao.MedicalBookDao;
import com.example.abc.chinesemedicine.greendao.PrescriptionDao;

import java.util.ArrayList;
import java.util.List;

import bean.AcuPoint;
import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import bean.Examination;
import bean.MedicalBook;
import bean.Prescription;
import view.ExamActivity;

public class ExamCollectionRecyclerViewAdapter extends RecyclerView.Adapter<ExamCollectionRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<Examination> list;


    public ExamCollectionRecyclerViewAdapter(Context context, List<Examination> list)
    {
        this.context=context;
        this.list=list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_searchresult_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ExamActivity.class);
                intent.putExtra("sortType","我的收藏");
                intent.putExtra("position",holder.getAdapterPosition());
                intent.putParcelableArrayListExtra("examCollectionList", (ArrayList<? extends Parcelable>) list);
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Examination examination=list.get(position);

        Glide.with(context).load(examination.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.image);
        holder.name.setText(examination.getTitle());
        holder.sortType.setText("标签分类 : "+examination.getSortType());


    }

    @Override
    public int getItemCount() {
        return list.size();
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

}
