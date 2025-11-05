package pe.edu.upeu.pantoja_app_exa2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Long carrera;
}
