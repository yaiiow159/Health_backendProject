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
- 會員管理
- 會員統計功能

### 預約管理: <br>
- 預約列表功能
- 預約設定功能
- 健檢套餐功能
- 健檢組合功能
- 健檢項目功能

### 健康報告 <br>
- 報告詳情功能

### 統計分析 <br>
- 會員數統計
- 套餐占比
- 營運數據

### 系統配置 <br>
- 用戶管理
- 角色管理
- 權限管理
- 菜單管理

### 其他　<br>
- 修改密碼
- 個人資訊
- 登入登出
- 聯繫我門

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

### 用戶資料畫面
![用戶資料畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/27b8422a-6141-4398-b00d-796ab36aba86)

### 聯繫我們畫面
![聯繫我們](https://github.com/yaiiow159/Health_backendProject/assets/39752246/f74aa46a-7d45-4318-9d6a-2c1c4c173416)

### 會員管理畫面
![會員管理查詢](https://github.com/yaiiow159/Health_backendProject/assets/39752246/36061e72-a096-4c0b-babe-edc460d63150)
![會員管理](https://github.com/yaiiow159/Health_backendProject/assets/39752246/cf330b15-6758-4a2e-b87f-43b002157b47)
![編輯會員成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/dac10656-24a4-421b-9200-e135da29d51f)
![會員刪除功能](https://github.com/yaiiow159/Health_backendProject/assets/39752246/c262abcd-cf70-4e39-a3db-83ccc831b6d2)
![新增會員成功畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/a7046365-4922-4a11-9a3a-2e3fa8b468b9)

### 預約列表畫面
![預約列表畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/97d73b77-76e6-4ace-9fcd-79b9096ea63e)
![新增預約畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/fa848878-0b0f-4760-b500-a12a9abb4d26)
![新增預約畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/9c2d6ad7-425e-4da8-9618-da3b8be36604)

### 預約設置畫面
![模板下載](https://github.com/yaiiow159/Health_backendProject/assets/39752246/fe187219-b76e-42d2-b719-2aab10c3656a)
![預約設置成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/5ddb70b9-be1c-4c38-ba11-1fd5d3885ffb)

健檢項目畫面
![檢查項目新增成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/e36935c8-e05d-488d-9b2a-65c43619b2b8)
![健檢項目編輯成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/9f2a66f4-dc16-41a2-acf2-0572e80a1be9)
![健檢項目管理頁面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/427cafbe-f9fb-4c31-a4ce-bb023be6d542)
![刪除檢查項成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/facd0b67-0828-4fd2-bfaa-ddc5e69f20be)

### 健檢組合功能
![新增健檢組合](https://github.com/yaiiow159/Health_backendProject/assets/39752246/ac264432-3bb0-4200-919d-c6dc6f882f69)
![組合新增成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/28acbbd5-695c-46dc-9b2a-5189b3081205)
![健檢項目編輯成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/4b22de66-f6b9-46f9-8e0a-6153d1cb15ba)
![健檢項目新增成功查詢](https://github.com/yaiiow159/Health_backendProject/assets/39752246/250f950c-5e72-41dd-9682-861dc28f48c1)
![健檢組合新增成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/76d32dfc-487b-448e-a700-ff6f6532753f)
![健檢組合查詢成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/49290294-bdd6-4912-b33b-ae6bcdfb1012)

### 健檢套餐功能
![新增健檢套餐成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/8d6cde49-ee13-45c6-ac50-16056adbb795)
![健檢組合查詢成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/b04e508f-4d8c-4506-aabc-e89cad45aa35)
![健檢套餐管理](https://github.com/yaiiow159/Health_backendProject/assets/39752246/f0f7646c-1ab5-4593-8bc5-875c8ad84aee)
![新增健檢套餐](https://github.com/yaiiow159/Health_backendProject/assets/39752246/2ca54e4d-5522-4c0f-be6f-d2f38fdebec4)

### 會員數量分析
![會員數統計分析](https://github.com/yaiiow159/Health_backendProject/assets/39752246/7c9609f1-0b5c-4136-ba77-670130a6ddb5)

### 套餐占比分析
![套餐占比分析](https://github.com/yaiiow159/Health_backendProject/assets/39752246/c5f21388-3d78-46af-9c07-d9c3446eefc0)

### 運營狀況數據
![運營數據分析2](https://github.com/yaiiow159/Health_backendProject/assets/39752246/a3706a04-f124-4647-b9f5-702ce24ce5bc)
![運營數據分析](https://github.com/yaiiow159/Health_backendProject/assets/39752246/3e6c71bd-3e7d-4151-aad3-75bcadda9355)


### 用戶管理
![用戶資料畫面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/90c26463-3e79-43e9-8637-3f0b9d169791)
![用戶管理頁面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/ad8104bc-b618-4f9c-aa23-c3531f1b5244)

### 會員管理
![刪除會員](https://github.com/yaiiow159/Health_backendProject/assets/39752246/f7f5dcf2-7f29-46b1-9c91-b82f32c81b0b)
![新增會員](https://github.com/yaiiow159/Health_backendProject/assets/39752246/60663332-b6ac-4ca5-b49a-74342bd67397)
![新增會員成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/587d1f41-36f7-40da-8090-554d97e14997)
![會員管理頁面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/f5a9dc92-7e99-492d-a1a5-98d9dc87214a)
![編輯會員成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/71132ad2-c0e5-46c4-9a47-41c64e57090e)
![會員刪除功能](https://github.com/yaiiow159/Health_backendProject/assets/39752246/ac3c1c53-e05a-4b5f-b4f0-9b55255cb8d1)

### 角色管理
![角色管理新增](https://github.com/yaiiow159/Health_backendProject/assets/39752246/fa2c9acd-dbf3-45ab-844d-3901ee0796b3)
![角色管理頁面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/e8c6a481-b481-4a97-a8ab-5f97bbf824bf)

### 權限管理
![權限管理頁面](https://github.com/yaiiow159/Health_backendProject/assets/39752246/11d959aa-230a-442b-a3d4-dd5428ee003b)
![權限新增成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/448bea27-b385-4175-96ba-9b4391b2454f)
![權限編輯成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/39d830cd-f5b9-49db-8fd5-ad7aadc7f20e)
![權限刪除成功](https://github.com/yaiiow159/Health_backendProject/assets/39752246/8fab2588-cf65-4c54-9b24-b7df6a26667f)









