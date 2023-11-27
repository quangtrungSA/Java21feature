package projectloom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {
    static final int NUMBER_OF_THREADS = 10;
    static final List<String> API_URLS = List.of(
            "https://jsonplaceholder.typicode.com/posts",
            "https://jsonplaceholder.typicode.com/users"
            // Thêm các API bạn muốn gọi vào đây
    );

    public static void main(String[] args) {
        List<ApiRequest> apiRequests = new ArrayList<>();
        for (String apiUrl : API_URLS) {
            apiRequests.add(new ApiRequest(apiUrl));
        }

        // Phiên bản 1: sử dụng ThreadPoolExecutor
        ExecutorService executor1 = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        executeApiRequests(executor1, apiRequests);
        executor1.shutdown();

        // Phiên bản 2: sử dụng Project Loom
        ExecutorService executor2 = Executors.newVirtualThreadPerTaskExecutor();
        executeApiRequests(executor2, apiRequests);
        executor2.shutdown();
    }

     static void executeApiRequests(ExecutorService executor, List<ApiRequest> apiRequests) {
        try {
            List<Future<String>> futures = executor.invokeAll(apiRequests);
            for (Future<String> future : futures) {
                String response = future.get();
                // Xử lý kết quả trả về từ API
                System.out.println(response);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
