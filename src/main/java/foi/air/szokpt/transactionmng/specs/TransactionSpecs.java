package foi.air.szokpt.transactionmng.specs;

import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.TrxType;
import org.springframework.data.jpa.domain.Specification;

public class TransactionSpecs {

    public static Specification<Transaction> hasCardBrand(CardBrand cardBrand) {
        return (root, query, builder) ->
                cardBrand == null ? null : builder.equal(
                        root.get("cardBrand"), cardBrand
                );
    }

    public static Specification<Transaction> hasTrxType(TrxType trxType) {
        return (root, query, builder) ->
                trxType == null ? null : builder.equal(
                        root.get("trxType"), trxType
                );
    }

}
