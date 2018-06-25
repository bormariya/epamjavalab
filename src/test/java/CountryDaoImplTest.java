import dao.lab7.jpa.CountryJpaDaoImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.Country;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import utils.lab7.TestConfigurationLab7;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Illustrates basic use of Hibernate as a JPA provider.
 */
@ExtendWith(SpringExtension.class)
@FieldDefaults(level = PRIVATE, makeFinal = true)
@ContextConfiguration(classes = TestConfigurationLab7.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryDaoImplTest {

	private CountryJpaDaoImpl countryJpaDao;
	private Country	exampleCountry = new Country("Australia", "AU");

	@Test
	public void testSaveCountry() throws Throwable {

		int dataSize = countryJpaDao.getCountryList().size();
		countryJpaDao.save(exampleCountry);

		List<Country> countryList = countryJpaDao.getCountryList();
		assertEquals(dataSize + 1, countryList.size());
		assertTrue(countryList.contains(exampleCountry));
	}

	@Test
	public void testGtAllCountries() throws Throwable {

		int dataSize = countryJpaDao.getCountryList().size();
		countryJpaDao.save(new Country("Canada", "CA"));

		List<Country> countryList = countryJpaDao.getCountryList();
		assertEquals(dataSize + 1, countryList.size());
	}

	@Test
	public void testGetCountryByName() throws Throwable {

		Country	exampleCountry = new Country("Russia", "RU");
		countryJpaDao.save(exampleCountry);

		Country country = countryJpaDao.getCountryByName("Russia");
		assertEquals(exampleCountry, country);
	}

}
