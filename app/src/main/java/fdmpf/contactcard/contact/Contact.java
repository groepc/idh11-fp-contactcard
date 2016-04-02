package fdmpf.contactcard.contact;

/**
 * Created by Hans on 27-3-2016.
 */
public class Contact {

    private int id;

    private String imageUrl;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String postalCode;
    private String city;
    private String phone;
    private String cell;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public String getCell () {
        return cell;
    }

    public void setCell (String cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id= " + getId() + "," +
                "imageUrl='" + imageUrl + '\'' +
                ", first='" + getFirstName() + '\'' +
                ", last='" + getLastName() + '\'' +
                '}';
    }
}
