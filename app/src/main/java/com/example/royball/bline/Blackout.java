package com.example.royball.bline;

import android.content.Context;
import android.media.AudioManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by royball on 7/19/2015.
 */
public class Blackout {
    //Class variables
    private int startHour, startMinute, endHour, endMinute;
    private String title;

    //Constructor
    public Blackout(String title, int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.title = title;
    }

    public Blackout() {
        this.startHour = 99;
        this.startMinute = 99;
        this.endHour = 99;
        this.endMinute = 99;
        this.title = "";
    }

    //Getters and Setters
    public int getStartHour() {
        return startHour;
    }

    public String getStartHourString() {
        if (startHour < 10) {
            return "0" + Integer.toString(startHour);
        } else {
            return Integer.toString(startHour);
        }
    }

    public int getStartMinute() {
        return startMinute;
    }

    public String getStartMinuteString() {
        if (startMinute < 10) {
            return "0" + Integer.toString(startMinute);
        } else {
            return Integer.toString(startMinute);
        }
    }

    public int getEndHour() {
        return endHour;
    }

    public String getEndHourString() {
        if (endHour < 10) {
            return "0" + Integer.toString(endHour);
        } else {
            return Integer.toString(endHour);
        }
    }

    public int getEndMinute() {
        return endMinute;
    }

    public String getEndMinuteString() {
        if (endMinute < 10) {
            return "0" + Integer.toString(endMinute);
        } else {
            return Integer.toString(endMinute);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static void checkInBlackout(Blackout[] blackouts, AudioManager myAudioManager) {
        //This is where I'll check to see if we are currently in a blackout, if so set it to vibrate

        for (int i = 0; i < blackouts.length; i++) {
            //Compare current time to start time
            //if greater compare current time to end time
            //if less set ringer to vibrate

            //Get current time
            Calendar c = Calendar.getInstance();

            //Create date format for comparisons
            SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");

            //Parse current time, start time, and end time
            try {
                Date currentTime = sdf.parse(c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE));
                Date startTime = sdf.parse(blackouts[i].getStartHour() + ":" + blackouts[i].getStartMinute());
                Date endTime = sdf.parse(blackouts[i].getEndHour() + ":" + blackouts[i].getEndMinute());

                //compare the values
                if (startTime.compareTo(endTime) > 0) {
                    if (currentTime.compareTo(startTime) > 0 || currentTime.compareTo(endTime) < 0) {
                        myAudioManager.setRingerMode(1);
                    }
                } else if (currentTime.compareTo(startTime) > 0 && currentTime.compareTo(endTime) < 0) {
                    myAudioManager.setRingerMode(1);
                }

            } catch (ParseException e) {
                //Create toast saying it's jacked up... meh
            }


        }

    }
}
