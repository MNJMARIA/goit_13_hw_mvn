package task_3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Work {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private final HttpClient CLIENT = HttpClient
            .newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public void getOpenTasksByUserId(int userId) throws IOException, InterruptedException {
        JSONArray allUsersTasks = getJsonArrayFromUrl(BASE_URL + "/users/" + userId + "/todos");

        List<Task> tasks = new ArrayList<>();

        for(Object object: allUsersTasks){
            JSONObject jsonTask = (JSONObject) object;

            Task task = new Task(jsonTask.getInt("userId"),
                    jsonTask.getInt("id"),
                    jsonTask.getString("title"),
                    jsonTask.getBoolean("completed"));

            tasks.add(task);
        }
        List<Task> openedTasks = tasks
                .stream()
                .filter(it -> !it.isCompleted())
                .collect(Collectors.toList());

        System.out.println(openedTasks);

    }

    private JSONArray getJsonArrayFromUrl(String urlString) throws IOException, InterruptedException {
        JSONArray jsonArray = null;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        jsonArray = new JSONArray(response.body());
        return jsonArray;
    }
}

