package pe.edu.upeu.pantoja_app_exa2.controller.error;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
