package pe.edu.upeu.pantoja_app_exa2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="TBL_CARRERA")
public class Carrera {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "El codigo no puede estar vacío")
    @Column(name = "CODIGO")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante> estudiantes;
}
