package foi.air.szokpt.transactionmng.controllers;

import foi.air.szokpt.transactionmng.dtos.responses.ApiResponse;
import foi.air.szokpt.transactionmng.dtos.responses.TransactionDataResponse;
import foi.air.szokpt.transactionmng.dtos.responses.TransactionPageData;
import foi.air.szokpt.transactionmng.entities.Transaction;
import foi.air.szokpt.transactionmng.enums.CardBrand;
import foi.air.szokpt.transactionmng.enums.TrxType;
import foi.air.szokpt.transactionmng.services.TransactionService;
import foi.air.szokpt.transactionmng.util.ApiResponseUtil;
import foi.air.szokpt.transactionmng.util.Authorizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class TransactionController {

    private final TransactionService transactionService;
    private final Authorizer authorizer;

    @Autowired
    public TransactionController(TransactionService transactionService, Authorizer authorizer) {
        this.transactionService = transactionService;
        this.authorizer = authorizer;
    }

    @GetMapping("/transactions")
    public ResponseEntity<ApiResponse<TransactionPageData>> getTransactions(
            @RequestParam(required = false) Integer page,
            @RequestParam(name = "card_brand", required = false) List<CardBrand> cardBrand,
            @RequestParam(name = "trx_type", required = false) List<TrxType> trxType,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime before,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime after,
            @RequestParam(required = false, name = "amount_greater_than") BigDecimal amountGreaterThan,
            @RequestParam(required = false, name = "amount_less_than") BigDecimal amountLessThan,
            @RequestParam(required = false) Boolean processed) {
        TransactionPageData transactionPageData = transactionService.getTransactions(
                page, cardBrand, trxType, before, after, amountGreaterThan,
                amountLessThan, processed);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Transactions successfully fetched", transactionPageData));
    }

    @GetMapping("transactions/{guid}")
    public ResponseEntity<ApiResponse<TransactionDataResponse>> getTransaction(@PathVariable UUID guid){
        TransactionDataResponse transaction = transactionService.getTransaction(guid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Transaction successfully fetched", transaction));
    }

    @PutMapping("transactions/{guid}")
    public ResponseEntity<ApiResponse<Void>> updateTransaction(
            @PathVariable UUID guid,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Transaction newTransactionData
    ) {
        authorizer.verifyToken(authorizationHeader);
        transactionService.updateTransaction(guid, newTransactionData);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.success("Transaction successfully updated."));
    }
}
