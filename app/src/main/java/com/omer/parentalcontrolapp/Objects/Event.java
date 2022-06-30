package com.omer.parentalcontrolapp.Objects;

import java.util.Date;

public class Event {
    private String date;
    private String eventDetails;
    public Event(String date, String eventDetails) {
        this.date = date;
        this.eventDetails = eventDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }
}
