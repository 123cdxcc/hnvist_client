package cn.hnvist.client.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.hnvist.client.R;
import cn.hnvist.client.bean.NewsBean;

public class NewsItemAdapter extends BaseAdapter {

    private List<NewsBean> data;
    private Context context;

    public NewsItemAdapter(List<NewsBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemNewsListImg = convertView.findViewById(R.id.item_news_list_img);
            viewHolder.itemNewsListTitle = convertView.findViewById(R.id.item_news_list_title);
            viewHolder.itemNewsListDesc = convertView.findViewById(R.id.item_news_list_desc);
            viewHolder.itemNewsListPushTime = convertView.findViewById(R.id.item_news_list_pushTime);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(data.get(position).getImgUrl()).into(viewHolder.itemNewsListImg);
        viewHolder.itemNewsListTitle.setText(data.get(position).getTitle());
        viewHolder.itemNewsListDesc.setText(data.get(position).getDesc());
        viewHolder.itemNewsListPushTime.setText(data.get(position).getPushTime());
        return convertView;
    }

    class ViewHolder{
        ImageView itemNewsListImg;
        TextView itemNewsListTitle;
        TextView itemNewsListDesc;
        TextView itemNewsListPushTime;
    }

}
