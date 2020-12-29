package com.android.products.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.android.products.R
import com.android.products.fragment.HomeFragment
import com.android.products.fragment.SignInFragment
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.layout_navigation_view.view.*

class ProductsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        if (getSharedPreferences("Accounts", MODE_PRIVATE).getInt("sign", -1) != 0) {
            //
            startActivityForResult(Intent(this, FragmentActivity::class.java).putExtra(
                "request",
                0
            ), 0)
        }
        imageButton_products_drawer.setOnClickListener { l: View? ->
            //
            drawerLayout_products.openDrawer(GravityCompat.START)
        }
        textView_products
        var actionBarDrawerToggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout_products,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout_products.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout_products, HomeFragment()).commit()
        val view = navigationView_makeyourselfathome.inflateHeaderView(R.layout.layout_navigation_view)
        view.button_products_home.setOnClickListener { l: View? ->
            //
        }
        view.button_products_profile.setOnClickListener { l: View? ->
            //
            startActivityForResult(
                Intent(this, FragmentActivity::class.java).putExtra(
                    "request",
                    3
                ), 3
            )
        }
        floatingActionButton_products.setOnClickListener { l: View? ->
            //
            getSharedPreferences("Products", MODE_PRIVATE).edit().putInt("product", 0).putInt("id", 0).apply()
            startActivityForResult(Intent(this, FragmentActivity::class.java).putExtra(
                "request",
                1
            ), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == RESULT_OK) {
            //
        } else if (requestCode == 1 && resultCode == RESULT_OK) {
            //
        }
    }
}