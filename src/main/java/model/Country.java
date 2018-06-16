package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode
@Component("country")
@Table(name = "country")
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
	long id;
	@Column
    String name;
	@Column(name = "code_name")
    String codeName;

    public Country() {
    }

    public Country(long id, String name, String codeName) {
        this.id = id;
        this.name = name;
        this.codeName = codeName;
    }
    public Country(String name, String codeName) {
        this.name = name;
        this.codeName = codeName;
    }

    public String toString() {
        return id + ". " + name + " (" + codeName + ")";
    }
}
