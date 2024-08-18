package homework_8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "memory")
@NoArgsConstructor
public class TransactionalMemory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    public TransactionalMemory(Long amount, Long userId, Date transactionDate) {
        this.amount = amount;
        this.userId = userId;
        this.transactionDate = transactionDate;
        this.isCancelled = false;
    }
}
