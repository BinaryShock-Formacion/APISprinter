package home.prueba.sprinter.service;

import home.prueba.sprinter.model.Articulo;
import home.prueba.sprinter.repository.ArticuloRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
Esta clase contiene la lógica de negocio para manejar los artículos.
Aquí es donde implementas los métodos para crear, leer, actualizar y eliminar artículos.
La clase ArticuloService utiliza el repositorio ArticuloRepository para acceder a la base de datos.
 */
@Service
public class ArticuloService {
    @Autowired
    private ArticuloRepository articuloRepository;

    public List<Articulo> obtenerTodos(){
        return articuloRepository.findAll();
    }

    /*
    Este método llama a findById(id) en el repositorio. findById es un método proporcionado por JpaRepository que devuelve un Optional<Articulo>
    Si el método se declarara como public Articulo obtenerPorId(Long id), habría varias desventajas:
        > Posibilidad de Null
        > Verificación de Existencia

     */
    public Optional<Articulo> obtenerArticuloPorId(Long id){
        return articuloRepository.findById(id);
    }

    public Articulo crearArticulo(Articulo articulo){
        return articuloRepository.save(articulo);
    }

    @Transactional
    public Articulo actualizar(Long id, Articulo articulo) {
        Articulo existente = articuloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        existente.setNombre(articulo.getNombre());
        existente.setDescripcion(articulo.getDescripcion());
        existente.setPrecio(articulo.getPrecio());
        existente.setStock(articulo.getStock());
        existente.setCategoria(articulo.getCategoria());
        existente.setCaracteristicas(articulo.getCaracteristicas());

        return articuloRepository.save(existente);
    }

    public void eliminar(Long id) {
        articuloRepository.deleteById(id);
    }
}
