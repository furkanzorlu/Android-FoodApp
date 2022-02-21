package com.example.foodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.wangyuwei.particleview.ParticleView
import android.widget.Toast
import me.wangyuwei.particleview.ParticleView.ParticleAnimListener


class SplashActivity : AppCompatActivity() {
    lateinit var particleView: ParticleView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        particleView = findViewById(R.id.particleView);
        particleView.startAnim()
        particleView.setOnParticleAnimListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}