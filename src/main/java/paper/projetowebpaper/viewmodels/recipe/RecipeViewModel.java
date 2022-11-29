package paper.projetowebpaper.viewmodels.recipe;

import paper.projetowebpaper.viewmodels.comment.CommentViewModel;
import paper.projetowebpaper.viewmodels.image.ImageViewModel;
import paper.projetowebpaper.viewmodels.rating.RatingViewModel;
import paper.projetowebpaper.viewmodels.user.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeViewModel {
    public Integer id;
    public String name;
    public String steps;
    public String ingredients;
    public Integer timeInMinutes;
    public UserViewModel author;
    public Double rate;
    public List<ImageViewModel> images = new ArrayList<>();
    public List<CommentViewModel> comments = new ArrayList<>();
    public List<RatingViewModel> ratings = new ArrayList<>();

    public RecipeViewModel() {
    }

    public RecipeViewModel(Integer id, String name, String steps,
                           String ingredients, Integer timeInMinutes,
                           UserViewModel author, Double rate, List<ImageViewModel> images,
                           List<CommentViewModel> comments, List<RatingViewModel> ratings) {
        this.id = id;
        this.name = name;
        this.steps = steps;
        this.ingredients = ingredients;
        this.timeInMinutes = timeInMinutes;
        this.author = author;
        this.rate = rate;
        this.images = images;
        this.comments = comments;
        this.ratings = ratings;
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

    public UserViewModel getAuthor() {
        return author;
    }

    public void setAuthor(UserViewModel author) {
        this.author = author;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public List<ImageViewModel> getImages() {
        return images;
    }

    public void setImages(List<ImageViewModel> images) {
        this.images = images;
    }

    public List<CommentViewModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentViewModel> comments) {
        this.comments = comments;
    }

    public List<RatingViewModel> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingViewModel> ratings) {
        this.ratings = ratings;
    }
}
