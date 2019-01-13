shutdown immediate;
startup mount;
recover automatic from '/u01/osp51/talldata/archive' standby database;
auto
alter database open;
select * from stud.tbl2;
exit;
