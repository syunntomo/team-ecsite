set names utf8;
set foreign_key_checks=0;

drop database if exists mimosa;
create database if not exists mimosa;

use mimosa;

create table user_info(
	id int primary key not null auto_increment,
	user_id varchar(16) not null unique,
	password varchar(16) not null,
	family_name varchar(32) not null,
	first_name varchar(32) not null,
	family_name_kana varchar(32) not null,
	first_name_kana varchar(32) not null,
	sex tinyint default 0 not null,
	email varchar(32) not null,
	status tinyint default 0 not null,
	logined tinyint default 0 not null,
	regist_date datetime not null,
	update_date datetime default null
	)
default charset=utf8
;

	insert into user_info values
	(1,"guest","guest","インターノウス","ゲストユーザー","いんたーのうす","げすとゆーざー",0,"guest@email.com",0,0,now(),null),
	(2,"guest1","guest1","インターノウス","ゲストユーザー1","いんたーのうす","げすとゆーざー1",0,"guest@email.com",0,0,now(),null),
	(3,"guest2","guest2","インターノウス","ゲストユーザー2","いんたーのうす","げすとゆーざー2",0,"guest@email.com",0,0,now(),null),
	(4,"guest3","guest3","インターノウス","ゲストユーザー3","いんたーのうす","げすとゆーざー3",0,"guest@email.com",0,0,now(),null),
	(5,"guest4","guest4","インターノウス","ゲストユーザー4","いんたーのうす","げすとゆーざー4",0,"guest@email.com",0,0,now(),null),
	(6,"guest5","guest5","インターノウス","ゲストユーザー5","いんたーのうす","げすとゆーざー5",0,"guest@email.com",0,0,now(),null),
	(7,"guest6","guest6","インターノウス","ゲストユーザー6","いんたーのうす","げすとゆーざー6",0,"guest@email.com",0,0,now(),null),
	(8,"guest7","guest7","インターノウス","ゲストユーザー7","いんたーのうす","げすとゆーざー7",0,"guest@email.com",0,0,now(),null),
	(9,"guest8","guest8","インターノウス","ゲストユーザー8","いんたーのうす","げすとゆーざー8",0,"guest@email.com",0,0,now(),null),
	(10,"guest9","guest9","インターノウス","ゲストユーザー9","いんたーのうす","げすとゆーざー9",0,"guest@email.com",0,0,now(),null),
	(11,"guest10","guest10","インターノウス","ゲストユーザー10","いんたーのうす","げすとゆーざー10",0,"guest@email.com",0,0,now(),null),
	(12,"guest11","guest11","インターノウス","ゲストユーザー11","いんたーのうす","げすとゆーざー11",0,"guest@email.com",0,0,now(),null);

