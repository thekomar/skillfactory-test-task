package skill.factory.testtask.mapper;

import org.mapstruct.Mapper;
import skill.factory.testtask.domain.User;
import skill.factory.testtask.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
