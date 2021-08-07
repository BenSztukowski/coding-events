package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.EventCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventImageRepository extends CrudRepository<EventCategory, Integer> {
}
