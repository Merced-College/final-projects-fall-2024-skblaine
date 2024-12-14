import java.util.HashSet;

//class will gather recipe information from a HashSet. Formats the recipe.
class Recipe {
    private String name;
    private HashSet<String> ingredients;
    private String category;

    public Recipe(String name, HashSet<String> ingredients, String category) {
        this.name = name;
        this.ingredients = ingredients;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getIngredients() {
        return ingredients;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Recipe: " + name + "\nCategory: " + category + "\nIngredients: " + ingredients.toString();
    }
}
