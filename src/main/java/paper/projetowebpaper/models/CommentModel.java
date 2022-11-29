package paper.projetowebpaper.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comment")
public class CommentModel {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="comment_seq_gen")
    @SequenceGenerator(name="comment_seq_gen", sequenceName="COMMENT_SEQ")
    public Integer id;

    @Column(name = "content", nullable = false)
    public String content;

    @Column(name = "createdAt", nullable = false)
    public LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "authorId")
    public UserModel author;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    public RecipeModel recipe;

    public CommentModel() {
    }

    public CommentModel(Integer id, String content, LocalDateTime createdAt, UserModel author, RecipeModel recipe) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public RecipeModel getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeModel recipe) {
        this.recipe = recipe;
    }
}
