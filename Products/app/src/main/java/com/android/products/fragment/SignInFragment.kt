package com.android.products.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.android.products.R
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        textView_sign_in
        button_sign_in.setOnClickListener { l: View? ->
            //
            if (editText_sign_in_name.text.toString().isNotEmpty() && editText_sign_in_password.text.toString().isNotEmpty()) {
                //
                if (editText_sign_in_name.text.toString().equals(activity!!.getSharedPreferences("Accounts",
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("name", "")) && editText_sign_in_password.text.toString().equals(activity!!.getSharedPreferences("Accounts",
                        AppCompatActivity.MODE_PRIVATE
                    ).getString("password", ""))) {
                    //
                    activity!!.getSharedPreferences("Accounts", AppCompatActivity.MODE_PRIVATE).edit().putInt("sign", 0).apply()
                    activity!!.setResult(AppCompatActivity.RESULT_OK)
                    activity!!.finish()
                }
            }
        }
        button_sign_in_sign_up.setOnClickListener { l: View? ->
            //
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, SignUpFragment()).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == AppCompatActivity.RESULT_OK) {
            //
            activity!!.setResult(AppCompatActivity.RESULT_OK)
            activity!!.finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignInFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }
}