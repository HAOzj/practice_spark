# 储存地址
spark储存地址分为
- 本地,在driver上,最好用绝对路径
- hdfs集群,路径前面是hdfs的前缀,
    1. hdfs://IP:PORT/,测试机器的端口号为8020
    2. 线上用的阿里环境为hdfs://emr-header/

# 设置driver
运行spark时的deploy-mode
- client时,driver为执行的机器.  
- cluster时,driver为集群中任意一台机器.

