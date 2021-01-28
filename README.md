# Range_management_module

## 介绍

创新实践-比赛场地管理系统｜后端部分

本系统可以实现 比赛场地 的新建、修改、删除操作、查询操作

## 开发工具

 Intelij IDEA：优秀的Java后端开发IDE环境

 Java语言：优秀的面向对象的程序语言

 SpringBoot：易上手、轻量级组件容器框架。一切皆组件

 MyBatis：Java的ORM工具，帮助我们将对象持久化到数据库

 SQL：数据库查询语言

 MySQL：易学习的中小型数据库。

## IDEA插件

![image-20210128163904552](https://gitee.com/sun-roc/picture/raw/master/img/image-20210128163904552.png)

## 数据库设计

场地表 <range>

| 字段名称       | 数据类型     | 样例             | 说明                 |
| -------------- | ------------ | ---------------- | -------------------- |
| id             | int(11)      |                  | 主键                 |
| range_code     | varchar(20)  | A109             | 场地编码，手填，唯一 |
| range_name     | varchar(100) | 第3桥牌室        | 射击次数             |
| range_location | varchar(100) | N7 - 523         | 出局人数             |
| status         | Int(11)      | 1: open 2: close |                      |
| close_reason   | varchar(100) |                  | 关闭原因             |
| description    | varchar(255) | 这是一条备注     | 备注                 |
| created_at     | datetime     |                  | 创建时间             |
| updated_at     | datetime     |                  | 更新时间             |
| created_by     | varchar(50)  |                  | 创建人               |
| updated_by     | varchar(50)  |                  | 更新人               |

 

比赛项目表 <competition_event>

| 字段名称               | 数据类型    | 样例                              | 说明                               |
| ---------------------- | ----------- | --------------------------------- | ---------------------------------- |
| id                     | int(11)     |                                   | 主键                               |
| competition_event_code | varchar(20) | CE0230101                         | *比赛项目编码，自动生成*，全局唯一 |
| competition_event_name | varchar(20) | 男子100米接力                     | 比赛项目名称                       |
| suite_type             | int         | 1: 成年组  2: 青少年组  3：老年组 | 组别                               |
| range_code             | varchar(20) |                                   | 场地编码                           |
| plan_start_at          | Date        | 2020-05-10                        | 计划开始日期                       |
| plan_end_at            | Date        | 2020-05-15                        | 计划结束日期                       |
| status                 |             | 1: 未开始 2：进行中 3：已结束     | 状态                               |
| created_at             | datetime    |                                   | 创建时间                           |
| updated_at             | datetime    |                                   | 更新时间                           |
| created_by             | varchar(50) |                                   | 创建人                             |
| updated_by             | varchar(50) |                                   | 更新人                             |

## 接口设计

###  获取场地详情

接口地址：range/get

请求方法: GET

**入参**

| 参数名    | 参数描述 | 数据类型 | 必填 | 示例/备注 |
| --------- | -------- | -------- | ---- | --------- |
| rangeCode | 场地编码 | String   | O    |           |

**出参：**

| 参数名        | 参数描述 | 数据类型 | 示例/备注 |
| ------------- | -------- | -------- | --------- |
| data          | 场地详情 | Object   |           |
| rangeCode     | 场地编码 | String   |           |
| rangeName     | 场地名称 | String   |           |
| rangeLocation | 位置     | String   |           |
| status        | 状态编码 | number   |           |
| statusDesc    | 状态描述 | String   |           |
| closeReason   | 关闭原因 | String   |           |
| description   | 备注     | String   |           |
| createdAt     | 创建时间 | String   |           |
| createdBy     | 创建人   | String   |           |
| updatedAt     | 更新时间 | String   |           |
| updatedBy     | 更新人   | String   |           |

 

接口描述：根据检索条件查询*场地表*（range），返回结果记录

 

### 获取场地列表（分页）

接口地址：range/list

请求方法: GET

**入参**

| 参数名        | 参数描述 | 数据类型 | 必填 | 示例/备注 |
| ------------- | -------- | -------- | ---- | --------- |
| rangeName     | 场地名称 | String   | O    |           |
| rangeLocation | 位置     | String   | O    |           |
| status        | 状态     | number   | O    | 下拉列表  |

**出参：**

| 参数名        | 参数描述 | 数据类型       | 示例/备注 |
| ------------- | -------- | -------------- | --------- |
| data          | 场地列表 | List of Object |           |
| rangeCode     | 场地编码 | String         |           |
| rangeName     | 场地名称 | String         |           |
| rangeLocation | 位置     | String         |           |
| status        | 状态编码 | number         |           |
| statusDesc    | 状态描述 | String         |           |
| updatedAt     | 更新时间 | String         |           |

接口描述：根据检索条件查询*场地表*（range），返回结果记录列表



### 新建场地

接口地址：range/add

请求方法：POST

**入参**

| 参数名        | 参数描述 | 数据类型 | 必填 | 示例/备注  |
| ------------- | -------- | -------- | ---- | ---------- |
| rangeName     | 场地名称 | String   | M    |            |
| rangeLocation | 位置     | String   | M    |            |
| status        | 状态编码 | number   | M    |            |
| closeReason   | 关闭原因 | String   | M/O  | 关闭时必填 |
| description   | 备注     | String   | O    |            |

**出参：**

| 参数名 | 参数描述 | 数据类型 | 必填 | 示例/备注    |
| ------ | -------- | -------- | ---- | ------------ |
| data   | 场地编码 | String   | M    | LA1902039190 |

接口描述：

1、 校验场地名称的唯一性，若存在报错“场地名称已经存在”

2、 创建场地记录，场地编码自动生成，将输入的相应字段填入

 

###  修改场地

接口地址：range/update

请求方法：POST

**入参****:**

| 参数名        | 参数描述 | 数据类型 | 必填 | 示例/备注  |
| ------------- | -------- | -------- | ---- | ---------- |
| rangeCode     | 场地编码 | String   | M    |            |
| rangeName     | 场地名称 | String   | M    |            |
| rangeLocation | 位置     | String   | M    |            |
| status        | 状态编码 | number   | M    |            |
| closeReason   | 关闭原因 | String   | M/O  | 关闭时必填 |
| description   | 备注     | String   | O    |            |

**出参：**

| 参数名 | 参数描述 | 数据类型 | 必填 | 示例/备注    |
| ------ | -------- | -------- | ---- | ------------ |
| data   | 场地编码 | String   | M    | LA1902039190 |

接口描述：

1、 根据场地编码查询场地记录，若不存在则报错

2、 校验场地名称的唯一性，若存在报错“场地名称已经存在”

3、 创建场地记录，场地编码自动生成，将输入的相应字段填入

 

### 删除场地

接口地址：range/delete

请求方法：POST

**入参**

| 参数名     | 参数描述 | 数据类型        | 必填 | 示例/备注 |
| ---------- | -------- | --------------- | ---- | --------- |
| rangeCodes | 场地代码 | Array of String | M    |           |

**出参：**

| 参数名 | 参数描述   | 数据类型 | 必填 | 示例/备注    |
| ------ | ---------- | -------- | ---- | ------------ |
| data   | 删除记录数 | number   | M    | LA1902039190 |

接口描述：

1、 根据场地编码查询competition_event表中status!=3的记录，如果有记录，则报错“该场地已经被比赛使用，不能删除”

2、 根据场地代码，删除场地记录信息



## 数据库文件

数据库文件(labs.sql)放在了后端项目的根目录下上传了,
包含两个表,range和competition_event,其中competition_event写的很简单,值包含了不能被删除的场地信息

## 安装

1. 使用maven 自动导入
2. 修改数据库配置文件

## 详细设计