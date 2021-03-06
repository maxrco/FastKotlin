package czh.fast.sample.mvp.ui.layout.activity

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import czh.fast.lib.utils.anko.smartRefreshLayout
import czh.fast.sample.mvp.ui.activity.MultiActivity
import czh.fast.sample.utils.ankoToolBar

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MultiActivityUI : AnkoComponent<MultiActivity> {

    lateinit var rcv: RecyclerView

    override fun createView(ui: AnkoContext<MultiActivity>): View = with(ui) {
        return verticalLayout {
            backgroundColor = Color.parseColor("#ffffff")
            gravity = Gravity.CENTER_HORIZONTAL

            ankoToolBar {
                title = "多type布局"
            }
            rcv = recyclerView {
                layoutManager = LinearLayoutManager(owner)
            }

        }
    }

}
