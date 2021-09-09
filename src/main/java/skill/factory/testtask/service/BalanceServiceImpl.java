package skill.factory.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import skill.factory.testtask.entity.UserEntity;
import skill.factory.testtask.exceptions.UserNotFoundException;
import skill.factory.testtask.repo.UserRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements BalanceService {
    private final UserRepository userRepository;

    @Override
    public BigDecimal getBalance(int id) {
        return userRepository.findById(id)
                .map(UserEntity::getBalance)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
