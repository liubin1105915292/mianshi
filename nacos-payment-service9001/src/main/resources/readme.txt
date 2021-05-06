##nacos配置文件

spring:
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: org.gjt.mm.mysql.Driver
    url: jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=false
    username: root
    password: 1105915292
  redis:
    host: 192.168.0.109
    port: 6379
mybatis:
  mapper-locations: classpath:mapper/*.xml
  type-aliases-package: com.atguigu.springclond.entities
management:
  endpoints:
   web:
    exposure:
     include: '*'
logging:
  level:
   com.luki.nacos.dao: debug