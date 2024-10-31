package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown = true) //if data receive the database has any extre/unnown attribute ignore it
public class JsonPlaceHolderPojo {
/*
    {
       "userId": 55,
       "title": "Tidy your room",
       "completed": false
       }
 */

    // Step1: Create all variables as prvate (Encapsulation)
    private Integer userId;
    private String title;
    private Boolean completed;

    // Step2:


    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // Step4: Create toString() Method


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
