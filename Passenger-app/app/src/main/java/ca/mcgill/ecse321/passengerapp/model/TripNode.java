package ca.mcgill.ecse321.passengerapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import java.io.Serializable;

@JsonIdentityInfo(generator=JSOGGenerator.class)
public class TripNode implements Serializable {

    private Long id;
    private Position position;
    private PointType pointType;
    private String name;
    private String time;


    public TripNode() {
        this.setPosition(new Position("ontario"));
    }

    public TripNode(
            String name,
            PointType pointType,
            String time) {
        super();
        this.name= name;
        this.pointType = pointType;
        this.time = time;
        this.setPosition(new Position("ontario"));
        System.out.println(this.position.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPointType(PointType value) {
        this.pointType = value;
    }

    public PointType getPointType() {
        return this.pointType;
    }

    public void setTime(String value) {
        this.time = value;
    }

    public String getTime() {
        return this.time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof TripNode){
            return id.equals(((TripNode) obj).getId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return id.intValue();
    }

}