package com.czx.test.vlayouttest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzixue on 2017/4/9.
 */

public class CommonAdapter extends DelegateAdapter.Adapter<CommonAdapter.ViewHolder> {
    private List<String> dataList = new ArrayList<>();
    private LayoutHelper layoutHelper;

    public CommonAdapter(LayoutHelper layoutHelper) {
        this.layoutHelper = layoutHelper;
    }

    public void addAll(List<String> dataList) {
        if (dataList == null)
            return;
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
        return new OneHolder(view);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public class OneHolder extends ViewHolder {

        public OneHolder(View itemView) {
            super(itemView);
        }
    }

    public class TwoHolder extends ViewHolder {

        public TwoHolder(View itemView) {
            super(itemView);
        }
    }

}
