package com.edacy.echat.model.contacts;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by djiddou on 10/28/17.
 */

@Entity
public final class Contact {

    @PrimaryKey
    int id;

    // Bind Json field name with Java field name
    @ColumnInfo(name = "full_name")
    @SerializedName("name")
    String fullName;

    @ColumnInfo(name = "phone")
    String phone;

    @ColumnInfo(name = "picture")
    String picture;

    public Contact(String fullName, String phone, String picture) {
        this.fullName = fullName;
        this.phone = phone;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
