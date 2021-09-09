package skill.factory.testtask.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import skill.factory.testtask.entity.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = :id";

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Optional<UserEntity> findById(int id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new MapSqlParameterSource("id", id), (rs,rn)->{
                    return new UserEntity(
                            rs.getInt("id"),
                            rs.getBigDecimal("balance"));
                })
        );

    }
}
