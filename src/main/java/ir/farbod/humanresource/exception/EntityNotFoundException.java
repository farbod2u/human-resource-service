package ir.farbod.humanresource.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id) {
        super("Entity with ID = " + id.toString() + " not found.");
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
