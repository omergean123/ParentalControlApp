package com.omer.parentalcontrolapp.Objects;

public class Task {
    private String subject , pages;

    public Task(String subject, String pages) {
        this.subject = subject;
        this.pages = pages;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
