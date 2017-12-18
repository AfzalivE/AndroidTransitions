package com.afzaln.viewanimations.flow

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.afzaln.viewanimations.flow.AppDispatcher.AlarmScreen
import flow.Flow
import kotlinx.android.synthetic.main.screen_home.view.*

class HomeView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        homeFab.setOnClickListener({
            Flow.get(context).set(AlarmScreen())

        })
    }

}