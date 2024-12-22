package foi.air.szokpt.transactionmng.controllers;

import foi.air.szokpt.transactionmng.dtos.responses.ApiResponse;
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
            @RequestParam(name = "card_brand", required = false) CardBrand cardBrand,
            @RequestParam(name = "trx_type", required = false) TrxType trxType,
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

    @GetMapping("transactions/{id}")
    public ResponseEntity<ApiResponse<Transaction>> getTransaction(@PathVariable int id){
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.successWithData("Transaction successfully fetched", transaction));
    }

    @PutMapping("transactions/{id}")
    public ResponseEntity<ApiResponse<Void>> updateTransaction(
            @PathVariable int id,
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Transaction newTransactionData) {

        authorizer.verifyToken(authorizationHeader);
        transactionService.updateTransaction(id, newTransactionData);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponseUtil.success("Transaction successfully updated."));
    }
}
