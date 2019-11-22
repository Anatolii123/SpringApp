package application.controller;

import application.controller.MatrixCalculationController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixCalculationController.class)
public class MatrixCalculationControllerTest {

    @Autowired
    private MatrixCalculationController controller;

    @Test
    public void test() throws Exception {
        assertThat(controller).isNotNull();
    }
}
