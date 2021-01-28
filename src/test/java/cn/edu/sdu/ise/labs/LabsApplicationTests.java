package cn.edu.sdu.ise.labs;

import cn.edu.sdu.ise.labs.controller.RangeController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("cn.edu.sdu.ise.labs.dao")
@Slf4j
public class LabsApplicationTests {
    @Test
    public void test() {

    }
}
