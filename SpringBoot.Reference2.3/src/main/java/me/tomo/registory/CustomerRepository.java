package me.tomo.registory;

import me.tomo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by usr0200379 on 15/09/17.
 *
 *
 * @Transactionalをつけることで
 * このクラスをDIコンテナから取得すると、そのクラスの各メソッドが他のクラスから呼ばれた場合に、
 * 自動的にDBトランザクションの制御が行われる
 */
@Repository
@Transactional
public class CustomerRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    SimpleJdbcInsert insert;

    @PostConstruct
    public void init() {
        insert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
                .withTableName("customers")
                .usingGeneratedKeyColumns("id");
    }


    static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
        Integer id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        return new Customer(id, firstName, lastName);
    };


    public List<Customer> findAll() {
        // queryはリストでの取得が可能
        return jdbcTemplate.query("select id, first_name, last_name from customers order by id", customerRowMapper);
    }

    public Customer findOne(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        return jdbcTemplate.queryForObject("select id, first_name, last_name from customers where id = :id", param, customerRowMapper);
    }


    public Customer save(Customer customer) {

        // フィールド名と自動でマッピングしてくれる
        SqlParameterSource param = new BeanPropertySqlParameterSource(customer);

        String insertSql = "insert into customers(first_name, last_name) values(:firstName, :lastName)";
        String updateSql = "update customers set first_name = :firstName, last_name = :lastName where id = :id";
        if (customer.getId() == null) {
//            jdbcTemplate.update(insertSql, param);
            Number key = insert.executeAndReturnKey(param);
            customer.setId(key.intValue());
        } else {
            jdbcTemplate.update(updateSql, param);
        }
        return customer;
    }

    public void delete(Integer customerId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", customerId);
        jdbcTemplate.update("delete from customers where id = :id", param);
    }
}
