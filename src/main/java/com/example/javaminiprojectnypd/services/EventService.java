package com.example.javaminiprojectnypd.services;

import com.example.javaminiprojectnypd.models.Event;
import com.example.javaminiprojectnypd.models.ICountDTO;
import com.example.javaminiprojectnypd.models.MessageReceivedDTO;
import com.example.javaminiprojectnypd.models.ValidFields;
import com.example.javaminiprojectnypd.repositories.EventRepository;
import com.example.javaminiprojectnypd.repositories.ValidFieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ValidFieldsRepository validFieldsRepository;

    public MessageReceivedDTO addEvent(Event event) {
        MessageReceivedDTO mr = new MessageReceivedDTO();
        mr.setBool(false);
        Optional<Event> eventFound = eventRepository.findById(event.getComplaintID());
        if (eventFound.isEmpty()) {
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
        return eventRepository.countByOffenseCode();
    }

    public MessageReceivedDTO deleteEvent(int eventId) {
        MessageReceivedDTO mr = new MessageReceivedDTO();
        mr.setBool(false);
        Optional<Event> eventFound = eventRepository.findById(eventId);
        if (eventFound.isPresent()) {
            eventRepository.deleteById(eventId);
            mr.setMessage("Event " + eventId + " deleted");
            mr.setBool(true);
            return mr;
        }
        mr.setMessage("Event " + eventId + " could not be found");
        return mr;
    }

    public MessageReceivedDTO validateInput(HashMap<String, String> data, String category) {
        MessageReceivedDTO mr = new MessageReceivedDTO();
        mr.setBool(true);
        mr.setMessage("");
        StringBuilder errorMessage = new StringBuilder();

        List<ValidFields> vFields = validFieldsRepository.findByCategory(category);
        if(data.size() > vFields.size()) {
            mr.setBool(false);
            errorMessage.append("There should only be ").append(vFields.size()).append(" arguments:");
            for (ValidFields vField : vFields) {
                errorMessage.append("\n").append(vField.getFieldId());
            }
            mr.setMessage(errorMessage.toString());
            return mr;
        }

        errorMessage.append("Errors:");
        for (ValidFields vField : vFields) {
            if (data.get(vField.getFieldId()) == null) {
                errorMessage.append("\n").append(vField.getFieldId()).append(" not found");
                mr.setBool(false);
            }
        }

        if (!mr.getBool()) {
            mr.setMessage(errorMessage.toString());
            return mr;
        }
        return mr;
    }
}
