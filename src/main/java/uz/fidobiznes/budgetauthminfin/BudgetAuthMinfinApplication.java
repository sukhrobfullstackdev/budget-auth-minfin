package uz.fidobiznes.budgetauthminfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;

@SpringBootApplication(exclude = {SecurityFilterAutoConfiguration.class})
public class BudgetAuthMinfinApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetAuthMinfinApplication.class, args);
    }

}
