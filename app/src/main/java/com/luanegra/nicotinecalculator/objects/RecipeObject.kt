package com.luanegra.nicotinecalculator.objects

class RecipeObject {
    private var savedrecipe: String = ""
    private var id: String = ""

    constructor()

    constructor(savedrecipe: String, id: String) {
        this.savedrecipe = savedrecipe
        this.id = id
    }

    fun getsavedrecipe(): String{
        return savedrecipe
    }

    fun setsavedrecipe(savedrecipe: String){
        this.savedrecipe = savedrecipe
    }

    fun getid(): String{
        return id
    }

    fun setid(id: String){
        this.id = id
    }


}