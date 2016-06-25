
package com.stuffit.www.interfaces;

import com.stuffit.www.data.objects.Recipe;
import java.util.List;

/**
 *
 * @author Bikramjit
 */
public interface Search {
    
    public List<Recipe> searchRecipes(List<String> l);
}


