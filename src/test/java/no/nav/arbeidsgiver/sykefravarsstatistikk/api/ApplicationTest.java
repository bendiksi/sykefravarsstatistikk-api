package no.nav.arbeidsgiver.sykefravarsstatistikk.api;

import no.nav.arbeidsgiver.sykefravarsstatistikk.api.statistikk.sykefraværshistorikk.SykefraværshistorikkController;
import no.nav.security.token.support.spring.test.EnableMockOAuth2Server;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("mvc-test")
@EnableMockOAuth2Server
@SpringBootTest(
        classes = SykefraværsstatistikkLocalApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)@TestPropertySource(properties = {"wiremock.mock.port=8082", "spring.h2.console.enabled=false"})
public class ApplicationTest {

    @Autowired
    private SykefraværshistorikkController controller;

    @Test
    public void contexLoads() {
        assertThat(controller).isNotNull();
    }

}
