package trycatch.hs.hansungadress.model;

/**
 * Created by trycatch on 2017. 11. 28..
 */

public class AddressModel {
    private String department;
    private String position;
    private String name;
    private String photo;
    private String office;
    private String phone;
    private String email;
    private boolean favorite;

    public AddressModel(String department, String position, String name, String photo, String office, String phone, String email, boolean favorite) {
        this.department = department;
        this.position = position;
        this.name = name;
        this.photo = photo;
        this.office = office;
        this.phone = phone;
        this.email = email;
        this.favorite = favorite;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
