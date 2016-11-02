package utouu.functiondemo.fragment.childfg;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;

/**
 * 视频播放
 */
public class VideoFragment extends BaseFragment {
    private PtrFrameLayout mPtrFrameLayout;
    private ListView mListView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {
        mListView = findView(R.id.lv_video_list);
        mPtrFrameLayout = findView(R.id.load_more_list_view_ptr_frame);
    }

    @Override
    protected void initData() {
        mPtrFrameLayout.setLoadingMinTime(1000);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                // here check list view, not content.
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });
    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {

    }

}
