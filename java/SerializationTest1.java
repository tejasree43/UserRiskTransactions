import com.fasterxml.jackson.databind.ObjectMapper;
import com.userrisktransactions.model.Role;
import com.userrisktransactions.model.User;

import java.util.Set;

public class SerializationTest1 {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        // Create a Role object
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_USER");

        // Create a User object
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setFirstName("Test");
        user.setLastName("User");

        // Associate the role with the user
        user.setRoles(Set.of(role));
        role.setUsers(Set.of(user)); // This line is for completeness; ensure you handle it correctly.

        // Serialize the User object
        String json = objectMapper.writeValueAsString(user);
        System.out.println("Serialized User JSON: " + json);

        // Deserialize back to User
        User deserializedUser = objectMapper.readValue(json, User.class);
        System.out.println("Deserialized User: " + deserializedUser);
    }
}