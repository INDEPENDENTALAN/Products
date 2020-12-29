package com.android.products.fragment

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.products.R
import com.android.products.entity.ProductsEntity
import com.android.products.sqlite.ProductSQLite
import kotlinx.android.synthetic.main.fragment_products.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream


class ProductsFragment : Fragment() {

    lateinit var productsEntity: ProductsEntity
    lateinit var bitmap: Bitmap
    lateinit var productSQLite: ProductSQLite
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
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //
        floatingActionButton_image.setOnClickListener { l: View? ->
            //
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }
        productSQLite = ProductSQLite(activity!!)

        var id: Int = activity!!.getSharedPreferences("Products", AppCompatActivity.MODE_PRIVATE).getInt("id", -1)
        button_done.setOnClickListener { l: View? ->
            //
            if (editText_name.text.toString().isNotEmpty() && editText_price.text.toString().isNotEmpty() && editText_details.text.toString().isNotEmpty()) {
                //
                val db: SQLiteDatabase = productSQLite.getWritableDatabase()
                val contentValues = ContentValues()
                contentValues.put("name", editText_name.text.toString())
                contentValues.put("image", onBitmapToString(bitmap))
                contentValues.put("price", editText_price.text.toString())
                contentValues.put("details", editText_details.text.toString())
                if (activity!!.getSharedPreferences("Products", AppCompatActivity.MODE_PRIVATE).getInt("product", -1) == 0) {
                    //
                    db.insert("products", null, contentValues)
                } else if (activity!!.getSharedPreferences("Products", AppCompatActivity.MODE_PRIVATE).getInt("product", -1) == 1) {
                    //
                    db.update("products", contentValues, "id = ? ", arrayOf(id.toString()))
                }
            }
        }
    }

    fun onBitmapToString(bitmap: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b: ByteArray = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }

    fun onStringToBitmap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //
        if (requestCode == 0) {
            //
            try {
                //
                val imageUri: Uri = data!!.data!!
                val imageStream: InputStream? = activity!!.contentResolver.openInputStream(imageUri)
                val bitmap = BitmapFactory.decodeStream(imageStream)
                imageView_image.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                //
                e.printStackTrace()
                Toast.makeText(activity!!, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            //
            Toast.makeText(activity, "You haven't picked Image", Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProductsFragment().apply {
                arguments = Bundle().apply {
                    //
                }
            }
    }
}