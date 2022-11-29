package paper.projetowebpaper.viewmodels.recipe;

import paper.projetowebpaper.viewmodels.image.ImageViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeRegisterViewModel {
    public Integer id;
    public String name;
    public String steps;
    public String ingredients;
    public Integer timeInMinutes;
    public Integer authorId;

    public List<ImageViewModel> images = new ArrayList<>();

    public RecipeRegisterViewModel() {
    }

    public RecipeRegisterViewModel(Integer id, String name, String steps, String ingredients, Integer timeInMinutes, Integer authorId, List<ImageViewModel> images) {
        this.id = id;
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.timeInMinutes = timeInMinutes;
        this.authorId = authorId;
        this.images = images;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(Integer timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List<ImageViewModel> getImages() {
        return images;
    }

    public void setImages(List<ImageViewModel> images) {
        this.images = images;
    }
}
