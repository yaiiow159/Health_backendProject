# 醫起來 (仿醫療健檢系統)
模擬醫療健康檢查單位系統，可以根據病患各項數據提供看診預約、或是購買醫療檢查項目的檢查，組合或是一個set，管理員身分可進行系統設置功能<br>
如會員管理、角色管理、權限管理、用戶管理等功能權限，也可以觀看各項系統數據分系來觀察成長曲線

## 系統介紹: 
使用springboot + vue的分布式權限管理系統，使用dubbo作為服務控管搭配zookeeper-cluster作為服務註冊中心 + 分布式數據存儲 <br>
為避免service單節點service服務掛掉，採取三節點方式增加系統穩定性 <br>
系統方面拆分成四個模組，health-provider、health-backender、health-common、health-interface

## db關係圖
![Diagram 1](https://github.com/yaiiow159/Health_backendProject/assets/39752246/be410ee2-6fc4-4dea-8625-3e7df1f94d22)

##　模組拆分與設計想法
![未命名绘图 drawio](https://github.com/yaiiow159/Health_backendProject/assets/39752246/e4f79f1c-0126-45de-b7aa-c057e0b3e6c9)


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
+ 容器化: docker、docker-compose、docker-swarm
+ 容器管理平台: portainer
+ 雲服務: google-storage、computer-engine、gcloud-sql (進行中)

<hr>

## 畫面預覽

### dubbo-admin管理服務
![dubbo-admin](https://github.com/yaiiow159/Health_backendProject/assets/39752246/8701bfd4-40e9-4cfe-b64a-61ea4f9c4b97)

### 使用docker-compose搭建zookeeper-cluster 與dubbo-admin
![docker-compose](https://github.com/yaiiow159/Health_backendProject/assets/39752246/cd135d68-d727-42e0-b562-fd8674696c17)

### 登入畫面
![註冊畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/25f9db4b-0049-48a9-bbcd-3b3344230104)
![登入畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/58e31b13-8346-4516-b33c-3e0a09bd8fea)

### 首頁畫面
![首頁畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/30abcd6b-a644-46be-966b-1aefb5ce898a)

### 下載與上傳模板訊息畫面
![上傳文件預約](https://github.com/yaiiow159/Health_backendProject/assets/39752246/140db553-588c-461d-ab15-b83f51e3123a)
![上傳成功畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/b2ee4f49-bc0d-42b0-8a85-ccfd5b7f22c2)

用戶資料畫面
![用戶資料畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/27b8422a-6141-4398-b00d-796ab36aba86)

聯繫我們畫面
![聯繫我們](https://github.com/yaiiow159/Health_backendProject/assets/39752246/f74aa46a-7d45-4318-9d6a-2c1c4c173416)

會員管理畫面
![會員管理查詢](https://github.com/yaiiow159/Health_backendProject/assets/39752246/36061e72-a096-4c0b-babe-edc460d63150)
![會員管理](https://github.com/yaiiow159/Health_backendProject/assets/39752246/cf330b15-6758-4a2e-b87f-43b002157b47)
![新增會員成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/cd72e536-fa6d-45d1-88aa-ae1e0e41f731)
![新增會員](https://github.com/yaiiow159/Health_backendProject/assets/39752246/409f3a6b-054a-4f04-8fd7-bef2addf8487)

預約列表畫面


















