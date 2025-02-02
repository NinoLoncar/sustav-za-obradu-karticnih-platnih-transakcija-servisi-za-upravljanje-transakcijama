package foi.air.szokpt.transactionmng.specs;

import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.TrxType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionSpecs {

    public static Specification<Transaction> hasCardBrand(List<CardBrand> cardBrands) {
        return (root, query, builder) ->
                cardBrands == null || cardBrands.isEmpty() ? builder.conjunction() :
                        root.get("cardBrand").in(cardBrands);

    }

    public static Specification<Transaction> hasTrxType(List<TrxType> trxTypes) {
        return (root, query, builder) ->
                trxTypes == null  || trxTypes.isEmpty() ? builder.conjunction() :
                        root.get("trxType").in(trxTypes);
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

    public static Specification<Transaction> amountGreaterThan(
            BigDecimal amountGreaterThan) {
        return (root, query, builder) ->
                amountGreaterThan == null ? builder.conjunction() : builder.greaterThanOrEqualTo(
                        root.get("amount"), amountGreaterThan
                );
    }

    public static Specification<Transaction> amountLessThan(
            BigDecimal amountLessThan) {
        return (root, query, builder) ->
                amountLessThan == null ? builder.conjunction() : builder.lessThanOrEqualTo(
                        root.get("amount"), amountLessThan
                );
    }

    public static Specification<Transaction> isProcessed(Boolean processed) {
        return (root, query, builder) ->
                processed == null ? builder.conjunction() : builder.equal(
                        root.get("processed"), processed
                );
    }
}
