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

import bean.MedicalBook;
import view.ChineseMedicineDataActivity;

public class MedicalBookRecyclerViewAdapter extends RecyclerView.Adapter<MedicalBookRecyclerViewAdapter.ViewHolder>{

    private Context context;

    private List<MedicalBook> list;

    public MedicalBookRecyclerViewAdapter(Context context,List<MedicalBook> list)
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
                intent.putExtra("sortType","medicalBook");
                intent.putExtra("object",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicalBook book=list.get(position);
        holder.tv_medicalBookName.setText(book.getName());
        Glide.with(context).load(book.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.iv_medicalBookPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_medicalBookPhoto;
        TextView tv_medicalBookName;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_medicalBookPhoto=(ImageView)itemView.findViewById(R.id.iv_medicinePhoto);
            tv_medicalBookName=(TextView)itemView.findViewById(R.id.tv_medicineName);
        }
    }
}
