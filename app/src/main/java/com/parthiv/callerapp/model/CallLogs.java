package com.parthiv.callerapp.model;

import java.util.Objects;

public class CallLogs {

    private String number;
    private int callType;
    private String callDate;
    private String callDuration;
    private String name;

    public CallLogs(String number, int callType, String callDate, String callDuration, String name) {
        this.number = number;
        this.callType = callType;
        this.callDate = callDate;
        this.callDuration = callDuration;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallLogs callLogs = (CallLogs) o;
        return Objects.equals(number, callLogs.number) &&
                Objects.equals(callType, callLogs.callType) &&
                Objects.equals(callDate, callLogs.callDate) &&
                Objects.equals(callDuration, callLogs.callDuration) &&
                Objects.equals(name, callLogs.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, callType, callDate, callDuration, name);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
