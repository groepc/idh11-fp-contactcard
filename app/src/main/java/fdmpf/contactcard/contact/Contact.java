package fdmpf.contactcard.contact;

/**
 * Created by Hans on 27-3-2016.
 */
public class Contact {
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String imageUrl;
    public String title;
    public String first;
    public String last;

    @Override
    public String toString() {
        return "Person{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                '}';
    }
}
