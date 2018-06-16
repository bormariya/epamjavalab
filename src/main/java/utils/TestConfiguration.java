package utils;

import lombok.AllArgsConstructor;
import model.Contact;
import model.Country;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ImportResource("orm.xml")
@ComponentScan({"model", "aop", "dao"})
public class TestConfiguration {

    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;

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

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(H2)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("db-schema.sql")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource());
        bean.setPersistenceUnitName("springframework.lab.orm.jpa");
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        bean.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
        return bean;
    }
}
