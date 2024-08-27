package api.test;

/*
Serialization --> POJO to JSON object
Deserialization --> JSON object --> POJO
 */

import api.payload.POJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SerializationDeserialization {

    @Test
    void convertPojoToJson() throws JsonProcessingException {

        POJO pojo = new POJO();
        HashMap<String, String> map = new HashMap<>();

        map.put("year", "2021");
        map.put("price", "1000");
        map.put("CPU model", "m2");
        map.put("Hard disk size", "256 GB");
        pojo.setName("Apple MacBook Air");
        pojo.setData(map);

        //Convert JAVA object to JSON object (Serialization)
        ObjectMapper objMapper = new ObjectMapper();
        String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        System.out.println(jsonData);
    }

    @Test
    void convertJsonToPojo() throws JsonProcessingException {

        String jsondata = "{\n" +
                "  \"name\" : \"Apple MacBook Air\",\n" +
                "  \"data\" : {\n" +
                "    \"year\" : \"2021\",\n" +
                "    \"price\" : \"1000\",\n" +
                "    \"CPU model\" : \"m2\",\n" +
                "    \"Hard disk size\" : \"256 GB\"\n" +
                "  }\n" +
                "}";

        //Convert JSON data to POJO object
        ObjectMapper objMapper = new ObjectMapper();
        POJO pojo = objMapper.readValue(jsondata, POJO.class);

        System.out.println(pojo.getName());
        pojo.getData().forEach((key,value) -> System.out.println(key + " : " + value));

    }

}
