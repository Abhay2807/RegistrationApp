package com.example.usermanagementapp.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import com.example.usermanagementapp.R
import com.example.usermanagementapp.firebase.FirestoreClass

class SplashActivity : AppCompatActivity() {


    private lateinit var splash_messg:TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splash_messg=findViewById(R.id.tv_app_name)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val typeface: Typeface =
            Typeface.createFromAsset(assets, "carbon bl.ttf")
         splash_messg.typeface=typeface


        Handler().postDelayed({

            var currentUserId= FirestoreClass().getCurrentUserID()

            if(currentUserId==null){
                // We are not Signed In

            startActivity(Intent(this@SplashActivity, IntroActivity::class.java))
            finish()}

            else
            {  // We are already Signed In

                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }


        }, 2500)



    }
}