# 프로젝트 설명
## #카프카, Spring boot 를 활용하여 주문 접수 및 주문 생성 로직 구현

# kafka docker start
```
$ cd kafka
$ docker-compose -f kafka-cluster.yml up
```
# server
```
$ cd server
# spring boot app start
```

# Front end 
```
$ cd nuxtjs-order-client
$ yarn install # node_modules install
$ yarn dev # client server start
```

# server architecture
![Alt text](./source/architecture.jpg)

## Front Client 페이지 캡처
### 메인 화면("/")
![Alt text](./source/main.png)
### 스토어 리스트("/store-list")
![Alt text](./source/shoplist.png)
### 상품 리스트("/product/:_storeId/list")
![Alt text](./source/ordersubmit.png)
### 매장 주문 확인 페이지("/order/:_storeId/order-list")
![Alt text](./source/orderlist.png)


