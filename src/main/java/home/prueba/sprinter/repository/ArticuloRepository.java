package home.prueba.sprinter.repository;

import home.prueba.sprinter.model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Este archivo define la interfaz de repositorio, que extiende JpaRepository.
Esto permite manejar las operaciones de CRUD (Crear, Leer, Actualizar y Eliminar) de manera automática
 */
@Repository
public interface ArticuloRepository extends JpaRepository<Articulo, Long> {

    /*
    Al extender JpaRepository, tu repositorio hereda automáticamente los siguientes métodos:

    save(S entity): Guarda una entidad (puede ser una nueva o una existente).
    findById(ID id): Busca una entidad por su ID.
    findAll(): Recupera todas las entidades del repositorio.
    deleteById(ID id): Elimina una entidad por su ID.
    count(): Devuelve el número total de entidades en el repositorio.
    existsById(ID id): Verifica si una entidad con el ID dado existe
     */
}
