package foi.air.szokpt.transactionmng.converters;

import foi.air.szokpt.transactionmng.enums.TrxType;
import org.springframework.stereotype.Component;

@Component
public class TrxTypeConverter extends EnumConverter<TrxType> {

    protected TrxType getInvalid() {
        return TrxType.invalid;
    }

    protected Class<TrxType> getEnumType() {
        return TrxType.class;
    }
}
