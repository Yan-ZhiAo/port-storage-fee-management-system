# 港口堆存费管理系统

港口堆存费管理系统是一个面向港口仓储与货物出入库业务的前后端分离课程设计项目。系统围绕公司、仓库、入库单、出库单和堆存费核算展开，支持基础资料维护、库存吨数与件数管理、出入库记录查询与编辑，以及出库时按入库时间和免费堆存天数计算堆存费用。

## 项目功能

- 账号登录：前端登录页对接后端账号接口，后端使用 JWT 返回登录态，并通过拦截器校验请求。
- 公司管理：维护公司编号、公司名称、所属港口、负责人、联系电话和堆存费总计。
- 仓库管理：维护仓库编号、剩余吨数、剩余件数、所属港口和所属公司。
- 入库管理：记录入库单号、入库时间、车牌号、入库吨数、入库件数和关联仓库。
- 出库管理：记录出库单号、出库时间、出库吨数、出库件数和关联仓库，并展示堆存费。
- 堆存费计算：出库时关联同一仓库的入库记录，按入库日期排序，结合免费天数和吨/天费率计算费用。
- 数据库约束：MySQL 脚本包含公司、仓库、入库、出库、账号等表结构，并通过外键、逻辑删除、时间戳和触发器维护库存数据。

## 技术栈

| 模块 | 技术 |
| --- | --- |
| 前端 | Vue 2, Vue Router, Vuex, Element UI, Axios, Vue CLI |
| 后端 | Java 17, Spring Boot 3.3.1, Spring Web, MyBatis-Plus, Spring Security Crypto |
| 数据层 | MySQL, Redis, MyBatis XML Mapper |
| 认证 | JWT, BCrypt |
| 工程化 | Maven Wrapper, npm, Dockerfile, docker-compose |

## 项目结构

```text
.
|-- Backend/
|   |-- sun.sql                  # MySQL 建库和示例数据脚本
|   |-- sql.txt                  # 表、触发器和过程设计说明
|   |-- Backend/
|       |-- src/main/java/        # Spring Boot 后端代码
|       |-- src/main/resources/   # application.yml 和 MyBatis Mapper
|       |-- pom.xml
|       |-- Dockerfile
|       |-- docker-compose.yml
|-- Frontend/
|   |-- src/api/                 # 前端接口封装
|   |-- src/views/               # 登录、公司、仓库、入库、出库页面
|   |-- src/router/              # 路由与菜单
|   |-- package.json
|-- README.md
```

## 快速开始

### 1. 初始化数据库

先在本地创建 MySQL 数据库并导入脚本：

```sql
CREATE DATABASE sun DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```

然后导入 `Backend/sun.sql`。项目默认使用数据库名 `sun`。

### 2. 启动后端

```powershell
cd Backend\Backend
$env:DB_URL="jdbc:mysql://localhost:3306/sun?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
$env:REDIS_HOST="localhost"
.\mvnw.cmd spring-boot:run
```

后端默认端口为 `6789`，可以通过 `SERVER_PORT` 环境变量覆盖。

### 3. 启动前端

```powershell
cd Frontend
npm install
npm run dev
```

前端开发服务默认运行在 `http://localhost:8888`，接口地址由 `VUE_APP_BASE_API` 配置。

## 配置说明

后端配置位于 `Backend/Backend/src/main/resources/application.yml`，支持以下环境变量：

| 变量 | 说明 | 默认值 |
| --- | --- | --- |
| `SERVER_PORT` | 后端服务端口 | `6789` |
| `DB_URL` | MySQL JDBC 地址 | `jdbc:mysql://localhost:3306/sun...` |
| `DB_USERNAME` | MySQL 用户名 | `root` |
| `DB_PASSWORD` | MySQL 密码 | 空 |
| `REDIS_HOST` | Redis 主机 | `localhost` |
| `REDIS_PORT` | Redis 端口 | `6379` |

前端可在 `Frontend/.env.development`、`Frontend/.env.production` 中配置 `VUE_APP_BASE_API`。

## 说明

仓库只提交源码、SQL 脚本、构建配置和必要静态资源；`node_modules`、后端 `target`、IDE 配置、日志和本地课程报告文档不会进入版本库。
