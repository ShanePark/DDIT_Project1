--------------------------------------------------------
--  파일이 생성됨 - 목요일-12월-24-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table USER_PICK
--------------------------------------------------------

  CREATE TABLE "DDIT"."USER_PICK" 
   (	"RES_NAME" VARCHAR2(50 BYTE), 
	"USER_ID" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into DDIT.USER_PICK
SET DEFINE OFF;
Insert into DDIT.USER_PICK (RES_NAME,USER_ID) values ('스시생각','user');
Insert into DDIT.USER_PICK (RES_NAME,USER_ID) values ('괴산올갱이','user');
Insert into DDIT.USER_PICK (RES_NAME,USER_ID) values ('맘스터치','user');
Insert into DDIT.USER_PICK (RES_NAME,USER_ID) values ('맥도날드 대전 센트럴DT점','user');
