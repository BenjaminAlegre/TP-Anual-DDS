package model.entities.entidades.adapters.adapters;

import model.entities.entidades.adapters.entidadesDeNormalizacion.ListadoDepartamentos;
import model.entities.entidades.adapters.entidadesDeNormalizacion.ListadoMunicipios;
import model.entities.entidades.adapters.entidadesDeNormalizacion.ListadoPosiblesDirecciones;
import model.entities.entidades.adapters.entidadesDeNormalizacion.ListadoProvincias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GeoDdsService {

    @GET("provincias")
    Call<ListadoProvincias> provincias(@Query("campos") String campos);

    @GET("direcciones")
    Call<ListadoPosiblesDirecciones> normalizarDireccion(@Query("direccion") String direccionIngresada);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("departamento") String nombre);

    @GET("departamentos")
    Call<ListadoDepartamentos> departamentos(@Query("provincia") String nombre);


// estos metodos cubren lon basico para traer los datos normalizados de las locaciones, y las posibles dirrecciones normalizadas por la api
    // con la idea de solo trar una vez los datos desde la api y persistirlos en la base de datos(falta implementar) para evitar hacer reques innecesarioas
    // y en cuanto la las direcciones son las que se mostrarian en un desplegable como opciones para clickearr por el usuario por medio de peticiones asincronas


}
