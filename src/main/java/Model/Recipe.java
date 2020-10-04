package Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final List<String> steps;
    private final List<String> ingredients;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe(){
        name = "";
        steps = new ArrayList<>();
        ingredients = new ArrayList<>();
    }

    public void addStep(String step) {
        steps.add(step);
    }
    public void addIngredient(String step) {
        ingredients.add(step);
    }

    public void removeStep(String step) {
        steps.remove(step);
    }
    public void removeIngredient(String ingredient) {ingredients.remove(ingredient);}

    @Override
    public String toString() {
        return "Recipe{" +
                "steps=" + steps +
                ", ingredients=" + ingredients +
                ", name='" + name + '\'' +
                '}';
    }
}
