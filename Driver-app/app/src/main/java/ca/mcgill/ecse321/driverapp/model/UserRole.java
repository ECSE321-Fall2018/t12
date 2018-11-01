package ca.mcgill.ecse321.driverapp.model;


public class UserRole {

    private Long id;
    private String name;

    public UserRole() {

    }

    public UserRole(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
