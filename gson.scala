import com.google.gson.{JsonObject, JsonParser}


def parseJsonStr(str: String) = {
  val json = new JsonParser()
  val obj = json.parse(str).asInstanceOf[JsonObject]
  obj
}


val df = ...

df.flatMap { jsonStr =>
        val recs = mutable.ArrayBuffer[(String, String, String)]()
        val obj = parseJsonStr(jsonStr)
        val sessionId = if (obj.has("ssid")) obj.get("ssid").getAsString else ""  // getAsDouble.toSting会用科学记数法

        if (region.nonEmpty & uid.nonEmpty & sessionId.nonEmpty & obj.has("res")) {
          val res = obj.get("res").getAsJsonArray.iterator()  // 读取list of json string
          while (res.hasNext) {
            val ele = res.next().getAsJsonObject  // 不同getAsString,会因为序列化报错
            val strategy = if (ele.has("recall_strategy")) ele.get("recall_strategy").getAsString else ""
            if (strategy.nonEmpty) {
              val rec = (sessionId, strategy)
              recs += rec
            }
          }
        }
        recs
    }
