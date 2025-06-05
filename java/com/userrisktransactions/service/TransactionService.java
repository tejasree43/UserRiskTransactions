package com.userrisktransactions.service;


import com.userrisktransactions.dto.CreateTransactionDTO;
import com.userrisktransactions.model.Transaction;
import com.userrisktransactions.model.User;
import com.userrisktransactions.repository.TransactionRepository;
import com.userrisktransactions.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    public Transaction createTransaction(Long userId , CreateTransactionDTO createTransactionDTO) {

        User user =userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setAmount(createTransactionDTO.getAmount());
        transaction.setType(createTransactionDTO.getType());
        transaction.setTimetstamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    public Transaction cacheTransaction(Transaction transaction)
    {
        return transaction;
    }

}
