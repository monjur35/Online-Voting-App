package com.example.onlinevotingapp.Model;

public class ElectionModel {
    private String electionID;
    private String positionName;
    private String formattedDate;
    private String startTime;
    //private long startTimeStamp;
    private String endTime;
    //private long endTimeStamp;
    private String candidate1;
    private String candidate2;
    private String candidate3;
    private String candidate1TotalVote;
    private String candidate2TotalVote;
    private String candidate3TotalVote;


    public ElectionModel() {
    }

    public ElectionModel(String electionID, String positionName, String formattedDate, String startTime, String endTime, String candidate1, String candidate2, String candidate3, String candidate1TotalVote, String candidate2TotalVote, String candidate3TotalVote) {
        this.electionID = electionID;
        this.positionName = positionName;
        this.formattedDate = formattedDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.candidate1 = candidate1;
        this.candidate2 = candidate2;
        this.candidate3 = candidate3;
        this.candidate1TotalVote = candidate1TotalVote;
        this.candidate2TotalVote = candidate2TotalVote;
        this.candidate3TotalVote = candidate3TotalVote;
    }

    public String getElectionID() {
        return electionID;
    }

    public void setElectionID(String electionID) {
        this.electionID = electionID;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

   /* public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }*/

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

   /* public long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }*/

    public String getCandidate1() {
        return candidate1;
    }

    public void setCandidate1(String candidate1) {
        this.candidate1 = candidate1;
    }

    public String getCandidate2() {
        return candidate2;
    }

    public void setCandidate2(String candidate2) {
        this.candidate2 = candidate2;
    }

    public String getCandidate3() {
        return candidate3;
    }

    public void setCandidate3(String candidate3) {
        this.candidate3 = candidate3;
    }

    public String getCandidate1TotalVote() {
        return candidate1TotalVote;
    }

    public void setCandidate1TotalVote(String candidate1TotalVote) {
        this.candidate1TotalVote = candidate1TotalVote;
    }

    public String getCandidate2TotalVote() {
        return candidate2TotalVote;
    }

    public void setCandidate2TotalVote(String candidate2TotalVote) {
        this.candidate2TotalVote = candidate2TotalVote;
    }

    public String getCandidate3TotalVote() {
        return candidate3TotalVote;
    }

    public void setCandidate3TotalVote(String candidate3TotalVote) {
        this.candidate3TotalVote = candidate3TotalVote;
    }
}
