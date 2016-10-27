package utouu.functiondemo.framework.base.baselistviewadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Du_Li on 2016/8/29.18:41
 * 封装ViewHolder
 */
public class BaseListView_ViewHolder {
    /**
     * SparseArray<View> mViews; 相当于map集合
     * SparseArray是android里为<Interger,Object>这样的Hashmap而专门写的类,
     * 目的是提高内存效率，其核心是折半查找函数（binarySearch）
     * SparseArray有两个优点：
     * 1.避免了自动装箱（auto-boxing）
     * 2.数据结构不会依赖于外部对象映射。
     * <p/>
     * 添加键值对： public void put (int key , E value)    public void append(int key , E value)
     * 四个方法可以执行删除操作:
     * (1)public void delete (int key )
     * (2)public void remove (int key )
     * (3)public void removeAt (int key )
     * (4)public void clear ()
     * 修改数据的方法
     * (1)public void put (int key , E value)
     * (2)public void setValueAt(int index , E value)
     * <p/>
     * 查找数据
     * (1)public E get(int key)
     * (2)public E get(int key , E ValueIfKeyNotFound)
     */
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public BaseListView_ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);

    }

    /**
     * 复用View
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static BaseListView_ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        /**
         * convertView == null 的时候
         * 调用上面的构造方法  初始化
         * 不为null时  复用
         */
        if (convertView == null) {
            return new BaseListView_ViewHolder(context, parent, layoutId, position);
        } else {
            BaseListView_ViewHolder holder = (BaseListView_ViewHolder) convertView.getTag();
            holder.mPosition = position; // 更新position
            return holder;
        }
    }

    /**
     * 可以用来记录位置   拿到每条的position
     *
     * @return
     */

    public int getmPosition() {
        return mPosition;
    }

    /**
     * findViewById
     *
     * @param viewId 控件Id
     * @param <T>    泛型View
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 专门为TextView设置内容
     *
     * @param viewId  TextView 的Id
     * @param content 要设置的内容
     * @return
     */

    public BaseListView_ViewHolder setText(int viewId, String content) {
        TextView tv = getView(viewId);
        tv.setText(content);
        return this;
    }

//    public BaseListView_ViewHolder setImage(int viewId, String url){
//        ImageView imageView = getView(viewId);
//
//        ImageLoader.getInstance().disPlayImage(imageView,url);
//    }

    /**
     * facebook 图片
     *
     * @param viewId
     * @param url
     * @return
     */
//    public BaseListView_ViewHolder setSimpleDraweeView(int viewId, String url) {
//        SimpleDraweeView imageView = getView(viewId);
//
//        ImageLoader.getInstance().disPlayImage(imageView, url);
//
//        return this;
//    }


}
