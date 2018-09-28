package ca.mcgill.ecse321.webservice.model;

import javax.persistence.Entity;

@Entity
public class Admin{
private String username;
   
   public void setUsername(String value) {
this.username = value;
    }
public String getUsername() {
return this.username;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
       }
   }
