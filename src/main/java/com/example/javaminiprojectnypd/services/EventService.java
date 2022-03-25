package com.example.javaminiprojectnypd.services;

import com.example.javaminiprojectnypd.models.EventDTO;
import com.example.javaminiprojectnypd.models.ICountDTO;
import com.example.javaminiprojectnypd.models.MessageReceivedDTO;
import com.example.javaminiprojectnypd.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public MessageReceivedDTO addEvent(EventDTO event) {
        MessageReceivedDTO mr = new MessageReceivedDTO();
        mr.setBool(false);
        Optional<EventDTO> eventFound = eventRepository.findById(event.getComplaintID());
        if(eventFound.isEmpty()) {
            eventRepository.save(event);
            mr.setMessage("Event " + event.getComplaintID() + " created");
            mr.setBool(true);
            return mr;
        }
        mr.setMessage("Event " + event.getComplaintID() + " already exists");
        return mr;
    }

    public Integer getTotal() {
        return eventRepository.findAll().size();
    }

    public List<ICountDTO> getTotalGroupedByOffense() {
        List<ICountDTO> eventDTOCollection = eventRepository.countByOffenseCode();
        return eventDTOCollection;
    }

    public MessageReceivedDTO deleteEvent(int eventId) {
        MessageReceivedDTO mr = new MessageReceivedDTO();
        mr.setBool(false);
        Optional<EventDTO> eventFound = eventRepository.findById(eventId);
        if(!eventFound.isEmpty()) {
            eventRepository.deleteById(eventId);
            mr.setMessage("Event " + eventId + " deleted");
            mr.setBool(true);
            return mr;
        }
        mr.setMessage("Event " + eventId + " could not be found");
        return mr;
    }
}
