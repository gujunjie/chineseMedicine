package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abc.chinesemedicine.R;

import java.util.List;

import bean.StudyTimeLine;

public class StudyTimeLineRecyclerViewAdapter extends RecyclerView.Adapter<StudyTimeLineRecyclerViewAdapter.ViewHolder> {



    private Context context;

    private List<StudyTimeLine> list;

    public StudyTimeLineRecyclerViewAdapter(Context context,List<StudyTimeLine> list)
    {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_studytimeline_item,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            StudyTimeLine timeLine=list.get(position);
            holder.tv_time.setText(timeLine.getTime());
            holder.tv_activityText.setText(timeLine.getActivityText());

            if(position==0)
            {
                holder.iv_circle.setImageResource(R.drawable.circle_pink);
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        ImageView iv_circle;
        TextView tv_time;
        TextView tv_activityText;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            iv_circle=(ImageView)itemView.findViewById(R.id.iv_circle);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time);
            tv_activityText=(TextView)itemView.findViewById(R.id.tv_activityText);
        }
    }
}
