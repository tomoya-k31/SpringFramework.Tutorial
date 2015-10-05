package me.tomo;

import me.tomo.domain.Customer;
import me.tomo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by usr0200379 on 15/09/18.
 */
@EnableAutoConfiguration
public class App implements CommandLineRunner {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT ID, FIRST_NAME, LAST_NAME FROM CUSTOMERS WHERE ID = :id";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", 1);

        Customer result = jdbcTemplate.queryForObject(sql, param, (rs, rowNum) -> {
            return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
        });

        System.out.println(result);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
