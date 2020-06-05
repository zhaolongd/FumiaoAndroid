package com.fumiao.pay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fumiao.pay.R;
import com.fumiao.pay.bean.home.BannerBean;
import com.fumiao.pay.widget.BannerLayout;

import java.util.List;



public class WebBannerAdapter extends RecyclerView.Adapter<WebBannerAdapter.MzViewHolder> {

    private Context context;
    private List<BannerBean> bannerList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public WebBannerAdapter(Context context, List<BannerBean> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }

    @Override
    public MzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MzViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(MzViewHolder holder, final int position) {
        if (bannerList == null || bannerList.isEmpty())
            return;
        final int P = position % bannerList.size();
        String url = bannerList.get(P).getImage();
        ImageView img = (ImageView) holder.imageView;
        Glide.with(context).load(url).into(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(P);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bannerList != null) {
           return bannerList.size();
        }
       return 0;
    }


    class MzViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MzViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
