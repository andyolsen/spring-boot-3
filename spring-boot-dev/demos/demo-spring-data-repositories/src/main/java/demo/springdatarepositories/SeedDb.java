package demo.springdatarepositories;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SeedDb {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@PostConstruct
    public void init() {
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "James", 21000, "London");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Marie", 22000, "Edinburgh");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Peter", 23000, "Belfast");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Sally", 24000, "Cardiff");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Peter", 51000, "London");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Berty", 52000, "Edinburgh");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Milly", 53000, "Belfast");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Jayne", 54000, "Cardiff");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Wally", 91000, "London");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Emily", 92000, "Edinburgh");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Tommy", 93000, "Belfast");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Colin", 94000, "Cardiff");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Sarah", 121000, "London");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Darel", 122000, "Edinburgh");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Benji", 123000, "Belfast");
        jdbcTemplate.update("insert into EMPLOYEES (name, salary, region) values (?, ?, ?)", "Carys", 124000, "Cardiff");
    }
}
