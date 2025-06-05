import com.fasterxml.jackson.databind.ObjectMapper;
import com.userrisktransactions.model.Transaction;
import com.userrisktransactions.model.User;

import java.math.BigDecimal;

public class TransactionTest {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a User and Transaction for testing
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
       // user.setEmail("test@example.com");

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setType("DEPOSIT");
        transaction.setUser(user);

        // Serialize the Transaction
        String json = objectMapper.writeValueAsString(transaction);
        System.out.println("Serialized JSON: " + json);

        // Deserialize back to Transaction
        Transaction deserializedTransaction = objectMapper.readValue(json, Transaction.class);
        System.out.println("Deserialized Transaction: " + deserializedTransaction);
    }
}