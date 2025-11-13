package pe.edu.upeu.pantoja_app_exa2.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "La fecha no puede estar vacía") // ✅ Usa NotNull, no NotBlank
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE) // ✅ importante para tipos Date
    private Date fechaNacimiento;

    @NotNull(message = "La carrera no puede estar vacía") // ✅ Usa NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IDCARRERA", nullable  = false)
    private Carrera carrera;
}


