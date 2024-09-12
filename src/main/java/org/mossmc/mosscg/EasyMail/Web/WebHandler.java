package org.mossmc.mosscg.EasyMail.Web;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.mossmc.mosscg.EasyMail.BasicInfo;
import org.mossmc.mosscg.EasyMail.Mail.MailSend;

public class WebHandler implements HttpHandler {
    /**
     * 基础请求格式（POST）：
     * {
     *     token: "xxx",
     *     mailTitle: "xxx",
     *     mailSenderName: "xxx",
     *     mailContent: "xxx"
     * }
     * 基础返回格式：
     * {
     *     "status": true
     * }
     */
    public static JSONObject failedData = new JSONObject();
    public static JSONObject successData = new JSONObject();
    @Override
    public void handle(HttpExchange exchange) {
        try {
            WebBasic.initBasicResponse(exchange);
            String ip = WebBasic.getRemoteIP(exchange);
            BasicInfo.logger.sendInfo(ip+" "+exchange.getRequestURI().toString());
            JSONObject data = WebBasic.loadRequestData(exchange);
            BasicInfo.logger.sendInfo(data.toString());
            if (!data.containsKey("token") || !data.containsKey("mailTitle") || !data.containsKey("mailSenderName") || !data.containsKey("mailContent")) {
                WebBasic.completeResponse(exchange,failedData,data);
                return;
            }
            String receiveToken = data.getString("token");
            if (!receiveToken.equals(BasicInfo.config.getString("token"))) {
                WebBasic.completeResponse(exchange,failedData,data);
                return;
            }
            MailSend.sendMail(data.getString("mailTitle"),data.getString("mailContent"),data.getString("mailSenderName"));

            WebBasic.completeResponse(exchange,successData,data);
        } catch (Exception e) {
            BasicInfo.logger.sendException(e);
        }
    }
}
