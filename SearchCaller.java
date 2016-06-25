package com.stuffit.www.data;

import com.stuffit.www.data.objects.Recipe;
import com.stuffit.www.interfaces.Search;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public class SearchCaller {

    private Search searcher;

    
    public SearchCaller(Search searchMethod, List<String> ingred) {
        this.searcher = searchMethod;
    }

    public List<Recipe> executeSearch(List<String> ingredientList) {

        return searcher.searchRecipes(ingredientList);
        //return null;
    }
}
