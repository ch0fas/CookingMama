package cm.recipes;
import java.util.ArrayList;

// The structure for the different recipes we have
public abstract class Recipe
{
    // Recipe attributes
    protected String recipe_name;
    protected int cooking_time; // How much time it takes to cook, in minutes
    protected double yield; // How many "servings" the original recipe amounts can create
    protected ArrayList<String> ingredients; // The ingredients by themselves, useful to check for allergies
    protected ArrayList<String> instructions; // The instructions
    protected NutrientProfile nutrients;

    public abstract double getPortionWeight();

    public Recipe(String recipe_name, int cooking_time, double yield, ArrayList<String> ingredients, ArrayList<String> instructions, NutrientProfile nutrients)
    {
        setRecipeName(recipe_name);
        setCookingTime(cooking_time);
        setYield(yield);
        setIngredients(ingredients);
        setInstructions(instructions);
        setNutrientProfile(nutrients);
    }

    // Setters

    public void setRecipeName(String name) { this.recipe_name = name; }
    public void setCookingTime(int time)
    {
        if (time > 0)
        {
            this.cooking_time = time;
        } else this.cooking_time = 30;
    }
    public void setYield(double yield)
    {
        if (yield > 0)
        {
            this.yield = yield;
        } else this.yield = 2;
    }
    public void setIngredients(ArrayList<String> ingredients) { this.ingredients = ingredients; }
    public void setInstructions(ArrayList<String> instructions) { this.instructions = instructions; }
    public void setNutrientProfile(NutrientProfile nutrients) { this.nutrients = nutrients; }

    // Getters
    public String getRecipeName() { return this.recipe_name; }
    public int getCookingTime() { return this.cooking_time; }
    public double getYield() { return this.yield; }
    public ArrayList<String> getIngredients() { return this.ingredients; }
    public ArrayList<String> getInstructions() { return this.instructions; }
    public NutrientProfile getNutrientProfile() { return this.nutrients; }
}