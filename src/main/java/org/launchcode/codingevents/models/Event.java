package org.launchcode.codingevents.models;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {

//    @Id
//    @GeneratedValue
//    private int id;

    @Size(min = 3, max= 50, message="Name must be between 3 and 50 characters.")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message= "email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @NotBlank(message="Must enter location")
    @NotNull(message="Must enter location")
    private String location;

    @Positive(message = "Must enter a positive number")
    private int numberOfAttendees;

    private EventType type;

    public Event(){ }

    public Event(String name, String description, String contactEmail, String location,
                 int numberOfAttendees, EventType type)
    {
        this.name = name;
        this.description=description;
        this.contactEmail=contactEmail;
        this.location=location;
        this.type=type;
        this.numberOfAttendees=numberOfAttendees;

    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getId() {
//        return id;
//    }

    @Override
    public String toString() {
        return "Id: "+ "\nName: " + name + "\nDescription: " + description;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event event = (Event) o;
//        return id == event.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
