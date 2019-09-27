package th.go.rd.rdoffline.repository;

import th.go.rd.rdoffline.entity.MonitorDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MonitorRepository extends CrudRepository<MonitorDataEntity, Long> {

    @Modifying
    MonitorDataEntity save(MonitorDataEntity data);

    @Query
    List<MonitorDataEntity> findAll();

}
