package cm.users;
import cm.recipes.Recipe;

public interface DietaryFilter
{
    // Different method for each filter, matches recipes according to specific wording
    boolean matches(Recipe recipe);

}