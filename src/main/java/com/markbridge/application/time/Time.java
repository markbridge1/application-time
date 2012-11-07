/*
 * Center for Knowledge Management Kalmanovitz Library, UCSF
 * 
 * The University of California, San Francisco, CA 94143, 415/476-9000 (c) 2012
 * The Regents of the University of California All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * 
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.markbridge.application.time;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author mbridge
 */
public class Time {
    
    public static DateFormat DATE_FORMAT = 
            DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, Locale.US);

    private String startTime;
    private String stopTime;
    
    public static String getDateTimeString() {
        Date now = Calendar.getInstance().getTime();
        return DATE_FORMAT.format(now);
    }

    public static String getMinApproxDuration(String start, String end) {
        String retVal = "-";
        try {
            Date startDate = DATE_FORMAT.parse(start);
            Date endDate = DATE_FORMAT.parse(end);
            Double durationMins = Double.parseDouble("" + (endDate.getTime() - startDate.getTime())) / 60000;
            retVal = durationMins.toString();
            retVal = retVal.substring(0, retVal.indexOf(".") + 2);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return retVal;
    }
    
    public static void pause(long milliseconds) {
        try {
            Thread.currentThread().sleep(milliseconds);
        } catch (InterruptedException ex) {
            //if interrupted approximate a delay
            String s = "";
            for(long lo = 0; lo < milliseconds; lo++) {
                s += lo;
            }
            System.out.println(s);
        }
    }
    
    public Time start() {
        setStartTime(Time.getDateTimeString());
        return this;
    }
    
    public Time stop() {
        setStopTime(Time.getDateTimeString());
        return this;
    }

    public String getMinApproxDuration() {
        return Time.getMinApproxDuration(startTime, stopTime);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }
    
}
