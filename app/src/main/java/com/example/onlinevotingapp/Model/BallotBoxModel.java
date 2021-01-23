package com.example.onlinevotingapp.Model;

public class BallotBoxModel {
    private String ballotPaperId;
    private String votedCandidate;
    private String electonID;

    public BallotBoxModel() {
    }

    public BallotBoxModel(String ballotPaperId, String votedCandidate, String electonID) {
        this.ballotPaperId = ballotPaperId;
        this.votedCandidate = votedCandidate;
        this.electonID = electonID;
    }

    public String getBallotPaperId() {
        return ballotPaperId;
    }

    public void setBallotPaperId(String ballotPaperId) {
        this.ballotPaperId = ballotPaperId;
    }

    public String getVotedCandidate() {
        return votedCandidate;
    }

    public void setVotedCandidate(String votedCandidate) {
        this.votedCandidate = votedCandidate;
    }

    public String getElectonID() {
        return electonID;
    }

    public void setElectonID(String electonID) {
        this.electonID = electonID;
    }
}
