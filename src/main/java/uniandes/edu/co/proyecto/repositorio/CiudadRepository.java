package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {


    @Query(value = "SELECT * FROM CIUDAD", nativeQuery = true)
    Collection<Ciudad> darCiudades();

    @Query(value = "SELECT * FROM CIUDAD WHERE idCiudad = :id", nativeQuery = true)
    Ciudad darCiudad(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDAD (idCiudad, nombre) VALUES (alpescab_sequence.nextval, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE idCiudad = :id", nativeQuery = true)
    void eliminarCiudad(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET nombre = :nombre WHERE idCiudad = :id", nativeQuery = true)
    void actualizarCiudad(@Param("id") long id, @Param("nombre") String nombre);
}
