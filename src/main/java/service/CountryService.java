package service;

import model.Country;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

public interface CountryService {

    List<Country> getAllCountriesInsideTransaction(Propagation propagation) throws Throwable;

    List<Country> getAllCountriesRequired() throws Throwable;

    List<Country> getAllCountriesRequiresNew() throws Throwable;

    List<Country> getAllCountriesSupports() throws Throwable;

    List<Country> getAllCountriesNever() throws Throwable;

    List<Country> getAllCountriesMandatory() throws Throwable;

    List<Country> getAllCountriesNotSupported() throws Throwable;

    List<Country> getAllCountries() throws Throwable;
}
