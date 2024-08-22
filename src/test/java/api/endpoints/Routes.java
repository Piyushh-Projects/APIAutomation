package api.endpoints;

/*
Swagger URI --> https://reqres.in

Create user(POST) : https://reqres.in/api/users
Get User (GET) : https://reqres.in/api/users/{userId}
Update User (PUT) : https://reqres.in/api/users/{userId}
Delete User (DELETE) : https://reqres.in/api/users/{userId}

 */

public class Routes {

    public static String base_url = "https://reqres.in/api";

    public static String post_url = base_url + "/users";

    public static String get_url = base_url + "/users/{userId}";

    public static String update_url = base_url + "/users/{userId}";

    public static String delete_url = base_url + "/users/{userId}";

}
