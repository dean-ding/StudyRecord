package com.dean.memory

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder

class MainActivity : AppCompatActivity() {

    private var unBind: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        unBind = ButterKnife.bind(this@MainActivity)
    }

    @OnClick(R2.id.test)
    fun skip() {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        unBind?.unbind()
    }
}
