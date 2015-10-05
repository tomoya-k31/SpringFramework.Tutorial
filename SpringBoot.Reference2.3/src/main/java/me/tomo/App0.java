package me.tomo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

/**
 * Created by usr0200379 on 15/09/18.
 */
@EnableAutoConfiguration
public class App0 implements CommandLineRunner {

    /**
     * autoconfigureという仕組みでDataSourceやJdbcTemplateが自動で生成されDIコンテナに登録される
     */
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        String sql = "select 1";
        SqlParameterSource param = new MapSqlParameterSource();
        Integer result = jdbcTemplate.queryForObject(sql, param, Integer.class);
        System.out.println(result);



        String sql2 = "select :a + :b";
        SqlParameterSource param2 = new MapSqlParameterSource()
                .addValue("a", 100)
                .addValue("b", 200);
        Integer result2 = jdbcTemplate.queryForObject(sql2, param2, Integer.class);
        System.out.println(result2);
    }

    public static void main(String[] args) {
        SpringApplication.run(App0.class, args);
    }
}
