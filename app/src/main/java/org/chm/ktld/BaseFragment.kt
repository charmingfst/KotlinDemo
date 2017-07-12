package org.chm.ktld

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ason on 2017/6/26.
 */

abstract class BaseFragment : Fragment() {

    protected var mView: View? = null

    /**
     * 表示View是否被初始化
     */
    protected var isViewInitiated: Boolean = false
    /**
     * 表示对用户是否可见
     */
    protected var isVisibleToUser: Boolean = false
    /**
     * 表示数据是否初始化
     */
    protected var isDataInitiated: Boolean = false
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mView == null) {
            mContext = context
            mView = View.inflate(mContext, layoutId, null)
            initView(mView)
        } else {
            // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，
            // 要不然会发生这个rootview已经有parent的错误。
            //Note that despite the fact that the right-hand side of as? is a non-null type String the result of the cast is nullable.
            val parent = mView?.parent as ViewGroup?
            parent?.removeView(mView)
        }
        return mView
    }

    protected abstract open fun initView(view: View?)

    protected abstract val layoutId: Int


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isViewInitiated = true
        initListener()
        initData()
        prepareFetchData()
    }

    protected fun initListener() {}

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        prepareFetchData()
    }

    abstract fun fetchData()

    /***

     * @param forceUpdate 表示是否在界面可见的时候是否强制刷新数据
     * *
     * @return
     */
    @JvmOverloads fun prepareFetchData(forceUpdate: Boolean = false): Boolean {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            //  界面可见的时候再去加载数据
            fetchData()
            isDataInitiated = true
            return true
        }
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    protected fun initData() {}

}
