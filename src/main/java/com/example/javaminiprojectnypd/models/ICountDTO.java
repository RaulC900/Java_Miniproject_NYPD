package com.example.javaminiprojectnypd.models;

// Used for Spring Data Projection
// To use interface-based projection,
// we must define a Java interface composed of getter methods that match the projected attribute names
public interface ICountDTO {
    Long getCount();
    Integer getOffenseCode();
}
