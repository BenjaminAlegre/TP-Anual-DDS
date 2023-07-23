package model.entities.entidades;

import model.entities.servicio.Monitoreable;

import java.util.List;

public abstract class Establecimiento {
    private String nombre;
    private Direccion ubicacion;
    private List<Monitoreable> monitoreables;

}
