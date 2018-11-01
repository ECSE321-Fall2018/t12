package ca.mcgill.ecse321.driverapp.model;



public class Registration {

    private Long id;
    private Role role;
    private User user;
    private Trip trip;

    public Registration() {
    }

    public Registration(Role role,
                        User user,
                        Trip trip) {
        setRole(role);
        setUser(user);
        setTrip(trip);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role value) {
        this.role = value;
    }

    public Role getRole() {
        return this.role;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

}
