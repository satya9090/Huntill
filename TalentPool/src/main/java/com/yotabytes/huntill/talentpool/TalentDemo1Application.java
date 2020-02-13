package com.yotabytes.huntill.talentpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication (scanBasePackages={"com.yotabytes.huntill.talentpool.controller","com.yotabytes.huntill.talentpool.service","com.yotabytes.huntill.talentpool.repository","com.yotabytes.huntill.talentpool.domain","com.yotabytes.huntill.talentpool.utils","com.yotabytes.huntill.talentpool.*"})
@RestController
@EntityScan("com.yotabytes.huntill.talentpool.domain")
@EnableJpaRepositories("com.yotabytes.huntill.talentpool.repository")

public class TalentDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(TalentDemo1Application.class, args);
	}

}
