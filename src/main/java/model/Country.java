package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Component("country")
@EqualsAndHashCode
public class Country implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
    private String name;
    private String codeName;

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
