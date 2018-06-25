package dao.lab7.jpa;

import model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryJpaDao")
public class CountryJpaDaoImpl extends AbstractJpaDao {

	public Country save(Country country) throws Throwable {
		withEntityManager(entityManager ->
            country.setId(entityManager.merge(country).getId()));
		return country;
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
				.setParameter("name", name)
				.getResultList().get(0));
	}

}
