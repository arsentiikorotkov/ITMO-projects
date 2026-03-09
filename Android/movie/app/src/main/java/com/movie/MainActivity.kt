package com.movie

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.doOnEnd
import com.movie.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animator1: ObjectAnimator =
            ObjectAnimator.ofFloat(binding.text, View.ALPHA, 0f, 1f).setDuration(1950)
        val animator2: ObjectAnimator =
            ObjectAnimator.ofFloat(binding.text, View.ALPHA, 1f, 0f).setDuration(1950)

        val animatorSet = AnimatorSet()
        animatorSet.play(animator1).before(animator2)
        animatorSet.doOnEnd { animatorSet.start() }
        animatorSet.start()
    }
}