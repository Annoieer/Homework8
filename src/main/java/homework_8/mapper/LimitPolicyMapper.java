package homework_8.mapper;

import homework_8.dto.LimitPolicyDto;
import homework_8.entity.LimitPolicy;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LimitPolicyMapper {
    private final ModelMapper modelMapper;

    public LimitPolicyMapper() {
        this.modelMapper = new ModelMapper();
    }

    public LimitPolicyDto toDto(LimitPolicy limit) {
        return modelMapper.map(limit, LimitPolicyDto.class);
    }
}
