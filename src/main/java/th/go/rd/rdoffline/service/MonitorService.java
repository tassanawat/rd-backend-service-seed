package th.go.rd.rdoffline.service;

import th.go.rd.rdoffline.repository.MonitorRepository;
import th.go.rd.rdoffline.entity.MonitorDataEntity;
import com.codahale.metrics.MetricRegistry;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
@Transactional
public class MonitorService {

    @Autowired
    MeterRegistry registry;

    @Autowired
    MetricRegistry metrics;

    @Autowired
    private MonitorRepository monitorRepository;

    @Scheduled(fixedDelay = 60000)
    public void collectMetrics() {
        Double heapUsed = registry.find("jvm.memory.used").tags("area", "heap").gauge().value();
        Double nonHeapUsed = registry.find("jvm.memory.used").tags("area", "nonheap").gauge().value();
        Double threadLive = registry.find("jvm.threads.live").gauge().value();
        Double threadDaemon = registry.find("jvm.threads.daemon").gauge().value();
        double systemCPUValue = registry.find("system.cpu.usage").gauge().value();
        Double systemCPU = 0.;
        if (systemCPUValue > 0) {
            systemCPU = systemCPUValue * 100;
        }
        double processCPUValue = registry.find("process.cpu.usage").gauge().value();
        Double processCPU = 0.;
        if (processCPUValue > 0) {
            processCPU = processCPUValue * 100;
        }

        com.codahale.metrics.Meter meter = metrics.meter("requests");

        MonitorDataEntity data = new MonitorDataEntity();
        data.setSystemCpu(round(systemCPU, 4));
        data.setProcessCpu(round(processCPU, 4));
        data.setMemoryUsedHeap(heapUsed);
        data.setMemoryUsedNonHeap(nonHeapUsed);
        data.setThreadsLive(threadLive.intValue());
        data.setThreadsDaemon(threadDaemon.intValue());
        data.setRequestsPerMinute(round(meter.getOneMinuteRate(), 2));
        data.setTimestamp(new Date());
        monitorRepository.save(data);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
