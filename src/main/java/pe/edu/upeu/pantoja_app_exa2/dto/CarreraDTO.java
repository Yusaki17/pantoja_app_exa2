package pe.edu.upeu.pantoja_app_exa2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarreraDTO {
    private Long id;
    private String codigo;
    private String nombre;
}
