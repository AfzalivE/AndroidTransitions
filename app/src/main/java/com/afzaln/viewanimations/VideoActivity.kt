package com.afzaln.viewanimations

import android.net.Uri
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.video_activity.*


class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_activity)
        initializePlayer()

        startButton.setOnClickListener({
            player.playWhenReady = true

            // fade in the video by fading out this view
            TransitionManager.beginDelayedTransition(rootView)
            fadeView.visibility = View.GONE
        })
    }

    private lateinit var player: SimpleExoPlayer

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
            DefaultRenderersFactory(this),
            DefaultTrackSelector(),
            DefaultLoadControl()
        )

        playerView.player = player

        player.seekTo(0, 0)
        player.volume = 0f

        val uri = Uri.parse("asset:///" + "sample_video.mp4")
        val mediaSource = ExtractorMediaSource.Factory(
            DefaultDataSourceFactory(this, "exoplayer-codelab")
        ).createMediaSource(uri)

        player.prepare(mediaSource, true, false)
    }
}