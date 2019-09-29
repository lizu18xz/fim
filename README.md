
### FIM推送系统

#### 项目整体流程：
 
 - 启动chat服务器,启动成功后注册到zk上。
 
 - 启动connector服务器,等待客户端请求,维护客户端的链接。
 
 - 客户端登陆
 ````
 发送登陆请求到connector服务器,connector服务器进行登录信息的判断,登陆成功后,选择可以使用的chat服务器,同时绑定登陆信息和chat服务器信息。
 connector服务器返回给客户端chat服务器的IP和端口,客户端获取到IP和端口后,建立与chat服务器的长连接,然后发送登陆请求到chat服务器。
 chat服务器接收到登陆请求后保存登陆信息。
 ````
 
 - 消息群发:
 ````
 客户端发送群发的指令到connector服务器, connector服务器遍历所有的chat服务器，
 并向所有chat服务器发起Http请求,传递客户端的信息和发送的消息。chat服务器 发送消息给所有的channel.
 ````
 
 - 消息点对点发送:
 ````
 客户端发送群发的指令到connector服务器,connector服务器根据 接收者的信息 获取到其所在的chat服务器，
 并向所有chat服务器发起Http请求,传递客户端的信息和要发送的消息,chat服务器 发送消息给对应的channel.
 ````
 
 ### 存储
 - 用户注册信息存储到Mysql数据库
 - 使用 zk 存储所有注册的服务端信息
 - 使用 redis 存储登陆信息和chat服务器的关系
 
 
 ### 扩展
 
 - chat服务器可以水平扩展
 - connector服务器也可以水平扩展
 
 
 ### 安装
 - redis.conf
 ````
 bind 0.0.0.0
 requirepass root123
 daemonize yes
 
 src/redis-server redis.conf
 ````
 
 
 ### TODO
 ```text
修改保存用户和chat信息的位置
离线消息的保存


整体流程的梳理
```
 
 
 
 
 
 
 
 
 
 
 
 
 