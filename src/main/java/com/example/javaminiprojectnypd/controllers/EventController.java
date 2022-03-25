package com.example.javaminiprojectnypd.controllers;

import com.example.javaminiprojectnypd.models.EventDTO;
import com.example.javaminiprojectnypd.models.ICountDTO;
import com.example.javaminiprojectnypd.models.MessageReceivedDTO;
import com.example.javaminiprojectnypd.models.TotalDTO;
import com.example.javaminiprojectnypd.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@Validated
@RequestMapping("/dataset")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/stats/total", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TotalDTO> getTotalNumOfEvents() {
        TotalDTO totalDTO = new TotalDTO();
        totalDTO.setTotal(eventService.getTotal());
        return new ResponseEntity<>(totalDTO, HttpStatus.OK);
        //return eventService.getTotal();
    }

    @GetMapping(value = "/stats/offenses")
    public List<ICountDTO> getTotalGroupedByOffense() {
        List<ICountDTO> listGrouped;
        listGrouped = eventService.getTotalGroupedByOffense();
        return listGrouped;
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable("id")
                                               @NotNull
                                               @Min(value = 100000000, message = "id must be a 9-digit number")
                                               @Max(value = 999999999, message = "id must be a 9-digit number") int eventId) {
        MessageReceivedDTO mr = eventService.deleteEvent(eventId);
        if(mr.getBool()) {
            return new ResponseEntity<>(mr.getMessage(), HttpStatus.OK);
        }
        return new ResponseEntity<>(mr.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping
    public ResponseEntity<String> createRecord(@Valid @RequestBody EventDTO event) {
        MessageReceivedDTO mr = eventService.addEvent(event);
        if(mr.getBool()) {
            return new ResponseEntity<>(mr.getMessage(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(mr.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleException(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder("Errors: \n");
        BindingResult br = ex.getBindingResult();
        List<FieldError> feList = br.getFieldErrors();
        ListIterator<FieldError> feListIterator = feList.listIterator();
        while(feListIterator.hasNext()){
            FieldError temp = feListIterator.next();
            errorMessage.append(temp.getDefaultMessage());
            if(feListIterator.hasNext()) {
                errorMessage.append("\n");
            }
        }
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleException(ConstraintViolationException ex) {
        // Loop through FieldErrors in ex.getBindingResult();
        // return *YourErrorReponse* filled using *fieldErrors*
        StringBuilder errorMessage = new StringBuilder("Errors: \n");
        Set<ConstraintViolation<?>> constraintViolationsSet = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolationsSet.iterator();
        while(iterator.hasNext()) {
            ConstraintViolation<?> constraintViolation = iterator.next();
            errorMessage.append(constraintViolation.getMessage());
            if(iterator.hasNext()) {
                errorMessage.append("\n");
            }
        }
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.NOT_ACCEPTABLE);
    }
}
