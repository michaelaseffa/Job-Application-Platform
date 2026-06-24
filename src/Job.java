import java.io.Serializable;

public class Job implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String category;

    public Job(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return title + " (" + category + ")";
    }
}