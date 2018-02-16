package project.webshop.dao.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.Date;
import java.util.List;

@RedisHash("orderinfo")
public class OrderInfo {

    @Id
    String id;
    @Indexed
    Date date;


}