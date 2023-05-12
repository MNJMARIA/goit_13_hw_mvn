package task_3;

import java.io.IOException;

public class Main {
   /*
    Напишіть програму, що буде взаємодіяти з API https://jsonplaceholder.typicode.com.

    Завдання 3
    Доповніть програму методом, що буде виводити всі відкриті задачі
    для користувача з ідентифікатором X.
    https://jsonplaceholder.typicode.com/users/1/todos.
    Відкритими вважаються всі задачі, у яких completed = false.*/

    public static void main(String[] args) throws IOException, InterruptedException {
        Work work = new Work();
        work.getOpenTasksByUserId(1);
    }
}
