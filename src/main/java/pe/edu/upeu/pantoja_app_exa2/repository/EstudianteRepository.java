package pe.edu.upeu.pantoja_app_exa2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.pantoja_app_exa2.entity.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
