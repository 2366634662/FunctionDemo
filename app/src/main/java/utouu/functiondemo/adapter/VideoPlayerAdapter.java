package utouu.functiondemo.adapter;

import android.content.Context;

import utouu.functiondemo.framework.base.baselistviewadapter.BaseListViewAdapter;
import utouu.functiondemo.framework.base.baselistviewadapter.BaseListView_ViewHolder;

/**
 * Created by Du_Li on 2016/10/27.
 * Desc:
 */

public class VideoPlayerAdapter extends BaseListViewAdapter {

    public VideoPlayerAdapter(Context context) {
        super(context);
    }

    @Override
    public void initDatas(BaseListView_ViewHolder holder, Object bean) {

    }

    @Override
    public int getItemLayoutId() {
        return 0;
    }
}
