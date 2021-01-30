package com.luanegra.nicotinecalculator.ui.recipes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luanegra.nicotinecalculator.R
import com.luanegra.nicotinecalculator.adapters.RecipeAdapter
import com.luanegra.nicotinecalculator.objects.RecipeObject

class RecipesFragment : Fragment() {
    private var recipesAdapter: RecipeAdapter? = null
    lateinit var recycler_recipes: RecyclerView
    private var recipesList: List<RecipeObject>? = null
    var root: View? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_recipes, container, false)
        recycler_recipes = root!!.findViewById(R.id.recycler_recipes)
        recycler_recipes.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = false
        recycler_recipes.layoutManager = linearLayoutManager
        recipesList = ArrayList()
        recipesAdapter = RecipeAdapter(root!!.context, (recipesList as ArrayList<RecipeObject>))
        recycler_recipes.adapter = recipesAdapter
        getAllRecipes()
        return root
    }

    fun getAllRecipes(){
        val sharedPreferences: SharedPreferences = root!!.context.getSharedPreferences("NicotineCalculator", Context.MODE_PRIVATE)
        val allEntries: Map<String, *> = sharedPreferences.all
        for ((key, value) in allEntries) {
            var lines = value.toString().lines()
            val recipe = RecipeObject(key, lines[0], lines[1], lines[2], lines[3])
            if (!recipesList!!.contains(recipe)){
                (recipesList as ArrayList).add(recipe)
                recipesAdapter!!.notifyDataSetChanged()
            }
        }

    }
}