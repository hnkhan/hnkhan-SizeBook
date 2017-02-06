package com.example.hnkhan.hnkhan_sizebook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
This is our Records class where we store all the information
about our records
 */

public class Records
{
    private String name;
    private Date date;
    private Float neck;
    private Float bust;
    private Float chest;
    private Float waist;
    private Float hip;
    private Float inseam;
    private String comment;

    public Records(String name)
    {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(String date) {
        //http://stackoverflow.com/questions/8573250/android-how-can-i-convert-string-to-date
        SimpleDateFormat _simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = _simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Float getNeck() {
        return neck;
    }

    public void setNeck(Float neck) {
        this.neck = neck;
    }

    public Float getBust() {
        return bust;
    }

    public void setBust(Float bust) {
        this.bust = bust;
    }

    public Float getChest() {
        return chest;
    }

    public void setChest(Float chest) {
        this.chest = chest;
    }

    public Float getWaist() {
        return waist;
    }

    public void setWaist(Float waist) {
        this.waist = waist;
    }

    public Float getHip() {
        return hip;
    }

    public void setHip(Float hip) {
        this.hip = hip;
    }

    public Float getInseam() {
        return inseam;
    }

    public void setInseam(Float inseam) {
        this.inseam = inseam;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){
        return this.getName();
    }
}
