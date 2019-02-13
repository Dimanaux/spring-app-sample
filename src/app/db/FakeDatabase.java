package app.db;

import app.db.entities.User;

import java.time.LocalDate;
import java.util.List;


/**
 * FakeDatabase returns hard-coded List<User>
 */
public class FakeDatabase implements Database {
    @Override
    public List<User> getUsers() {
        return List.of(
                new User(1, "Dima", "dima@mail.ru", LocalDate.now()),
                new User(2, "Emilya", "emilya@mail.ru", LocalDate.now()),
                new User(3, "Vova", "vova@mail.ru", LocalDate.now()),
                new User(4, "Max", "max@mail.ru", LocalDate.now()),
                new User(5, "Timur", "timur@mail.ru", LocalDate.now())
        );
    }
}
