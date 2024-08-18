package homework_8.mapper;

import homework_8.dto.UserLimitDto;
import homework_8.entity.UserLimit;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class UserLimitMapper {
    private final ModelMapper modelMapper;

    public UserLimitMapper() {
        this.modelMapper = new ModelMapper();
        TypeMap<UserLimitDto, UserLimit> typeMap = modelMapper.createTypeMap(UserLimitDto.class, UserLimit.class);
        typeMap.addMappings(mapper -> {
            mapper.map(UserLimitDto::getId, UserLimit::setId);
            mapper.map(UserLimitDto::getUserId, UserLimit::setUserId);
            mapper.map(UserLimitDto::getUserLimit, UserLimit::setUserLimit);
        });
    }

    public UserLimitDto toDto(UserLimit limit) {
        return modelMapper.map(limit, UserLimitDto.class);
    }
}
