package pe.edu.upeu.pantoja_app_exa2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import pe.edu.upeu.pantoja_app_exa2.dto.EstudianteDTO;
import pe.edu.upeu.pantoja_app_exa2.entity.Estudiante;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EstudianteMapper {

    // Manejo seguro si carrera es null
    @Mapping(source = "carrera.id", target = "carreraId", defaultValue = "0L")
    @Mapping(source = "carrera.nombre", target = "carreraNombre", defaultValue = "")
    EstudianteDTO toDto(Estudiante estudiante);

    @Mapping(source = "carreraId", target = "carrera.id")
    @Mapping(target = "carrera.nombre", ignore = true)
    Estudiante toEntity(EstudianteDTO estudianteDTO);

    List<EstudianteDTO> toDto(List<Estudiante> estudiantes);
}
