package utils.lab7;

import lombok.AllArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import utils.TestConfiguration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
@EnableAspectJAutoProxy
@ImportResource("orm.xml")
@Import(TestConfiguration.class)
@ComponentScan({"model", "aop", "dao.lab7.jpa"})
public class TestConfigurationLab7 {

    InstrumentationLoadTimeWeaver instrumentationLoadTimeWeaver;

    @Value("#{dataSource}")
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        bean.setPersistenceUnitName("springframework.dao.lab7.jpa");
        bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        bean.setLoadTimeWeaver(instrumentationLoadTimeWeaver);
        return bean;
    }
}
