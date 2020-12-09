package cn.hnvist.client.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

import cn.hnvist.client.bean.BannerBean;

public class BannerImageAdapter extends BannerAdapter<BannerBean, BannerImageAdapter.BannerImageHolder> {
    private Context context;

    public BannerImageAdapter(List<BannerBean> data) {
        super(data);
    }


    @Override
    public BannerImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerImageHolder(imageView);
    }

    @Override
    public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
        Glide.with(context).load(data.getUrl()).centerCrop().into(holder.imageView);
    }


    static class BannerImageHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public BannerImageHolder(@NonNull ImageView imageView) {
            super(imageView);
            this.imageView = imageView;
        }
    }
}
