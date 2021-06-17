package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    //findAll, save, findById are included as part of the interface

    @GetMapping
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String renderCreateEventForm(Model model){
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        model.addAttribute("types", EventType.values());
        return "events/create";
    }

    // lives at /events/create
    @PostMapping("create")
    public String createEvent(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()){
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
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds){

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

    //lives at events/edit/{id}
//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId){
//        //TODO model.addAttribute("event", EventData.getById(eventId);
//        Event event = eventRepository.findById(eventId);
//        String title = "Edit Event: " + event.getName();
//        model.addAttribute("event", event);
//        model.addAttribute("title", title);
//        return "events/edit";
//    }

//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description) {
//        Event event = eventRepository.findById(eventId);
//        event.setName(name);
//        event.setDescription(description);
//        return "redirect:";
//    }
}
