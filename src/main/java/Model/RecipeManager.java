package Model;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Singleton class that stores all our saved recipes. We serialize and
 * deserialize this class to save our recipes to files.
 */
public class RecipeManager implements Iterable<Recipe> {
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
        if(recipe.getName().equals("")) return;
        recipes.add(recipe);
        serialize();
    }

    public boolean contains(Recipe recipe){
        boolean ret = false;
        if(recipe!=null) {
            for (Recipe r : recipes) {
                if (r.getName().equals(recipe.getName())) {
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    public void serialize() {
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
            System.err.println("Recipes.json doesn't exist yet.");
            System.err.println("This is ok if this no recipes are saved yet.");
        }
    }

    @Override
    public Iterator<Recipe> iterator() {
        return recipes.iterator();
    }

    public void update(Recipe recipe) {
        if(recipe == null) return;
        int i=0;
        while( (i<recipes.size()) && (!recipes.get(i).getName().equals(recipe.getName())) ){
            i++;
        }
        recipes.remove(i);
        recipes.add(recipe);
        serialize();
    }

    public int size() {
        return recipes.size();
    }

    public void remove(Recipe recipe){
        recipes.remove(recipe);
    }
}
