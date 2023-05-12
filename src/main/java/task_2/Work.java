package task_2;

import org.json.JSONArray;
import java.io.FileWriter;
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
    public void printAnswer2Task(int userId) throws IOException, InterruptedException {
        // Отримання всіх постів користувача
        JSONArray userPosts = getJsonArrayFromUrl(BASE_URL + "/users/" + userId + "/posts");

        // Знаходження id останнього поста
        int lastPostId = userPosts.getJSONObject(userPosts.length() - 1).getInt("id");

        // Отримання всіх коментарів до останнього поста
        JSONArray commentsUnderLastPost = getJsonArrayFromUrl(BASE_URL + "/posts/" + lastPostId + "/comments");

        // Запис коментарів у файл
        String fileName = "user-"+ userId + "-post-" + lastPostId + "-comments.json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(commentsUnderLastPost.toString());
        fileWriter.close();

        System.out.println("Коментарі до останнього поста користувача " + userId + " були записані у файл " + fileName);
    }
    private JSONArray getJsonArrayFromUrl(String urlString) throws IOException, InterruptedException {
        JSONArray jsonArray = null;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = CLIENT
                .send(request, HttpResponse.BodyHandlers.ofString());

        jsonArray = new JSONArray(response.body());
        return jsonArray;
    }
}
