package skill.factory.testtask.repo;

import skill.factory.testtask.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(int id);

}
