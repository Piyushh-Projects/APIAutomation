package api.payload;

/*
POJO --> Plain Old Java Object
*/

public class User {

    String name;
    String job;

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
