
package com.ti.apitraining.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("job_title")
    @Expose
    public String jobTitle;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("active")
    @Expose
    public String active;
    @SerializedName("email_verified_at")
    @Expose
    public String emailVerifiedAt;
    @SerializedName("images_count")
    @Expose
    public String imagesCount;

}
