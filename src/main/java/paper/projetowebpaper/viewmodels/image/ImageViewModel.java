package paper.projetowebpaper.viewmodels.image;

public class ImageViewModel {
    public Integer id;
    public String url;
    public Integer recipeId;

    public ImageViewModel() {
    }

    public ImageViewModel(Integer id, String url, Integer recipeId) {
        this.id = id;
        this.url = url;
        this.recipeId = recipeId;
    }
}
