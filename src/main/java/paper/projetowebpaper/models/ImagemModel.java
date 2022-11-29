package paper.projetowebpaper.models;

import javax.persistence.*;

@Entity(name = "image")
public class ImagemModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="image_seq_gen")
    @SequenceGenerator(name="image_seq_gen", sequenceName="IMAGE_SEQ")
    public Integer id;

    @Column(name = "url", nullable = false)
    public String url;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    public RecipeModel recipe;

    public ImagemModel() {
    }

    public ImagemModel(Integer id, String url, RecipeModel recipe) {
        this.id = id;
        this.url = url;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RecipeModel getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeModel recipe) {
        this.recipe = recipe;
    }
}
