package com.cass.crud_back.model;

import com.cass.crud_back.enums.Category;
import com.cass.crud_back.enums.Status;
import com.cass.crud_back.enums.converters.CategoryConverter;
import com.cass.crud_back.enums.converters.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inactive' WHERE id = ?")
//The syntax "id = ?" represents the id parameter passed in the delete method on controller within the controller class

@Where(clause = "status = 'Active'")
//filter using hibernate properties
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id") //converts the "id" field in spring app to "_id"
    private Long id;

    @NotNull
    @NotBlank //doesn't allow spaces
    @Length(min = 5, max = 40)
    @Column(length = 40, nullable = false)
    private String name;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

    @NotNull
    @NotEmpty
    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "course")
    //remove the lessons (child class) when the course is deleted
    private List<Lesson> lessons = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
