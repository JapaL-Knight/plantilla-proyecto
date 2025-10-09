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

    @Query(value = "SELECT * FROM CIUDAD WHERE id = :id", nativeQuery = true)
    Ciudad darCiudad(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CIUDAD (id, nombre) VALUES (ALPESCAB_SEQUENCE.nextval, :nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CIUDAD WHERE id = :id", nativeQuery = true)
    void eliminarCiudad(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CIUDAD SET nombre = :nombre WHERE id = :id", nativeQuery = true)
    void actualizarCiudad(@Param("id") Integer   id, @Param("nombre") String nombre);

    @Query(value = "SELECT * FROM CIUDAD WHERE id = (SELECT MAX(id) FROM CIUDAD)", nativeQuery = true)
    Ciudad darUltimaCiudadCreada();
}
