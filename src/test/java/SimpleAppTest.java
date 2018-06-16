import model.Contact;
import model.Country;
import model.UsualPerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.TestConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleAppTest {
	
	private static BeanFactory BEAN_FACTORY = new AnnotationConfigApplicationContext(TestConfiguration.class);

	@Test
	public void testInitPerson() {
		assertEquals(getExpectedPerson(), BEAN_FACTORY.getBean("person"));
	}

	private UsualPerson getExpectedPerson() {
		return UsualPerson.builder()
                .id(1L)
				.age(35)
				.height(1.78F)
				.isProgrammer(true)
				.firstName("John")
				.lastName("Smith")
				.country(Country.builder()
					.id(1L)
					.codeName("RU")
					.name("Russia")
					.build())
				.contact(Contact.builder()
					.id(1L)
					.type("EMAIL")
					.content("asd@asd.ru")
					.build())
				.contact(Contact.builder()
					.id(1L)
					.content("+7-234-456-67-89")
					.build())
				.build();
	}
}
