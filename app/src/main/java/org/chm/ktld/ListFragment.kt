package org.chm.ktld

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast


import org.chm.ktld.BaseFragment
import org.chm.ktld.R

import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_list.*
import org.chm.ktld.adpater.MyAdapter

/**
 * Created by ason on 2017/6/26.
 */

class ListFragment(override val layoutId: Int) : BaseFragment() {

    internal var mRecyclerView: RecyclerView? = null
    private var title:String? = "测试"

    internal var mDatas: MutableList<String> = ArrayList()
    private var mAdapter: MyAdapter? = null
//    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null

    override fun initView(view: View?) {

    }
    //这个使用kotlin-android-extensions, 防止view为空,所以放在onViewCreated方法中
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arguments = arguments //property access syntax

        title = arguments?.getString(KEY)

        val layoutManager = LinearLayoutManager(mContext)

        recyclerView!!.layoutManager = layoutManager

        (0..49).map {
            var s = String.format("我是第%d个" + title, it)
            mDatas.add(s)
        }

//        for (i in 0..49) {
//            val s = String.format("我是第%d个" + title, i)
//            mDatas.add(s)
//        }

        mAdapter = MyAdapter(mContext, mDatas)
        recyclerView!!.adapter = mAdapter

        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.postDelayed({
                swipeRefreshLayout!!.isRefreshing = false
                Toast.makeText(mContext, "刷新完成", Toast.LENGTH_SHORT).show()
            }, 1200)
        }
    }

    fun tooglePager(isOpen: Boolean) {
        if (isOpen) {
            setRefreshEnable(false)
            scrollToFirst(false)
        } else {
            setRefreshEnable(true)
        }
    }

    fun scrollToFirst(isSmooth: Boolean) {
        if (isSmooth) {
            mRecyclerView?.smoothScrollToPosition(0)
        } else {
            mRecyclerView?.scrollToPosition(0)
        }
    }

    fun setRefreshEnable(enabled: Boolean) {
        swipeRefreshLayout!!.isEnabled = enabled
    }

//    override fun getLayoutId(): Int {
//        return R.layout.fragment_list
//    }

    override fun fetchData() {

    }

    companion object {
        private val KEY = "key"

        fun newInstance(title: String): ListFragment {
            val fragment = ListFragment(R.layout.fragment_list)
            val bundle = Bundle()
            bundle.putString(KEY, title)
            fragment.arguments = bundle
            return fragment
        }
    }
}