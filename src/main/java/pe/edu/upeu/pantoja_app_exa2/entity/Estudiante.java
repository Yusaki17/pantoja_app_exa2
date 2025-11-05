package pe.edu.upeu.pantoja_app_exa2.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="TBL_ESTUDIANTES")
public class Estudiante {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "NOMBRE")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "APELLIDO")
    private String apellido;

    @NotBlank(message = "La fecha no puede estar vacío")
    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @NotBlank(message = "El IDCarrera no puede estar vacío")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IDCARRERA")
    private Carrera carrera;

}
