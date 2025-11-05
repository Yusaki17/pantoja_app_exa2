package pe.edu.upeu.pantoja_app_exa2.controller.general;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Servidor activo";
    }
}