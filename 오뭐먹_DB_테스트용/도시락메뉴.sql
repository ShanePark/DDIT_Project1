--------------------------------------------------------
--  파일이 생성됨 - 목요일-12월-24-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BOXMENU
--------------------------------------------------------

  CREATE TABLE "DDIT"."BOXMENU" 
   (	"BOX_NAME" VARCHAR2(60 BYTE), 
	"BOX_MENU" VARCHAR2(300 BYTE), 
	"BOX_DATE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "DDIT"."BOXMENU"."BOX_NAME" IS '도시락가게ID';
   COMMENT ON COLUMN "DDIT"."BOXMENU"."BOX_MENU" IS '도시락가게메뉴';
   COMMENT ON COLUMN "DDIT"."BOXMENU"."BOX_DATE" IS '날짜별';
REM INSERTING into DDIT.BOXMENU
SET DEFINE OFF;
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','탕수육/두부구이/치자단무지무침/유채나물/만둣국',to_date('2020/12/01','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','삼겹살김치볶음/스크램블에그/마늘쫑어묵조림/비엔나케익/된장국',to_date('2020/12/02','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/비엔나햄/무말랭이/고추참치/미역국',to_date('2020/12/03','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈까스/시금치나물/후랑크햄/김무침/어묵국',to_date('2020/12/04','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈육장조림/마늘쫑무침/버섯볶음/야채고로케/두부된장국',to_date('2020/12/07','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/황태무침/숙주나물/콩자반/북엇국',to_date('2020/12/08','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','버섯불고기/어묵야채볶음/해물땡소스/우엉조림/콩나물국',to_date('2020/12/09','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','탕수육/두부구이/치자단무지무침/유채나물/만둣국',to_date('2020/12/10','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','삼겹살김치볶음/스크램블에그/마늘쫑어묵조림/비엔나케익/된장국',to_date('2020/12/11','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/비엔나햄/무말랭이/고추참치/미역국',to_date('2020/12/14','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈까스/시금치나물/후랑크햄/김무침/어묵국',to_date('2020/12/15','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈육장조림/마늘쫑무침/버섯볶음/야채고로케/두부된장국',to_date('2020/12/16','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/황태무침/숙주나물/콩자반/북엇국',to_date('2020/12/17','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','버섯불고기/어묵야채볶음/해물땡소스/우엉조림/콩나물국',to_date('2020/12/18','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','탕수육/두부구이/치자단무지무침/유채나물/만둣국',to_date('2020/12/21','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','삼겹살김치볶음/스크램블에그/마늘쫑어묵조림/비엔나케익/된장국',to_date('2020/12/22','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/비엔나햄/무말랭이/고추참치/미역국',to_date('2020/12/23','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈까스/시금치나물/후랑크햄/김무침/어묵국',to_date('2020/12/24','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','돈육장조림/마늘쫑무침/버섯볶음/야채고로케/두부된장국',to_date('2020/12/28','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','함박스테이크/황태무침/숙주나물/콩자반/북엇국',to_date('2020/12/29','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','버섯불고기/어묵야채볶음/해물땡소스/우엉조림/콩나물국',to_date('2020/12/30','RRRR/MM/DD'));
Insert into DDIT.BOXMENU (BOX_NAME,BOX_MENU,BOX_DATE) values ('대전도시락','생선까스/명엽채조림/콩나물무침/무말랭이/어묵국',to_date('2020/12/31','RRRR/MM/DD'));
--------------------------------------------------------
--  Constraints for Table BOXMENU
--------------------------------------------------------

  ALTER TABLE "DDIT"."BOXMENU" MODIFY ("BOX_DATE" NOT NULL ENABLE);
