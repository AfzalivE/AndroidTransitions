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
//        val screen: HomeScreen? = Flow.getKey(this)

//        val alarmView = LayoutInflater.from(context).inflate(R.layout.activity_alarm, null)
//
//        val transition = TransitionSet()
//            .addTransition(Slide(Gravity.BOTTOM)
//                .addTarget(alarmView.alarmView)
//                .setDuration(500)
//                .setInterpolator(LinearOutSlowInInterpolator()))
//            .addTransition(ChangeBounds()
//                .addTarget(homeView.home_fab)
//                .addTarget(alarmView.alarm_fab))
//            .addListener(object : TransitionListenerAdapter() {
//                override fun onTransitionEnd(transition: Transition) {
//                    viewGroup.removeView(homeView)
//                }
//            })

        homeFab.setOnClickListener({
            Flow.get(context).set(AlarmScreen())

//            TransitionManager.beginDelayedTransition(viewGroup, transition)
//            viewGroup.addView(alarmView)
//            homeView.removeView(homeView.home_fab)

        })
    }

}