package com.example.adminapplication;

public class Room {

    String roomName;
    String roomQuestion;
    String questionID;
    String roomID;

    public Room(){

    }

    public Room(String roomID, String roomName, String roomQuestion, String questionID) {

        this.roomName = roomName;
        this.roomQuestion = roomQuestion;
        this.questionID = questionID;
        this.roomID = roomID;

    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomQuestion() {
        return roomQuestion;
    }

    public String getQuestionID() {
        return questionID;
    }

    public String getRoomID() {
        return roomID;
    }

}
