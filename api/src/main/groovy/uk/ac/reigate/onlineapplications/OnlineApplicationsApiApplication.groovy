package uk.ac.reigate.onlineapplications

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class OnlineApplicationsApiApplication {

	static void main(String[] args) {
		SpringApplication.run(OnlineApplicationsApiApplication, args)
	}
}
