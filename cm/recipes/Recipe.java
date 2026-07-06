package cm.recipes;
import java.util.ArrayList;

// The structure for the different recipes we have
public class Recipe
{
    // Recipe attributes
    protected String recipe_name;
    protected int cooking_time; // How much time it takes to cook, in minutes
    protected double recipe_yield; // How many "servings" the original recipe amounts can create
    protected String time_of_day; // If a recipe is for "breakfast", "lunch" or "dinner"
    protected ArrayList<String> ingredients; // The ingredients by themselves, useful to check for allergies
    protected ArrayList<String> instructions; // The instructions
    protected NutrientProfile nutrients;

    public Recipe(String recipe_name, int cooking_time, double recipe_yield, String time_of_day, ArrayList<String> ingredients, ArrayList<String> instructions, NutrientProfile nutrients)
    {
        setRecipeName(recipe_name);
        setCookingTime(cooking_time);
        setYield(recipe_yield);
        setTimeOfDay(time_of_day);
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
    public void setYield(double recipe_yield)
    {
        if (recipe_yield > 0)
        {
            this.recipe_yield = recipe_yield;
        } else this.recipe_yield = 2;
    }
    public void setTimeOfDay(String time_of_day)
    {
        if (time_of_day.equals("breakfast") || time_of_day.equals("lunch") || time_of_day.equals("dinner"))
        {
            this.time_of_day = time_of_day;
        } else this.time_of_day = "dinner";
    }
    public void setIngredients(ArrayList<String> ingredients) { this.ingredients = ingredients; }
    public void setInstructions(ArrayList<String> instructions) { this.instructions = instructions; }
    public void setNutrientProfile(NutrientProfile nutrients) { this.nutrients = nutrients; }

    // Getters
    public String getRecipeName() { return this.recipe_name; }
    public int getCookingTime() { return this.cooking_time; }
    public double getYield() { return this.recipe_yield; }
    public String getTimeOfDay() { return this.time_of_day; }
    public ArrayList<String> getIngredients() { return this.ingredients; }
    public ArrayList<String> getInstructions() { return this.instructions; }
    public NutrientProfile getNutrientProfile() { return this.nutrients; }
    
    public String getPrettyIngredientsAndInstructions()
    {
        String final_string = "";
        for (String e:getIngredients())
        {
            final_string += e;
            final_string += ",";
        }
        final_string = final_string.substring(0, final_string.length() - 1);
        final_string += "\nINSTRUCTIONS: \n"; 
        for (String e:getInstructions())
        {
            final_string += e;
            final_string += "\n";
        }
        return final_string;
    }
    
    public String toString()
    {
        return String.format("Recipe: %s\nCooking Time: %d min\nYield: %.1f servings\nType of Meal: %s\nIngredients: %s", getRecipeName(), getCookingTime(), getYield(), getTimeOfDay(), getPrettyIngredientsAndInstructions());
    }
}