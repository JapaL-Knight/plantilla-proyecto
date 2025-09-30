package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Punto;

@Repository
public interface PuntoRepository extends JpaRepository<Punto, Long> {
    
    @Query("SELECT p FROM Punto p WHERE p.servicio.idServicio = :idServicio ORDER BY p.orden ASC")
    List<Punto> puntosDeServicio(Long idServicio);

    @Query(value = "SELECT column_name, data_type, data_length, nullable FROM user_tab_columns WHERE table_name = 'PUNTOS' ORDER BY column_id;", nativeQuery = true)

    Collection<Punto> darPuntos();


    @Query(value = "SELECT * FROM PUNTO WHERE IDPUNTO = :id", nativeQuery = true)
    Punto darPunto(@Param("id") long id);


    @Query(value = "SELECT * FROM PUNTO WHERE IDCIUDAD = :idCiudad", nativeQuery = true)
    Collection<Punto> darPuntosPorCiudad(@Param("idCiudad") long idCiudad);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PUNTO (IDPUNTO, IDCIUDAD, DIRECCION, LONGITUD, LATITUD, ORDEN, IDSERVICIO) " +
                "VALUES (alpescab_sequence.nextval, :idCiudad, :direccion, :longitud, :latitud, :orden, :idServicio)", 
        nativeQuery = true)
    void insertarPunto(
                    @Param("idCiudad") Long idCiudad,
                    @Param("direccion") String direccion,
                    @Param("longitud") Double longitud,
                    @Param("latitud") Double latitud,
                    @Param("orden") Integer orden,
                    @Param("idServicio") Long idServicio);




    @Modifying
    @Transactional
    @Query(value = "UPDATE PUNTO SET IDCIUDAD = :idCiudad, DIRECCION = :direccion, " +
                   "LONGITUD = :longitud, LATITUD = :latitud, ORDEN = :orden WHERE IDPUNTO = :id",
            nativeQuery = true)
    void actualizarPunto(@Param("id") Long id,
                         @Param("idCiudad") Long idCiudad,
                         @Param("direccion") String direccion,
                         @Param("longitud") Double longitud,
                         @Param("latitud") Double latitud,
                         @Param("orden") Integer orden);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PUNTO WHERE IDPUNTO = :id", nativeQuery = true)
    void eliminarPunto(@Param("id") long id);
}