package service;

import dao.CountryDao;
import model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.*;

@Transactional
@Service("countryService")
public class CountryServiceImpl implements CountryService {

	
	@Autowired
	private CountryDao countryDao;

	public List<Country> getAllCountriesInsideTransaction(
			Propagation propagation) throws Throwable {
		if (Propagation.REQUIRED.equals(propagation)) {
			return getAllCountriesRequired();
		} else if (REQUIRES_NEW.equals(propagation)) {
			return getAllCountriesRequiresNew();
		} else if (Propagation.SUPPORTS.equals(propagation)) {
			return getAllCountriesSupports();
		} else if (NEVER.equals(propagation)) {
			return getAllCountriesNever();
		} else if (Propagation.MANDATORY.equals(propagation)) {
			return getAllCountriesMandatory();
		} else if (NOT_SUPPORTED.equals(propagation)) {
			return getAllCountriesNotSupported();
		} else {
			return getAllCountries();
		}
	}

	@Transactional(readOnly = true, propagation = REQUIRED)
	public List<Country> getAllCountriesRequired() throws Throwable {
		return countryDao.getCountryList();
	}

	@Transactional(readOnly = true, propagation = REQUIRES_NEW)
	public List<Country> getAllCountriesRequiresNew() throws Throwable {
		return countryDao.getCountryList();
	}

	@Transactional(readOnly = true, propagation = SUPPORTS)
	public List<Country> getAllCountriesSupports() throws Throwable {
		return countryDao.getCountryList();
	}

	@Transactional(readOnly = true, propagation = NEVER)
	public List<Country> getAllCountriesNever() throws Throwable {
		return countryDao.getCountryList();
	}

	@Transactional(readOnly = true, propagation = Propagation.MANDATORY)
	public List<Country> getAllCountriesMandatory() throws Throwable {
		return countryDao.getCountryList();
	}

	@Transactional(readOnly = true, propagation = NOT_SUPPORTED)
	public List<Country> getAllCountriesNotSupported() throws Throwable {
		return countryDao.getCountryList();
	}

	public List<Country> getAllCountries() throws Throwable {
		return countryDao.getCountryList();
	}

	public CountryDao getCountryDao() {
		return countryDao;
	}

	public void setCountryDao(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

}