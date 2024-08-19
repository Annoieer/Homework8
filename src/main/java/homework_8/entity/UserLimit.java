package homework_8.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "limits")
@NoArgsConstructor
public class UserLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_limit")
    private Long userLimit;

    @Column(name = "policy_level")
    private Long policyLevel;

    public UserLimit(Long userId, Long userLimit, Long policyLevel) {
        this.userId = userId;
        this.userLimit = userLimit;
        this.policyLevel = policyLevel;
    }
}
