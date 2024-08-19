package homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LimitPolicyDto {
    private Long id;
    private String name;
    private Long userLimit;
}
