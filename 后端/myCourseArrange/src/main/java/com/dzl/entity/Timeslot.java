package com.dzl.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//时段表示班级上课在周几的什么时间
//星期一          星期二      星期三     星期四    星期五
//08:30-09:10  08:30-09:10 08:30-09:10 08:30-09:10 08:30-09:10
//09:20-10:00
//10:20-11:00
//11:10-11:50
//14:00-14:40
//14:50-15:30
//15:40-16:20
//16:30-17:10
@Entity
public class Timeslot {
    @Id
    @GeneratedValue
    private  int timeslotId;
    private  String timeslot;
    public Timeslot(){}
    public Timeslot(int timeslotId, String timeslot) {
        this.timeslot = timeslot;
        this.timeslotId = timeslotId;
    }

    public int getTimeslotId() {
        return this.timeslotId;
    }

    public String getTimeslot(){
        return this.timeslot;
    }
}
