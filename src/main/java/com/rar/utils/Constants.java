package com.rar.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constants {

    private Constants() {
        //empty constructor
    }

    public static final Integer CREATED = 0;
    public static final Integer ROLLED_OUT =1;
    public static final Integer END_DATE_PASSED = 2;
    public static final Integer DISCONTINUED =3;
    public static final Integer PUBLISHED = 4;
    public static final Integer EDITED_AFTER_ROLL_OUT= 5;
    public static final String PROJECT_ID = "Project Id not found";
    public static final String ROF = "for";
    public static final String REWARD_ID = "Reward Id not found";
    public static final String SUCCESS = "Successful";
    public static final Integer ROLE_EMPLOYEE = 1;
    public static final Integer ROLE_ADMIN = 2;
    
    @Value("${jwt.secret}")
    public static final  String SECRET = "xsNxAnioE69S0DZ4YUVUrCzR";

}
