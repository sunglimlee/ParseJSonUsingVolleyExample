package com.example.parsejsonusingvolleyexample

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

//지금 새로운 객체를 하나만 만들어서 무담을 줄여주겠다는 거잖아.
class VolleySingleton private constructor(mContext: Context) {
    //ㅋ 황당하네.. 또 하나 아네.. 그냥 멤버변수를 Primary 생성자의 파라미터로 바로 초기화도 가능하네..
    private var mRequestQueue: RequestQueue = Volley.newRequestQueue(mContext.applicationContext)
    //여기서 볼 수 있는 것 처럼 Primary 생성자의 context 를 init 에서 사용할 수 있다는 것... 꼭 기억하자.. 자주 사용하는거다.
    init {
    }

    companion object { //최초 이 객체를 싱글톤으로 생성하기 위해서 필요한 static 변수와 함수
        private var mInstance: VolleySingleton? = null

        @Synchronized fun getInstance(context : Context) : VolleySingleton {
            if (mInstance == null) {
                mInstance = VolleySingleton(context)
            }
            return mInstance as VolleySingleton
        }
    }

    fun getRequestQueue() : RequestQueue {
        return mRequestQueue
    }
}