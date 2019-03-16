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

import bean.ExamSyllabus;
import view.ExamActivity;
import com.example.abc.chinesemedicine.R;

import java.util.List;

public class ExamRecyclerViewAdapter extends RecyclerView.Adapter<ExamRecyclerViewAdapter.ViewHolder> {

    private List<ExamSyllabus> list;

    private Context context;

    public ExamRecyclerViewAdapter(List<ExamSyllabus> list, Context context)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_examitem_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, ExamActivity.class);
               intent.putExtra("sortType",list.get(holder.getAdapterPosition()).getTitle());
               context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         ExamSyllabus examSyllabus =list.get(position);
         holder.tv_title.setText(examSyllabus.getTitle());
         holder.tv_subTitle.setText(examSyllabus.getSubTitle());
        Glide.with(context).load(examSyllabus.getExamIcon()).placeholder(R.color.colorPlaceHolder).into(holder.iv_examIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_examIcon;
        TextView tv_title;
        TextView tv_subTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_examIcon=(ImageView)itemView.findViewById(R.id.iv_examIcon);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);
            tv_subTitle=(TextView)itemView.findViewById(R.id.tv_subTitle);
        }
    }

}
