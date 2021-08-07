package org.launchcode.codingevents.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import java.awt.*;

@Entity
public class EventImage extends AbstractEntity{

    @OneToOne(mappedBy = "eventImage")
    private Event event;

    @Column(nullable = true, length = 64)
    private String photos;



    public EventImage(){}

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    @Transient
    public String getPhotos() {
        if (photos == null){
            return null;
        }
        return "/user-photos/" + getId() + "/" + photos;
    }
}
