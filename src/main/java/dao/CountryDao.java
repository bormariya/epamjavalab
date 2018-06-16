package dao;

import model.Country;

import java.util.List;

public interface CountryDao {
    default void save(Country country) throws Throwable {
    }

    default List<Country> getCountryList() throws Throwable {
        return null;
    }

    default List<Country> getCountryListStartWith(String name) {
        return null;
    }

    default void updateCountryName(String codeName, String newCountryName) {
    }

    default void loadCountries() {
    }

    default Country getCountryByCodeName(String codeName) {
        return null;
    }

    default Country getCountryByName(String codeName) throws Throwable {
        return null;
    }
}
