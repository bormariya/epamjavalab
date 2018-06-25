package dao.lab6;

import dao.CountryDao;
import model.Country;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@Repository("countryDao")
public class CountryDaoImpl extends NamedParameterJdbcDaoSupport implements CountryDao {

	public static final String[][] COUNTRY_INIT_DATA = {
			{ "Russian Federation", "RU" },
			{ "Australia", "AU" },
			{ "Canada", "CA" },
			{ "France", "FR" },
			{ "Hong Kong", "HK" },
			{ "Iceland", "IC" },
			{ "Japan", "JP" },
			{ "Nepal", "NP" },
			{ "Sweden", "SE" },
			{ "Switzerland", "CH" },
			{ "United Kingdom", "GB" },
			{ "United States", "US" } };

	private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values (?, ?)";
	private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
	private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
	private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = :name";
	private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = :codeName";
	private static final String UPDATE_COUNTRY_NAME_SQL = "update country SET name = :newCountryName " +
															"WHERE code_name = :codeName";

	private static final RowMapper<Country> COUNTRY_ROW_MAPPER = ((resultSet, __) ->
        new Country(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("code_name")
        )
    );

	public CountryDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Country save(Country country) {
		country.setId(save(country.getName(), country.getCodeName()));
		return country;
	}

	private long save(String name, String codeName){
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(con -> {
			PreparedStatement ps = con.prepareStatement(LOAD_COUNTRIES_SQL, RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setString(2, codeName);
			return  ps;
		}, keyHolder);
		return keyHolder.getKey().longValue();
	}

	@Override
	public List<Country> getCountryList() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		return namedParameterJdbcTemplate.query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
	}

	@Override
	public List<Country> getCountryListStartWith(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
				"name", name + "%");
		return namedParameterJdbcTemplate.query(GET_COUNTRIES_BY_NAME_SQL,
				sqlParameterSource, COUNTRY_ROW_MAPPER);
	}

	@Override
	public void updateCountryName(String codeName, String newCountryName) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource();
		((MapSqlParameterSource) sqlParameterSource)
				.addValue("codeName", codeName)
				.addValue("newCountryName", newCountryName);

		namedParameterJdbcTemplate.update(UPDATE_COUNTRY_NAME_SQL, sqlParameterSource);
	}

	@Override
	public void loadCountries() {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource;
		for(String[] countryData : COUNTRY_INIT_DATA) {
			save(new Country(countryData[0], countryData[1]));
		}
//		for (int i = 0; i < COUNTRY_INIT_DATA.length; i++) {
//			sqlParameterSource = new MapSqlParameterSource();
//			((MapSqlParameterSource) sqlParameterSource).addValue("id", i);
//			((MapSqlParameterSource) sqlParameterSource).addValue("name", COUNTRY_INIT_DATA[i][0]);
//			((MapSqlParameterSource) sqlParameterSource).addValue("codeName", COUNTRY_INIT_DATA[i][1]);
//			namedParameterJdbcTemplate.update(LOAD_COUNTRIES_SQL, sqlParameterSource);
//		}
	}

	@Override
	public Country getCountryByCodeName(String codeName) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("codeName", codeName);

		return namedParameterJdbcTemplate.query(GET_COUNTRY_BY_CODE_NAME_SQL, sqlParameterSource,
				COUNTRY_ROW_MAPPER).get(0);
	}

	@Override
	public Country getCountryByName(String name) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", name);
		namedParameterJdbcTemplate.query(GET_ALL_COUNTRIES_SQL, sqlParameterSource,
				COUNTRY_ROW_MAPPER);
		return namedParameterJdbcTemplate.query(GET_COUNTRY_BY_NAME_SQL, sqlParameterSource,
				COUNTRY_ROW_MAPPER).get(0);
	}
}
