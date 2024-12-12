package foi.air.szokpt.transactionmng.converters;

import foi.air.szokpt.transactionmng.enums.CardBrand;
import org.springframework.stereotype.Component;

@Component
public class CardBrandConverter extends EnumConverter<CardBrand> {
    @Override
    public CardBrand convert(String source) {
        CardBrand cardBrand = CardBrand.getByName(source.toLowerCase());
        return cardBrand == null ? getInvalid() : cardBrand;
    }

    protected CardBrand getInvalid() {
        return CardBrand.Invalid;
    }

    protected Class<CardBrand> getEnumType() {
        return CardBrand.class;
    }

}
