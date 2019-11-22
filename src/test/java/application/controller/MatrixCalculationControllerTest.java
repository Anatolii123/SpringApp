package application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MatrixCalculationController.class)
@AutoConfigureMockMvc
public class MatrixCalculationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MatrixCalculationController controller;

    @Test
    public void test() throws Exception {
        assertThat(controller).isNotNull();
    }
}
