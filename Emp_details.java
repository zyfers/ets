package com.example.lenovo.employeetrackingsystem;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 17-11-2017.
 */

public class Emp_details {
    String Id;
    String name;
    String email;
    String mobileno;
    String loc ;
    String des;
    String task;
    Emp_details(String Id, String name, String email, String mobileno, String loc, String des, String task){
        this.Id=Id;
        this.name=name;
        this.email=email;
        this.mobileno=mobileno;
        this.loc=loc;
        this.des=des;
        this.task=task;
      }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLoc() {
        return loc;
    }

    public String getId() {
        return Id;
    }

    public String getDes() {
        return des;
    }

    public String getMobileno() {
        return mobileno;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Id", Id);
        result.put("name", name);
        result.put("email", email);
        result.put("mobileno", mobileno);
        result.put("loc", loc);
        result.put("des", des);
        result.put("task", task);

        return result;
    }

}

