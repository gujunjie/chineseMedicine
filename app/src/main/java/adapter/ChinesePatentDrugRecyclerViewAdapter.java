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
import com.example.abc.chinesemedicine.R;

import java.util.List;

import bean.ChineseMedicine;
import bean.ChinesePatentDrug;
import view.ChineseMedicineDataActivity;

public class ChinesePatentDrugRecyclerViewAdapter extends RecyclerView.Adapter<ChinesePatentDrugRecyclerViewAdapter.ViewHolder>{

    private Context context;

    private List<ChinesePatentDrug> list;

    public ChinesePatentDrugRecyclerViewAdapter(Context context,List<ChinesePatentDrug> list)
    {
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_chinesemedicine_item,parent,false);

       final ViewHolder holder=new ViewHolder(view);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChineseMedicineDataActivity.class);
                intent.putExtra("sortType","chinesePatentDrug");
                intent.putExtra("object",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChinesePatentDrug drug=list.get(position);
        holder.tv_medicineName.setText(drug.getName());
        Glide.with(context).load(drug.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.iv_medicinePhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_medicinePhoto;
        TextView tv_medicineName;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_medicinePhoto=(ImageView)itemView.findViewById(R.id.iv_medicinePhoto);
            tv_medicineName=(TextView)itemView.findViewById(R.id.tv_medicineName);
        }
    }
}
