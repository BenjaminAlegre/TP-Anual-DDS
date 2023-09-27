package model.entities.persistencia;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public abstract class EntidadPersistente {
    @Id
    @GeneratedValue
    private Integer id;

   // private Boolean borrado = false;
}
