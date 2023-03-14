package com.ti.apitraining.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("full_name")
        @Expose
        public String fullName;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("fcmToken")
        @Expose
        public Object fcmToken;
        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("refresh_token")
        @Expose
        public String refreshToken;
        @SerializedName("is_active")
        @Expose
        public Boolean isActive;

        public String password;

        public Student(String email, String password) {
                this.email = email;
                this.password = password;
        }

        public Student(String fullName, String email, String gender, String password) {
                this.fullName = fullName;
                this.email = email;
                this.gender = gender;
                this.password = password;
        }

        public Student() {
        }
}
