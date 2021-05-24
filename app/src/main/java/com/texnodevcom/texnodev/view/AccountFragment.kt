package com.texnodevcom.texnodev.view

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.texnodevcom.texnodev.R
import kotlinx.android.synthetic.main.account_about_us_dialog.*
import kotlinx.android.synthetic.main.fragment_account.*


class AccountFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        darkMode()

        getAboutUsDialog()

//        setOnClickPrivacy()

    }

    private fun darkMode(){
        switch_darkLight.setOnClickListener {
            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme){
                Configuration.UI_MODE_NIGHT_YES -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
                Configuration.UI_MODE_NIGHT_NO-> {
                    AppCompatDelegate.setDefaultNightMode((AppCompatDelegate.MODE_NIGHT_YES))
                }
            }
        }
    }

    private fun getAboutUsDialog(){
        appAboutUsID.setOnClickListener {
            val view = View.inflate(requireContext(), R.layout.account_about_us_dialog, null)
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(view)

            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(R.color.fragmentBackground)
        }
    }

    private fun setOnClickPrivacy(){
        appPrivacyID.setOnClickListener {
            val action = AccountFragmentDirections.actionAccountFragmentToPrivacyFragment()
            findNavController().navigate(action)
        }
    }

}