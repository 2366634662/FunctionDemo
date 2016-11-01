package utouu.functiondemo.fragment.childfg;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import butterknife.BindView;
import utouu.functiondemo.R;
import utouu.functiondemo.framework.base.BaseFragment;

/**
 * 视频播放
 */
public class VideoFragment extends BaseFragment {


    @BindView(R.id.lv_video_player)
    ListView lvVideoPlayer;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView(View convertView, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public void onClick(View v) {

    }

}
