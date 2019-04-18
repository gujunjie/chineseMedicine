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
import view.ErrorExaminationActivity;
import com.example.abc.chinesemedicine.R;

import java.util.List;

import bean.ErrorExamSyllabus;

public class ErrorExamRecyclerViewAdapter extends RecyclerView.Adapter<ErrorExamRecyclerViewAdapter.ViewHolder> {

    private List<ErrorExamSyllabus> list;

    private Context context;

    public ErrorExamRecyclerViewAdapter(List<ErrorExamSyllabus> list, Context context)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_errorexam_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent=new Intent(context, ErrorExaminationActivity.class);
                   intent.putExtra("sortType",list.get(holder.getAdapterPosition()).getSortType());
                   context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         ErrorExamSyllabus errorExamSyllabus =list.get(position);
         holder.tv_sortTypeText.setText(errorExamSyllabus.getSortType());
         holder.tv_errorNumber.setText(errorExamSyllabus.getErrorNumber()+"");
         Glide.with(context).load(errorExamSyllabus.getIcon()).placeholder(R.color.colorPlaceHolder).into(holder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_icon;
        TextView tv_sortTypeText;
        TextView tv_errorNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_icon=(ImageView)itemView.findViewById(R.id.iv_icon);
            tv_sortTypeText=(TextView)itemView.findViewById(R.id.tv_sortTypeText);
            tv_errorNumber=(TextView)itemView.findViewById(R.id.tv_errorNumber);
        }
    }

}
