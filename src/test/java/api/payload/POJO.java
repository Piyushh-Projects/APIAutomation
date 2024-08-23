package api.payload;

import java.util.HashMap;

public class POJO {

    String name;
    HashMap<String, String> data;

    public HashMap<String, String> getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

}
