package com.tryagain.todo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class TodoEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private boolean done;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TodoEntity(Long id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    public TodoEntity()
    {
        super();
    }



}
