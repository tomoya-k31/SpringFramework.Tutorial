package me.tomo.registory;

import me.tomo.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by usr0200379 on 15/09/17.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {


    // Entityのプロパティ名を入れる
    // - 直接SQLを書く場合はnativeQuery = true
    @Query(value = "SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    List<Customer> findAllOrderByName();


    @Query(value = "SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
    Page<Customer> findAllOrderByName(Pageable pageable);
}
