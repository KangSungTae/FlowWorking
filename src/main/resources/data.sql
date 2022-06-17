create table extension (
	ext_id varchar(20) not null, 
	check_yn varchar(1), 
	main_yn varchar(1), 
	reg_dtm date, 
	upt_dtm date,
	primary key (ext_id)
);

insert into extension values('bat','N','Y',now(),null);
insert into extension values('cmd','N','Y',now(),null);
insert into extension values('com','N','Y',now(),null);
insert into extension values('cpl','N','Y',now(),null);
insert into extension values('exe','N','Y',now(),null);
insert into extension values('scr','N','Y',now(),null);
insert into extension values('js','N','Y',now(),null);
