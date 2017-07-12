package org.chm.ktld


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_coordinator.*
import java.util.*

/**
 * Created by ason on 2017/6/26.
 */
class CoordinatorActivity : FragmentActivity() {

    internal lateinit var mFragments: MutableList<Fragment>

    private var mTitles = arrayOf("主页", "微博", "相册")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinator)

        setupViewPager()
    }

    private fun setupViewPager() {

        mFragments = ArrayList<Fragment>()
//        for (i in mTitles.indices) {
//            val listFragment = ListFragment.newInstance(mTitles[i])
//            mFragments.add(listFragment)
//        }
        mTitles.map {
            val listFragment = ListFragment.newInstance(it)
            mFragments.add(listFragment)
        }

        // 为ViewPager设置适配器
        val adapter = ContentPagerAdapter(mFragments, mTitles)
        viewpager.adapter = adapter

        //  第三步：将ViewPager与TableLayout 绑定在一起
        tabLayout.setupWithViewPager(viewpager)
    }

    internal inner class ContentPagerAdapter(val tabFragments: List<Fragment>, val tabIndicators: Array<String>)
        : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment {
            return tabFragments[position]
        }

        override fun getCount(): Int {
            return tabIndicators.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabIndicators[position]
        }
    }
}