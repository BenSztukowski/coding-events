package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.EventDetails;
import org.launchcode.codingevents.models.Tag;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    //findAll, save, findById are included as part of the interface

    @GetMapping
    public String displayAllEvents(@RequestParam(required = false) Integer categoryId,
                                   @RequestParam(required = false) Integer tagId, Model model) {

        if (categoryId == null && tagId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else if (categoryId != null) {
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                model.addAttribute("events", category.getEvents());
            }
        } else {
            Optional<Tag> result = tagRepository.findById(tagId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Tag ID: " + tagId);
            } else {
                Tag tag = result.get();
                model.addAttribute("title", "Events with tag: " + tag.getName());
                model.addAttribute("events", tag.getEvents());
            }
        }
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "events/create";
    }

    // lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        } else {

            eventRepository.save(newEvent);
            return "redirect:";
        }
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event Id: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }

    //responds to /events/add-tag/eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId= " + event.getId();
        }
        return "redirect: add-tag";
    }

    //lives at events/edit/{id}
    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Optional optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            String title = "Edit Event: " + event.getName();
            model.addAttribute("event", event);
            model.addAttribute("title", title);
            return "events/edit";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("edit")
    public String processEditForm(Model model, int eventId, String name, String description, String contactEmail,
                                  String location, Integer numberOfAttendees) {
        Optional optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            model.addAttribute("event", event);
            event.setName(name);
            event.setEventDetails(new EventDetails(description,contactEmail,location, event.getEventDetails().getNumberOfAttendees()));
            return "events/detail";
        } else {
            return "redirect:";
        }
    }
}
