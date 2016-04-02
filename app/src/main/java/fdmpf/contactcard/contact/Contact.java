package fdmpf.contactcard.contact;

/**
 * Created by Hans on 27-3-2016.
 */
public class Contact {

    public int getId() { return id;}

    public void setId(int id) {
        this.id = id;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String imageUrl;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private int id;

    @Override
    public String toString() {
        return "Person{" +
                "id= " + getId() + "," +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                ", first='" + getFirstName() + '\'' +
                ", last='" + getLastName() + '\'' +
                '}';
    }
}
