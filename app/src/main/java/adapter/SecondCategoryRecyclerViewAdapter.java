package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import java.util.List;

import bean.ChinesePatentDrugSecondCategory;
import view.ChineseMedicineActivity;

public class SecondCategoryRecyclerViewAdapter extends RecyclerView.Adapter<SecondCategoryRecyclerViewAdapter.ViewHolder> {



    private List<ChinesePatentDrugSecondCategory> list;

    private Context context;

    public SecondCategoryRecyclerViewAdapter(Context context,List<ChinesePatentDrugSecondCategory> list)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sortitemview_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChineseMedicineActivity.class);
                intent.putExtra("sortType","chinesePatentDrug");
                intent.putExtra("type",list.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ChinesePatentDrugSecondCategory secondCategory=list.get(position);
            holder.tv_type.setText(secondCategory.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        TextView tv_type;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv_type=(TextView)itemView.findViewById(R.id.tv_type);

        }
    }
}
