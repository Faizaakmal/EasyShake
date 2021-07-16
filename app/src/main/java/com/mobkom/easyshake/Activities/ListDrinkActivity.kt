package com.mobkom.easyshake.Activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.mobkom.easyshake.R
import com.mobkom.easyshake.Adapter.DrinkAdapter
import com.mobkom.easyshake.databinding.ActivityListDrinkBinding
import com.mobkom.easyshake.Model.ModelCategories
import com.mobkom.easyshake.Model.ModelDrink
import com.mobkom.easyshake.Retrofit.ApiEndPoint
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ListDrinkActivity : AppCompatActivity() {

    private val binding: ActivityListDrinkBinding by lazy{
        ActivityListDrinkBinding.inflate(layoutInflater)
    }

    companion object {
        const val LIST_DRINK = "CATEGORIES"
    }

    var modelDrink: MutableList<ModelDrink> = ArrayList()
    var modelCategories: ModelCategories? = null
    var drinkAdapter: DrinkAdapter? = null
    var progressDialog: ProgressDialog? = null
    var strCategory: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Please Wait...")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Showing Drinks")

        setSupportActionBar(binding.toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get data intent
        modelCategories = intent.getSerializableExtra(LIST_DRINK) as ModelCategories
        if (modelCategories != null) {
            strCategory = modelCategories?.strCategory

            binding.tvCategories.text = strCategory

            drinkAdapter = DrinkAdapter(modelDrink, this@ListDrinkActivity)
            binding.rvListDrink.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            binding.rvListDrink.adapter = drinkAdapter
            binding.rvListDrink.setHasFixedSize(true)

            //get data drink
            getListDrink(strCategory)
        }
    }

    private fun getListDrink(strCategory: String?) {
        progressDialog?.show()
        AndroidNetworking.get(ApiEndPoint.BASEURL + ApiEndPoint.URL_FILTER)
                .addPathParameter("strCategory", strCategory)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        progressDialog?.dismiss()
                        try {
                            val jsonArray = response.getJSONArray("drinks")
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)
                                val dataModel = ModelDrink()
                                dataModel.idDrink = jsonObject.getString("idDrink")
                                dataModel.strDrink = jsonObject.getString("strDrink")
                                dataModel.strDrinkThumb = jsonObject.getString("strDrinkThumb")
                                modelDrink.add(dataModel)
                            }
                            drinkAdapter?.notifyDataSetChanged()
                        } catch (e: JSONException) {
                            Toast.makeText(this@ListDrinkActivity,
                                    "Failed to show drinks :(", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog?.dismiss()
                        Toast.makeText(this@ListDrinkActivity,
                                "Looks like there is problem with your connection :(", Toast.LENGTH_SHORT).show()
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}