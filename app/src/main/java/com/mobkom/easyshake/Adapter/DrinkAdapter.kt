package com.mobkom.easyshake.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobkom.easyshake.Activities.RecipeDrinkActivity
import com.mobkom.easyshake.databinding.ListItemDrinkBinding
import com.mobkom.easyshake.Model.ModelDrink
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class DrinkAdapter(private val modelDrink: List<ModelDrink>, private val context: Context) :
        RecyclerView.Adapter<DrinkAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ListItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val model = modelDrink[position]

            Glide.with(context)
                    .load(model.strDrinkThumb)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .fitCenter()
                    .into(holder.binding.imageDrink)

            holder.binding.tvNameDrink.text = model.strDrink

            rootView.setOnClickListener {
                val intent = Intent(context, RecipeDrinkActivity::class.java)
                intent.putExtra(RecipeDrinkActivity.DRINK_RECIPE, modelDrink[position])
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = modelDrink.size

    class ViewHolder(val binding: ListItemDrinkBinding) : RecyclerView.ViewHolder(binding.root)

}