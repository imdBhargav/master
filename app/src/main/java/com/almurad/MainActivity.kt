package com.almurad

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.almurad.databinding.ActivityMainBinding
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    private lateinit var binding: ActivityMainBinding

    val URL = "http://amre.webhop.biz:9081";

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Glide.with(this).load(R.drawable.spalsh).into(binding.ivSplashBg)

        if (hasInternet(this)) {
            binding.webView.settings.javaScriptEnabled = true
            binding.webView.loadUrl(URL)
            binding.webView.settings.domStorageEnabled = true
            binding.webView.settings.allowContentAccess = true
            binding.webView.settings.allowFileAccess = true
            binding.webView.settings.allowFileAccessFromFileURLs = true
            binding.webView.settings.allowUniversalAccessFromFileURLs = true
            binding.webView.settings.setSupportZoom(true)
            binding.webView.webViewClient = WebViewClient()
            binding.webView.isClickable = true
            binding.webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    binding.ivSplashBg.visibility = View.GONE
                }
            }
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * A method which returns the state of internet connectivity of user's phone.
     */
    fun hasInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
