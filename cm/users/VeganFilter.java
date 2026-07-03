package cm.users;
import cm.recipes.Recipe;

public class VeganFilter implements DietaryFilter
{
    private final String[] VEGAN_KEYWORDS = {"milk", "cheese", "butter", "beef", "meat", "chicken", "egg", "eggs", "honey", "pork", "turkey", "fish", "cream", "yogurt"};

    @Override
    public boolean matches(Recipe recipe)
    {
        for (String ingredient: recipe.getIngredients())
        {
            for (String keyword: VEGAN_KEYWORDS)
            {
                if (ingredient.contains(keyword))
                {
                    return false;
                }
            }
        }

        return true;
    }
}