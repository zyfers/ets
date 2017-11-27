package com.example.lenovo.employeetrackingsystem;


public class getTask{

    String tkey;
    String taskname;
    String taskdescription;
    String tasklocation;
    getTask(){
        this.tkey=null;
        this.taskname=null;
        this.taskdescription=null;
        this.tasklocation=null;
    }
    getTask(String taskname, String taskdescription, String tasklocation){
        this.tkey=tkey;
        this.taskname=taskname;
        this.taskdescription=taskdescription;
        this.tasklocation=tasklocation;
    }
    public String getTkey() {
        return tkey;
    }

    public String getTaskname()
    {
        return taskname;
    }
    public String getTaskdescription()
    {
        return taskdescription;
    }

    public String getTasklocation() {
        return tasklocation;
    }

    public String setTaskname(String taskname){ this.taskname=taskname; return taskname;}
    public String setTaskdescription(String taskdescription){ this.taskdescription=taskdescription; return taskdescription; }
    public String setTasklocation(String tasklocation){ this.tasklocation=tasklocation; return tasklocation;}



   /* @Exclude
    public Map<String, Object> togetTMap() {
        HashMap<String, Object> geTresult = new HashMap<>();

        geTresult.put("taskname", taskname);
        geTresult.put("taskdescription", taskdescription);
        geTresult.put("tasklocation", tasklocation);


        return geTresult;
    }
*/

}