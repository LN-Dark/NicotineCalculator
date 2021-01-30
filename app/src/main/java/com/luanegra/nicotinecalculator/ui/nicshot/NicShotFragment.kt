package com.luanegra.nicotinecalculator.ui.nicshot

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.luanegra.nicotinecalculator.R
import www.sanju.motiontoast.MotionToast

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
            if (!txt_desiredstreght.text.isNullOrEmpty()){
                if (!txt_nicotinestreght.text.isNullOrEmpty()){
                    if (!txt_totalamount.text.isNullOrEmpty()){
                        val result: Float = calculateTotalAmount(txt_desiredstreght.text.toString().toFloat(), txt_nicotinestreght.text.toString().toFloat(), txt_totalamount.text.toString().toFloat())
                        txt_result.setText("${txt_totalamount.text.toString().toInt().minus(result)} ${getString(R.string.ofjuice)} ${result} ${getString(R.string.ofnicotine)}")
                    }else{
                        MotionToast.darkToast(requireActivity(),
                                getString(R.string.warning),
                                getString(R.string.totalamountisempty),
                                MotionToast.TOAST_WARNING,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
                    }
                }else{
                    MotionToast.darkToast(requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.concentratednicotineisempty),
                            MotionToast.TOAST_WARNING,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
                }
            }else{
                MotionToast.darkToast(requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.desirestreghtisempty),
                        MotionToast.TOAST_WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
            }
        }

        btn_save.setOnClickListener {
            if (!txt_desiredstreght.text.isNullOrEmpty()){
                if (!txt_nicotinestreght.text.isNullOrEmpty()){
                    if (!txt_totalamount.text.isNullOrEmpty()){
                        if (!txt_result.text.isNullOrEmpty()){
                            saveRecipe(txt_desiredstreght.text.toString().toFloat(), txt_nicotinestreght.text.toString().toFloat(), txt_totalamount.text.toString().toFloat(), txt_result.text.toString())
                        }else{
                            MotionToast.darkToast(requireActivity(),
                                    getString(R.string.warning),
                                    "You need to calculate the result first!",
                                    MotionToast.TOAST_WARNING,
                                    MotionToast.GRAVITY_BOTTOM,
                                    MotionToast.LONG_DURATION,
                                    ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
                        }
                    }else{
                        MotionToast.darkToast(requireActivity(),
                                getString(R.string.warning),
                                getString(R.string.totalamountisempty),
                                MotionToast.TOAST_WARNING,
                                MotionToast.GRAVITY_BOTTOM,
                                MotionToast.LONG_DURATION,
                                ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
                    }
                }else{
                    MotionToast.darkToast(requireActivity(),
                            getString(R.string.warning),
                            getString(R.string.concentratednicotineisempty),
                            MotionToast.TOAST_WARNING,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
                }
            }else{
                MotionToast.darkToast(requireActivity(),
                        getString(R.string.warning),
                        getString(R.string.desirestreghtisempty),
                        MotionToast.TOAST_WARNING,
                        MotionToast.GRAVITY_BOTTOM,
                        MotionToast.LONG_DURATION,
                        ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
            }
        }

        return root
    }

    fun calculateTotalAmount(txt_desiredstreght: Float, txt_nicotinestreght: Float, txt_totalamount: Float): Float{
        var resultNic: Float = (txt_desiredstreght / txt_nicotinestreght) * txt_totalamount
        hideKeyboard(root!!.rootView)
        return resultNic
    }

    fun saveRecipe(txt_desiredstreght: Float, txt_nicotinestreght: Float, txt_totalamount: Float, txt_result: String){
        val sharedPreferences: SharedPreferences = root!!.context.getSharedPreferences("NicotineCalculator", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor =  sharedPreferences.edit()
        editor.putString("$txt_totalamount$txt_nicotinestreght", "${resources.getString(R.string.desired_strength)
        } : $txt_desiredstreght \n${resources.getString(R.string.concentrated_nicotine_strength)
        } : $txt_nicotinestreght \n${resources.getString(R.string.amount_of_e_liquid_with_0_nicotine)} : $txt_totalamount \n${resources.getString(R.string.result)
        } : ${txt_result}")
        editor.apply()
        editor.commit()
        MotionToast.darkToast(requireActivity(),
                getString(R.string.SUCCESS),
                getString(R.string.recipesaved),
                MotionToast.TOAST_SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(root!!.context,R.font.helvetica_regular))
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = root!!.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}