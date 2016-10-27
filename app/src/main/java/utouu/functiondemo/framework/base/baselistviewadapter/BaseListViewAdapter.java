package utouu.functiondemo.framework.base.baselistviewadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * ListView适配器
 * Created by Du_Li on 2016/8/29.19:20
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {

    protected Context context;// 上下文
    protected List<T> datas;//数据源

    public BaseListViewAdapter(Context context) {
        this.context = context;
    }

    public void setListDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //拿到ViewHolder  实例化
        BaseListView_ViewHolder holder = BaseListView_ViewHolder.getViewHolder(context, convertView, parent, getItemLayoutId(), position);

        initDatas(holder, getItem(position));

        return holder.getConvertView();
    }

    public abstract void initDatas(BaseListView_ViewHolder holder, T bean);

    /**
     * @return item 布局Id
     */
    public abstract int getItemLayoutId();

}
