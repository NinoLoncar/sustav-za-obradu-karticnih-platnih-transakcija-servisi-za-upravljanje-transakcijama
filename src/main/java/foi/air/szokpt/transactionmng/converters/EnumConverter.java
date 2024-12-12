package foi.air.szokpt.transactionmng.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public abstract class EnumConverter<T extends Enum<T>> implements Converter<String, T> {
    private static final Logger log = LoggerFactory.getLogger(EnumConverter.class);

    @Override
    public T convert(String source) {
        try {
            return Enum.valueOf(getEnumType(), source.toLowerCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return getInvalid();
        }
    }

    protected abstract T getInvalid();

    protected abstract Class<T> getEnumType();
}
