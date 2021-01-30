package com.luanegra.nicotinecalculator.objects

class RecipeObject {
    private var id: String = ""
    private var forcaDesejada: String = ""
    private var forcaConcentrada: String = ""
    private var totalLiquido: String = ""
    private var resultado: String = ""

    constructor()

    constructor(id: String, forcaDesejada: String, forcaConcentrada: String, totalLiquido: String, resultado: String) {
        this.id = id
        this.forcaDesejada = forcaDesejada
        this.forcaConcentrada = forcaConcentrada
        this.totalLiquido = totalLiquido
        this.resultado = resultado
    }


    fun getid(): String{
        return id
    }

    fun setid(id: String){
        this.id = id
    }

    fun getforcaDesejada(): String{
        return forcaDesejada
    }

    fun setforcaDesejada(forcaDesejada: String){
        this.forcaDesejada = forcaDesejada
    }

    fun getforcaConcentrada(): String{
        return forcaConcentrada
    }

    fun setforcaConcentrada(forcaConcentrada: String){
        this.forcaConcentrada = forcaConcentrada
    }

    fun gettotalLiquido(): String{
        return totalLiquido
    }

    fun settotalLiquido(totalLiquido: String){
        this.totalLiquido = totalLiquido
    }

    fun getresultado(): String{
        return resultado
    }

    fun setresultado(resultado: String){
        this.resultado = resultado
    }


}