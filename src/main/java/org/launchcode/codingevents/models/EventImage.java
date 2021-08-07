package org.launchcode.codingevents.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.awt.*;

@Entity
public class EventImage extends AbstractEntity{

    @OneToOne(mappedBy = "eventImage")
    private Event event;

    @Column(nullable = true, length = 64)
    private String photos;

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
