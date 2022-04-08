package icu.f2v.kakafarm.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    private Integer id;

    //seller的id
    private String seller;

    //本地描述图片地址[json]
    private String pictures;

    //描述
    private String description;

    //[json]
    private String tags;

    //目标筹集资金(包含成本)
    private Integer funds;

    //seller已投入成本
    private Integer cost;

    //数量
    private Integer quantity;

    //购买单价
    private Double unitPrice;

    /**
     * 筹集列表[json]
     * {
     *  funds: [
     *  {
     *      user: xxx
     *      money: xxx
     *  },
     *  ...
     *  ]
     * }
     */
    private String fundsList;
}
