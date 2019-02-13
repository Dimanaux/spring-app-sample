package app;

import app.db.Database;
import app.db.entities.User;
import app.views.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.PrintWriter;

/**
 * @author barskoidim@gmail.com
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("spring-config.xml");
        App app = (App) applicationContext.getBean("app");
        app.run();
    }

    // ----------------------------

    private final Database database;
    private final View<User> view;
    private final PrintWriter writer;

    public App(Database database, View<User> view, PrintWriter printWriter) {
        this.database = database;
        this.view = view;
        this.writer = printWriter;
    }

    public void run() {
        writer.println(view.showAll(database.getUsers()));
        writer.close();
    }
}
