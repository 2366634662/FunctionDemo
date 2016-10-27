package utouu.functiondemo.framework.base.baserecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Du_Li on 2016/8/30.20:04
 */
public abstract class BaseRecyclerCommonAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected Context mContext;
    protected List<T> mDatas;

    public BaseRecyclerCommonAdapter(Context context) {
        mContext = context;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseRecyclerViewHolder viewHolder = BaseRecyclerViewHolder.getViewHolder(mContext, parent, mLayoutId());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        initdatas(holder, mDatas.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void initdatas(BaseRecyclerViewHolder holder, T bean, int position);

    public abstract int mLayoutId();


}
