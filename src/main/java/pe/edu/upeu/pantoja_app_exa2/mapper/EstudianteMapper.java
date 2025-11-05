package pe.edu.upeu.pantoja_app_exa2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.pantoja_app_exa2.dto.EstudianteDTO;
import pe.edu.upeu.pantoja_app_exa2.entity.Estudiante;
import pe.edu.upeu.pantoja_app_exa2.mapper.Base.BaseMapper;
@Mapper(componentModel = "spring")
public interface EstudianteMapper extends BaseMapper<Estudiante, EstudianteDTO> {

    @Mapping(target = "carrera", ignore = true)
    Estudiante toEntity(EstudianteDTO dto);

    @Mapping(source = "carrera.id", target = "carrera")
    EstudianteDTO toDTO(Estudiante entity);
}
