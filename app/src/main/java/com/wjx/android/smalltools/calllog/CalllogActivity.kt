package com.wjx.android.smalltools.calllog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.wjx.android.smalltools.R
import kotlinx.android.synthetic.main.activity_calllog.*

class CalllogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calllog)
//        call_in.isChecked = true
        call_in.setOnClickListener{
            if (call_in.isChecked) {
                in_call_log_type.visibility = View.GONE
                call_in.isChecked = false
            } else {
                call_in.isChecked = true
                in_call_log_type.visibility = View.VISIBLE
            }
        }
    }
}
