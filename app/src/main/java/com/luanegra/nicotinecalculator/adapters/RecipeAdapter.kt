package com.luanegra.nicotinecalculator.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.luanegra.nicotinecalculator.R
import com.luanegra.nicotinecalculator.objects.RecipeObject


class RecipeAdapter(mContext: Context, mRecipeList: List<RecipeObject>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder?>() {
    private val mContext = mContext
    private val mRecipeList: List<RecipeObject> = mRecipeList

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recipe: TextView = itemView.findViewById(R.id.recipe)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View?
            view = LayoutInflater.from(mContext).inflate(
                R.layout.recipe_object_layout,
                parent,
                false
            )

        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: RecipeObject = mRecipeList[position]
        holder.recipe.text = recipe.getsavedrecipe()
        holder.recipe.setOnClickListener {
            val options = arrayOf<CharSequence>(
                    mContext.getString(R.string.deleterecipe),
                    mContext.getString(R.string.cancel)
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
            builder.setTitle("Choose an option")
            builder.setItems(options) { dialog, which ->
                if (which == 0) {
                    val sharedPreferences: SharedPreferences = mContext.getSharedPreferences("NicotineCalculator", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor =  sharedPreferences.edit()
                    editor.remove(recipe.getid()).apply()
                    (mRecipeList as ArrayList).removeAt(position)
                    notifyDataSetChanged()
                }else{
                    dialog.dismiss()
                }
            }
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return mRecipeList.size
    }

}