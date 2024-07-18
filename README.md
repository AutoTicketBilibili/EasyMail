# EasyMail
一个简单的邮件API

> 需要java环境/参考java8 <br>
> 直接run.bat运行即可


怎么使用配置文件写的很清楚了

代码参考如下/Python

```
data = {
      "token": token,
      "mailSenderName": "发送者名字",
      "mailTitle": "邮件标题",
      "mailContent": "邮件内容"
}
url = "http://127.0.0.1:7654/API"
sessionTemp = requests.Session()
sessionTemp.post(url, headers=BasicInfo.headers, json=data, timeout=10)
