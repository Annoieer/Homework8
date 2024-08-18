package homework_8.mapper;

import homework_8.dto.TransactionalMemoryDto;
import homework_8.entity.TransactionalMemory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionalMemoryMapper {
    private final ModelMapper modelMapper;

    public TransactionalMemoryMapper() {
        this.modelMapper = new ModelMapper();
    }

    public TransactionalMemoryDto toDto(TransactionalMemory memory) {
        return modelMapper.map(memory, TransactionalMemoryDto.class);
    }
}
