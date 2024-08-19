package homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CancelUserLimitChangeResponseDto {
    String info;
    UserLimitDto userLimitDto;
}
