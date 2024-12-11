package foi.air.szokpt.transactionmng.specs;

import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.TrxType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransactionSpecs {

    public static Specification<Transaction> hasCardBrand(CardBrand cardBrand) {
        return (root, query, builder) ->
                cardBrand == null ? builder.conjunction() : builder.equal(
                        root.get("cardBrand"), cardBrand
                );
    }

    public static Specification<Transaction> hasTrxType(TrxType trxType) {
        return (root, query, builder) ->
                trxType == null ? builder.conjunction() : builder.equal(
                        root.get("trxType"), trxType
                );
    }

    public static Specification<Transaction> beforeDateTime(LocalDateTime before) {
        return (root, query, builder) ->
                before == null ? builder.conjunction() : builder.lessThanOrEqualTo(
                        root.get("transactionTimestamp"), before
                );
    }

    public static Specification<Transaction> afterDateTime(LocalDateTime after) {
        return (root, query, builder) ->
                after == null ? builder.conjunction() : builder.greaterThanOrEqualTo(
                        root.get("transactionTimestamp"), after
                );
    }
}
