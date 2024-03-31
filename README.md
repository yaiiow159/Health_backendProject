# 健康管理系統

## 系統介紹: 
使用springboot + vue的分布式權限管理系統，使用dubbo作為服務控管搭配zookeeper作為服務註冊中心
系統方面拆分成四個模組，health-provider、health-backender、health-common、health-interface

## 功能介紹: <br>
### 會員管理: <br>
- 會員檔案功能
- 會員註冊功能
- 會員統計功能

### 預約管理: <br>
- 預約列表功能
- 預約設定功能
- 套餐配置功能
- 檢查配套功能



模組說明:
+ health-provider:主要提供式服務提供模組裡面有mybatis-mapper各項xml文件以及服務實現物件提供dubbo發現服務 
+ health-commons:提供各項entity以及使用常數 還有工具類存放
+ health-interface: 提供服務層介面
+ health-backend:提供控制層(controller)還有權限控制等配置類

--------------------------------------------------------------------------------------------------
+ 前端使用技術: html5、css、element-ui、vue2、ajax、bootstrap
+ 後端使用技術: springboot、spring-security(權限控管)、mybatis-plus(orm框架)、dubbo、zookeeper
+ 資料庫: mysql8
+ 非關係型資料庫:redis



