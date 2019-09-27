package th.go.rd.rdoffline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeamServiceTest {

    @Autowired
    private TeamsService teamsService;

    @Test
    public void test() throws IOException {
        teamsService.create("KBK");
    }
}
