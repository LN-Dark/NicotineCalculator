package com.luanegra.nicotinecalculator.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.luanegra.nicotinecalculator.R


class AboutFragment : Fragment() {
    var root: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_about, container, false)
        val paypal: ImageView = root!!.findViewById(R.id.paypal)
        val github: ImageView = root!!.findViewById(R.id.github)

        paypal.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/paypalme/pedrocruz77"))
            startActivity(browserIntent)
        }

        github.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/LN-Dark"))
            startActivity(browserIntent)
        }

        return root
    }
}