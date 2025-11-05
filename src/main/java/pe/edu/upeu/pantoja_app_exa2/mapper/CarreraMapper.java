package pe.edu.upeu.pantoja_app_exa2.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.pantoja_app_exa2.dto.CarreraDTO;
import pe.edu.upeu.pantoja_app_exa2.entity.Carrera;
import pe.edu.upeu.pantoja_app_exa2.mapper.Base.BaseMapper;
@Mapper(componentModel = "spring")
public interface CarreraMapper extends BaseMapper<Carrera, CarreraDTO> {
}
