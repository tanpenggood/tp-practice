package com.itplh.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-03-20 20:44
 * @version: v1.0.0
 */
public class MockServer {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8081);
        WireMock.removeAllMappings();

        mockThenUrlEqual("/user/1", "1.json");
        mockThenUrlEqual("/user/2", "2.json");
    }

    private static void mockThenUrlEqual(String testUrl, String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource("mock/response/" + fileName);
        List<String> lines = FileUtils.readLines(resource.getFile(), "UTF-8");
        String json = String.join("\n", lines.toArray(new String[lines.size()]));
        WireMock.stubFor(get(urlEqualTo(testUrl))
                .willReturn(WireMock.aResponse()
                        .withBody(json)
                        .withStatus(200)));
    }
}
