DROP DATABASE laptopshoponline;

CREATE DATABASE laptopshoponline;

USE laptopshoponline;

-- Tạo Table
CREATE TABLE accounts(
	account_id int primary key auto_increment,
	username varchar(50) not null unique,
	passwords varchar(200),
	firstname varchar(50) charset utf8mb4 not null,
	lastname varchar(50) charset utf8mb4 not null,
    address varchar(150),
	gmail varchar(50) not null,
	phonenumber varchar(50),
	activation_code varchar(50),
	passwordreset_code varchar(50),
	active_account int,
	provider varchar(50),
	roles VARCHAR(10)
);

CREATE TABLE news
(
    news_id int primary key auto_increment,
    account_id int not null,
    title varchar(200) charset utf8mb4,
    content varchar(5000) charset utf8mb4,
    image_link varchar(500),
    created date,
    isdeleted int,
    CONSTRAINT PRO_ADMIN_ID_FK FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE supplier
(
    supplier_id int primary key auto_increment,
    supplier_name varchar(50) charset utf8mb4 not null,
    image_link VARCHAR(500),
    isdeleted int
);

CREATE TABLE product
(
    product_id int primary key auto_increment,
    product_name  nvarchar(50) not null,
    supplier_id int not null,
    quantity int not null,
    image varchar(500),
    unitprice INT not null,
    discount varchar(10),
    description varchar(500) charset utf8mb4,
    isdeleted int,
    CONSTRAINT PRO_SUPPLIER_ID_FK FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE CASCADE
);

CREATE TABLE cart
(
	cart_id int primary key auto_increment,
    account_id int,
    product_id int,
    cart_product_quantity INT,
    isdeleted int,
    CONSTRAINT CART_PRODUCT_ID_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
    CONSTRAINT CART_CUSTOMER_ID_FK FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    order_id int primary key auto_increment,
    account_id int not null,
    order_date date not null,
    receipt_date date,
    total_amount int,
    shipping int,
    address varchar(50) charset utf8mb4 not null,
    customer_note varchar(50) charset utf8mb4,
    status int,
    CONSTRAINT ORDER_ACCOUNT_ID_FK FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE orderdetail(
	order_id int not null,
	product_id int not null,
	quantity int not null,
	CONSTRAINT ORDERDETAIL_PK PRIMARY KEY(order_id, product_id),
    CONSTRAINT ORDERDETAIL_ORDER_ID_FK FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT ORDERDETAIL_PRODUCT_ID_FK FOREIGN KEY(product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

CREATE TABLE reviews(
	order_id int,
	product_id int,
	account_id int,
	contents varchar(500) charset utf8mb4,
	rate varchar(10),
    reviews_date date,
    isdeleted int,
	CONSTRAINT REVIEWS_PK PRIMARY KEY(order_id, product_id, account_id),
	CONSTRAINT REVIEWS_ORDER_ID_FK FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
	CONSTRAINT REVIEWS_PRODUCT_ID_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE,
	CONSTRAINT REVIEWS_ACCOUNT_ID_FK FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

-- insert 
-- insert accounts
insert into accounts(username, passwords,firstname,lastname,address,gmail,phonenumber,activation_code,passwordreset_code,active_account,provider,roles) 
values ("vy","$2a$12$Kq19WMhEm3ZcABroPMiVZO2ERyhO0.wttcpHKYUF7JegGd5LV5kgS", "kim","vy","Quảng Ngãi","vy@gmail.com","012121","","",1,1,2);
-- mk 1234
insert into accounts(username, passwords,firstname,lastname,address,gmail,phonenumber,activation_code,passwordreset_code,active_account,provider,roles)
values ("phi","$2a$12$d0ffly5D.xsQSArJruAHsugNn6.0gIbXh/Nmy0EtDY4c9OAPuWoou", "phi","bùi","Quảng Ngãi","doan@gmail.com","0132322121","","",1,1,1);
-- mk 12345
insert into accounts(username, passwords,firstname,lastname,address,gmail,phonenumber,activation_code,passwordreset_code,active_account,provider,roles)
values ("tin","$2a$12$69P4b7LD3p6QCe92VJcuxuymCzVhzwv/hAXtYd1i.1yUUsJZojnB2", "tin","vỹ trung","Quảng Nam","nguyenthanhtu@gmail.com","0326000692","","",1,1,1);
-- mk 123456

-- insert supplier
insert into supplier (supplier_name,image_link,isdeleted)
values ('HP','https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/imagesup/logo-2834458_960_720.jpg?raw=true',0);
insert into supplier (supplier_name,image_link,isdeleted)
values ('DELL','https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/imagesup/Dell-logo.jpg?raw=true',0);
insert into supplier (supplier_name,image_link,isdeleted)
values ('ASUS','https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/imagesup/logo-asus-inkythuatso-2-01-26-09-21-11.jpg?raw=true',0);
insert into supplier (supplier_name,image_link,isdeleted)
values ('ACER','https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/imagesup/logo-acer-inkythuatso-2-01-27-15-49-45.jpg?raw=true',0);
insert into supplier (supplier_name,image_link,isdeleted)
values ('MSI','https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/imagesup/logo-msi-inkythuatso-4-01-27-14-36-47.jpg?raw=true',0);

-- insert product
-- la[top HP
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop HP 14-DQ2055WM 39K15UA', 1, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/hp-14s-fq1080au-r3-4k0z7pa-win11-xy-2.jpg?raw=true', 20000000, '', 'Phủ nhôm bền bỉ, không gian trải nghiệm đủ đầy</br>Hiệu năng lấp đầy nhu cầu người dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop HP 15S-FQ2602TU 4B6D3PA', 1, 5, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/hp-15s-du1108tu-i3-10110u-2z6l7pa-2.jpg?raw=true', 22000000, '', 'Cấu hình ổn định với chip intel thế hệ 11</br>Kích thước màn hình 15,6 inches và độ phân giải Full HD',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop HP 15s fq2662TU i3', 1, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/hp-340s-g7-i3-240q4pa-1-org.jpg?raw=true', 20000000, '', 'Laptop HP 15s fq2662TU i3 (6K795PA) sẽ là sự lựa chọn phù hợp cho học sinh, sinh viên muốn tìm kiếm một chiếc laptop học tập - văn phòng sở hữu cấu hình ổn định, đáp ứng tốt mọi nhu cầu cơ bản cùng một mức giá lý tưởng, dễ dàng tiếp cận</br>Hiệu năng lấp đầy nhu cầu người dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop HP VICTUS 16 d0292TX', 1, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/hp-340s-g7-i3-240q4pa-2-1-org.jpg?raw=true', 20000000, '', 'Laptop HP VICTUS 16 d0292TX i5 (5Z9R3PA) sẽ là một người bạn đồng hành đắc lực trên mọi chiến trường ảo của game thủ nhờ ngoại hình sang trọng, hiện đại cùng những thông số kỹ thuật mạnh mẽ.</br>Hiệu năng lấp đầy nhu cầu người dùng',0);

-- laptop DELL
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Dell Inspiron 3501 5580BLK', 2, 15, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/dell-gaming-g15-5511-i7-p105f006agr-01.jpg?raw=true', 15000000, '', 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('LAPTOP DELL INSPIRON 3505 i3505-A542BLK', 2, 5, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/dell-gaming-g15-5511-i7-p105f006agr-2-1.jpg?raw=true', 18000000, '', 'Thiết kế mỏng nhẹ, màn hình kích thước lớn với viền siêu mỏng</br>Hiệu năng ổn định trong tầm giá với con chip AMD Ryzen 5 và SSD PCIE',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Dell Gaming G15 5515 R5', 2, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/dell-gaming-g15-5515-r5-p105f004dgr-4.jpg?raw=true', 20000000, '', 'Bộ hiệu năng gây ấn tượng đến từ con chip AMD mạnh mẽ cùng thiết kế cá tính, nổi bật, laptop Dell Gaming G15 5515 R5 (P105F004DGR) sẽ đáp ứng tối ưu mọi nhu cầu từ các tác vụ văn phòng cơ bản đến những ứng dụng đồ họa, chơi game giải trí chuyên nghiệp.</br>Thiết kế đậm chất riêng, đầy ấn tượng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Dell Inspiron 15 3511', 2, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/dell-vostro-3510-i5-p112f002bbl-2-2.jpg?raw=true', 20000000, '', 'Laptop Dell Inspiron 15 3511 i3 (P112F001CBL) sở hữu thiết kế sang trọng, thanh lịch với sức mạnh hiệu năng đến từ dòng chip Intel thế hệ thứ 11 đáp ứng tốt các tác vụ học tập, văn phòng và giải trí cơ bản của người dùng học sinh, sinh viên.</br>Hiệu năng lấp đầy nhu cầu người dùng',0);
-- laptop ASUS
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop ASUS VivoBook 15 A515EA', 3, 15, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/asus-vivobook-x515ea-i3-bq1415w-2-2.jpg?raw=true', 20000000, '', 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop ASUS VivoBook Flip TM420IA-EC031T', 3, 25, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/asus-vivobook-x515ea-i3-bq1415w-1-1.jpg?raw=true', 18000000, '', 'Thiết kế mỏng nhẹ, bản lề quay 360 ấn tượng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Asus TUF Gaming FX506LHB', 3, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/asus-tuf-gaming-fx506hm-i7-hn366w-1.jpg?raw=true', 20000000, '', 'Laptop Asus TUF Gaming được bao bọc bởi lớp vỏ nhựa màu đen huyền bí, khối lượng 2.3 kg cho phép bạn chiến game ở mọi không gian</br>Hiệu năng lấp đầy nhu cầu người dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Asus VivoBook 15X OLED A1503ZA ', 3, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/asus-tuf-gaming-fx507z-i7-hn123w-1-1.jpg?raw=true', 20000000, '', 'Laptop Asus VivoBook 15X OLED A1503ZA i5 (L1290W) là phiên bản laptop cao cấp - sang trọng không chỉ giải quyết triệt để các tác vụ học tập, văn phòng nhờ sức mạnh bùng nổ đến từ bộ vi xử lý Intel thế hệ 12 mà còn mang đến không gian giải trí trọn vẹn khi sở hữu những thông số màn hình ấn tượng. </br>Hiệu năng lấp đầy nhu cầu người dùng',0);

-- laptop ACER
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Acer Aspire 3 A315-56-37DV', 4, 25, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/acer-aspire-3-a315-57-379k-i3-nxkagsv001-3-1.jpg?raw=true', 18000000, '', 'Thiết kế mỏng nhẹ, cứng cáp</br>Cấu hình ổn định với chip Intel Core i3-1005G1',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Acer Aspire 5 A514-54-540F', 4, 5, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/acer-aspire-a315-56-32tp-i3-nxhs5sv00k-1.jpg?raw=true', 23000000, '', 'Thiết kế mỏng nhẹ, cứng cáp</br>Cấu hình ổn định',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Acer TravelMate B3 TMB311 31 C2HB N4020', 4, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/acer-aspire-a315-56-32tp-i3-nxhs5sv00k-xy-2%20(1).jpg?raw=true', 20000000, '', 'Laptop Acer TravelMate B3 TMB311 31 C2HB (NX.VNFSV.006) nhắm đến phân khúc laptop học tập - văn phòng, phục vụ cho các nhu cầu cơ bản trên laptop, với kích thước nhỏ gọn và hiệu năng đủ dùng, là sự lựa chọn tốt cho học sinh, sinh viên.</br>Hiệu năng lấp đầy nhu cầu người dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop Acer Nitro 5 Gaming AN515 45 R6EV R5 5600H', 4, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/acer-nitro-5-gaming-an515-45-r86d-r7-nhqbcsv005-xy-2.jpg?raw=true', 20000000, '', 'Phá cách với diện mạo mạnh mẽ đến từ laptop Acer Nitro 5 Gaming AN515 45 R6EV R5 5600H (NH.QBMSV.006) mang đến cho người dùng hiệu năng ổn định, hỗ trợ bạn trong mọi tác vụ hằng ngày hay chiến những trận game cực căng một cách mượt mà.</br>Kiểu dáng nổi bật, thu hút mọi ánh nhìn',0);

-- laptpo MSI
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop MSI Gaming BRAVO 15 A4DCR-270VN', 5, 5, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/msi-gaming-gf65-thin-10ue-i5-286vn-1-2.jpg?raw=true', 23000000, '', 'Màn hình 15.6" hỗ trợ công nghệ Freesynce, tần số quét màn hình 144Hz</br>Viền màn hình siêu mỏng, thiết kế vỏ nhôm cao cấp',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop MSI Gaming GL75 Leopard 10SCSK 056VN', 5, 5, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/msi-gaming-alpha-15-b5eek-r7-218vn-1-1.jpg?raw=true', 23000000, '', 'Thiết kế nhỏ gọn, màn hình đến 17.3 inches</br>Cấu hình mạnh mẽ với Core i5-10200H, 8GB RAM, 512GB SSD, VGA GTX 1650 Ti',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop MSI Gaming GF63 Thin 11UC i5 11400H', 5, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/msi-gaming-leopard-gp76-11ug-i7-823vn-1.jpg?raw=true', 20000000, '', 'Laptop MSI Gaming GF63 Thin 11UC (445VN) khơi nguồn mọi cảm hứng cho game thủ bởi ngoại hình hầm hố, năng động cùng những thông số kỹ thuật ấn tượng. </br>Hiệu năng lấp đầy nhu cầu người dùng',0);
insert into product (product_name,supplier_id,quantity,image,unitprice,discount,description,isdeleted)
values ('Laptop MSI Modern 14 B11MOU i7 1195G7', 5, 10, 'https://github.com/PhiBuiYH/Project_TieuLuanChuyenNganh/blob/master/IMAGE/msi-creator-z16p-b12ugst-i7-050vn-a-01.jpg?raw=true', 20000000, '', 'MSI Modern 14 B11MOU i7 (847VN) là một chiếc laptop học tập - văn phòng ở mức giá tầm trung nhưng sở hữu sức mạnh hiệu năng vượt trội đến từ con chip Intel thế hệ 11 hiện đại cùng vẻ ngoài sang trọng, cao cấp, hứa hẹn đáp ứng tốt cho công việc cũng như giải trí hoàn hảo.</br>Hiệu năng lấp đầy nhu cầu người dùng',0);

-- insert orders-orderdetail
insert into orders(account_id,order_date, receipt_date,total_amount,shipping,address,customer_note,status)
values (1,'2021-10-5','2021-10-10','38000000',0,'Đồng Nai','Đúng hàng giúp mình^_^',4);
insert into orderdetail(order_id,product_id,quantity)
values (1,5,1);
insert into orderdetail(order_id,product_id,quantity)
values (1,6,1);

insert into orders(account_id,order_date, receipt_date,total_amount,shipping,address,customer_note,status)
values (2,'2021-11-15','2021-11-18','41000000',0,'Quảng Ngãi','Lấy màu đen nha shop',4);
insert into orderdetail(order_id,product_id,quantity)
values (2,7,1);
insert into orderdetail(order_id,product_id,quantity)
values (2,9,1);


-- insert news
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, 'Dell XPS 17 - cấu hình mạnh, giá 80 triệu đồng', '<p>Dell XPS 17 (mã 9720) ra mắt trên toàn cầu vào tháng 6 và mới có mặt tại Việt Nam giữa tháng 9. So với hai bản tiền nhiệm, máy không khác nhiều về thiết kế với kiểu dáng vuông vắn, vỏ nhôm nguyên khối và logo Dell kim loại nằm giữa. Dù màn hình 17 inch, XPS 17 có kích cỡ tổng thể không chênh lệch nhiều so với các mẫu 16 inch trên thị trường nhờ viền mỏng.</p><p>Máy nặng 2,5 kg, cao hơn một số mẫu laptop 16-17 inch cho văn phòng khác như MacBook Pro M1 2021 16 inch 2,2 kg, Asus Vivobook Pro 16X-M7600QE 16,1 inch 1,8 kg, hay Lenovo ThinkBook 16p G2 ACH 16 inch 2 kg. Tuy nhiên, máy nhẹ hơn một chút so với các mẫu chuyên game 17 inch cùng kiểu dáng như MSI Katana GF76 11UC 2,6 kg hay Asus ROG Strix G713RM 2,9 kg.</p><p>Với số đo 1,9 mm, máy khá dày so với các mẫu laptop cùng kích thước. Tuy vậy, XPS 17 vẫn tạo cảm giác mỏng nhờ thiết kế vát theo hình chiếc nêm với phần mỏng hướng về phía trước. Phần viền mạ crôm anodized giúp bảo vệ bề mặt khỏi trầy xước, cũng như tạo cảm giác mạnh mẽ, cứng cáp.</p><p>Tương tự các model tiền nhiệm, sản phẩm không có nhiều cổng kết nối. Mỗi bên cạnh trái và phải chứa hai cổng USB Type C hỗ trợ Thunderbolt 4. Riêng cạnh phải thêm khe cắm thẻ nhớ SD và giắc cắm tai nghe 3,5 mm. Hệ thống tản nhiệt của máy chủ yếu được đặt ở mặt sau, kết hợp tản nhiệt qua bề mặt nhôm.</p><p>Điểm nhấn của XPS 17 là cấu hình. Sản phẩm được trang bị chip Core i7 thế hệ 12, GPU GeForce RTX 3060 6 GB GDDR6, RAM tối đa 32 GB công nghệ DDR5 mới nhất, bộ nhớ trong SSD 1 TB. Thông số này tương đương MSI Creator 17 B11UG hay HP Zbook Fury 17 G7 26F43AV nhưng sản phẩm của Dell đắt hơn đối thủ.</p><p>Về hiệu năng, bài đo với Geekbench 5 đa lõi cho thấy XPS 17 đạt 13.241 điểm, cao hơn MacBook Pro 16 inch (12.627 điểm) và Asus Zenbook Pro 16X OLED (12.476 điểm). Với Cinebench R23 đa lõi, laptop của Dell cũng hơn hai model trên với điểm lần lượt 16.152, 12.365 và 14.958. Dù vậy, ở phép đo 3DMark Wild Life Extreme, XPS 17 thua Zenbook Pro 16X OLED với 12.892 điểm và 12.615 điểm tương ứng.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/09/21/DSC03182-1663693788.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=n5AzXTMq2GyyOJCDWxQokA"></p>', 'https://i1-sohoa.vnecdn.net/2022/09/20/DSC03225-1663687720.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=Atpt7ZgC5rNdL9LyXcGsFg','2022-12-28',0);
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, 'Asus ra Zenbook Pro 16X OLED giá 80 triệu đồng', '<p>Mẫu laptop vừa được Asus bán tại thị trường Việt Nam có thiết kế AAS Ultra độc đáo, trong đó bàn phím tự động nâng lên khi mở máy. Sản phẩm hướng tới người sáng tạo nội dung. Với mức giá 80 triệu đồng, máy cạnh tranh với MacBook Pro 16 inch, Dell XPS 17 hay ConceptD 7 Ezel.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/09/30/DSC5786-1664554576.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=u5wuxE6Fga-dca_b27sWOQ"></p><p>Thiết kế AAS Ultra nâng bàn phím lên 14,5 mm, đồng thời tăng khả năng làm mát nhờ lưu lượng gió nhiều hơn 30%, giảm nhiệt độ bề mặt máy khoảng 7 độ C và tăng hiệu năng so với thế hệ trước. Ngoài ra, bàn phím nâng lên cũng giúp tạo âm thanh nổi tốt hơn.</p><p>Tuy nhiên, nhược điểm của cơ chế này là máy sẽ hút nhiều bụi và khó vệ sinh hơn so với thiết kế truyền thống.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/09/30/DSC5766-1664554525.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=51-PzPbqMMCYhwkM_8-94g"></p><p>Zenbook Pro 16X OLED được trang bị màn hình OLED cảm ứng có kích thước 16 inch, độ phân giải 4K cùng tần số quét 60 Hz, sử dụng công nghệ HDR NanoEdge. Màn hình có tốc độ phản hồi nhanh 0,2 ms, độ bao phủ màu 100% DCP-P3, chứng nhận Dolby Vision và Pantone về độ chính xác màu. Độ sáng màn hình là 550 nit.</p><p>Trong khi các laptop chạy Windows đều hỗ trợ màn hình cảm ứng, đối thủ đến từ Apple vẫn sử dụng màn hình truyền thống.</p><p>Bàn phím có đèn nền RGB cho hiệu ứng chuyển màu đẹp mắt, hành trình phím dài, kết hợp việc bàn phím đặt góc nghiêng cho cảm giác gõ tốt và dễ chịu hơn, hạn chế mỏi cổ tay khi sử dụng.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/10/01/DSC5757-2-1664557508.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=O5PQ6UQUvBqDcw3XKgI5qA"></p><p>Touchpad có kích thước 150 x 90 mm gần gấp đôi so với thế hệ trước, sử dụng công nghệ thiết bị truyền động cộng hưởng tuyến tính (LRA) giúp phản hồi xúc giác chính xác. Asus cũng tích hợp cảm biến áp suất ở dưới touchpad, giúp máy có tốc độ nhận diện và phản hổi không có độ trễ.</p><p>Điểm độc đáo của sản phẩm là Asus Dial mỏng 3,2 mm với bề mặt phủ kính. Asus Dial cho phép điều chỉnh nhiều thông số như độ sáng, âm lượng, hỗ trợ các phần mềm chỉnh sửa hình ảnh, video... Chức năng của các phần mềm có thể được điều chỉnh để sử dụng trong các ứng dụng khác nhau.</p>', 'https://i1-sohoa.vnecdn.net/2022/09/30/DSC5770-1664554519.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=aOJPtr1PDX28frNBF1zL2A','2022-12-28',0);
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, '5 laptop thay thế MacBook Air M2', '<p><strong>Macbook Air M2 ( từ 33 triệu đồng)</strong></p><p>MacBook Air M2 đang thu hút sự chú ý của người dùng Việt sau khi sản phẩm&nbsp;xuát hiện&nbsp;trên thị trường xách tay tuần trước. Sản phẩm được làm bằng nhôm nguyên khối với thiết kế mới, vuông vắn và đồng nhất tương tự MacBook Pro, nặng 1,22 kg và mỏng 11 mm. Máy sử dụng màn hình 13,6 inch độ phân giải Retina, chip M2 và cấu hình cao nhất là RAM 16 GB, bộ nhớ 512 GB, sạc nhanh 67 W.</p><p>Máy dự kiến được bán chính hãng đầu tháng 8 với giá từ 33 triệu đồng. Trong tầm giá và cấu hình này, người dùng còn có nhiều lựa chọn laptop chạy Windows khác.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/07/21/DSCF1599-1656390087-1658375823.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=M_EpzehiIbOlGtX-x0EiaQ"></p><p><strong>Asus Zenbook 14x OLED Space Edition (từ 33 triệu đồng)</strong></p><p>Zenbook 14x OLED Space Edition có vỏ làm bằng nhôm được sơn giả tintanium. Máy có họa tiết mã morse và màn hình OLED nhỏ 3,5 inch ở bên ngoài. Bên trong là màn hình OLED 14 inch, độ phân giải 2,8K.</p><p>Model này mỏng 15,9 mm, nặng gần 1,4 kg. Máy đa dạng cấu hình với tùy chọn cao nhất là chip Core i7-12700H, RAM LPDDR5 16 GB, SSD 1 TB. Thời lượng pin cũng là nâng cấp đáng kể trên Zenbook 14x OLED khi dung lượng tăng lên 75 Wh. Theo công bố nhà sản xuất, pin có thể dùng tới 18 tiếng, chờ 48 tiếng.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/07/21/lg-gram-14z90n-konstrukcja-tyl-1658375840.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=yh3PpXHvGc7EW5KX2_H94A"></p><p><strong>LG Gram 14Z90P (44 triệu đồng)</strong></p><p>Mẫu laptop nhẹ nhất thị trường của LG nổi bật với thân máy làm bằng hợp kim magie và chỉ nặng 999 gram.</p><p>Máy đạt chuẩn độ bền quân đội Mỹ là MIL-STD-810G (G là chuẩn mới nhất của bộ quy chuẩn MIL-STD-810). LG vẫn sử dụng màn hình IPS cho sản phẩm với tính năng chống lóa và độ phân giải 1.920 x 1.200 pixel. Thiết bị tích hợp chip xử lý Core i7-1165G7, RAM 16 GB và ổ cứng SSD 512 GB. Viên pin 72 Wh cho thời gian sử dụng liên tục tới 18,5 tiếng.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/07/21/5806448-dell-xps-13-plus-1658375842.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=UyEyhRMnu0goQ65WV5Se3g"></p><p><strong>Dell XPS 13 Plus (từ 38 triệu đồng)</strong></p><p>Phiên bản cao cấp của XPS 13 có thiết kế tối giản với vỏ máy bằng nhôm và phần tựa tay bằng kính đẹp mắt. Máy nặng 1,24 kg và dày 15,28 mm. Dell trang bị cho sàn phẩm màn hình 13,4 inch với nhiều độ phân giải khác nhau từ FHD đến 4K và có thêm tuỳ chọn màn hình cảm ứng.</p><p>Tất cả các phiên bản đều sử dụng thế hệ chip Core i thứ 12, trong đó bản cấu hình cao nhất là Core i7, SSD 1 TB và RAM LPDDR5 16GB. Trackpad của máy được làm phẳng với phần lót tay sử dụng công nghệ Haptic ForcePad tạo cảm giác rung khi ấn xuống giống máy Mac. Thời lượng pin của máy khoảng 14 tiếng.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/07/21/Lenovo-Yoga-Slim-7-Carbon-1658375841.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=tGw-YDXsTXb_wlNLVTIrvw"></p><p><strong>Lenovo Yoga Slim 7 Carbon (32,4 triệu đồng)</strong></p><p>Slim 7 Carbon là một trong những laptop sử dụng màn hình OLED nhẹ nhất với 1,1 kg. Máy sử dụng vật liệu chính là sợi carbon và hợp kim magie để tăng độ bền.</p><p>Sản phẩm có màn hình 14 inch độ phân giải QHD. Thời lượng pin của model này lên tới 14,5 tiếng, kèm sạc nhanh với 15 phút sạc cho ba tiếng sử dụng. Sản phẩm tích hợp chip xử lý Ryzen 7 5800U cùng card đồ họa Radeon. Người dùng cũng có thể chọn phiên bản sử dụng card GeForce MX450, RAM 16 GB, bộ nhớ 1 TB.</p>','2022-12-28',0);
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, 'Loạt laptop 14 inch nổi bật tầm giá 20 triệu đồng', '<p><strong>Huawei Matebook 14 (21,49 triệu đồng)</strong></p><p>Laptop của Huawei được bằng kim loại nguyên khối màu xám cùng thiết kế đơn giản và chỉ nặng 1,49 kg. Máy sử dụng tấm nền IPS LCD với độ phân giải 2K viền siêu mỏng, tỷ lệ màn hình chiếm 90%. Sản phẩm hỗ trợ đầy đủ các cổng kết nối, gồm cổng USB C để sạc, truyền dữ liệu và xuất hình ảnh, hai cổng USB A, một cổng HDMI và một jack 3,5 mm.</p><p>Về cấu hình, Matebook 14 dùng chip Core i5 thế hệ thứ 11, RAM 8 GB và ổ cứng SSD 512 GB. Máy cài sẵn Windows Home 10 và nâng cấp miễn phí lên Windows 11. Pin 56 Wh hỗ trợ sạc nhanh 65 W.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/03/11/Lenovo-ThinkBook-14-VnE8-1593156-1646937617.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=ZLkE35oXpF_kTmvfHvOREg"></p><p><strong>Lenovo ThinkBook 14 G2 (20,39 triệu đồng)</strong></p><p>Laptop của Lenovo có thiết kế vuông vắn với vỏ ngoài bằng kim loại, đạt chuẩn độ bền quân đội MIL-STD-810H giúp chống va đập tốt. Máy nặng 1,4 kg.</p><p>Màn hình IPS có độ phân giải FHD với tỷ lệ 16:10. Cấu hình máy tương tự các đối thủ trong phân khúc với chip xử lý Core i5 thế hệ 11, RAM 8 GB và ổ SSD 512 GB. Sản phẩm được trang bị hai cổng USB C, hai cổng USB A, LAN, HDMI, jack 3.5 mm và đầu đọc thẻ SD. Máy chạy Windows Home 10 và có pin 45 Wh.</p><p><img src="https://i1-sohoa.vnecdn.net/2022/03/11/Asus-VivoBook-Pro-OLED-1-1646937623.jpg?w=1200&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=zPofS0GeZPf4_bfsGY9dIw"></p><p><strong>Asus VivoBook Pro OLED (21,99 triệu đồng)</strong></p><p>Mẫu laptop văn phòng của Asus có khung viền nhựa, mặt lưng bằng kim loại và cũng nặng 1,4 kg.</p><p>Asus là hãng đầu tiên sử dụng màn hình OLED cho laptop ở tầm giá 20 triệu đồng. Màn hình máy có độ phân giải 2,8K, tỷ lệ 16:10. VivoBook Pro có một cổng USB C, 2 cổng USB A, HDMI, jack cắm 3.5, khe cắm thẻ microSD và cổng sạc riêng. Về phần cứng, máy dùng chip AMD Ryzen7 5800H, RAM 8 GB và bộ nhớ trong 512 GB. Pin dung lượng 63 Wh và chạy Windows Home 11.</p>','2022-12-28',0);
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, 'Laptop rục rịch tăng giá', '<p>Nhiều mẫu laptop tại Việt Nam khan hàng, tăng giá từ 1 đến 3 triệu đồng mỗi máy từ tháng 4, do nguồn cung linh kiện thiếu hụt.</p><p>Tại một số cửa hàng máy tính, laptop có dấu hiệu tăng giá từ đầu năm. Đại diện một đơn vị kinh doanh thiết bị di động tại Hà Nội cho biết, từ tháng 2, hãng&nbsp;<a href="https://vnexpress.net/chu-de/dell-3304" rel="noopener noreferrer" target="_blank">Dell</a>&nbsp;đã áp dụng giá mới trên một số mẫu máy, tăng khoảng 10 đến 100 USD so với trước đó. Máy tính của&nbsp;<a href="https://vnexpress.net/chu-de/hp-3303" rel="noopener noreferrer" target="_blank">HP</a>&nbsp;cũng tăng giá với các sản phẩm dòng Pavilion chạy chip thế hệ mới. Đến tháng 4, nhiều hãng laptop khác cũng đưa ra bảng giá mới. Điển hình như&nbsp;<a href="https://vnexpress.net/chu-de/acer-inc-3301" rel="noopener noreferrer" target="_blank">Acer</a>, hãng có doanh số thuộc top đầu tại Việt Nam, tăng 5 đến 10% giá hầu hết sản phẩm.</p><p>Các mẫu máy như Nitro 5 AN515-55-5923, AN515-56-51N4 từng có giá 22 triệu đồng, được điều chỉnh lên 24 triệu đồng (tăng 9%), một số mẫu cao cấp hơn như AN515-57-5831/755P/7161, có giá từ 33 đến 46 triệu đồng, cũng đồng loạt tăng thêm 1 triệu đồng (tăng 2-3%). Một số sản phẩm chuẩn bị ra mắt cũng phải tăng giá niêm yết dự kiến.</p><p>Với các hãng khác, "việc tăng giá sẽ áp dụng cho các lô hàng mới. Những sản phẩm đã nhập về kho từ trước vẫn chưa bị điều chỉnh", Đức Tiến, đại diện cửa hàng máy tính An Phát tại Thái Hà (Hà Nội) chia sẻ.</p><p><img src="https://i1-sohoa.vnecdn.net/2021/04/02/laptop-tanggia-6732-1617382690.jpg?w=680&amp;h=0&amp;q=100&amp;dpr=1&amp;fit=crop&amp;s=DJr_D2rr7vtv_q1QMVYTiA"></p><p><br></p><p><br></p><p>Tuy nhiên, ông Tiến cho rằng nếu người dùng có ý định mua laptop, nên tiến hành sớm, bởi hiện tại "cung đang không đáp ứng được cầu" nên sẽ giao động giá trong thời gian tới khi nguồn hàng trong kho ít dần đi.</p><p><br></p><p>Ông Nguyễn Lạc Huy, đại diện CellphoneS, cho biết một số mẫu laptop có thể khan hàng trong vài tháng tới. Bên cạnh việc thiếu linh kiện, ông Huy cũng cho rằng, hàng hóa đang được ưu tiên cho thị trường châu Âu và châu Mỹ, nơi dịch bệnh vẫn diễn biến phức tạp và xu hướng học, làm việc online diễn ra cao. "Các nhà máy sản xuất ở Trung Quốc đều đang quá tải, lượng sản phẩm sản xuất không đủ cung ứng cho các đơn đặt hàng. Từ nay đến đầu tháng 5, sẽ có rất ít laptop được nhập khẩu thêm về Việt Nam", ông Huy cho biết.</p><p>Việc laptop tăng giá đã được dự báo từ trước. Hồi cuối tháng 3, trang&nbsp;<em>Gizchina</em>&nbsp;đã dự báo hai hãng laptop Acer và&nbsp;<a href="https://vnexpress.net/chu-de/asustek-computer-inc-3300" rel="noopener noreferrer" target="_blank">Asus</a>&nbsp;sẽ điều chỉnh giá từ 5 đến 10%, đồng thời cho rằng đây là đợt tăng giá đáng kể nhất của thị trường laptop 10 năm trở lại đây. Trang này dẫn lời một số nguồn tin trong ngành cho rằng dịch bệnh khiến nhu cầu sử dụng các sản phẩm công nghệ tăng, nguồn cung chip không đủ đáp ứng, dẫn đến tăng giá chip và kéo theo là mức giá của các sản phẩm đầu cuối như laptop cũng tăng theo.</p><p><em>Gizchina</em>&nbsp;dẫn lời Chen Junsheng - Chủ tịch của Acer, cho biết hãng hiện chỉ có thể đáp ứng được khoảng một phần ba số đơn hàng. Trong khi đó, Asus cũng có chênh lệch cung - cầu khoảng 25 đến 30%.</p><p>Toàn cầu đang đứng trước cuộc&nbsp;<a href="https://vnexpress.net/cuoc-khung-hoang-chip-toan-cau-4255480.html" rel="noopener noreferrer" target="_blank">khủng hoảng về chip bán dẫn</a>. Theo thống kê của&nbsp;<em>Susquehanna</em>, thời gian giao hàng - tính từ khi đặt mua đến khi chip thực sự được giao - tăng lên trung bình 15 tuần. Còn theo công ty sản xuất bán dẫn&nbsp;<em>Broadcom</em>, thời gian giao hàng của hãng đã tăng từ 12,2 tuần vào tháng 2/2020 lên 22,2 tuần năm nay.</p><p>Bên cạnh laptop, việc mua linh kiện máy tính, đặc biệt là card đồ họa ngày càng trở nên khó khăn. Nhiều mẫu&nbsp;<a href="https://vnexpress.net/card-do-hoa-chay-hang-vi-con-sot-tien-dien-tu-4232222.html" rel="noopener noreferrer" target="_blank">card đồ họa tăng giá</a>&nbsp;hàng chục triệu đồng, thậm chí card đồ họa đã qua sử dụng vẫn có thể&nbsp;<a href="https://vnexpress.net/card-do-hoa-cu-gia-cao-hon-hang-moi-vi-bitcoin-4238550.html" rel="noopener noreferrer" target="_blank">bán được giá cao hơn giá mua</a>&nbsp;ban đầu, do bị gom hàng để đào tiền điện tử. Thời điểm đầu năm, nhiều cửa hàng laptop ghi nhận&nbsp;<a href="https://vnexpress.net/cua-hang-may-tinh-kiem-bon-nho-hoc-online-4237001.html" rel="noopener noreferrer" target="_blank">doanh số tăng 600%</a>.</p>','2022-12-28',0);
insert into news(account_id,title,content,image_link,created,isdeleted) values (3, 'Dell tung ưu đãi dịp năm mới', '<p>Dell triển khai chương trình "Rung rinh mai vàng - Hàng nghìn quà tặng" từ ngày 12-22/1 cho khách hàng với tổng giá trị quà tặng lên đến 2 tỷ đồng.</p><p>Theo đó, khách hàng mua laptop hoặc máy tính để bàn Dell chính hãng có cơ hội trúng iPhone 12 Promax 256GB, màn hình, chỉ vàng SJC cùng nhiều quà tặng khác. Chương trình áp dụng cho các dòng máy XPS, G-Series, Inspiron, Vostro model 5.000 và 7.000 có sử dụng bộ vi xử lý Intel Core i3, i5 hoặc i7 tại các đại lý ủy quyền Dell trên toàn quốc.</p><p>Cụ thể, người mua máy tính Dell sẽ được tham gia rút thăm trúng nhiều giải thưởng. Cơ cấu giải thưởng gồm hai giải nhất mỗi giải một điện thoại iPhone 12 Promax 256GB, 5 giải nhì mỗi giải một màn hình Dell S2721HGF 27 inch, 15 giải ba mỗi giải một chỉ vàng miếng SJC. Chương trình còn có 15 giải tư màn hình Dell 24 inch S2419HGF, 20 giải năm là bộ chuyển đổi Dell DA200, 30 giải 6 là voucher ăn tối buffet tại nhà hàng The Log - Gem Center hoặc chuỗi nhà hàng khách sạn Golden Gate. Cuối cùng là hàng nghìn giải bảy gồm phiếu mua hàng điện tử Bách Hóa Xanh, VinID hoặc thẻ điện thoại điện tử trị giá 300.000 đồng. Để tham gia chương trình, khách hàng đăng nhập vào&nbsp;<a href="http://www.doiquadell.com/" rel="noopener noreferrer" target="_blank">website</a>, nhập đủ thông tin theo yêu cầu gồm họ tên, số điện thoại, giấy tờ tùy thân, số service tag... để nhận mã số dự thưởng.</p><p><br></p><p>Khách hàng có thể tìm hiểu thông tin, chọn lựa sản phẩm trực tiếp và chính hãng trên trang thương mại điện tử&nbsp;<a href="https://dellonline.vn/" rel="noopener noreferrer" target="_blank">Dell Online</a>. Website giới thiệu loạt sản phẩm Dell chính hãng từ các dòng máy laptop, desktop, màn hình, máy AiO, phụ kiện, phục vụ đa dạng nhu cầu từ doanh nghiệp, giới sáng tạo, học sinh sinh viên cho đến nhu cầu giải trí, chơi game sôi động.</p><p>"Trên kênh online, chúng tôi triển khai dịch vụ gửi máy đến tận tay người mua trong khu vực nội thành, tối đa 4 ngày cho vùng ngoại thành sau khi xác nhận đơn hàng", đại diện Dell cho biết.</p><p>Bên cạnh đó, Dell áp dụng dịch vụ bảo hành tận nơi Pro-Support dành cho laptop Vostro và Premium Support cho các dòng Inspiron, XPS và Gaming, hỗ trợ 24/7 kể cả ngày lễ bởi đội ngũ kỹ thuật của Dell. Dell còn cử kỹ sư kèm theo linh kiện thay thế đến tận nhà khách hàng để bảo hành sau khi chẩn đoán lỗi từ xa (Onsite break fix service). Pro Support và Premium-Support còn liên kết với tất cả trung tâm bảo hành của Dell trên toàn quốc, giúp khắc phục sự cố, giảm thiểu sự bất tiện của người dùng, đảm bảo tính bảo mật cho khách hàng cá nhân.</p>','2022-12-28',0);
