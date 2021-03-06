package com.hema.assist.feature.apply.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hema.assist.common.views.BannerLayout;
import com.wtw.p2p.R;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 卡片认证适配器
 * Created by 河马安卓 on 2018/4/20.
 */

public class CardCertificationAdapter extends RecyclerView.Adapter<CardCertificationAdapter.ChildViewHolder> {

    private Context mContext;

    private List<ItemModel> mData;

    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;

    public CardCertificationAdapter(Context context, List<ItemModel> list) {
        this.mContext = context;
        this.mData = list;
    }


    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }


    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChildViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_card_image, parent, false));
    }


    @Override
    public void onBindViewHolder(ChildViewHolder holder, final int position) {

        if (mData == null && mData.size() <= 0) {
            return;
        }

        // 去重
        final int p = position % mData.size();

        holder.setImageView(p);

    }


    @Override
    public int getItemCount() {
        if (mData != null)
            return mData.size();
        else
            return 0;
    }


    class ChildViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_card)
        ImageView ivCard;
        @BindView(R.id.tv_msg)
        TextView tvMsg;
        @BindView(R.id.btn_authentication)
        TextView btnAuthentication;

        public ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setImageView(final int position) {
            tvTitle.setText(String.format("%s", mContext.getResources().getString(mData.get(position).title)));
            ivCard.setImageDrawable(mContext.getResources().getDrawable(mData.get(position).img));
            tvMsg.setText(String.format("%s", mData.get(position).msg));
            btnAuthentication.setText(String.format("%s", mContext.getResources().getString(mData.get(position).btn)));
            btnAuthentication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClick.onItemClick(v, position, mData.get(position));
                }
            });
        }
    }

    public static class ItemModel implements Serializable {

        public int title;
        public int img;
        public String msg;
        public int btn;

        // 标识是否已经认证 true 已经认证 ，false，没有认证
        public boolean isSign;

        public ItemModel(int title, int img, String msg, int btn, boolean isSign) {
            this.title = title;
            this.img = img;
            this.msg = msg;
            this.btn = btn;
            this.isSign = isSign; //  标识是否已经 认证
        }

    }

    public interface ItemClick {
        void onItemClick(View v, int position, ItemModel model);
    }

    private ItemClick mItemClick;

    public void setOnItemClick(ItemClick itemClick) {
        this.mItemClick = itemClick;
    }

}
