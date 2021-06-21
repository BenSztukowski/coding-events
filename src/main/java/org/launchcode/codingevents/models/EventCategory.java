package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class EventCategory extends AbstractEntity {


    @NotBlank(message = "Name is required")
    private String name;

    public EventCategory(){}

    public EventCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
