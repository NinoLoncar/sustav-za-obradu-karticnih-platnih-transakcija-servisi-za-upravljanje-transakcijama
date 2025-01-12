package foi.air.szokpt.transactionmng.schedulers;

import foi.air.szokpt.transactionmng.handlers.DataFetchHandler;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RawDataFetchScheduler {
    private final DataFetchHandler dataFetchHandler;

    public RawDataFetchScheduler(DataFetchHandler dataFetchHandler){
        this.dataFetchHandler = dataFetchHandler;
    }

    @PostConstruct
    @Scheduled(cron = "0 0 1,13 * * *", zone = "Europe/Zagreb")
    public void
    periodicRawDataFetch(){
        dataFetchHandler.fetchData();
    }
}
