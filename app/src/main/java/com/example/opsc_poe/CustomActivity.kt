package com.example.opsc_poe

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView

class CustomActivity(
    context: Context,
    attrs: AttributeSet
) : RelativeLayout(context, attrs)
{
    init
    {
        inflate(context, R.layout.customactivity, this)

        val customStyle =  context.obtainStyledAttributes(attrs, R.styleable.customactivitystyle)

        val primarytext = findViewById<TextView>(R.id.tvPrimaryText)
        val secondaytext = findViewById<TextView>(R.id.tvSecondaryText)
        //val bar = findViewById<View>(R.id.vBar)
        //val block = findViewById<View>(R.id.vBlock)
        val blocktext = findViewById<TextView>(R.id.tvBlockText)
        val blockx = findViewById<TextView>(R.id.tvBlockX)
        try
        {
            primarytext.text = customStyle.getString(R.styleable.customactivitystyle_primarytext)
            secondaytext.text = customStyle.getString(R.styleable.customactivitystyle_secondarytext)
            //bar.backgroundTintList = ColorStateList.valueOf(R.styleable.customactivitystyle_barcolour)
            //block.backgroundTintList = ColorStateList.valueOf(R.styleable.customactivity_blockcolour)
            blocktext.text = customStyle.getString(R.styleable.customactivitystyle_blocktext)
            blockx.text = customStyle.getString(R.styleable.customactivitystyle_xtext)
        }
        finally
        {
            customStyle.recycle()
        }
    }
}