package skill.factory.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import skill.factory.testtask.controller.model.UserModel;
import skill.factory.testtask.mapper.UserMapper;
import skill.factory.testtask.service.BalanceService;

import java.math.BigDecimal;

@RestController
@RequestMapping("balance")
@RequiredArgsConstructor
public class BalanceController {
    private final BalanceService balanceService;
    private final UserMapper userMapper;

    @GetMapping("/user/{id}")
    public ResponseEntity<BigDecimal> getBalanceByUserId(@PathVariable int id) {
        return ResponseEntity.ok(
                balanceService.getBalance(id)
        );
    }

    @PutMapping("take")
    public ResponseEntity<UserModel> takeMoney(@RequestBody UserModel userModelToUpdate) {
        return ResponseEntity.ok(
                userMapper.toUserModel(
                        balanceService.takeMoney(
                                userMapper.toUser(userModelToUpdate))
                ));
    }

    @PutMapping("put")
    public ResponseEntity<UserModel> put(@RequestBody UserModel userModelToUpdate) {
        return ResponseEntity.ok(
                userMapper.toUserModel(
                        balanceService.putMoney(
                                userMapper.toUser(userModelToUpdate))
                ));
    }
}
