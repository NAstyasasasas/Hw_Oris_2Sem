import com.example.spring.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.example.spring.service.UserService;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

        UserService service = context.getBean(UserService.class);

        UserEntity us = new UserEntity("nastya");
        UserEntity us2 = new UserEntity("Ann");


    }
}
