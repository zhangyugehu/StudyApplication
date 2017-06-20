package com.thssh.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MainViewHolder>{

    private Context mContext;
    private List<MainBean> mDatas;

    public MainRecycleAdapter(Context mContext, List<MainBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new MainViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        MainBean bean = mDatas.get(position);
        if(bean == null) { return; }
        holder.setTitle(bean.getTitle())
                .setDes(bean.getDes());
    }

    @Override
    public int getItemCount() {
        if(mDatas == null) { return 0; }
        return mDatas.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTv;
        private TextView desTv;

        public MainViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
            desTv = (TextView) itemView.findViewById(R.id.des_tv);
        }

        public MainViewHolder setTitle(String text){
            if(TextUtils.isEmpty(text)){ return this; }
            this.titleTv.setText(text);
            return this;
        }

        public MainViewHolder setDes(String text){
            if(TextUtils.isEmpty(text)){ return this; }
            this.desTv.setText(text);
            return this;
        }
    }
}
