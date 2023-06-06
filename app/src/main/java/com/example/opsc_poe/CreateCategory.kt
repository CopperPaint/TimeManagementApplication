package com.example.opsc_poe

import android.annotation.SuppressLint
import android.content.Intent
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

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCreateCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)




        //colour picker
        //--------------------------------------------------------------------------------
        colorPreview = findViewById<View>(R.id.preview_selected_color)
        val picker = findViewById<Button>(R.id.pick_color_button)
        picker.setOnClickListener()
        {
            openColorPickerDialogue()
        }


        //create category button
        //--------------------------------------------------------------------------------
        binding.btnCreate.setOnClickListener()
        {
            //create new category object
            var category = Temp_CategoryDataClass(
                categoryID = GlobalClass.categories.size + 1,
                userID = GlobalClass.user.userID,
                name = binding.etName.text.toString(),
                description = binding.etDescription.text.toString(),
                colour = intToColorString(defaultcolor)
            )
            //save to global class
            GlobalClass.categories.add(category)
            //back to home page
            var intent = Intent(this, Home_Activity::class.java)
            startActivity(intent)
        }
    }


    //Color Picker Popup
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

    //Convert Color Int to String
    fun intToColorString(color: Int): String {
        return String.format("#%06X", 0xFFFFFF and color)
    }
}