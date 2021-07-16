package com.mobkom.easyshake.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobkom.easyshake.Activities.ListDrinkActivity
import com.mobkom.easyshake.databinding.ListItemCategoriesBinding
import com.mobkom.easyshake.Model.ModelCategories

class CategoriesAdapter(private val modelCategories: List<ModelCategories>, private val context: Context) :
        RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
            ListItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val model = modelCategories[position]

            /*Random randomColor = new Random();
            int currentColor = Color.argb(255, randomColor.nextInt(256), randomColor.nextInt(256), randomColor.nextInt(256));
            holder.cvCategories.setCardBackgroundColor(currentColor);*/

            holder.binding.tvCategories.text = model.strCategory

            rootView.setOnClickListener {
                val intent = Intent(context, ListDrinkActivity::class.java)
                intent.putExtra(ListDrinkActivity.LIST_DRINK, modelCategories[position])
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = modelCategories.size

    class ViewHolder(val binding: ListItemCategoriesBinding) : RecyclerView.ViewHolder(binding.root)

}