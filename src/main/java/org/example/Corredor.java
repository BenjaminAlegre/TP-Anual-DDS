package org.example;
import org.example.Competencia.Competencia;
import org.example.Ubicacion.Ubicacion;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "corredor")
public class Corredor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer nroDNICorredor;
    @Column
    private String tipoDNI;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private float peso;
    @Column
    private Date fechaNacimiento;
    @Column
    private String Nacionalidad;

    @ManyToOne
    @JoinColumn(name = "competencia_id", referencedColumnName = "competencia_id")
    private Competencia competencia_id;

    @ManyToOne
    @JoinColumn(name = "ubicacion_codigo", referencedColumnName = "ubicacion_codigo")
    private Ubicacion ubicacion_codigo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroDNICorredor() {
        return nroDNICorredor;
    }

    public void setNroDNICorredor(Integer nroDNICorredor) {
        this.nroDNICorredor = nroDNICorredor;
    }

    public String getTipoDNI() {
        return tipoDNI;
    }

    public void setTipoDNI(String tipoDNI) {
        this.tipoDNI = tipoDNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return Nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        Nacionalidad = nacionalidad;
    }

    public Competencia getCompetencia_id() {
        return competencia_id;
    }

    public void setCompetencia_id(Competencia competencia_id) {
        this.competencia_id = competencia_id;
    }

    public Ubicacion getUbicacion_codigo() {
        return ubicacion_codigo;
    }

    public void setUbicacion_codigo(Ubicacion ubicacion_codigo) {
        this.ubicacion_codigo = ubicacion_codigo;
    }
}
