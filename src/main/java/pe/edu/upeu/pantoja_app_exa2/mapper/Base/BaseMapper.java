package pe.edu.upeu.pantoja_app_exa2.mapper.Base;

import java.util.List;

public interface BaseMapper<E,DTO> {
    DTO toDTO(E entity);
    E toEntity(DTO dto);
    List<DTO> toDTOList(List<E> entities);
    List<E> toEntityList(List<DTO> dtos);
}
