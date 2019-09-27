package th.go.rd.rdoffline.service;

import th.go.rd.rdoffline.repository.TeamRepository;
import th.go.rd.rdoffline.entity.TeamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TeamsService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamEntity findTeamByTitle(String title) {
        return teamRepository.findTeamByTitle(title);
    }


    public TeamEntity findTeamById(Integer id) {
        return teamRepository.findTeamById(id);
    }

    public List<TeamEntity> findAllTeams() {
        return teamRepository.findAllTeams();
    }

    public void create(String key) throws IOException {

        String filePath = "C:\\Users\\tassana\\Desktop\\Sawaneh-Abu-Hanifa-Faruqi-ur.pdf";
        byte[] bFile = Files.readAllBytes(new File(filePath).toPath());
        TeamEntity team = new TeamEntity();
        team.setFile(bFile);
        team.setTitle("UBU");
        team.setClub(false);
        team.setNational("f");
        team.setKey(key);
        team.setCountryId(444);
        team.setUpdatedAt(new Date());
        team.setCreatedAt(new Date());
        teamRepository.save(team);
    }

}
