package app.db;

import app.db.entities.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * FileDatabase returns users from .csv file
 * expected schema is
 * id: int, name: string, email: string, date: string
 * where string cannot contain commas even in '' and ""
 * date is accepted in format YYYY-MM-DD
 */
public class FileDatabase implements Database {
    private final BufferedReader reader;
    private final List<User> users;

    public FileDatabase(BufferedReader reader) {
        this.reader = reader;
        users = Collections.emptyList();
    }

    @Override
    public List<User> getUsers() {
        if (users == null) {
            readUsers();
        }
        return Collections.unmodifiableList(users);
    }

    private void readUsers() {
        try {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",\\s*");
                User user = new User(
                        Integer.parseInt(split[0]),
                        split[1],
                        split[2],
                        LocalDate.parse(split[3])
                );
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("failed to read from file.");
            e.printStackTrace();
        }
    }
}
