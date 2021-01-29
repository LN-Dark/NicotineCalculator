package com.luanegra.nicotinecalculator.ui.nicshot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.luanegra.nicotinecalculator.R
import com.shashank.sony.fancytoastlib.FancyToast

class NicShotFragment : Fragment() {

    var root: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_nicshot, container, false)
        val txt_desiredstreght: TextInputEditText = root!!.findViewById(R.id.txt_desiredstreght)
        val txt_nicotinestreght: TextInputEditText = root!!.findViewById(R.id.txt_nicotinestreght)
        val txt_totalamount: TextInputEditText = root!!.findViewById(R.id.txt_totalamount)
        val txt_result: TextInputEditText = root!!.findViewById(R.id.txt_result)
        val result_layout: TextInputLayout = root!!.findViewById(R.id.result_layout)
        val btn_calculate: Button = root!!.findViewById(R.id.btn_calculate)
        val btn_save: Button = root!!.findViewById(R.id.btn_save)
        result_layout.isEnabled = false
        btn_calculate.setOnClickListener {
            if (!txt_desiredstreght.text!!.isEmpty()){
                if (!txt_nicotinestreght.text!!.isEmpty()){
                    if (!txt_totalamount.text!!.isEmpty()){
                        val result: Float = calculateTotalAmount(txt_desiredstreght.text.toString().toFloat(), txt_nicotinestreght.text.toString().toFloat(), txt_totalamount.text.toString().toFloat())
                        txt_result.setText("${txt_totalamount.text.toString().toInt().minus(result)} of juice (in ml) + ${result} Nicotine (in ml)")

                    }else{
                        FancyToast.makeText(requireContext(), "Total Amount is empty..." , FancyToast.LENGTH_LONG,FancyToast.WARNING,true)
                    }
                }else{
                    FancyToast.makeText(requireContext(), "Concentrated Nicotine Strength is empty..." , FancyToast.LENGTH_LONG,FancyToast.WARNING,true)
                }
            }else{
                FancyToast.makeText(requireContext(), "Desired Strength is empty..." , FancyToast.LENGTH_LONG,FancyToast.WARNING,true)
            }
        }

        return root
    }

    fun calculateTotalAmount(txt_desiredstreght: Float, txt_nicotinestreght: Float, txt_totalamount: Float): Float{
        var resultNic: Float = (txt_desiredstreght / txt_nicotinestreght) * txt_totalamount
        return resultNic
    }
}