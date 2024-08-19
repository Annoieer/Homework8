package homework_8.mapper;

import homework_8.dto.MemoryDto;
import homework_8.entity.Memory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MemoryMapper {
    private final ModelMapper modelMapper;

    public MemoryMapper() {
        this.modelMapper = new ModelMapper();
    }

    public MemoryDto toDto(Memory memory) {
        return modelMapper.map(memory, MemoryDto.class);
    }
}
