package Model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that stores all our saved recipes. We serialize and
 * deserialize this class to save our recipes to files.
 */
public class RecipeManager {
    private static final String FILE_LOCATION = "Recipes.json";
    private static RecipeManager instance = null;

    private final List<Recipe> recipes;
    private RecipeManager(){
        recipes = new ArrayList<>();
    }

    public static RecipeManager getInstance(){
        if(instance == null){
            instance = new RecipeManager();
        }
        return instance;
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
        serialize();
    }

    public boolean contains(Recipe recipe){
        boolean ret = false;
        for (Recipe r:recipes) {
            if (r.getName().equals(recipe.getName())) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    private void serialize() {
        try (Writer writer = new FileWriter(FILE_LOCATION)) {
            Gson gson = new Gson();
            gson.toJson(this, writer);
        }
        catch(IOException exception){
            System.err.println("An error occurred saving recipes to file.");
            exception.printStackTrace();
        }
    }

    public void deserialize() {
        try(Reader reader = new FileReader(FILE_LOCATION)) {
            Gson gson = new Gson();
            instance = gson.fromJson(reader, RecipeManager.class);
        }catch(IOException exception){
            System.err.println("An error occurred loading recipes from file.");
            exception.printStackTrace();
        }
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }
}
