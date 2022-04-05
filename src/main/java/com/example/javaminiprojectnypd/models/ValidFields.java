package com.example.javaminiprojectnypd.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valid_fields")
public class ValidFields {

    @Id
    private String fieldId;

    private String category;

    public ValidFields() {
    }

    public ValidFields(String fieldId, String category) {
        this.fieldId = fieldId;
        this.category = category;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
