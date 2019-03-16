package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.abc.chinesemedicine.EditNoteActivity;
import com.example.abc.chinesemedicine.MyApplication;
import com.example.abc.chinesemedicine.R;
import com.example.abc.chinesemedicine.greendao.NoteDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import bean.Note;


public class NoteRecyclerViewAdapter extends RecyclerView.Adapter<NoteRecyclerViewAdapter.ViewHolder> {

    private List<Note> list;

    private Context context;

    private boolean isShowCheckBox=false;


    private List<Boolean> isCheckedList;

    public NoteRecyclerViewAdapter(List<Note> list,List<Boolean> isCheckedList,Context context)
    {
        this.list=list;
        this.context=context;
        this.isCheckedList=isCheckedList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_noteitem_layout,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.rl_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditNoteActivity.class);
                intent.putExtra("note",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);


            }
        });



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

              Note note=list.get(position);
              holder.tv_noteTitle.setText(note.getTitle());
              holder.tv_subject.setText(note.getSubject());
              holder.tv_noteText.setText(note.getNoteText());
              holder.tv_time.setText(note.getTime());
              //holder.checkbox.setOnClickListener(null);
              holder.checkbox.setChecked(isCheckedList.get(position));
              holder.checkbox.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      isCheckedList.set(position,!isCheckedList.get(position));
                      Log.d("onclick", isCheckedList+"");
                  }
              });

              if(isShowCheckBox)
              {
                  holder.checkbox.setVisibility(View.VISIBLE);
              }else {

                  holder.checkbox.setVisibility(View.GONE);
              }





              int color=(position+1)%4;
              switch (color)
              {
                  case 1:
                      holder.rl_background.setBackgroundResource(R.mipmap.notebackground_blue);
                      break;
                  case 2:
                      holder.rl_background.setBackgroundResource(R.mipmap.notebackground_red);
                      break;
                  case 3:
                      holder.rl_background.setBackgroundResource(R.mipmap.notebackground_orange);
                      break;
                  case 0:
                      holder.rl_background.setBackgroundResource(R.mipmap.notebackground_green);
                      break;
              }

              switch (note.getSubject())
              {
                  case "中药学":
                      Glide.with(context).load(R.mipmap.noteitembackground_chinesemedicine).into(holder.iv_noteItemIcon);
                      break;
                  case "方剂学":
                      Glide.with(context).load(R.mipmap.noteitembackground_prescription).into(holder.iv_noteItemIcon);
                      break;
                  case "中成药":
                      Glide.with(context).load(R.mipmap.noteitembackground_chinesepatentdrug).into(holder.iv_noteItemIcon);
                      break;
                  case "经典医书":
                      Glide.with(context).load(R.mipmap.noteitembackground_medicalbook).into(holder.iv_noteItemIcon);
                      break;
                  case "针灸学":
                      Glide.with(context).load(R.mipmap.noteitembackground_acupoint).into(holder.iv_noteItemIcon);
                      break;
              }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void showCheckBox(boolean isShowCheckBox)
    {
        this.isShowCheckBox=isShowCheckBox;
        notifyDataSetChanged();
    }

    public void setAllCheckedOrAllUnChecked(boolean isAllChecked)
    {

        if(isAllChecked)
        {
            for(int i=0;i<list.size();i++)
            {
                isCheckedList.set(i,true);
            }


        }else
        {
            for(int i=0;i<list.size();i++)
            {
                isCheckedList.set(i,false);
            }


        }



        notifyDataSetChanged();

    }

    public boolean deleteCheckedItem()
    {
        List<Note> deleteNoteList=new ArrayList<>();
        List<Boolean> deleteIsCheckedList=new ArrayList<>();

        for(int i=0;i<isCheckedList.size();i++)
        {
            if(isCheckedList.get(i))
            {
                deleteNoteList.add(list.get(i));
                deleteIsCheckedList.add(isCheckedList.get(i));

            }
        }

        if(deleteIsCheckedList.size()==0)
        {
            Toast.makeText(context,"您没有选取任何笔记",Toast.LENGTH_SHORT).show();
            return false;
        }else
        {

            list.removeAll(deleteNoteList);
            isCheckedList.removeAll(deleteIsCheckedList);
            notifyDataSetChanged();//删除列表中的内容

            NoteDao dao= MyApplication.getDaoSession().getNoteDao();
            for(int i=0;i<deleteNoteList.size();i++)
            {
                dao.delete(deleteNoteList.get(i));
            }
            //删除对应数据库中的内容
            return true;
        }


    }


    static class ViewHolder extends RecyclerView.ViewHolder
    {
        View view;
        RelativeLayout rl_background;
        TextView tv_noteTitle;
        TextView tv_subject;
        TextView tv_noteText;
        TextView tv_time;
        ImageView iv_noteItemIcon;
        CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            tv_noteTitle=(TextView)itemView.findViewById(R.id.tv_noteTitle);
            tv_subject=(TextView)itemView.findViewById(R.id.tv_subject);
            tv_noteText=(TextView)itemView.findViewById(R.id.tv_noteText);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time);
            rl_background=(RelativeLayout) itemView.findViewById(R.id.rl_background);
            iv_noteItemIcon=(ImageView) itemView.findViewById(R.id.iv_noteItemIcon);
            checkbox=(CheckBox) itemView.findViewById(R.id.checkbox);

        }
    }
}
