package DTO;

import lombok.Getter;

@Getter
public class IncidenteDTO {
    private Integer id;
    private String estado;
    private String observaciones;



    public IncidenteDTO(Integer id, String estado, String observaciones) {
        this.id = id;
        this.estado = estado;
        this.observaciones = observaciones;
        // Inicializar otros campos
    }
}
