# Arsenal
## 遇到和解决的问题如下
### 问题：feign 在远程调用服务端的时候无法转发Header
### 解决：
### 思路：重写feign的拦截器，将请求中的Header保存至新的Template中传到远端服务中
### 操作：实现RequestInterceptor接口并重写apply()方法，但是发现这样做之后会导致拦截器无法过去第一次的Request请求，找到问题的原因是因为Hystrix熔断机制的默认隔离策略是THREAD（线程隔离），当隔离策略是THREAD时是无法从ThreadLocal拿到RequestContextHolder.getRequestAttributes()的，所以需要更改默认隔离策略为第二种模式：SEMAPHORE（信号量隔离），但是不推荐，因为如果使用SEMAPHORE模式可能会导致熔断器不执行，比如熔断时间timeoutInMilliseconds: 10000 小于 被调用程序的执行时间，就会导致熔断器不工作，那就需要自定义隔离策略，参见 https://www.zby123.club ,整体思路参考https://blog.csdn.net/Crystalqy/article/details/79083857 的大神。
