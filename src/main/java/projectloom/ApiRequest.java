package projectloom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class ApiRequest implements Callable<String> {
    private final String apiUrl;

    public ApiRequest(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public String call() throws Exception {
        // Thực hiện gửi yêu cầu đến API và xử lý kết quả trả về.
        // Trong ví dụ này, chúng ta chỉ đơn giản tải về nội dung của API.
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return content.toString();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API data: " + apiUrl, e);
        }
    }
}
