package pe.edu.upeu.pantoja_app_exa2.controller.general;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.pantoja_app_exa2.dto.EstudianteDTO;
import pe.edu.upeu.pantoja_app_exa2.service.general.service.EstudianteService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiante")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(estudianteService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> read(@PathVariable Long id) throws ServiceException {
        EstudianteDTO dto = estudianteService.read(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@RequestBody EstudianteDTO estudianteDTO) throws ServiceException {
        EstudianteDTO created = estudianteService.create(estudianteDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) throws ServiceException {
        EstudianteDTO updated = estudianteService.update(id, estudianteDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        estudianteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
