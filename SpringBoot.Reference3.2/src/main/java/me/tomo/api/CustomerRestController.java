package me.tomo.api;

import me.tomo.domain.Customer;
import me.tomo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by usr0200379 on 15/09/21.
 */
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

//    /**
//     * curl http://localhost:8080/api/customers/ -v -X GET
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    List<Customer> getCustomers() {
//        return customerService.findAll();
//    }

    /**
     * デフォルトpage=0, size=10
     */
    @RequestMapping(method = RequestMethod.GET)
    Page<Customer> getCustomers(@PageableDefault(size = 1) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        return customers;
    }

    /**
     * curl http://localhost:8080/api/customers/1 -v -X GET
     *
     * valueに"api/customers"からの相対パスを指定
     * {id}はプレースホルダを指定
     *
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Customer getCustomer(@PathVariable Integer id) {    // @PathVariable : プレースホルダに指定したパラメータの取得
        return customerService.findOne(id);
    }

//    /**
//     * curl http://localhost:8080/api/customers/ -v -X POST -H "Content-Type: application/json" -d "{\"firstName\":\"Tomo\",\"lastName\":\"Kaku\"}"
//     * @param customer
//     * @return
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    Customer postCustomers(@RequestBody Customer customer) {
//        return customerService.save(customer);
//    }
    /**
     * RestでPostで新規作成した場合にレスポンスのLocationヘッダに、今作ったリソースへのアクセスURLを設定するのが普通
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder urlBuilder) {
        Customer created = customerService.create(customer);
        URI location = urlBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }

    /**
     * curl http://localhost:8080/api/customers/5 -v -X PUT -H "Content-Type: application/json" -d "{\"firstName\":\"Tomo\",\"lastName\":\"Kaku\"}"
     * @param id
     * @param customer
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.save(customer);
    }

    /**
     * curl http://localhost:8080/api/customers/5 -v -X DELETE
     * @param id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        customerService.delete(id);
    }
}
