package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final String tableName = "products";
    private final String[] columns = {"prod_name", "prod_type", "prod_amt", "prod_price", "prod_discount"};

    @RequestMapping("/select")
    public Product select(@RequestParam(value = "condition", required = false, defaultValue = "potato~Iran") String cond) {
        String condition ="";
        if(!cond.equals("")) {
            String[] Condition = cond.split("~");
            condition = "prod_name='" + Condition[0] + "'AND prod_type LIKE'" + Condition[1] + "'";
        }
        var instance = ConnectSQLClass.getinstance();
        var url = "jdbc:postgresql://localhost:5432/postgres";
        var user = "postgres";
        var pass = "postgres";
        instance.connect(url, user, pass);

        List<String> list = instance.select(tableName, columns, condition);
        String[] str = list.get(0).split(",");




        return new Product(str);

    }
}
