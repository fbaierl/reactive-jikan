package net.sandrohc.jikan.test;

import net.sandrohc.jikan.Jikan;
import net.sandrohc.jikan.Jikan.JikanBuilder;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class RequestTest {

	protected static Jikan jikan;
	protected static ClientAndServer mockServer;

	@BeforeAll
	public static void setup() {
		jikan = new JikanBuilder().debug(true).baseUrl(MockUtils.MOCK_URL).maxRetries(1).userAgent("reactive-jikan/unit-tests").build();
		if (mockServer == null)
			mockServer = MockUtils.createMockServer();
	}

	@BeforeEach
	public void stopServer() {
		mockServer.reset();
	}

}
