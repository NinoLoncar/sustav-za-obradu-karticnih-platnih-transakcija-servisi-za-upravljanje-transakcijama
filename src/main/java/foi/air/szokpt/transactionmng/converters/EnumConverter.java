package foi.air.szokpt.transactionmng.converters;

import org.springframework.core.convert.converter.Converter;

public abstract class EnumConverter<T extends Enum<T>> implements Converter<String, T> {
    @Override
    public T convert(String source) {
        try {
            return Enum.valueOf(getEnumType(), source);
        } catch (IllegalArgumentException | NullPointerException e) {
            return getInvalid();
        }
    }

    protected abstract T getInvalid();

    protected abstract Class<T> getEnumType();
}
