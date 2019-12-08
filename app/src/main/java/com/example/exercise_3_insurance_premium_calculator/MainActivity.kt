package com.example.exercise_3_insurance_premium_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    lateinit var ageSpiner : Spinner
    lateinit var gender:RadioGroup
    lateinit var smoker:CheckBox
    lateinit var premPrice:TextView
    lateinit var btnCal : Button
    lateinit var btnClr :Button

    val premiumPrice: IntArray = intArrayOf(60, 70, 90, 120, 150, 150)
    val extraMalePrice: IntArray = intArrayOf(0, 50, 100, 150, 200, 200)
    val extraSmokerPrice: IntArray = intArrayOf(0, 100, 150, 200, 250, 300)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ageSpiner = findViewById(R.id.spinnerAge)
        gender = findViewById(R.id.radioGroupGender)
        smoker = findViewById(R.id.checkBoxSmoker)
        premPrice = findViewById(R.id.textViewPrePrice)

        btnCal = findViewById(R.id.buttonCal)
        btnClr = findViewById(R.id.buttonClr)

        btnCal.setOnClickListener {
            calPremium()
        }

        btnClr.setOnClickListener {
            clear()
        }
    }

    fun calPremium(){
        premPrice.setText(R.string.insurance_premium)
        var total =0
        if(ageSpiner.selectedItemPosition != -1){
           total+= premiumPrice[ageSpiner.selectedItemPosition]
            if (gender.getCheckedRadioButtonId() == -1 )
            {
                Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show()

            }else
            {
                if(gender.radioButtonMale.isChecked()){
                    total+= extraMalePrice[ageSpiner.selectedItemPosition]
                }

                if(smoker.isChecked()){
                    total+= extraSmokerPrice[ageSpiner.selectedItemPosition]
                }
            }
        }
        premPrice.append("RM")
        premPrice.append(total.toString())
    }

    fun clear(){
        ageSpiner.setSelection(0)
        gender.clearCheck()
        if(smoker.isChecked){
            smoker.toggle()
        }
        premPrice.setText(R.string.insurance_premium)
    }
}
