package utouu.functiondemo.framework.base.baserecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Du_Li on 2016/8/30.19:57
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }


    public static BaseRecyclerViewHolder getViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = View.inflate(context, layoutId, null);
        BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(itemView);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getViewId(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public BaseRecyclerViewHolder setText(int viewId, String text) {
        TextView tv = getViewId(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecyclerViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getViewId(viewId);
        view.setImageResource(resId);
        return this;
    }

    public BaseRecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getViewId(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}

