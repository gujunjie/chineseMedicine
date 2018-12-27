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

import bean.TuiJian;
import view.TuiJianWebViewActivity;

public class MoreRecyclerViewAdapter extends RecyclerView.Adapter<MoreRecyclerViewAdapter.ViewHolder>{


    private Context context;

    private List<TuiJian.TuiJianBean> list;

    public MoreRecyclerViewAdapter(Context context,List<TuiJian.TuiJianBean> list)
    {
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_more_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TuiJian.TuiJianBean tuiJianBean=list.get(holder.getAdapterPosition());
                Intent intent=new Intent(context, TuiJianWebViewActivity.class);
                intent.putExtra("html",tuiJianBean.getHtml());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TuiJian.TuiJianBean tuiJianBean=list.get(position);
        Glide.with(context).load(tuiJianBean.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.iv_morePhoto);
        holder.tv_title.setText(tuiJianBean.getTitle());
        holder.tv_subTitle.setText(tuiJianBean.getSubTitle());
        holder.tv_sortType.setText(tuiJianBean.getSortType());
        holder.tv_lastTime.setText(tuiJianBean.getLastTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_morePhoto;
        TextView tv_title;
        TextView tv_subTitle;
        TextView tv_sortType;
        TextView tv_lastTime;


        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_morePhoto=(ImageView)itemView.findViewById(R.id.iv_morePhoto);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);
            tv_subTitle=(TextView)itemView.findViewById(R.id.tv_subTitle);
            tv_sortType=(TextView)itemView.findViewById(R.id.tv_sortType);
            tv_lastTime=(TextView)itemView.findViewById(R.id.tv_lastTime);
        }
    }
}
