package pe.edu.upeu.pantoja_app_exa2.service.general.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja_app_exa2.controller.error.ResourceNotFoundException;
import pe.edu.upeu.pantoja_app_exa2.dto.EstudianteDTO;
import pe.edu.upeu.pantoja_app_exa2.entity.Carrera;
import pe.edu.upeu.pantoja_app_exa2.entity.Estudiante;
import pe.edu.upeu.pantoja_app_exa2.mapper.CarreraMapper;
import pe.edu.upeu.pantoja_app_exa2.mapper.EstudianteMapper;
import pe.edu.upeu.pantoja_app_exa2.repository.CarreraRepository;
import pe.edu.upeu.pantoja_app_exa2.repository.EstudianteRepository;
import pe.edu.upeu.pantoja_app_exa2.service.general.service.EstudianteService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final CarreraMapper carreraMapper;
    private final CarreraRepository carreraRepository;

    @Override
    public EstudianteDTO create(EstudianteDTO estudianteDTO) throws ServiceException {
        try {
            Carrera carrera = carreraRepository.findById(estudianteDTO.getCarreraId())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con código " + estudianteDTO.getCarreraId() + " no encontrada"));

            Estudiante estudiante = this.estudianteMapper.toEntity(estudianteDTO);
            estudiante.setCarrera(carrera);
            Estudiante newEstudiante = estudianteRepository.save(estudiante);
            return this.estudianteMapper.toDto(newEstudiante);
        } catch (Exception e) {
            log.error("Error al crear el estudiante", e);
            throw new ServiceException("Error al crear el estudiante", e);
        }
    }

    @Override
    public EstudianteDTO read(Long aLong) throws ServiceException {
        try {
            Estudiante estudiante = estudianteRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado"));
            return estudianteMapper.toDto(estudiante);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el estudiante con id " + aLong, e);
        }
    }

    @Override
    public EstudianteDTO update(Long aLong, EstudianteDTO estudianteDTO) throws ServiceException {
        try {
            Estudiante estudiante = estudianteRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado"));
            Carrera carrera = carreraRepository.findById(estudianteDTO.getCarreraId())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
            estudiante.setNombre(estudianteDTO.getNombre());
            estudiante.setApellido(estudianteDTO.getApellido());
            estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
            estudiante.setCarrera(carrera);
            Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
            return this.estudianteMapper.toDto(estudianteActualizado);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el estudiante con id " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!estudianteRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado");
            }
            estudianteRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el estudiante con id " + aLong, e);
        }
    }

    @Override
    public List<EstudianteDTO> listAll() throws ServiceException {
        try {
            List<Estudiante> estudiantes = estudianteRepository.findAll();
            if (estudiantes == null || estudiantes.isEmpty()) {
                return List.of(); // lista vacía si no hay datos
            }
            // Usar la versión de lista
            return estudianteMapper.toDto(estudiantes);
        } catch (Exception e) {
            log.error("Error al listar todos los estudiantes", e);
            throw new ServiceException("Error al listar todos los estudiantes", e);
        }
    }

}