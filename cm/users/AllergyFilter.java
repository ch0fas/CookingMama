package cm.users;
import cm.recipes.Recipe;

public class AllergyFilter implements DietaryFilter
{
    private String allergen;

    public AllergyFilter(String allergen)
    {
        this.allergen = allergen;
    }

    // Like the other filters, but this one only checks the different separate allergen the user may type in, like "chocolate" or "nuts"
    @Override
    public boolean matches(Recipe recipe)
    {
        for (String ingredient: recipe.getIngredients())
        {
            if (ingredient.contains(this.allergen))
            {
                return false;
            }
        }

        return true;
    }
}