package com.wjx.android.smalltools.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.wjx.android.smalltools.R

import com.wjx.android.smalltools.bean.MainData

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/12 17:09
 */
class MainAdapter(layoutId: Int = R.layout.item_main) :
    BaseQuickAdapter<MainData, BaseViewHolder>(layoutId) {
    override fun convert(holder: BaseViewHolder, item: MainData) {
        holder?.let {
            holder.setImageResource(R.id.item_main_image, item.resId)
            holder.setText(R.id.item_main_text, item.name)
        }
    }

}