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
import view.TuiJianWebViewActivity;

import java.util.List;

import bean.TuiJian;

public class TuiJianRecyclerViewAdapter extends RecyclerView.Adapter<TuiJianRecyclerViewAdapter.ViewHolder> {

    private Context context;

    private List<TuiJian.TuiJianBean> list;

    private int itemCount;

    public TuiJianRecyclerViewAdapter(Context context, List<TuiJian.TuiJianBean> list, int itemCount)
    {
        this.context=context;
        this.list=list;
        this.itemCount=itemCount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_healthtuijian_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String html=list.get(holder.getAdapterPosition()).getHtml();
                Intent intent=new Intent(context, TuiJianWebViewActivity.class);
                intent.putExtra("html",html);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TuiJian.TuiJianBean tuiJianBean=list.get(position);
           Glide.with(context).load(tuiJianBean.getImageUrl()).placeholder(R.color.colorPlaceHolder).into(holder.iv_healthPhoto);
           holder.tv_title.setText(tuiJianBean.getTitle());
           holder.tv_subTitle.setText(tuiJianBean.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_healthPhoto;
        TextView tv_title;
        TextView tv_subTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_healthPhoto=(ImageView)itemView.findViewById(R.id.iv_healthPhoto);
            tv_title=(TextView)itemView.findViewById(R.id.tv_title);
            tv_subTitle=(TextView)itemView.findViewById(R.id.tv_subTitle);
        }
    }
}
