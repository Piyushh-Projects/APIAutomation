package api.test;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {

    @Test
    void testGenerateDummyData() {

        Faker faker = new Faker();

        String fullName = faker.name().fullName();
        String fName = faker.name().firstName();
        String lName = faker.name().lastName();
        String uName = faker.name().username();
        String password = faker.internet().password();
        String pNumber = faker.phoneNumber().cellPhone();
        String eMail = faker.internet().safeEmailAddress();
        String cc = faker.business().creditCardNumber();
        String animal = faker.animal().name();

        System.out.println("fullName : " + fullName);
        System.out.println("fName : " + fName);
        System.out.println("lName : " + lName);
        System.out.println("uName : " + uName);
        System.out.println("password : " + password);
        System.out.println("pNumber : " + pNumber);
        System.out.println("eMail : " + eMail);
        System.out.println("cc : " + cc);
        System.out.println("animal : " + animal);

    }

}
