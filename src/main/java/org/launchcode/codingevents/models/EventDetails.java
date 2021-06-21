package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.*;

@Entity
public class EventDetails extends AbstractEntity {

    @Size(max = 500, message = "Description too long!")
    private String description;

    @NotBlank(message= "email is required")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @OneToOne(mappedBy = "eventDetails")
    private Event event;

    @NotBlank(message="Must enter location")
    @NotNull(message="Must enter location")
    private String location;

    @Positive(message = "Must enter a positive number")
    private int numberOfAttendees;

    public EventDetails(String description, String contactEmail, String location, int numberOfAttendees) {
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = location;
        this.numberOfAttendees = numberOfAttendees;
    }

    public EventDetails(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }
}
