package com.afzaln.viewanimations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.*
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_circles.*

/**
 * Created by afzal on 2018-01-16.
 */
class CircleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circles)

        start.setOnClickListener({
//            applyPropertyAnimations()
            applyTransition()
        })

//        setupConstraintSetAnimations()
    }

    private fun applyTransition() {
        val sizeTransition = ChangeBounds().setInterpolator(FastOutSlowInInterpolator())
        val fadeTransition = Fade()

        val bothTransition = TransitionSet()
            .addTransition(sizeTransition)
            .addTransition(fadeTransition)

        val lp = inner_circle.layoutParams
        lp.height = (lp.height * 1.2).toInt()
        lp.width = (lp.width * 1.2).toInt()
        inner_circle.layoutParams = lp

        TransitionManager.beginDelayedTransition(root, sizeTransition)
        inner_circle.visibility = View.GONE
    }

    private fun setupConstraintSetAnimations() {
        val idleSet = ConstraintSet()
        val startedSet = ConstraintSet()

        idleSet.clone(root)
        startedSet.apply {
            clone(root)

//            setVisibility(R.id.outer_circle, View.GONE)
//            setVisibility(R.id.start, View.GONE)
            constrainHeight(R.id.inner_circle, resources.getDimension(R.dimen.dimen_260).toInt())
            constrainWidth(R.id.inner_circle, resources.getDimension(R.dimen.dimen_260).toInt())
//            setVisibility(R.id.inner_circle, View.GONE)
        }

        val transition = TransitionSet()
            .setOrdering(TransitionSet.ORDERING_SEQUENTIAL)
            .addTransition(AutoTransition()
                .addTarget(inner_circle)
                .setDuration(1000))
            .addTransition(Fade()
                .addTarget(inner_circle)
                .setDuration(2000)
                .setInterpolator(FastOutSlowInInterpolator()))

        start.setOnClickListener({
            TransitionManager.beginDelayedTransition(root, transition)

            startedSet.applyTo(root)
        })
    }

    private fun applyPropertyAnimations() {
        val animDuration = 350L

        val transition = AutoTransition()
            .setDuration(animDuration)

        TransitionManager.beginDelayedTransition(root, transition)

        inner_circle.visibility = View.GONE

        inner_circle.animate()
            .scaleX(1.2f)
            .scaleY(1.2f).duration = animDuration

        outer_circle.animate()
            .scaleX(0.80f)
            .scaleY(0.80f)
            .setDuration(animDuration)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    TransitionManager.beginDelayedTransition(root)
                    outer_circle.visibility = View.GONE
                    start.visibility = View.GONE
                }
            })
    }
}