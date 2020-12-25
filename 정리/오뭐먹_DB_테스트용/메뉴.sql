--------------------------------------------------------
--  파일이 생성됨 - 토요일-12월-26-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table MENU
--------------------------------------------------------

  CREATE TABLE "DDIT"."MENU" 
   (	"RES_ID" NUMBER(30,0), 
	"FOOD" VARCHAR2(50 BYTE), 
	"PRICE" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into DDIT.MENU
SET DEFINE OFF;
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'생연어 사시미',13000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'참치사시미',15000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'반반사시미',14500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'생연어초밥',11500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'참치초밥',13500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'모둠초밥',14000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'소고기숙주볶음',6500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'돼지고기 숙주볶음',7500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'타코야끼',4000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'치킨가라아게',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'버팔로 윙',5500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (1,'모둠 치킨',5500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'맛감자10개',1000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'빨간참치김밥',2500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'치즈떡볶이',3500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'치즈스틱',1500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'떡볶이',2500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'스팸마요',3500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'참치마요',3500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (2,'치킨마요',3500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (3,'올갱이정식',14000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (3,'올갱이해장국',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (3,'올갱이무침',25000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (3,'한방수육',24000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'고기가득리얼비프버거',9500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'내슈빌핫치킨버거',6100);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'언빌리버블버거',7100);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'인크레더블버거',6900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'화이트갈릭버거',6100);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (4,'싸이버거',5800);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (5,'한우소머리곰탕',9000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (5,'한우소머리우거지탕',9000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (5,'특곰탕',12000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (5,'특우거지탕',12000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (5,'유튜버들이즐겨찾는실비김치',12000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'빅맥',4900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'슈슈버거',4900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'더블불고기버거',4900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'1955버거',6900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'맥스파이시상하이버거',5500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'슈비버거',6900);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (6,'크리스피오리엔탈치킨버거',6300);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (7,'오복김밥',4000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (7,'오복김치볶음밥',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (7,'오복고기덮밥',9000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (7,'오복카레',7000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (7,'오복라면',4000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'모둠떡볶이',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'바로김밥(깻잎X)',3000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'등심돈까스',7000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'양푼이비빔밥',6500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'쫄면',6000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (8,'찹쌀순대',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'짜장면',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'소호짬뽕',7000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'쟁반짜장',12000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'잡채밥',6000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'레몬탕수육',15000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'야채볶음밥',6000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (9,'깐풍기',25000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (10,'맛짜장',4000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (10,'고추삼선짜장',6000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (10,'고추삼선짬뽕',7000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (10,'탕수육',12000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (10,'볶음밥',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'전통육개장',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'차돌육개장',9000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'육개장칼국수',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'맑은육개장',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'사골떡만두국',8000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'양지육개장전골',32000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'모둠수육전골',32000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'한판보쌈',10000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'메밀전병',5000);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'만두한접시',3500);
Insert into DDIT.MENU (RES_ID,FOOD,PRICE) values (11,'갈비탕',10000);
--------------------------------------------------------
--  Constraints for Table MENU
--------------------------------------------------------

  ALTER TABLE "DDIT"."MENU" MODIFY ("RES_ID" NOT NULL ENABLE);
