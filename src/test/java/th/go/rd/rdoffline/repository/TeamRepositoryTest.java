package th.go.rd.rdoffline.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import th.go.rd.rdoffline.entity.TeamEntity;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void test() throws IOException {

        String filePath = "C:\\Users\\tassana\\Desktop\\Sawaneh-Abu-Hanifa-Faruqi-ur.pdf";
        byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
        TeamEntity team = new TeamEntity();
        team.setFile(bFile);
//        team.setTitle("UBU");
        team.setClub(false);
        team.setNational("f");
        team.setKey("oUD");
        team.setCountryId(444);
        team.setUpdatedAt(new Date());
        team.setCreatedAt(new Date());
        teamRepository.save(team);
    }
}
