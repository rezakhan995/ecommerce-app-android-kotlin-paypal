package com.rezakhan.android.salesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_signup.setOnClickListener {
            var i = Intent(this, RegAct::class.java)
            startActivity(i)
        }

        login_btn.setOnClickListener {
            var url = "http://192.168.0.104/salesweb/login.php"

            var rq : RequestQueue = Volley.newRequestQueue(this)
            var sr = object: StringRequest(Request.Method.GET, url, Response.Listener { response ->
                if(response.equals("0")) {
                    Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
                }else {
                    UserInfo.mobile = login_mobile.text.toString()
                    UserInfo.access_token = response
                    var i = Intent(this, HomeAct::class.java)
                    startActivity(i)
                }
            }, Response.ErrorListener { error ->
                Toast.makeText(this,error.message, Toast.LENGTH_LONG).show()
            }){
                override fun getParams(): MutableMap<String, String> {
                    var map = HashMap<String, String>()
                    map.put("mobile", login_mobile.text.toString())
                    map.put("password", login_password.text.toString())
                    return map
                }
            }
            rq.add(sr)
        }
    }
}
