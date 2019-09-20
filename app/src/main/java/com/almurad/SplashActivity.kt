package com.almurad

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.almurad.databinding.ActivitySplashBinding
import com.bumptech.glide.Glide

class SplashActivity : AppCompatActivity() {

    val SPLASH_TIME = 2000L;
    private var binding: ActivitySplashBinding? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            val intent = MainActivity.newInstance(this)
            startActivity(intent)
            overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
            finish()
        }
    }
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Glide.with(this).load(R.drawable.spalsh).into(binding!!.ivSplashBg)

        handler = Handler()
        handler!!.postDelayed(mRunnable, SPLASH_TIME)
    }

    override fun onResume() {
        super.onResume()
        handler!!.postDelayed(mRunnable, SPLASH_TIME)
    }

    override fun onPause() {
        super.onPause()
        handler!!.removeCallbacks(mRunnable)
    }
}
