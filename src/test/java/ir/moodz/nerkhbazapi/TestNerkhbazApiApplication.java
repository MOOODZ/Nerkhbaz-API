package ir.moodz.nerkhbazapi;

import org.springframework.boot.SpringApplication;

public class TestNerkhbazApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(NerkhbazApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
