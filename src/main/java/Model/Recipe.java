package Model;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private List<String> steps = new ArrayList<>();
    private String name;

    public Recipe(String name) {
        this.name = name;
    }

    public void addStep(String step) {
        steps.add(step);
    }
}
