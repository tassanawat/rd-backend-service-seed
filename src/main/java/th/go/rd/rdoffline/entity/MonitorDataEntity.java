package th.go.rd.rdoffline.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "monitoring")
@SequenceGenerator(name = "hibernate_sequence")
@NamedQuery(name = "MonitorDataEntity.findAll", query = "select m from MonitorDataEntity m order by m.id")
public class MonitorDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequence")
    @TableGenerator(
            name="hibernate_sequence",
            table="hibernate_sequence",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue="SEQ_GEN",
            allocationSize=1
    )
    private Integer id;

    private Double systemCpu;
    private Double processCpu;
    private Integer threadsLive;
    private Integer threadsDaemon;
    private Double memoryUsedHeap;
    private Double memoryUsedNonHeap;
    private Double requestsPerMinute;

    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSystemCpu() {
        return systemCpu;
    }

    public void setSystemCpu(Double systemCpu) {
        this.systemCpu = systemCpu;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getProcessCpu() {
        return processCpu;
    }

    public void setProcessCpu(Double processCpu) {
        this.processCpu = processCpu;
    }

    public Integer getThreadsLive() {
        return threadsLive;
    }

    public void setThreadsLive(Integer threadsLive) {
        this.threadsLive = threadsLive;
    }

    public Integer getThreadsDaemon() {
        return threadsDaemon;
    }

    public void setThreadsDaemon(Integer threadsDaemon) {
        this.threadsDaemon = threadsDaemon;
    }

    public Double getMemoryUsedHeap() {
        return memoryUsedHeap;
    }

    public void setMemoryUsedHeap(Double memoryUsedHeap) {
        this.memoryUsedHeap = memoryUsedHeap;
    }

    public Double getMemoryUsedNonHeap() {
        return memoryUsedNonHeap;
    }

    public void setMemoryUsedNonHeap(Double memoryUsedNonHeap) {
        this.memoryUsedNonHeap = memoryUsedNonHeap;
    }

    public Double getRequestsPerMinute() {
        return requestsPerMinute;
    }

    public void setRequestsPerMinute(Double requestsPerMinute) {
        this.requestsPerMinute = requestsPerMinute;
    }
}