import java.util.ArrayList;

import cm.recipes.NutrientProfile;
import cm.recipes.Recipe;

public class Playground
{
    public static void main(String[] args) 
    {
        NutrientProfile np1 = new NutrientProfile(1, 2, 3, 4, 5, 6);
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("cereal");
        ingredients.add("milk");
        ArrayList<String> instructions = new ArrayList<>();
        instructions.add("Pour cereal into bowl");
        instructions.add("Pour milk");
        Recipe recipe1 = new Recipe("Cool Cereal", 5, 1, "breakfast", ingredients, instructions, np1);

        System.out.println(recipe1);
        // Nothing here for now    
    }
}