create table product_info(
	id int primary key not null auto_increment,
	product_id int not null unique,
	product_name varchar(100) not null unique,
	product_name_kana varchar(100) not null unique,
	product_description varchar(255) not null,
	category_id int not null,
	price int,
	image_file_path varchar(100),
	image_file_name varchar(50),
	release_date datetime not null,
	release_company varchar(50),
	status tinyint default 0 not null,
	regist_date datetime not null,
	update_date datetime default null,
	foreign key(category_id) references m_category(category_id)
	)
	default charset=utf8
	;

	insert into product_info values
	(1,1,"アームシェルチェア","あーむしぇるちぇあ","サイズ幅62cm奥行き62cm高さ82cmのダークブルーカラーです。",2,500,"images","ArmShellChair.jpg",now(),"Rose",0,now(),null),
	(2,2,"アームズチェア","あーむずちぇあ","サイズ幅62cm奥行き62cm高さ82cmのダークブルーカラーです。",2,500,"images","IronChair.jpg",now(),"Mimosa",0,now(),null),
	(3,3,"ハンモック","はんもっく","サイズ幅62cm奥行き62.5cm高さ81cmのイエローです。",2,500,"images","isu.jpg",now(),"Cosmos",0,now(),null),
	(4,4,"デザインチェア","でざいんちぇあ","サイズ幅58.5cm奥行き48cm高さ76.5cmのブラウンカラーです。",2,500,"images","DesignChair.jpg",now(),"Lily",0,now(),null),
	(5,5,"クリスマスクロス","くりすますくろす","テーブルランナーサイズ35×220cm、ランチョンマットサイズ45×65cm、素材は綿と麻を使用しています。セット内容はテーブルランナー1枚。テーブルランナー、食卓マットピアノカバー、靴棚敷物など違和感なく使用可能です。",3,1000,"images","ChristmasCross.jpg",now(),"Rose",0,now(),null),
	(6,6,"北欧風クロス","ほくおうふうくろす","サイズは100cm×140cm、素材は木綿を使用してます。高級感と実用性を兼ね揃えたオススメの逸品です。",3,500,"images","NorthernEuropeanTableCross.jpg",now(),"Mimosa",0,now(),null),
	(7,7,"無地クロス","むじくろす","サイズ130cm×170cm、素材は綿麻風格100％のポリエステルを使用しています。中性洗剤または30度以下の水に洗濯をお勧めます。",3,500,"images","PrainRaceCross.jpg",now(),"Cosmos",0,now(),null),
	(8,8,"シンプルクロス","しんぷるくろす","サイズ120cm×200cm、淡く優しい色味と、シンプルなデザインなので、シーンを問わずにお使いいただけます。",3,500,"images","SimpleCross.jpg",now(),"Lily",0,now(),null),
	(9,9,"ベンジャミン","べんじゃみん","バスケットサイズ直径約20cm高さ約50～60cm、受け皿バンブー・製バスケット・メッセージカード・お手入れの栞が付いてきます。※人工観葉植物ではありません。",4,1000,"images","Benjamin.jpg",now(),"Rose",0,now(),null),
	(10,10,"新ユッカ","しんゆっか","サイズ約高さ120㎝直径50㎝、鉢はプラスチック受け皿付きです。この商品に光触媒液でコーティングしています。",4,750,"images","NewYucca.jpg",now(),"Mimosa",0,now(),null),
	(11,11,"パキラ","ぱきら","サイズ高さ約27cm(花器サイズ7×7×8cm)、植物のため枝ぶりや葉付きに個体差がございます。",4,500,"images","Pachira.jpg",now(),"Cosmos",0,now(),null),
    (12,12,"モンステラ","もんすてら","サイズ高さ約70cm",4,700,"images","Monstera.jpg",now(),"Lily",0,now(),null);

create table cart_info(
	id int primary key not null auto_increment,
	user_id varchar(16) not null,
	temp_user_id varchar(16),
	product_id int not null,
	product_count int not null,
	price int not null,
	regist_date datetime not null,
	update_date datetime default null
	)
	default charset = utf8
	;

create table purchase_history_info(
	id int primary key not null auto_increment,
	user_id varchar(16) not null,
	product_id int not null,
	product_count int not null,
	price int not null,
	destination_id int not null,
	regist_date datetime not null,
	update_date datetime,
	foreign key(user_id) references user_info(user_id),
	foreign key(product_id) references product_info(product_id)
	)
	default charset=utf8
	;

create table destination_info(
	id int primary key not null auto_increment,
	user_id varchar(16) not null,
	family_name varchar(32) not null,
	first_name varchar(32) not null,
	family_name_kana varchar(32) not null,
	first_name_kana varchar(32) not null,
	email varchar(32) not null,
	tel_number varchar(13) not null,
	user_address varchar(50) not null,
	regist_date datetime not null,
	update_date datetime default null
	)
	default charset=utf8
	;

	insert into destination_info values
	(1,"guest","インターノウス","テストユーザー","いんたーのうす","てすとゆーざー","guest@internous.co.jp","090-4516-4721","東京都千代田区三番町1-1",now(),null);

create table m_category(
	id int primary key not null,
	category_id int not null unique,
	category_name varchar(20) not null unique,
	category_description varchar(100),
	insert_date datetime not null,
	update_date datetime default null
	)
	default charset=utf8
	;

	insert into m_category values
	(1,1,"全てのカテゴリー","イス・クロス・観葉植物全てのカテゴリーが対象となります",now(),null),
	(2,2,"チェア","チェアに関するカテゴリーが対象となります",now(),null),
	(3,3,"クロス","クロスに関するカテゴリーが対象となります",now(),null),
	(4,4,"観葉植物","観葉植物に関するカテゴリーが対象となります",now(),null);