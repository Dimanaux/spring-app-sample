package app.views;

import app.db.entities.User;

import java.util.List;
import java.util.stream.Stream;

/**
 * presents User models as table in html
 * one User = one row
 */
public class HtmlView implements View<User> {
    @Override
    public String show(User user) {
        String fields = Stream.of(user.getClass().getMethods())
                .filter(m -> m.getName().startsWith("get"))
                .map(m -> ejectString(m, user))
                .map(this::wrap)
                .reduce(String::concat)
                .orElse("<td>null</td>");
        return String.format("<tr>%s</tr>", fields);
    }

    @Override
    public String showAll(List<User> users) {
        String table = users.stream()
                .map(this::show)
                .reduce(String::concat)
                .orElse("Empty");
        return String.format("<table>%s</table>", table);
    }
    
    private String wrap(Object value) {
        return String.format("<td>%s</td>", value);
    }

    public String ejectString(Method getter, Object o) {
        try {
            return getter.invoke(o).toString();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "";
    }
}
