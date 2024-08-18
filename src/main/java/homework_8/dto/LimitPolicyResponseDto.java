package homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LimitPolicyResponseDto {
    List<LimitPolicyDto> limitPolices;
}
