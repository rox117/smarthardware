INSERT INTO product (id,name,price,description,date_added)
values (1,'brush','200','hair care',SYSDATE);

INSERT INTO product (id,name,price,description,date_added)
values (2,'comb','200','hair care',SYSDATE);

INSERT INTO product (id,name,price,description,date_added)
values (3,'brush2','200','hair care',SYSDATE);



INSERT INTO news (id,news_text,news_Date)
values (1,'some news for me',SYSDATE);

INSERT INTO news (id,news_text,news_Date)
values (2,'some news for me',SYSDATE);


INSERT INTO news (id,news_text,news_Date)
values (3,'some news for me',SYSDATE);



INSERT INTO Smart_User (id,user_name,password)
values (1,'customer','userpass');

INSERT INTO Smart_user (id,user_name,password)
values (2,'admin','userpass');


INSERT INTO role (name,description)
values ('ROLE_CUSTOMER','CUSTOMER USER');

INSERT INTO role (name,description)
values ('ROLE_ADMIN','ADMIN USER');

INSERT INTO user_roles (user_id,role_id)
values (1,'ROLE_CUSTOMER');

INSERT INTO user_roles (user_id,role_id)
values (2,'ROLE_ADMIN');