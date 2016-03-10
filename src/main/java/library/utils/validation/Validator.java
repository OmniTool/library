package library.utils.validation;

public interface Validator <E> {
    boolean exists(E entity);
    boolean isUsed(E entity);
}
