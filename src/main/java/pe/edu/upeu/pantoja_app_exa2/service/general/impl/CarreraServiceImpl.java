package pe.edu.upeu.pantoja_app_exa2.service.general.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pantoja_app_exa2.controller.error.ResourceNotFoundException;
import pe.edu.upeu.pantoja_app_exa2.dto.CarreraDTO;
import pe.edu.upeu.pantoja_app_exa2.entity.Carrera;
import pe.edu.upeu.pantoja_app_exa2.mapper.CarreraMapper;
import pe.edu.upeu.pantoja_app_exa2.repository.CarreraRepository;
import pe.edu.upeu.pantoja_app_exa2.service.general.service.CarreraService;

import java.util.List;
@Slf4j
@Service
public class CarreraServiceImpl implements CarreraService {
    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;

    public CarreraServiceImpl(CarreraRepository carreraRepository, CarreraMapper carreraMapper) {
        this.carreraRepository = carreraRepository;
        this.carreraMapper = carreraMapper;
    }

    @Override
    public CarreraDTO create(CarreraDTO carreraDTO) throws ServiceException {
        try {
            Carrera carrera = carreraMapper.toEntity(carreraDTO);
            Carrera carreraGuardada = carreraRepository.save(carrera);
            return carreraMapper.toDTO(carreraGuardada);
        } catch (Exception e) {
            log.error("Error al crear la carrera", e);
            throw new ServiceException("Error al crear la carrera", e);
        }
    }
    @Override
    public CarreraDTO read(Long aLong) throws ServiceException {
        try {
            Carrera carrera = carreraRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con id " + aLong + " no encontrada"));
            return carreraMapper.toDTO(carrera);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer la carrera con id " + aLong, e);
        }
    }
    @Override
    public CarreraDTO update(Long aLong, CarreraDTO carreraDTO) throws ServiceException {
        try {
            Carrera carrera = carreraRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con id " + aLong + " no encontrada"));
            carrera.setCodigo(carreraDTO.getCodigo());
            carrera.setNombre(carreraDTO.getNombre());
            Carrera carreraActualizada = carreraRepository.save(carrera);
            return carreraMapper.toDTO(carreraActualizada);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la carrera con id " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!carreraRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Carrera con id " + aLong + " no encontrada");
            }
            carreraRepository.deleteById(aLong);

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la carrera con id " + aLong, e);
        }
    }


    @Override
    public List<CarreraDTO> listAll() throws ServiceException {
        try {
            List<Carrera> carrera = carreraRepository.findAll();
            return carreraMapper.toDTOList(carrera);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todas las carreras", e);
        }
    }
}
