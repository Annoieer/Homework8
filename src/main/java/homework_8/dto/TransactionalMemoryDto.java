package homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TransactionalMemoryDto {
    private Long id;
    private Long amount;
    private Long userId;
    private Date transactionDate;
    private Boolean isCancelled;
}
