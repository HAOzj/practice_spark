# introduction  
apache的一种column-oriented database,基于HDFS,适用于大数据的实时读写

# 基本语法  


|  hbase语法   | equivalent in mysql 或作用 |
|  ----  | ----  |
| list  | tables |
| count 't1'  | select count(*) from t1 |
| scan 't1', {LIMIT=>2} | select * from t1 limit 2 |
| get 't1', 'rk',[<f1:col1>,....] | select col from t1 where pk=rk | 
| get 't1', 'rk',{COLUMN=>'f1:col1'} | select col from t1 where pk=rk | 
| put 't1','rk','f1:col1','value01' | 添加数据,必须指定rowkey和family column |
 | deleteall 't1','rk' | delete from t1 where pk='rk' | 
|  delete 't1','rk','f1:col1' | 删除对应行对应列 |
| truncate 't1' | delete from t1 |
| create 't1',{NAME => 'f1', VERSIONS => 2},{NAME => 'f2', VERSIONS => 2} | create table t1 (f1 text)| 
| describe 't1' | desc t1 | 
| disable 't1' (then) drop 't1' | drop t1 |
| alter 'test1',{NAME=>'body',TTL=>'180'},{NAME=>'meta', TTL=>'180'} | 把数据的保存时间修改为180s,默认为forever |