
package DAO;

import com.stuffit.www.data.DataManager;
import com.stuffit.www.data.objects.Ingredient;
import com.stuffit.www.data.objects.Recipe;
import java.util.List;

/**
 *
 * @author Bikramjit
 */

/*
Strong IS-A relationship in abstract classes, whereas not required in interface.
Interface names are more like adjectives.
*/
public abstract class DAO {
    
    private DataManager getDataManager(){
        DataManager dm;
        dm = new DataManager();
        return dm;
    }
}
