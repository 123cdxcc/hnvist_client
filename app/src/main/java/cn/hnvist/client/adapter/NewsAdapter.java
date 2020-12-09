package cn.hnvist.client.adapter;

import android.content.Context;
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

import cn.hnvist.client.R;
import cn.hnvist.client.bean.NewsBean;

public class NewsAdapter extends BaseAdapter {

    private List<NewsBean> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public NewsAdapter(Context context, List<NewsBean> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public NewsBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setNewsBean(List<NewsBean> data){
        this.objects = data;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_news_list, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((NewsBean)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(NewsBean object, ViewHolder holder) {
        //TODO implement
        Glide.with(context).load(object.getImgUrl()).centerCrop().into(holder.itemNewsListImg);
        holder.itemNewsListTitle.setText(object.getTitle());
        String desc = object.getDesc();
        if (desc.length() > 20){
            desc = desc.substring(0, 21);
        }
        desc += "……";
        holder.itemNewsListDesc.setText(desc);
        holder.itemNewsListPushTime.setText(object.getPushTime());
    }

    protected class ViewHolder {
        private ImageView itemNewsListImg;
        private TextView itemNewsListTitle;
        private TextView itemNewsListDesc;
        private TextView itemNewsListPushTime;

        public ViewHolder(View view) {
            itemNewsListImg = (ImageView) view.findViewById(R.id.item_news_list_img);
            itemNewsListTitle = (TextView) view.findViewById(R.id.item_news_list_title);
            itemNewsListDesc = (TextView) view.findViewById(R.id.item_news_list_desc);
            itemNewsListPushTime = (TextView) view.findViewById(R.id.item_news_list_pushTime);
        }
    }
    /*private List<NewsBean> data;
    private Context context;

    public NewsAdapter(List<NewsBean> data) {
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
        String desc = data.get(position).getDesc();
        if (desc.length() > 20){
            desc = desc.substring(0, 21);
        }
        desc += "……";
        holder.itemNewsListDesc.setText(desc);
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

    *//**
     * 定义RecyclerView选项单击事件的回调接口
     *//*
    public interface OnItemClickListener{
        void onItemClick(View view, int position, NewsBean data);
    }*/
}
