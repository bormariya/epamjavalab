package dao.jpa;

import model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao {

	public void save(Country country) throws Throwable {
		withEntityManager(entityManager -> entityManager.merge(country));
	}

	@Override
	public List<Country> getCountryList() throws Throwable {
		return mapEntityManager(entityManager -> entityManager
				.createQuery("SELECT c FROM model.Country c", Country.class)
				.getResultList());
	}

	@Override
	public Country getCountryByName(String name) throws Throwable {
		return mapEntityManager(entityManager -> entityManager
				.createQuery("SELECT c FROM model.Country c WHERE c.name = :name", Country.class)
				.getResultList().get(0));
	}

}
