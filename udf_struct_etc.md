# udf
pyspark构造udf可以加decorator或用udf方法

```python
from pyspark.sql.functions import col, udf
from pyspark.sql.types import StringType

# udf1和udf2等效
udf1 = udf(lambda x: str(x) + '乌拉', StringType())

@udf(returnType=StringType())
def udf2(x):
    return str(x) + '乌拉'

# 注册udf后直接在spark sql中用
from pyspark.conf import SparkConf
from pyspark.sql import SparkSession 

spark: SparkSession = SparkSession.builder.config(conf=SparkConf().setExecutorEnv('', ''))\
    .appName("JimParker") \
    .enableHiveSupport() \
    .getOrCreate()

spark.udf.register('udf1', udf1)

# 以下三种方法等效
# 效率上,第三种方法最好,因为是spark udf,不需要spark 引擎和JVM的Serde
df1 = spark.sql("select udf1(name) as russian_name, name from name_tbl")
df = spark.sql("select name from name_tbl")
df2 = df.select(udf1(col("name")).alias("russian_name"), "age")
df3 = df.selectExpr("concat(name, '乌拉') as russian_name", "name")
```


# 组合多字段
可以通过StructType来组合多个字段，这样在collect_list的时候也能保证各个字段的顺序一致

```python
from pyspark.sql.functions import col, collect_list, explode, udf
from pyspark.sql.types import StructType, StructField, StringType

struct = StructType([
    StructField('country', StringType(), False),
    StructField('capital', StringType(), False)
])

@udf(returnType=struct)
def struct_udf(country, capital):
    return (country, capital)

df = df.withColumn('struct', struct_udf('country', 'capital'))
gdf = df.groupBy('continent').agg(
    collect_list('struct').alias('structs')
)

df2 = gdf.select(
    'continent', 
    explode(gdf.structs).alias('struct')
)
df3 = df2.select(
    'continent', 
    col('struct.country'), 
    col('struct.capital')
)
```