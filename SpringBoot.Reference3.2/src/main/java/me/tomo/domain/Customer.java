package me.tomo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by usr0200379 on 15/09/17.
 */
@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor // JPAの仕様で引数の存在しないコンスタントの作成が必要
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
}
