package cn.hnvist.client.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import cn.hnvist.client.R;
import cn.hnvist.client.bean.NewsBean;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsViewHolder> {
    private List<NewsBean> data;
    private Context context;

    public NewsItemAdapter(List<NewsBean> data) {
        this.data = data;
    }

    public void setNewsBean(List<NewsBean> data){
        this.data = data;
        notifyDataSetChanged();
    }


    static class NewsViewHolder extends RecyclerView.ViewHolder{
        ImageView itemNewsListImg;
        TextView itemNewsListTitle;
        TextView itemNewsListDesc;
        TextView itemNewsListPushTime;
        public NewsViewHolder(@NonNull View itemView) {

            super(itemView);
            itemNewsListImg = itemView.findViewById(R.id.item_news_list_img);
            itemNewsListTitle = itemView.findViewById(R.id.item_news_list_title);
            itemNewsListDesc = itemView.findViewById(R.id.item_news_list_desc);
            itemNewsListPushTime = itemView.findViewById(R.id.item_news_list_pushTime);
        }
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getImgUrl()).centerCrop().into(holder.itemNewsListImg);
        holder.itemNewsListTitle.setText(data.get(position).getTitle());
        holder.itemNewsListDesc.setText(data.get(position).getDesc());
        holder.itemNewsListPushTime.setText(data.get(position).getPushTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position, data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private OnItemClickListener onItemClickListener;//声明一下这个接口
    //提供setter方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 定义RecyclerView选项单击事件的回调接口
     */
    public interface OnItemClickListener{
        void onItemClick(View view, int position, NewsBean data);
    }
}
