package com.example.opsc_poe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import yuku.ambilwarna.AmbilWarnaDialog

class CreateCategory : AppCompatActivity() {

    private var colorPreview: View? = null
    private var defaultcolor = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)


        val name = findViewById<EditText>(R.id.etName)
        val description = findViewById<EditText>(R.id.etDescription)

        //colour picker
        val pickcolourbutton = findViewById<Button>(R.id.pick_color_button)
        colorPreview = findViewById<View>(R.id.preview_selected_color)
        pickcolourbutton?.setOnClickListener()
        {
            openColorPickerDialogue()
        }

        //create category button
        val creatbutton = findViewById<Button>(R.id.btnCreate)
        creatbutton?.setOnClickListener()
        {
            //create category object
            var category = Temp_CategoryDataClass(
                userID = "set id of current user",
                name = name.text.toString(),
                description = description.text.toString(),
                colour = defaultcolor
            )
        }


    }

    fun openColorPickerDialogue()
    {
        val colorPickerDialogue = AmbilWarnaDialog(this, defaultcolor,
            object : AmbilWarnaDialog.OnAmbilWarnaListener {
                override fun onCancel(dialog: AmbilWarnaDialog?) {
                    // leave this function body as blank
                }
                override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                    defaultcolor = color
                    colorPreview?.setBackgroundColor(defaultcolor)
                }
            })
        colorPickerDialogue.show()
    }
}