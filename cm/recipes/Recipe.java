package cm.recipes;
import java.util.ArrayList;

// The structure for the different recipes we have
public abstract class Recipe
{
    // Recipe attributes
    protected String recipe_name;
    protected int cooking_time; // How much time it takes to cook
    protected double yield; // How many "servings" the original recipe amounts can create
    protected ArrayList<String> ingredients; // The ingredients by themselves, useful to check for allergies
    protected ArrayList<String> instructions; // The instructions
    protected NutrientProfile nutrients;

    public abstract double getPortionWeight();
}