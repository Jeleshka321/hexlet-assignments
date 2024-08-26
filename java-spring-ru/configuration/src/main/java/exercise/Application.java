package exercise;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.User;
import exercise.component.UserProperties;

@SpringBootApplication
@RestController
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    @Autowired
    private UserProperties userProperties;

    // Обработчик для получения списка администраторов
    @GetMapping("/admins")
    public List<String> getAdmins() {
        List<String> adminEmails = userProperties.getAdmins();

        // Получаем список имен администраторов по их email
        List<String> adminNames = users.stream()
                .filter(user -> adminEmails.contains(user.getEmail()))
                .map(User::getName)
                .sorted()
                .collect(Collectors.toList());

        return adminNames;
    }

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}