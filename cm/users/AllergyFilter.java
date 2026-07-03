package cm.users;
import cm.recipes.Recipe;

public class AllergyFilter implements DietaryFilter
{
    private String allergen;

    public AllergyFilter(String allergen)
    {
        this.allergen = allergen;
    }

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