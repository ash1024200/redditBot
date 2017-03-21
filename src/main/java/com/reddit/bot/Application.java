package com.reddit.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.jreddit.oauth.RedditOAuthAgent;
import com.github.jreddit.oauth.RedditToken;
import com.github.jreddit.oauth.app.RedditApp;
import com.github.jreddit.oauth.app.RedditInstalledApp;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) throws Exception {
		SpringApplication.run(Application.class);

		// Information about the app
		String userAgent = "jReddit: Reddit API Wrapper for Java";
		String clientID = "PfnhLt3VahLrbg";
		String redirectURI = "https://github.com/snkas/jReddit";

		// Reddit application
		RedditApp redditApp = new RedditInstalledApp(clientID, redirectURI);

		// Create OAuth agent
		RedditOAuthAgent agent = new RedditOAuthAgent(userAgent, redditApp);

		// Create token (will be valid for 1 hour)
		RedditToken token = agent.tokenAppOnly(false);
		System.out.println("Access Token: " + token.getAccessToken());
		System.out.println("Token Type: " + token.getTokenType());
		System.out.println("Refreshable: " + token.isRefreshable());
		System.out.println("Expired: " + token.isExpired());
		System.out.println("Expiration: " + token.getExpiration());
		System.out.println("Will expire in 61 minutes: " + token.willExpireIn((long) 3660));
		System.out.println("Will expire in 59 minutes: " + token.willExpireIn((long) 3540));
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
