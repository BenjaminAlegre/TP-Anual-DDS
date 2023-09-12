package model.entities.entidades.adapters.adapters;

import model.entities.entidades.adapters.entidadesDeNormalizacion.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;


public class ServicioGeoDds {


    private Retrofit retrofit;
    GeoDdsService geoDdsService;


    public ServicioGeoDds() throws Exception {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        geoDdsService = this.retrofit.create(GeoDdsService.class);
    }


    public List<Provincia> listadoProvincias() throws IOException {
        Call<ListadoProvincias> requestProvincias = geoDdsService.provincias("id,nombre");
        System.out.println(requestProvincias.request());
        Response<ListadoProvincias> responseProvincias = requestProvincias.execute();
        System.out.println("ejecuto request");

        return responseProvincias.body().provincias;
    }


    public ListadoDepartamentos listadoDepartamentos(Provincia provincia) throws IOException {
        Call<ListadoDepartamentos> requestDepartamentos = geoDdsService.departamentos(provincia.getNombre());
        Response<ListadoDepartamentos> responseDepartamentos = requestDepartamentos.execute();
        return responseDepartamentos.body();

    }

    public ListadoMunicipios listadoMunicipios(Departamento departamento) throws IOException {
        Call<ListadoMunicipios> requestMunicipios = geoDdsService.municipios(departamento.nombre);
        Response<ListadoMunicipios> responseMunicipios = requestMunicipios.execute();
        return responseMunicipios.body();

    }

    public ListadoPosiblesDirecciones normalizarDireccion(String direccionIngresada) throws IOException {
        Call<ListadoPosiblesDirecciones> requestNormalizarDireccion = geoDdsService.normalizarDireccion(direccionIngresada);
        Response<ListadoPosiblesDirecciones> responseNormalizarDireccion = requestNormalizarDireccion.execute();
        ListadoPosiblesDirecciones direccionNormalizada = responseNormalizarDireccion.body();
        return responseNormalizarDireccion.body();
    }
}

