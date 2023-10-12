package model.entities.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter@Setter
@Entity
@DiscriminatorValue("Organizacion")
public class Organizacion extends Entidad{


    @Enumerated(EnumType.STRING)
    private TipoOrganizacion tipo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizacion")
    private List<Sucursal> sucursales;

    public void agregarSucursal(Sucursal sucursal){
        sucursales.add(sucursal);
    }

    public void eliminarSucursal(Sucursal sucursal){
        sucursales.remove(sucursal);
    }

}
