package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Entity
public class Event extends AbstractEntity {

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

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    public Event(){ }

    public Event(String name, String description, String contactEmail, String location,
                 int numberOfAttendees, EventCategory eventCategory)
    {
        this.name = name;
        this.description=description;
        this.contactEmail=contactEmail;
        this.location=location;
        this.eventCategory=eventCategory;
        this.numberOfAttendees=numberOfAttendees;

    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
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


    @Override
    public String toString() {
        return "Id: "+ "\nName: " + name + "\nDescription: " + description;
    }

}
