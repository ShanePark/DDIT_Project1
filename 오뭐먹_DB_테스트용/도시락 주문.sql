--------------------------------------------------------
--  파일이 생성됨 - 목요일-12월-24-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOX_ORDER
--------------------------------------------------------

  CREATE TABLE "DDIT"."BOX_ORDER" 
   (	"BOX_NAME" VARCHAR2(60 BYTE), 
	"USER_ID" VARCHAR2(20 BYTE), 
	"ORDER_DATE" DATE DEFAULT SYSDATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "DDIT"."BOX_ORDER"."BOX_NAME" IS '도시락가게ID';
   COMMENT ON COLUMN "DDIT"."BOX_ORDER"."USER_ID" IS '유저ID';
   COMMENT ON COLUMN "DDIT"."BOX_ORDER"."ORDER_DATE" IS '주문날짜';
REM INSERTING into DDIT.BOX_ORDER
SET DEFINE OFF;
Insert into DDIT.BOX_ORDER (BOX_NAME,USER_ID,ORDER_DATE) values ('대전도시락','user',to_date('2020/12/24','RRRR/MM/DD'));
Insert into DDIT.BOX_ORDER (BOX_NAME,USER_ID,ORDER_DATE) values ('대전도시락','user2',to_date('2020/12/24','RRRR/MM/DD'));
--------------------------------------------------------
--  Constraints for Table BOX_ORDER
--------------------------------------------------------

  ALTER TABLE "DDIT"."BOX_ORDER" MODIFY ("BOX_NAME" NOT NULL ENABLE);
