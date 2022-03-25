CREATE TABLE Event(
     CMPLNT_NUM        NUMBER(9,0) NOT NULL
    ,CMPLNT_FR_DT      DATE
    ,CMPLNT_FR_TM      VARCHAR(8)
    ,CMPLNT_TO_DT      DATE
    ,CMPLNT_TO_TM      VARCHAR(8)
    ,ADDR_PCT_CD       NUMBER(3,0)
    ,RPT_DT            DATE
    ,KY_CD             NUMBER(3,0) NOT NULL
    ,OFNS_DESC         VARCHAR(31)
    ,PD_CD             NUMBER(3,0)
    ,PD_DESC           VARCHAR(44)
    ,CRM_ATPT_CPTD_CD  VARCHAR(9)
    ,LAW_CAT_CD        VARCHAR(11)
    ,BORO_NM           VARCHAR(13)
    ,LOC_OF_OCCUR_DESC VARCHAR(11)
    ,PREM_TYP_DESC     VARCHAR(27)
    ,JURIS_DESC        VARCHAR(19)
    ,JURISDICTION_CODE INTEGER
    ,PARKS_NM          VARCHAR(2)
    ,HADEVELOPT        VARCHAR(12)
    ,HOUSING_PSA       VARCHAR(5)
    ,X_COORD_CD        INTEGER
    ,Y_COORD_CD        INTEGER
    ,SUSP_AGE_GROUP    VARCHAR(7)
    ,SUSP_RACE         VARCHAR(24)
    ,SUSP_SEX          VARCHAR(1)
    ,TRANSIT_DISTRICT  INTEGER
    ,Latitude          NUMERIC(11,9)
    ,Longitude         NUMERIC(20,9)
    ,Lat_Lon           VARCHAR(40)
    ,PATROL_BORO       VARCHAR(25)
    ,STATION_NAME      VARCHAR(10)
    ,VIC_AGE_GROUP     VARCHAR(7)
    ,VIC_RACE          VARCHAR(30)
    ,VIC_SEX           VARCHAR(1)
    ,PRIMARY KEY(CMPLNT_NUM)
);