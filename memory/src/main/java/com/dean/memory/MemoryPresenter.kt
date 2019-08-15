package com.dean.memory

/**
 * Created: tvt on 2019-08-15 15:12
 */
class MemoryPresenter(callback: IMemoryCallback) {

    private val model: MemoryModel = MemoryModel()
    private val mCallback = callback

    fun login(name: String, password: String) {
        model.login(name, password, object : IModelCallback {
            override fun login(code: Int) {
                if (code == 0) {
                    mCallback.loginSucc()
                } else {
                    mCallback.loginFail()
                }
            }
        })
    }
}