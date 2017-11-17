package ac.uk.bristol.cs.santa.grottobooking.web;

public class GrottoDTO {
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String address;
    String name;
}
