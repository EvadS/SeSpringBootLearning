package com.se.enm.model;

import com.se.enm.enums.Category;
import com.se.enm.enums.Priority;
import com.se.enm.enums.Status;
import com.se.enm.enums.Type;

import javax.persistence.*;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    //Mapping Ordinal Value
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    /**
     * Mapping String Value
     * (+) more readable
     * (-consumes a lot more space than necessary)
     */
    @Enumerated(EnumType.STRING)
    private Type type;


    // Using @PostLoad and @PrePersist annotations -->
    @Basic
    private int priorityValue;

    private Priority priority;
    // ---- category
    private Category category;

    @PostLoad
    void fillTransient() {
        if (priorityValue > 0) {
            this.priority = Priority.of(priorityValue);
        }
    }
// <--- Using @PostLoad and @PrePersist annotations

    @PrePersist
    void fillPersistent() {
        if (priority != null) {
            this.priorityValue = priority.getPriority();
        }
    }

    public int getPriorityValue() {
        return priorityValue;
    }

    public void setPriorityValue(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // standard constructors, getters and setters
}