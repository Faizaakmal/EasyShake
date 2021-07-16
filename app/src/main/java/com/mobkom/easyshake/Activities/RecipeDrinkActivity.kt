package com.mobkom.easyshake.Activities

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.mobkom.easyshake.R
import com.mobkom.easyshake.databinding.ActivityRecipeDrinkBinding
import com.mobkom.easyshake.Model.ModelDrink
import com.mobkom.easyshake.Retrofit.ApiEndPoint
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.json.JSONException
import org.json.JSONObject

class RecipeDrinkActivity : AppCompatActivity() {

    private val binding: ActivityRecipeDrinkBinding by lazy {
        ActivityRecipeDrinkBinding.inflate(layoutInflater)
    }

    companion object {
        const val DRINK_RECIPE = "RECIPE"
    }

    var modelDrink: ModelDrink? = null
    var progressDialog: ProgressDialog? = null
    var idDrink: String? = null
    var strDrink: String? = null
    var strCategory: String? = null
    var strAlcoholic: String? = null
    var strGlass: String? = null
    var strInstructions: String? = null
    var strDrinkThumb: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle("Please Wait...")
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Showing Recipe")

        setSupportActionBar(binding.toolbar)
        assert(supportActionBar != null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get data intent
        modelDrink = intent.getSerializableExtra(DRINK_RECIPE) as ModelDrink
        if (modelDrink != null) {
            idDrink = modelDrink?.idDrink

            //get data recipe
            getRecipeDrink(idDrink)
        }
    }

    private fun getRecipeDrink(idDrink: String?) {
        progressDialog?.show()
        AndroidNetworking.get(ApiEndPoint.BASEURL + ApiEndPoint.URL_RECIPE)
                .addPathParameter("idDrink", idDrink)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        progressDialog?.dismiss()
                        try {
                            val jsonArray = response.getJSONArray("drinks")
                            for (i in 0 until jsonArray.length()) {
                                val jsonObject = jsonArray.getJSONObject(i)

                                //ambil json data dari website cocktailDB
                                strDrink = jsonObject.getString("strDrink")
                                strCategory = jsonObject.getString("strCategory")
                                strAlcoholic = jsonObject.getString("strAlcoholic")
                                strGlass = jsonObject.getString("strGlass")
                                strInstructions = jsonObject.getString("strInstructions")
                                strDrinkThumb = jsonObject.getString("strDrinkThumb")

                                //set data
                                binding.tvTitle.text = strDrink
                                binding.tvSubtitle.text = strCategory
                                binding.tvAlcoholic.text = strAlcoholic
                                binding.tvGlass.text = strGlass
                                binding.tvInstructions.text = strInstructions

                                Glide.with(this@RecipeDrinkActivity)
                                        .load(strDrinkThumb)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .fitCenter()
                                        .into(binding.imageDrink)
                            }

                            //set ingredient & measure
                            var x = 0
                            while (x < 15) {
                                if (x == 0) {
                                    x++
                                }
                                binding.tvIngredients.append("\u2022 " + jsonArray.getJSONObject(0)
                                        .getString("strIngredient" + x)
                                        .replace("null","-") + "\n");
                                binding.tvMeasure.append(jsonArray.getJSONObject(0)
                                        .getString("strMeasure" + x)
                                        .replace("null","-") + "\n");
                                x++
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(this@RecipeDrinkActivity,
                                    "Failed to show recipe :(", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onError(anError: ANError) {
                        progressDialog?.dismiss()
                        Toast.makeText(this@RecipeDrinkActivity,
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