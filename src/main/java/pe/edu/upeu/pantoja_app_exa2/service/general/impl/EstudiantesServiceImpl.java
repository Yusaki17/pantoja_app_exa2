package pe.edu.upeu.pantoja_app_exa2.service.general.impl;
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
public class EstudiantesServiceImpl implements EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final EstudianteMapper estudianteMapper;
    private final CarreraMapper carreraMapper;
    private final CarreraRepository carreraRepository;

    public EstudiantesServiceImpl(EstudianteRepository estudianteRepository, EstudianteMapper estudianteMapper, CarreraMapper carreraMapper, CarreraRepository carreraRepository) {
        this.estudianteRepository = estudianteRepository;
        this.estudianteMapper = estudianteMapper;
        this.carreraMapper = carreraMapper;
        this.carreraRepository = carreraRepository;
    }

    @Override
    public EstudianteDTO create(EstudianteDTO estudianteDTO) throws ServiceException {
        try {
            Carrera carrera = carreraRepository.getById(estudianteDTO.getCarrera());

            Estudiante prod = estudianteMapper.toEntity(estudianteDTO);
            prod.setCarrera(carrera);
            Estudiante newEstudiante = estudianteRepository.save(prod);
            newEstudiante.setCarrera(carrera);
            return estudianteMapper.toDTO(newEstudiante);
        } catch (Exception e) {
            log.error("Error al crear el estudiante", e);
            throw new ServiceException("Error al crear el estudiante", e);
        }
    }
    @Override
    public EstudianteDTO read(Long aLong) throws ServiceException {
        try {
            Estudiante prod = estudianteRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrada"));
            return estudianteMapper.toDTO(prod);
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
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrada"));
            estudiante.setNombre(estudianteDTO.getNombre());
            estudiante.setApellido(estudianteDTO.getApellido());
            estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
            Estudiante newEstudiante = estudianteRepository.save(estudiante);
            return estudianteMapper.toDTO(newEstudiante);
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
                throw new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrada");
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
            List<Estudiante> estudiante = estudianteRepository.findAll();
            return estudianteMapper.toDTOList(estudiante);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todos los estudiantes", e);
        }
    }
}