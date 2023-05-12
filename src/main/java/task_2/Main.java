package task_2;

import java.io.IOException;

public class Main {
    /*
    Напишіть програму, що буде взаємодіяти з API https://jsonplaceholder.typicode.com.

    Завдання 2
    Доповніть програму методом, що буде виводити всі коментарі до
    останнього поста певного користувача і записувати їх у файл.
    https://jsonplaceholder.typicode.com/users/1/posts Останнім вважаємо пост
    з найбільшим id.
    https://jsonplaceholder.typicode.com/posts/10/comments
    Файл повинен називатись user-X-post-Y-comments.json,
    де Х - id користувача, Y - номер посту.*/

    public static void main(String[] args) throws IOException, InterruptedException {
        Work work = new Work();
        work.printAnswer2Task(1);
    }
}