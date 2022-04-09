package com.example.parsejsonusingvolleyexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var mTextViewResult : TextView
    private lateinit var mQueue : RequestQueue //parse 할때 필요한 거구나.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTextViewResult = findViewById(R.id.text_view_result)
        val button : Button = findViewById(R.id.button_parse)
        mQueue = Volley.newRequestQueue(this)
        button.setOnClickListener {
            jsonParse()
        }
    }

    private fun jsonParse() {
        // Json 을 parse 하는게 목표였다.
        // 대괄호 안에 object 가 있고 object 는 key 와 vale 로 구분이 되어 있다.
        // object 는 [] 이용해서 Array 를 만들 수 있고 각 array 의 내용들은 comma, 로 구분되어 있다.
        // 맨처음 JsonObjectRequest 를 하고 받은거에서 getJSONArray 를 하고 다시 getJSONObject 를 해서 그걸로 각 key값의
        // value 를 받는다. 맨마지막에 RequestQueue 객체를 통해서 (Volley에서 나오것 newRequestQueue)으로
        // mQueue.add(request) 를 해준다.
        // 한가지 더 알아야 할 내용은 맨처음 JsonObjectRequest를 해서 두가지의 listener 를 했고 처음의 response 에서
        // 이러한 작업들을 해주었다는 사실이다.
        val url : String = "현재 json 파일이 인터넷에 없어서 그냥 연습만 한다."
        val request : JsonObjectRequest = JsonObjectRequest(url, {
            try {
                val jsonArray : JSONArray = it.getJSONArray("Employees")
                for (i in 0 until jsonArray.length()) {
                    val employee : JSONObject = jsonArray.getJSONObject(i)
                    val firstName : String = employee.getString("firstname")
                    val age : Int = employee.getInt("age")
                    val email : String = employee.getString("email")

                    mTextViewResult.append("${firstName} ${age} ${email} \n\n")
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }, {
            it.printStackTrace()
        })
        mQueue.add(request) //이렇게 add를 해야지 되는구나.
    }
}