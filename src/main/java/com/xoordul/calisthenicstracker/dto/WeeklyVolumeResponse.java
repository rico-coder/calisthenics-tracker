package com.xoordul.calisthenicstracker.dto;

/**
 * Author: Rico Krenn
 * Created: 06/10/2026
 * Version: 1.0
 * Description: sends week and totalSets
 * Project: 200_Abschlussprojekt
 */
// This is what the server sends back with the volume request
public class WeeklyVolumeResponse {
    // sends back the week
    private String week;
    //sends back the sets made in the week
    private int totalSets;

    // Getter and Setter for week and totalSets
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public void setTotalSets(int totalSets) {
        this.totalSets = totalSets;
    }
}
