package com.example.opsc_poe

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.opsc_poe.databinding.ActivityCreateCategoryBinding
import yuku.ambilwarna.AmbilWarnaDialog

class CreateCategory : AppCompatActivity() {

    private var colorPreview: View? = null
    private var defaultcolor = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //global data
        var globaldata = GlobalClass()

        //colour picker
        colorPreview = findViewById<View>(R.id.preview_selected_color)
        val picker = findViewById<Button>(R.id.pick_color_button)
        picker.setOnClickListener()
        {
            openColorPickerDialogue()
        }

        //create category button
        binding.btnCreate.setOnClickListener()
        {
            //create category object
            var category = Temp_CategoryDataClass(
                userID = GlobalClass().user.userID,
                name = binding.etName.text.toString(),
                description = binding.etDescription.text.toString(),
                colour = intToColorString(defaultcolor)
            )
            GlobalClass().categories.add(category)
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
                    var previewColor = ColorStateList.valueOf(Color.parseColor(intToColorString(defaultcolor)))
                    colorPreview?.backgroundTintList = previewColor
                }
            })
        colorPickerDialogue.show()
    }

    fun intToColorString(color: Int): String {
        return String.format("#%06X", 0xFFFFFF and color)
    }
}