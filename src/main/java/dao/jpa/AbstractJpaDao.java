package dao.jpa;

import dao.CountryDao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Getter
@Setter
public class AbstractJpaDao implements CountryDao, JpaDao {

	protected EntityManagerFactory emf;

	public AbstractJpaDao() {
		super();
	}

	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}
}