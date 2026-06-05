package com.sparta.rp.ctrworldtour.services;

import org.springframework.stereotype.Service;

@Service
public class TournamentService {
    private int currentCourse;
    private boolean prelims;
    private String nextCourse;

    public String getNextCourse() {
        return nextCourse;
    }

    public void setNextCourse(String nextCourse) {
        this.nextCourse = nextCourse;
    }

    public int getCurrentCourse() {
        return currentCourse;
    }

    public void setCurrentCourse(int currentCourse) {
        this.currentCourse = currentCourse;
    }

    public boolean isPrelims() {
        return prelims;
    }

    public void setPrelims(boolean prelims) {
        this.prelims = prelims;
    }

    public void preliminaries() {
        this.prelims = true;
        this.currentCourse = 0;
    }

    public void mainEvent() {
        this.prelims = false;
        this.currentCourse = 0;
    }

    public void nextTrack() {
        setCurrentCourse(getCurrentCourse() + 1);
    }

}
