package th.go.rd.rdoffline.repository;

import th.go.rd.rdoffline.entity.TeamEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long> {

    @Query("select t from TeamEntity t where t.title = ?1")
    TeamEntity findTeamByTitle(String title);

    @Query("select t from TeamEntity t where t.id = ?1")
    TeamEntity findTeamById(Integer id);

    @Query("select t from TeamEntity t")
    List<TeamEntity> findAllTeams();
}
