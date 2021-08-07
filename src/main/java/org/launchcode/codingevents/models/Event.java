package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    private EventImage eventImage;

    @Size(min = 3, max= 50, message="Name must be between 3 and 50 characters.")
    @NotBlank(message = "Name is required")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Event(){ }

    public Event(String name, EventCategory eventCategory, EventImage eventImage)
    {
        this.name = name;
        this.eventCategory=eventCategory;
        this.eventImage = eventImage;

    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public EventImage getEventImage() {
        return eventImage;
    }

    public void setEventImage(EventImage eventImage) {
        this.eventImage = eventImage;
    }

    @Override
    public String toString() {
        return "Id: "+ "\nName: " + name;
    }

}
