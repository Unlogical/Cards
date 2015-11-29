package cards.models;

/**
 * Created by cyou on 11/29/15.
 */
public class CardSet {

    private final int id;
    private final String title;
    private final boolean privacy;
    private final String author;
    private final String creation_date;
    private final String description;

    public CardSet(Integer id, String title, boolean privacy, String author, String creation_date, String description) {
        this.id = id;
        this.title = title;
        this.privacy = privacy;
        this.author = author;
        this.creation_date = creation_date;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getPrivacy() {
        return privacy;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public String getDescription() {
        return description;
    }
}
