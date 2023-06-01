package com.example.opsc_poe

import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity

class CustomActivity(
    context: FragmentActivity? //FragmentActivity? was Context
) : RelativeLayout(context)
{
    /*
    //val tvprimarytext = findViewById<TextView>(R.id.tvPrimaryText)
    val secondaytext = findViewById<TextView>(R.id.tvSecondaryText)
    //val bar = findViewById<View>(R.id.vBar)
    val block = findViewById<View>(R.id.vBlock)
    val blocktext = findViewById<TextView>(R.id.tvBlockText)
    val blockx = findViewById<TextView>(R.id.tvBlockX)
    */

    init
    {
        inflate(context, R.layout.customactivity, this)
    }

    /*
    init
    {
        inflate(context, R.layout.customactivity, this)

        val customStyle =  context.obtainStyledAttributes(attrs, R.styleable.customactivitystyle)

        val primarytext = findViewById<TextView>(R.id.tvPrimaryText)
        val secondaytext = findViewById<TextView>(R.id.tvSecondaryText)
        //val bar = findViewById<View>(R.id.vBar)
        val block = findViewById<View>(R.id.vBlock)
        val blocktext = findViewById<TextView>(R.id.tvBlockText)
        val blockx = findViewById<TextView>(R.id.tvBlockX)

        try
        {
            primarytext.text = customStyle.getString(R.styleable.customactivitystyle_primarytext)
            secondaytext.text = customStyle.getString(R.styleable.customactivitystyle_secondarytext)
            block.setBackgroundColor(Color.argb(255,90,255,200))
            blocktext.text = customStyle.getString(R.styleable.customactivitystyle_blocktext)
            blockx.text = customStyle.getString(R.styleable.customactivitystyle_xtext)
        }
        finally
        {
            customStyle.recycle()
        }
    }
    */
}