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

public class GridAdapter extends DelegateAdapter.Adapter<GridAdapter.ViewHolder> {
    private List<String> dataList = new ArrayList<>();
    private LayoutHelper layoutHelper;

    public GridAdapter(LayoutHelper layoutHelper) {
        this.layoutHelper = layoutHelper;
//        this.dataList.addAll(dataList);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    public void addAll(List<String> dataList) {
        if (dataList == null)
            return;
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public String getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 3) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title, parent, false);
            return new TitleHolder(view);
        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_good, parent, false);
        return new GoodHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof TitleHolder) {
            ((TitleHolder) holder).tvTitle.setText(dataList.get(position));
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String item = dataList.get(position);
        if (item.contains("标题")) {
            return 3;
        }
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class TitleHolder extends ViewHolder {
        TextView tvTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    class GoodHolder extends ViewHolder {

        public GoodHolder(View itemView) {
            super(itemView);
        }
    }
}
