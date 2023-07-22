package model.entities.entidades.adapters.entidadesDeNormalizacion;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Setter

public class ListadoMunicipios {

    private Departamento departamento;

    public List<Municipio> municipios;

    public ListadoMunicipios(){

    }
    public ListadoMunicipios(Departamento departamento, List<Municipio> municipios) {
        this.departamento = departamento;
        this.municipios = municipios;
    }

    public Optional<Municipio> buscarMunicipios(String nombre){
        return this.municipios.stream()
                .filter(l -> l.nombre.equals(nombre))
                .findFirst();
    }

    public List<NombreMunicipio> devolverNombres(){
        List<NombreMunicipio> municipios = new ArrayList<>();
        for(Municipio l:this.municipios){
            municipios.add(new NombreMunicipio(l.nombre));
        }
        return municipios;
    }

    class NombreMunicipio{
        public String nombre;
        public NombreMunicipio(String nombre) {
            this.nombre = nombre;
        }
    }
}
