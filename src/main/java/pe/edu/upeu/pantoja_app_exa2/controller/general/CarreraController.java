package pe.edu.upeu.pantoja_app_exa2.controller.general;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.pantoja_app_exa2.dto.CarreraDTO;
import pe.edu.upeu.pantoja_app_exa2.service.general.service.CarreraService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carrera")
@CrossOrigin(origins = "http://localhost:4200")
public class CarreraController {
    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public ResponseEntity<List<CarreraDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(carreraService.listAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> read(@PathVariable Long id) throws ServiceException {
        CarreraDTO dto = carreraService.read(id);
        return ResponseEntity.ok(dto);
    }
    @PostMapping
    public ResponseEntity<CarreraDTO> create(@RequestBody CarreraDTO carreraDTO) throws ServiceException {
        CarreraDTO created = carreraService.create(carreraDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> update(@PathVariable Long id, @RequestBody CarreraDTO carreraDTO) throws ServiceException {
        CarreraDTO updated = carreraService.update(id, carreraDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        carreraService.delete(id);
        return ResponseEntity.noContent().build();
    }

}