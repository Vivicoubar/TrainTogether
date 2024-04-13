package fr.a2s.traintogether;

public class SessionModel {

    String id = "session0";
    String title = "Default";
    String warmup = "Default warmup";
    String session = "Default session";
    String stretching = "Default stretching";
    String sport = "none";

    public SessionModel(String id, String title, String warmup, String session, String stretching, String sport) {
        this.id = id;
        this.title = title;
        this.warmup = warmup;
        this.session = session;
        this.stretching = stretching;
        this.sport = sport;
    }

    public SessionModel() {

    }

    public String getSession() {
        return session;
    }

    public String getStretching() {
        return stretching;
    }

    public String getTitle() {
        return title;
    }

    public String getWarmup() {
        return warmup;
    }

    public String getId() {
        return id;
    }

    public String getSport() {
        return sport;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setStretching(String stretching) {
        this.stretching = stretching;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWarmup(String warmup) {
        this.warmup = warmup;
    }

    public void setId(String id) {
        this.id = id;
    }
}
