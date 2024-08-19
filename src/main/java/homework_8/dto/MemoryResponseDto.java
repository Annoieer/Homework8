package homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MemoryResponseDto {
    List<MemoryDto> memory;
}
