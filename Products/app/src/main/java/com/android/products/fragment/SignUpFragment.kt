package com.android.products.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.android.products.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        textView_sign_up
        editText_sign_up_name
        editText_sign_up_password
        editText_sign_up_confirm
        button_sign_up.setOnClickListener { l: View? ->
            //
            if (editText_sign_up_name.text.toString().isNotEmpty() && editText_sign_up_password.text.toString().isNotEmpty() && editText_sign_up_confirm.text.toString().isNotEmpty()) {
                //
                if (editText_sign_up_password.text.toString().equals(editText_sign_up_confirm.text.toString())) {
                    //
                    activity!!.getSharedPreferences("Accounts", AppCompatActivity.MODE_PRIVATE).edit().putString("name", editText_sign_up_name.text.toString()).putString("password", editText_sign_up_password.text.toString()).putInt("sign", 0).apply()
                    activity!!.setResult(AppCompatActivity.RESULT_OK)
                    activity!!.finish()
                }
            }
        }
        button_sign_up_sign_in.setOnClickListener { l: View? ->
            //
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, SignInFragment()).commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }
}