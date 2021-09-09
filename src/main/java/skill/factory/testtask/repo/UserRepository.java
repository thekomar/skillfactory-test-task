package skill.factory.testtask.repo;

import skill.factory.testtask.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(int id);

    Optional<UserEntity> update(UserEntity userEntity);
}
