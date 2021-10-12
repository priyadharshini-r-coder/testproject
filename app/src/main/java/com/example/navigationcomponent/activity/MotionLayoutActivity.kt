package com.example.navigationcomponent.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import com.example.navigationcomponent.R
import com.example.navigationcomponent.databinding.ActivityMotionLayoutBinding

class MotionLayoutActivity : AppCompatActivity() {
    //var btn_animate:Button?=null
   // var motion_layout:MotionLayout?=null
    var binding:ActivityMotionLayoutBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_motion_layout)

        var isTransitionToEnd = false
      // btn_animate=findViewById<Button>(R.id.btn_animate);
      //  motion_layout=findViewById<MotionLayout>(R.id.motion_layout)
   binding?.btnAnimate?.setOnClickListener {
       if (!isTransitionToEnd) {
           isTransitionToEnd = true
           binding?.motionLayout?.transitionToEnd()
       } else {
           isTransitionToEnd = false
           binding?.motionLayout?.transitionToStart()
       }
   }

    }
}