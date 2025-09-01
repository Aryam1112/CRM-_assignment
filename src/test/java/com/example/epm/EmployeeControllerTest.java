package com.example.epm;

import com.example.epm.dto.EmployeeDetailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate rest;

    @Test
    void getEmployeeDetail_shouldReturnOk() {
        ResponseEntity<EmployeeDetailDto> resp = rest.getForEntity("http://localhost:" + port + "/api/employees/1", EmployeeDetailDto.class);
        assertThat(resp.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(resp.getBody()).isNotNull();
        assertThat(resp.getBody().name()).isEqualTo("Alice");
    }
}
