package com.dean.memory

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.Unbinder

/**
 * Created: tvt on 2019-08-15 15:17
 */
class LoginActivity : Activity(), IMemoryCallback {

    private var unBinder: Unbinder? = null

    private var presenter: MemoryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        unBinder = ButterKnife.bind(this)

        presenter = MemoryPresenter(this@LoginActivity)
    }

    override fun onDestroy() {
        super.onDestroy()
        unBinder?.unbind()
    }

    @OnClick(R2.id.login_button)
    fun login() {
        presenter?.login("admin", "123456")
    }

    override fun loginSucc() {
        Toast.makeText(this@LoginActivity, "登陆成功", Toast.LENGTH_SHORT).show()
        this.finish()
    }

    override fun loginFail() {
        Toast.makeText(this@LoginActivity, "登陆失败", Toast.LENGTH_SHORT).show()
    }

}