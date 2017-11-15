package com.base.xiaopa.Adapter;

/**为RecycleView添加点击事件
 * @project OnRecyclerViewListener
 */
public interface OnRecyclerViewListener {
    void onItemClick(int position);
    boolean onItemLongClick(int position);
}
