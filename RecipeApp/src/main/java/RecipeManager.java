//import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

//class will manage the recipes file (add recipes, saving, retrieving, loading from the file )
class RecipeManager {
    private HashMap<String, Recipe> recipeMap;

    public RecipeManager() {
        recipeMap = new HashMap<String, Recipe>(); // Create the recipeMap
    }

    // Add a recipe to manager
    public void addRecipe(Recipe recipe) {
        recipeMap.put(recipe.getName(), recipe);
    }

    // Get recipe by name
    public Recipe getRecipeByName(String name) {
        return recipeMap.get(name);
    }

    // Save the recipes to a file
    public void saveRecipesToFile(String filename) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename));

            // name|category|ingredient1,ingredient2,...no spaces
            for (Recipe recipe : recipeMap.values()) {
                writer.write(recipe.getName() + "|" + recipe.getCategory() + "|");

                // Write ingredients as a comma-separated list
                writer.write(String.join(",", recipe.getIngredients()));
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    // Load recipes from a file
    public void loadRecipesFromFile(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = reader.readLine()) != null) {
                //trimming the recipes/ingreientds 
                // Splits the line to get name, category, and ingredients
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String name = parts[0]; // Recipe name
                    String category = parts[1]; // Category

                    // creates a HashSet (ingredients)
                    String[] ingredientsArray = parts[2].split(",");
                    HashSet<String> ingredients = new HashSet<String>();
                    for (String ingredient : ingredientsArray) {
                        ingredients.add(ingredient.trim().toLowerCase()); 
                    }

                    // Create a new object and adds it to the HashMap
                    recipeMap.put(name, new Recipe(name, ingredients, category));

                    // Debugging: print out the loaded recipe
                    System.out.println("Loaded recipe: " + name);
                    System.out.println("  Category: " + category);
                    System.out.println("  Ingredients: " + ingredients);
                } else {
                    System.out.println("Skipping invalid line: " + line); // if the line in txt file does not match given format
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    // Get all of the recipes for matching given input
    public HashMap<String, Recipe> getAllRecipes() {
        return recipeMap;
    }
}

