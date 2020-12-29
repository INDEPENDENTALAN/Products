package com.android.products.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.products.R
import com.android.products.fragment.ProductsFragment
import com.android.products.fragment.ProfileFragment
import com.android.products.fragment.SignInFragment
import com.android.products.fragment.SignUpFragment

class FragmentActivity : AppCompatActivity() {
    var check = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (intent.getIntExtra("request", -1) == 0) {
            //
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, SignInFragment()).commit()
            check = true
        } else if (intent.getIntExtra("request", -1) == 1) {
            //
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, ProductsFragment()).commit()
            check = false
        } else if (intent.getIntExtra("request", -1) == 2) {
            //
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, SignUpFragment()).commit()
        } else if (intent.getIntExtra("request", -1) == 3) {
            //
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, ProfileFragment()).commit()
            check = false
        } else if (intent.getIntExtra("request", -1) == 4) {
            //
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout_fragment, ProductsFragment()).commit()
            check = false
        }
    }

    override fun onBackPressed() {
        //
        if (check) {
            //
        } else {
            super.onBackPressed()
        }
    }
}