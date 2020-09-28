package Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private final List<String> steps;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe(String name) {
        this.name = name;
        this.steps = new ArrayList<>();
    }
    public Recipe(){
       this.steps = new ArrayList<>();
    }

    public void addStep(String step) {
        steps.add(step);
    }

    public int getSize(){
        return steps.size();
    }
}
