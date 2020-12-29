package com.android.products.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.android.products.R
import com.android.products.activity.FragmentActivity
import com.android.products.entity.ProductsEntity
import kotlinx.android.synthetic.main.fragment_sign_up.*

class HomeAdapter(var activity: Activity,var arrayList: ArrayList<ProductsEntity>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView_home_name: TextView
        var imageView_home_image : ImageView
        var textView_home_price: TextView
        var textView_home_details: TextView
        var imageButton_home_edit: ImageButton
        init {
            textView_home_name = itemView.findViewById(R.id.textView_home_name)
            imageView_home_image = itemView.findViewById(R.id.imageView_home_image)
            textView_home_price = itemView.findViewById(R.id.textView_home_price)
            textView_home_details = itemView.findViewById(R.id.textView_home_details)
            imageButton_home_edit = itemView.findViewById(R.id.imageButton_home_edit)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_home,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        //
        holder.textView_home_name.text = arrayList[position].name
        holder.textView_home_price.text = arrayList[position].price
        holder.textView_home_details.text = arrayList[position].details
        holder.imageView_home_image
        holder.imageButton_home_edit.setOnClickListener { l: View? ->
            //
            activity.startActivityForResult(
                Intent(activity, FragmentActivity::class.java).putExtra(
                "request",
                4
            ), 4)
            activity.getSharedPreferences("Products", AppCompatActivity.MODE_PRIVATE).edit().putInt("product", 1).putInt("id", arrayList[position].id).apply()
        }
    }

    fun updateList(list: ArrayList<ProductsEntity>) {
        arrayList = list
        notifyDataSetChanged()
    }
}