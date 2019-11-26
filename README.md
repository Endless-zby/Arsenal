# Arsenal
## 遇到和解决的问题如下
### 问题：feign 在远程调用服务端的时候无法转发Header
### 解决：club.zby.newplan.Interceptor.FeignHystrixConcurrencyStrategyIntellif and club.zby.newplan.Interceptor.FeignInterceptor  
### 思路：重写feign的拦截器，将请求中的Header保存至新的Template中传到远端服务中
### 操作：实现RequestInterceptor接口并重写apply()方法，但是发现这样做之后会导致拦截器无法过去第一次的Request请求，找到问题的原因是因为Hystrix熔断机制的默认隔离策略是THREAD（线程隔离），当隔离策略是THREAD时是无法从ThreadLocal拿到RequestContextHolder.getRequestAttributes()的，所以需要更改默认隔离策略为第二种模式：SEMAPHORE（信号量隔离），但是不推荐，因为如果使用SEMAPHORE模式可能会导致熔断器不执行，比如熔断时间timeoutInMilliseconds: 10000 小于 被调用程序的执行时间，就会导致熔断器不工作，那就需要自定义隔离策略，参见 https://www.zby123.club ,整体思路参考https://blog.csdn.net/Crystalqy/article/details/79083857 的大神。
### -----------------------------------------------------------------------------------------------------------

# 技术栈
## 前端：
### thymeleaf、sawl、ajax、javascript、bootstrap（模板）

## 后端：
### springboot、springcloud（feign、ureka、config、swagger）、springMVC、redis、mysql、springdata（jpa）、rabbit（消息队列）、elasticsearch（搜索引擎）、ftp、mybatis（爬虫模块中使用）、jsoup（爬虫https://jsoup.org/）

## Linux（阿里云centos7）：
### docker、sftp、（基本操作）

## 三方接口：
### qq互联（qq登录）、快递鸟（快递查询接口）、sms（阿里云短信服务）、对象存储cos（腾讯云）

### -----------------------------------------------------------------------------------------------------------

# 各个服务模块的功能如下：

## 服务名称：projecteureka:
### 用途：各个服务的集中管理

## 服务名称：newplan:
### 用途：前端（已废弃）

## 服务名称：newplan_commen:
### 用途：公共方法、类，例如MyLogger、返回体Result

## 服务名称：newplan_core:
### 用途：前端（thymeleaf模板），登录和用户信息在此处理，包括各服务的请求转发（feign）

## 服务名称：newplan_elasticsearch:
### 用途：站内搜索（elasticsearch-7.3.1 + logstash-7.3.1）

## 服务名称：newplan_express
### 用途：快递查询模块（快递鸟的接口http://www.kdniao.com/）

## 服务名称：newplan_finance
### 用途：账务系统，收支出记录（增删改查等）

## 服务名称：newplan_ftp
### 用途：文件云存储模块，ftp协议的文件上传下载（ftp://39.96.160.110）

## 服务名称：newplan_sms
### 用途：阿里云平台的短信服务（rabbit支持的短信发送系统）

## 服务名称：newplan_spider
### 用途：爬虫模块，目前以中国青年网为目标爬取头版头条，保存数据库

