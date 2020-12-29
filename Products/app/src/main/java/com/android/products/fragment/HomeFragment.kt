package com.android.products.fragment

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.products.adapter.HomeAdapter
import com.android.products.R
import com.android.products.entity.ProductsEntity
import com.android.products.sqlite.ProductSQLite
import kotlinx.android.synthetic.main.activity_products.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var arrayList: ArrayList<ProductsEntity>
    lateinit var adapter: HomeAdapter
    lateinit var productsEntity: ProductsEntity
    lateinit var productSQLite: ProductSQLite
    lateinit var cursor: Cursor
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        editText_search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                // TODO Auto-generated method stub
            }

            override fun afterTextChanged(s: Editable) {

                // filter your list from your input
                val list: ArrayList<ProductsEntity> = ArrayList()
                for (d in arrayList) {
                    //or use .equal(text) with you want equal match
                    //use .toLowerCase() for better matches
                    if (d.name.contains(s.toString())) {
                        list.add(d)
                    }
                }
                //update recyclerview
                adapter.updateList(list)
                //you can use runnable postDelayed like 500 ms to delay search text
            }
        })
        arrayList = ArrayList()
        productSQLite = ProductSQLite(activity!!)
        val db: SQLiteDatabase = productSQLite.getReadableDatabase()
        cursor = db.rawQuery("SELECT * FROM products", null)
        cursor.moveToFirst()
        while (cursor.isAfterLast() === false) {
            productsEntity = ProductsEntity(
                cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("image")),
                cursor.getString(cursor.getColumnIndex("price")),
                cursor.getString(cursor.getColumnIndex("details"))
            )
            cursor.moveToNext()
            arrayList.add(productsEntity)
        }
        adapter = HomeAdapter(activity!!, arrayList)
        recyclerView_home.setHasFixedSize(true)
        recyclerView_home.layoutManager = LinearLayoutManager(activity!!)
        recyclerView_home.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }
}