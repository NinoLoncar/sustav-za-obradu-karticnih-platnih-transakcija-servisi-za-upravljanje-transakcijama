package foi.air.szokpt.transactionmng.util.validation;

public interface Validator<T> {
    void validateData(T object);
}
