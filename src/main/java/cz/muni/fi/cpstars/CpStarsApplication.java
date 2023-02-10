package cz.muni.fi.cpstars;

import cz.muni.fi.cpstars.dal.temporaryData.TemporaryStarBasicInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CpStarsApplication {

	public static void main(String[] args) {
		TemporaryStarBasicInfo.loadData();
		SpringApplication.run(CpStarsApplication.class, args
		);
	}

}
