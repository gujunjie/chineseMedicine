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

import bean.Prescription;
import view.ChineseMedicineDataActivity;

public class PrescriptionRecyclerViewAdapter extends RecyclerView.Adapter<PrescriptionRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<Prescription> list;

    public PrescriptionRecyclerViewAdapter(Context context,List<Prescription> list)
    {
        this.list=list;
        this.context=context;
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
                intent.putExtra("sortType","prescription");
                intent.putExtra("object",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Prescription prescription=list.get(position);
         holder.tv_prescriptionName.setText(prescription.getName());
         Glide.with(context).load(prescription.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.iv_prescriptionPhoto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_prescriptionPhoto;
        TextView tv_prescriptionName;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_prescriptionPhoto=(ImageView)itemView.findViewById(R.id.iv_medicinePhoto);
            tv_prescriptionName=(TextView)itemView.findViewById(R.id.tv_medicineName);
        }
    }
}
