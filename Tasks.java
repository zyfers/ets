package com.example.lenovo.employeetrackingsystem;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 17-11-2017.
 */

public class Tasks {
    String tkey;
    String taskname;
    String taskdescription;
    String tasklocation;

    Tasks(String taskname, String taskdescription, String tasklocation){
        //this.tkey=tkey;
        this.taskname=taskname;
        this.taskdescription=taskdescription;
        this.tasklocation=tasklocation;
    }



    @Exclude
    public Map<String, Object> toMapTask() {
        HashMap<String, Object> resultTask = new HashMap<>();
        resultTask.put("taskname", taskname);
        resultTask.put("taskdescription", taskdescription);
        resultTask.put("tasklocation", tasklocation);

        return resultTask;
    }


    @Exclude
    public Map<String, Object> toTMap() {
        HashMap<String, Object> Tresult = new HashMap<>();
        Tresult.put("tkey", tkey);
        Tresult.put("taskname", taskname);
        Tresult.put("taskdescription", taskdescription);
        Tresult.put("tasklocation", tasklocation);


        return Tresult;
    }

}

