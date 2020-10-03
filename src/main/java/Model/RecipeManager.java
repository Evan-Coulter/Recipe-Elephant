package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that stores all our saved recipes. We serialize and
 * deserialize this class to save our recipes to files.
 */
public class RecipeManager {
    private static RecipeManager instance;

    private final List<Recipe> recipes;

    private RecipeManager(){
        recipes = new ArrayList<>();
    }

    public RecipeManager getInstance(){
        return instance;
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }
}
