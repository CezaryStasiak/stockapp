package cs.stockapp;

import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.metamodel.EntityType;

@SpringBootApplication
public class StockappApplication {
	public static void main(String[] args) {

		SpringApplication.run(StockappApplication.class, args);
	}

}
