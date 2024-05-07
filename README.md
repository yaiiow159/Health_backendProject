# 健康管理系統
模擬健康管理，可以根據病患各項數據提供看診預約、或是購買醫療檢查項目的檢查，組合或是一個set，管理員身分可進行系統設置功能<br>
如會員管理、角色管理、權限管理、用戶管理等功能權限

## 系統介紹: 
使用springboot + vue的分布式權限管理系統，使用dubbo作為服務控管搭配zookeeper作為服務註冊中心
系統方面拆分成四個模組，health-provider、health-backender、health-common、health-interface

模組說明:
+ health-provider:主要提供式服務提供模組裡面有mybatis-mapper各項xml文件以及服務實現物件提供dubbo發現服務 
+ health-commons:提供各項entity以及使用常數 還有工具類存放
+ health-interface: 提供服務層介面
+ health-backend:提供控制層(controller)還有權限控制等配置類

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
- 高級配套功能

### 健康報告 <br>
- 報告詳情功能

### 統計分析 <br>
- 會員數統計
- 套餐占比
- 運營數據

### 系統配置 <br>
- 用戶管理
- 角色管理
- 權限管理
- 會員管理

--------------------------------------------------------------------------------------------------
+ 前端使用技術: html5、css、element-ui、vue2、axios
+ 後端使用技術: springboot、spring-security(權限控管)、mybatis-plus(orm框架)、dubbo、zookeeper
+ 資料庫: mysql8
+ 非關係型資料庫:redis
+ 容器化: docker
+ 容器管理平台: portainer
+ 雲服務: google-storage、computer-engine
