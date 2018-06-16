import dao.jpa.CountryJpaDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.TestConfiguration;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = TestConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryDaoImplTest {

	private CountryJpaDaoImpl countryJpaDao;
	private Country	exampleCountry = new Country("Australia", "AU");

	@Test
	public void testSaveCountry() throws Throwable {

		countryJpaDao.save(exampleCountry);

		List<Country> countryList = countryJpaDao.getCountryList();
		assertEquals(1, countryList.size());
		assertEquals(exampleCountry, countryList.get(0));
	}

	@Test
	public void testGtAllCountries() throws Throwable {

		countryJpaDao.save(new Country("Canada", "CA"));

		List<Country> countryList = countryJpaDao.getCountryList();
		assertEquals(2, countryList.size());
	}

	@Test
	public void testGetCountryByName() throws Throwable {

		Country country = countryJpaDao.getCountryByName("Australia");
		assertEquals(exampleCountry, country);
	}

}
