package task_1;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Work {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClient CLIENT = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public String createUser(String requestBody) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(" CREATE USER: response.statusCode() = " + response.statusCode());
        System.out.println("createUser() = \n" + response.body());
        return response.body();
    }
    public String updateUser(int id, String requestBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("UPDATE USER: response.statusCode() = " + response.statusCode());
        System.out.println("updateUser() = \n" + response.body());
        return response.body();
    }
    public boolean deleteUser(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .DELETE()
                .build();

        /*У випадку видалення об'єкта з API, коли ми не очікуємо повернення даних,
         метод .discarding() можна використовувати, щоб уникнути необхідності
         обробляти повернену відповідь, та зменшити обсяг пам'яті, необхідний
         для обробки відповіді.*/
        HttpResponse<Void> response = CLIENT.send(request, HttpResponse.BodyHandlers.discarding());

        System.out.println("DELETE USER: response.statusCode() = " + response.statusCode());
        boolean result = response.statusCode() >= 200 && response.statusCode()<300;
        System.out.println("deleteUser() = " + result);
        return result;
    }
    public String getAllUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET ALL USERS: response.statusCode() = " + response.statusCode());
        System.out.println("getAllUsers() = " +  response.body());
        return response.body();
    }
    public String getUserById(int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users/" + id))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET USER BY ID: response.statusCode() = " + response.statusCode());
        System.out.println("getUserById() = " + response.body());
        return response.body();
    }
    public String getUserByUsername(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + "/users?username=" + username))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET USER BY USERNAME: response.statusCode() = " + response.statusCode());
        System.out.println("getUserByUsername() = " + response.body());
        return response.body();
    }

}
