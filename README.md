# Kafka Demo - Day 4

## Kafka Partitions & Message Keys

- Partition topic ka ek ordered log hota hai.
- Ek topic me multiple partitions hote hain, jisse messages parallel consume ho sakte hain.
- Kafka message key ke hash se partition select karta hai.
- Same key hamesha same topic ke same partition me jati hai.
- Kafka ordering sirf partition ke andar guarantee karta hai, poore topic me nahi.
- Is project me `orderId` ko Kafka key banaya gaya hai.

## Postman Test

Start Spring Boot app on port `8081`, then send POST requests:

`POST http://localhost:8081/orders`

Body:

```json
{
  "orderId": 101,
  "customerName": "Mahendra",
  "product": "Laptop",
  "amount": 75000
}
```

Same `orderId` ke saath request repeat karo:

```json
{
  "orderId": 101,
  "customerName": "Mahendra",
  "product": "Mouse",
  "amount": 1200
}
```

Console me dono messages ka `key` aur `partition` same dikhega. Agar `orderId` change karoge, partition change ho sakta hai.
