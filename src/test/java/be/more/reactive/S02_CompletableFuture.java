package be.more.reactive;

import be.more.reactive.util.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class S02_CompletableFuture extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(S02_CompletableFuture.class);

    @Test
    public void supplyAsyncCommonPool() throws Exception {
        final CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(
                () -> db.apply(query)
        );

        futureResult.thenAccept(result -> log.debug("Query result: '{}'", result));       //non-blocking

        TimeUnit.SECONDS.sleep(5);
        log.debug("Finishing");
    }

    @Test
    public void supplyAsyncWithCustomPool() throws Exception {
        final CompletableFuture<String> futureResult = CompletableFuture.supplyAsync(
                () -> db.apply(query),
                executorService
        );

        futureResult.thenAccept(result -> log.debug("Qurey result: '{}'", result));       //non-blocking

        TimeUnit.SECONDS.sleep(5);
        log.debug("Finishing");
    }

}

