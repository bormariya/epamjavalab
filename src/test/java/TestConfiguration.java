
import model.Contact;
import model.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan({"model", "aop"})
@EnableAspectJAutoProxy
public class TestConfiguration {
    @Bean
    public Long id() {
        return 1L;
    }

    @Bean
    public Integer age() {
        return 35;
    }

    @Bean
    public Float height() {
        return 1.78F;
    }

    @Bean
    public Boolean isProgrammer() {
        return true;
    }

    @Bean
    public String firstName() {
        return "John";
    }

    @Bean
    public String lastName() {
        return "Smith";
    }

    @Bean
    public Country country(){
        return  Country.builder().id(1L).codeName("RU").name("Russia").build();
    }

    @Bean
    public List<Contact> contacts() {
        return Arrays.asList(
                Contact.builder().id(1L).type("EMAIL").content("asd@asd.ru").build(),
                Contact.builder().id(1L).content("+7-234-456-67-89").build()
        );
    }

    @Bean
    public boolean broke(){
        return false;
    }
}
