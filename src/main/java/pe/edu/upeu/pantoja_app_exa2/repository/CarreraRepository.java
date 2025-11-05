package pe.edu.upeu.pantoja_app_exa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.pantoja_app_exa2.entity.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
