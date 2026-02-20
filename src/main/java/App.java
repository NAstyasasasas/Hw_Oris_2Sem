import entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class App {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/beans.xml");

        UserService service = context.getBean(UserService.class);

        UserEntity us = new UserEntity("nastya");
        UserEntity us2 = new UserEntity("Ann");
        service.createUser(us);
        service.createUser(us2);
        service.delUser(us);
        UserEntity us3 = service.getUserr("Ann");
        service.updateUser(us3.getId(), "Anna");


    }
}
