package ca.mcgill.ecse321.passengerapp.model;

import java.io.Serializable;

/**
 * Created by michelabdelnour on 2018-11-01.
 */

public class UserRole implements Serializable {

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

