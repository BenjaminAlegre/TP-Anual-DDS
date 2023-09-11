package org.example.Ubicacion;

import org.example.Corredor;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ubicacion_codigo;
    @Column
    private String descripcion;

    @OneToMany(mappedBy="ubicacion_codigo")
    private List<Corredor> corredores;

    public Integer getUbicacion_codigo() {
        return ubicacion_codigo;
    }

    public void setUbicacion_codigo(Integer ubicacion_codigo) {
        this.ubicacion_codigo = ubicacion_codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Corredor> getCorredores() {
        return corredores;
    }

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }
}
