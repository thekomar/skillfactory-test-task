package skill.factory.testtask.repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import skill.factory.testtask.entity.UserEntity;

import java.math.BigDecimal;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = :id";
    private static final String UPDATE_BALANCE = "UPDATE users SET balance = :balance WHERE id = :id RETURNING *";
    private static final RowMapper<UserEntity> MAPPER = (rs, rn) -> new UserEntity(
            rs.getInt("id"),
            rs.getBigDecimal("balance"));
    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Optional<UserEntity> findById(int id) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new MapSqlParameterSource("id", id), MAPPER)
        );

    }

    @Override
    public Optional<UserEntity> update(UserEntity userEntity) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("balance", userEntity.getBalance());
        parameterSource.addValue("id", userEntity.getId());
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        UPDATE_BALANCE, parameterSource, MAPPER
                )
        );
    }

}
