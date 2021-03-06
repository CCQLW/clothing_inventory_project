```sql
-- CREATE DATABASE clothing_inventory;
-- use clothing_inventory

CREATE TABLE GRN
(
    id INT auto_increment COMMENT '序号',
    detail_id INT COMMENT '入库明细序号',
    receipt_number VARCHAR(16) NOT NULL COMMENT '单据编号',
    warehouse VARCHAR(50) NOT NULL COMMENT '所入仓库',
    storage_time DATE NOT NULL COMMENT '入库时间',
    agent VARCHAR(50) NOT NULL COMMENT '经办人',
    source VARCHAR(50) NOT NULL COMMENT '来源',
    put_delete BINARY NOT NULL COMMENT '是否删除',
    PRIMARY KEY (id),
    FOREIGN KEY (detail_id) REFERENCES warehousing_detail(id)
);

CREATE TABLE article_number
(
    id INT auto_increment comment '序号',
    article_number VARCHAR(50) NOT NULL COMMENT '货号',
    trade_name VARCHAR(50) NOT NULL COMMENT '商品名',
    color_no VARCHAR(50) NOT NULL COMMENT '色号',
    size INT NOT NULL COMMENT '尺码',
    put_delete BINARY NOT NULL COMMENT '是否删除',
    PRIMARY KEY (id)
);

CREATE TABLE warehousing_detail
(
    id INT auto_increment comment '序号',
    grn_id INT COMMENT '入库单序号',
    article_id INT COMMENT '货号序号',
    article_number VARCHAR(50) NOT NULL COMMENT '货号',
    trade_name VARCHAR(50) NOT NULL COMMENT '商品名',
    color_no VARCHAR(50) NOT NULL COMMENT '色号',
    size INT NOT NULL COMMENT '尺码',
    number INT NOT NULL COMMENT '数量',
    put_delete BINARY NOT NULL COMMENT '是否删除',
    PRIMARY KEY (id),
    FOREIGN KEY (article_id) REFERENCES article_number(id),
    FOREIGN KEY (grn_id) REFERENCES grn(id)
);
```



```sql
CREATE TABLE USER (
	id INT UNIQUE auto_increment COMMENT "编号",
	username VARCHAR (45) COMMENT "用户名",
	passwd VARCHAR (45) COMMENT "密码",
	authority INT DEFAULT '-1' COMMENT "权限",
	is_delete INT DEFAULT '0' COMMENT "删除"
);

CREATE TABLE delivery_order (
	id INT auto_increment COMMENT '序号',
	receipt_number VARCHAR (50)  COMMENT '单据编号',
	warehouse VARCHAR (50)  COMMENT '所出仓库',
	storage_time DATE  COMMENT '出库时间',
	agent VARCHAR (50)  COMMENT '经办人',
	whereabouts VARCHAR (50)  COMMENT '去处',
	is_delete INT DEFAULT '0' COMMENT '是否删除',
	PRIMARY KEY (id)
);

CREATE TABLE delivery_details (
	id INT auto_increment COMMENT '序号',
	order_id INT COMMENT '出库订单序号',
	article_id INT COMMENT '货号序号',
	trade_name VARCHAR (50) COMMENT '商品名',
	color_no VARCHAR (50) COMMENT '色号',
	size INT COMMENT '尺码',
	number INT  COMMENT '数量',
	is_delete INT DEFAULT '0' COMMENT '是否删除',
	PRIMARY KEY (id),
	FOREIGN KEY (order_id) REFERENCES delivery_order (id)
	FOREIGN KEY (article_id) REFERENCES article_number(id),
);

```
