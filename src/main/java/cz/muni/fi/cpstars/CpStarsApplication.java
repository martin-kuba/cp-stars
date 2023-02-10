package cz.muni.fi.cpstars;

import cz.muni.fi.cpstars.dal.implementation.star.FragmentStarRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = FragmentStarRepositoryImpl.class)
@SpringBootApplication
public class CpStarsApplication {

	public static void main(String[] args) {
//		TemporaryStarBasicInfo.loadData();
//		reload();
		SpringApplication.run(CpStarsApplication.class, args
		);
	}

}
