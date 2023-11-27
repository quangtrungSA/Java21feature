package projectloom;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
@State(Scope.Thread)
public class AppBenchmark {
    private List<ApiRequest> apiRequests;

    @Setup
    public void setup() {
        apiRequests = new ArrayList<>();
        for (String apiUrl : App.API_URLS) {
            apiRequests.add(new ApiRequest(apiUrl));
        }
    }

    @Benchmark
    public void testThreadPoolExecutor() {
        ExecutorService executor1 = Executors.newFixedThreadPool(App.NUMBER_OF_THREADS);
        App.executeApiRequests(executor1, apiRequests);
        executor1.shutdown();
    }

    @Benchmark
    public void testProjectLoom() {
        ExecutorService executor2 = Executors.newVirtualThreadPerTaskExecutor();
        App.executeApiRequests(executor2, apiRequests);
        executor2.shutdown();
    }
}