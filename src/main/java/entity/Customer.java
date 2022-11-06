package entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Integer id;
    private String email;
    private String firstName;
    private String password;
    private List<Order> orders;
}