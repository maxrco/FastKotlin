package czh.fast.sample.mvp.ui.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.KeyEvent
import czh.fast.lib.utils.AppManager
import czh.fast.lib.widget.tablayout.listener.CustomTabEntity
import czh.fast.lib.widget.tablayout.listener.OnTabSelectListener
import czh.fast.sample.R

import czh.fast.sample.mvp.ui.layout.activity.MainUI
import org.jetbrains.anko.setContentView


import czh.fast.sample.base.AnkoActivity
import czh.fast.sample.mvp.model.TabEntity
import czh.fast.sample.mvp.ui.fragment.DbFragment
import czh.fast.sample.mvp.ui.fragment.NetFragment
import czh.fast.sample.mvp.ui.fragment.OtherFragment
import org.jetbrains.anko.toast

class MainActivity : AnkoActivity() {


    private val mTitles = arrayOf("网络", "数据库", "其他")
    private val mIconUnSelectIds = intArrayOf(R.mipmap.tab_massage_normal, R.mipmap.tab_optimization_normal, R.mipmap.tab_other_normal)
    private val mIconSelectIds = intArrayOf(R.mipmap.tab_massage_selected, R.mipmap.tab_optimization_selected, R.mipmap.tab_other_selected)
    private val mTabEntities: ArrayList<CustomTabEntity> = ArrayList()

    val ui = MainUI()
    override fun ankoLayout() {
        ui.setContentView(this)
    }

    override fun afterInitView() = with(ui) {
        (0 until mTitles.size)
                .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        initFragments()
        vp.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = mFragments[position]
            override fun getCount() = mFragments.size
        }
        vp.offscreenPageLimit = 2
        with(tab) {
            setTabData(mTabEntities)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onTabSelect(position: Int) {
                    vp.setCurrentItem(position, false)
                }

                override fun onTabReselect(position: Int) {

                }
            })
        }
    }

    lateinit var mFragments: MutableList<Fragment>
    private fun initFragments() {
        mFragments = ArrayList()
        mFragments.add(NetFragment.get())
        mFragments.add(DbFragment.get())
        mFragments.add(OtherFragment.get())
    }

    private var mExitTime: Long = 0
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                toast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                AppManager.AppExit()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
