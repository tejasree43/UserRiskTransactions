package com.userrisktransactions.controller;

import com.userrisktransactions.dto.CreateTransactionDTO;
import com.userrisktransactions.dto.CustomUserDetails;
import com.userrisktransactions.dto.TransactionDTO;
import com.userrisktransactions.model.Transaction;
import com.userrisktransactions.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("createTransaction")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO){

        System.out.println("createTransaction");
        CustomUserDetails userDetails = getCurrentUser();
        Long userId = userDetails.getId();

       Transaction createdTransaction = transactionService.createTransaction(userId,createTransactionDTO);

       transactionService.cacheTransaction(createdTransaction); // Cache the newly created transaction
                        return ResponseEntity.ok("Transaction is successful for User Id  :"+userId);
    }
    @GetMapping("getAllTransactions")
    public ResponseEntity<List<Optional<TransactionDTO>>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        List<Optional<TransactionDTO>> transactionDTOs = transactions.stream()
                .filter(Objects::nonNull) // Optional: filter out null transactions
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDTOs);
    }

    public CustomUserDetails getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomUserDetails) {
            return (CustomUserDetails) auth.getPrincipal();
        }
        throw new UsernameNotFoundException("User not found");
    }
    private Optional<TransactionDTO> convertToDTO(Transaction transaction) {
        if (transaction == null) {
            return Optional.empty();
        }
        return Optional.of(new TransactionDTO(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getTimetstamp()
        ));
    }
}
