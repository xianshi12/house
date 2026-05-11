# 房屋销售管理系统

这是一个基于 Spring Boot、MyBatis Plus、Vue 2、Element UI、MySQL、Redis 的房屋销售管理系统。项目包含前台用户端、后台管理端、开发商端业务能力，覆盖房源发布、房源浏览、看房预约、意向金支付、合同签订、合同回传、管理员审核、最终支付等流程。

## 技术栈

后端：

- Java 8
- Spring Boot 2.2.2.RELEASE
- Spring MVC
- MyBatis 2.1.1
- MyBatis Plus 2.3
- MySQL Connector/J
- Redis / Spring Data Redis / Jedis
- Apache Shiro 1.3.2
- JWT 0.11.5
- Apache POI 3.11
- Maven

前端：

- Vue 2
- Vue Router
- Vuex
- Element UI
- Axios / Vue Resource
- ECharts
- Swiper
- Vue CLI 4

数据库与中间件：

- MySQL，数据库名默认 `db_house`
- Redis，默认地址见 `code/src/main/resources/application.yml`

## 版本要求

- JDK 1.8
- Maven 3.6+
- MySQL 5.7 或 8.x
- Redis 5.x+
- Node.js 14 或 16 推荐
- npm 6+ 推荐

## 项目结构

```text
houseSaleSystem
├── code
│   ├── pom.xml
│   └── src/main
│       ├── java/com
│       │   ├── controller      后端接口
│       │   ├── entity          实体类
│       │   ├── service         业务服务
│       │   ├── dao             数据访问层
│       │   ├── config          系统配置
│       │   └── utils           工具类
│       └── resources
│           ├── mapper          MyBatis XML
│           ├── admin/admin     后台管理端 Vue 项目
│           ├── front/front     前台用户端 Vue 项目
│           └── application.yml 后端配置
├── db_house.sql                初始数据库脚本
├── db_house_v2.sql             更新后的数据库脚本
└── README.md
```

## 主要功能模块

用户端：

- 用户注册、登录、个人中心
- 房源信息浏览、筛选、分页、详情查看
- 房源图片展示、收藏、评论
- 看房预约申请
- 意向下单与意向金支付
- 合同下载、签署后回传
- 合同审核通过后支付
- 售房资讯浏览

开发商端：

- 开发商登录、注册
- 房源信息发布、编辑、删除
- 看房预约查看与审核
- 意向金支付记录查看
- 合同文件上传
- 合同签订记录查看

管理员端：

- 管理员登录
- 用户管理、开发商管理
- 房屋类型管理
- 房源信息管理
- 看房预约审核
- 意向金支付管理
- 合同签订审核
- 投诉建议管理
- 售房资讯分类与资讯管理
- 轮播图管理
- 数据统计与图表展示

## 核心业务流程

1. 开发商发布房源信息，填写房屋名称、类型、图片、价格、面积、位置、出售合同等信息。
2. 用户在前台房源信息页浏览房源，可按名称、楼层、厅室、地段、位置等条件检索，并进入详情页。
3. 用户可发起看房预约，开发商或管理员在后台审核预约记录。
4. 用户可对房源意向下单并支付意向金。
5. 开发商在合同签订模块上传合同文件，供用户下载。
6. 用户下载合同，线下签署后在个人中心合同详情页上传回传合同。
7. 用户点击提交审核，合同状态进入待审核。
8. 管理员审核用户回传合同。
9. 审核通过后，用户端合同详情页显示支付按钮，用户完成最终支付。

## 运行说明

1. 创建 MySQL 数据库：

```sql
CREATE DATABASE db_house DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

2. 导入数据库脚本：

```text
db_house_v2.sql
```

3. 修改后端配置：

```text
code/src/main/resources/application.yml
```

重点检查：

- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`
- `spring.redis.host`
- `spring.redis.port`
- `spring.redis.password`

4. 编译后端：

```bash
cd code
mvn package -DskipTests
```

5. 启动后端：

```bash
java -jar target/code-0.0.1-SNAPSHOT.jar
```

默认服务地址：

```text
http://localhost:8080/house
```

前台地址：

```text
http://localhost:8080/house/front/dist/index.html#/login
```

后台地址：

```text
http://localhost:8080/house/admin/dist/index.html#/login
```

## 前端单独构建

前台：

```bash
cd code/src/main/resources/front/front
npm install
npm run build
```

后台：

```bash
cd code/src/main/resources/admin/admin
npm install
npm run build
```

## 默认账号

管理员：

```text
账号：admin
密码：admin
```

开发商：

```text
账号：zs
密码：123456
```

用户：

```text
账号以数据库导入数据为准
密码：123456
```

## 最近完善内容

- 修复登录 Token 字段长度导致登录失败的问题。
- 修复中英文表名、字段名映射问题。
- 修复房源、房屋类型等列表 SQL 表别名错误。
- 修复 Redis 序列化导致部分列表不显示的问题。
- 修复用户端日期格式提交失败的问题。
- 修复房源信息页图片展示尺寸不一致的问题。
- 修复出售合同文件可下载但无法预览的问题。
- 完善合同签订流程：开发商上传合同、用户回传签署合同、管理员审核、审核通过后用户支付。
- 修复意向下单、合同签订已支付/未支付筛选逻辑。
- 修复房源前台列表缓存导致新发布房源在全部列表不显示的问题。

## 注意事项

- `target`、`node_modules`、前端构建缓存等目录不建议提交到 Git。
- 如果 Redis 地址变化，需要同步修改 `application.yml`。
- 如果前台页面仍显示旧数据，请重启后端并清理浏览器缓存后再访问。
