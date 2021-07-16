package com.mobkom.easyshake.Retrofit

object ApiEndPoint {
    var BASEURL = "https://www.thecocktaildb.com/api/json/v1/1/"
    var URL_CATEGORIES = "list.php?c=list"
    var URL_FILTER = "filter.php?c={strCategory}"
    var URL_RECIPE = "lookup.php?i={idDrink}"
}