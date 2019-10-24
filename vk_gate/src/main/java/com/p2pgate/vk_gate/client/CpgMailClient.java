package com.p2pgate.vk_gate.client;

import com.p2pgate.lib.domain.PayAccept;
import com.p2pgate.vk_gate.rest.exception.PayApiException;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CpgMailClient {

    private final String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/77.0.3865.90 Safari/537.36";


    public String getRefillState (String url) throws IOException, InterruptedException, PayApiException{

        String status = "", body = "";
        Connection.Response response;

        final Connection connection = Jsoup
                .connect(url)
                .userAgent(userAgent)
                .method(Connection.Method.POST)
                .ignoreContentType(true);

        do {

            Thread.sleep(850);
            response = connection.execute();

            if (response.statusCode() == 200) {

                body = response.body();
                status = getJsonValue(body, "status");

            } else {

                throw  new PayApiException("0", 500, "URL : " + url + " access ERROR.");
            }
        } while (!(status.equals("OK_FINISH") || status.equals("ERR_FINISH")));

        return body;
    }

    public String sendRefillConfirm(String url, String MD, String PaRes) throws IOException, PayApiException{

        Connection.Response response = Jsoup.connect(url)
                .data("MD",MD )
                .data("PaRes", PaRes)
                .userAgent(userAgent)
                .ignoreContentType(true)
                .followRedirects(true)
                .method(Connection.Method.POST)
                .execute();

        if (response.statusCode() == 200) {

            return getUrl(response.body());

        }
        throw  new PayApiException(url, 500, " Can't send 3DSec refill confirm.");
    }

    /* Prepare data req and request authorisation URI */
    public String getSessionUri(final PayAccept request, final Map<String, String> secValue) throws IOException,
            PayApiException {

        Connection.Response response = Jsoup
                .connect("https://cpg.money.mail.ru/api/in/order/pay")
                .data("session_id",secValue.get("session_id") )
                .data("signature", secValue.get("signature"))
                .data("pan", request.getFromCard())
                .data("cvv", request.getCvv())
                .data("cardholder","VK WALLET")
                .data("exp_date", request.getExpDate().replaceAll("(\\d{2})(\\d{2})", "$1.20$2"))
                .data("add_card","False")
                .header("Origin", "https://cpg.money.mail.ru")
                .userAgent(userAgent)
                .referrer("https://cpg.money.mail.ru/api/page/freepay/?language=ru&session_id="
                        +secValue.get("session_id") +"&signature="+secValue.get("signature"))
                .ignoreContentType(true)
                .followRedirects(true)
                .method(Connection.Method.POST)
                .execute();

        if (response.statusCode() == 200) {

            return getJsonValue(response.body(), "url");

        }
        throw  new PayApiException(request.getPayid(), 500, " Can't get 3DSec URI .");
    }


    // get 3DSecure Req values
    public String get3DSReq(String session_id, String signature, String url) throws IOException,
            InterruptedException, PayApiException {

        String status = "", body = "";
        Connection.Response response;
        final Connection connection = Jsoup
                .connect(url)
                .header("Origin", "https://cpg.money.mail.ru")
                .userAgent(userAgent)
                .referrer("https://cpg.money.mail.ru/api/page/freepay/?language=ru&session_id="
                        +session_id +"&signature="+signature)
                .method(Connection.Method.POST)
                .ignoreContentType(true);

        do {

            Thread.sleep(850);
            response = connection.execute();

            if (response.statusCode() == 200) {

                body = response.body();
                status = getJsonValue(body, "transaction_status");

            } else {

                throw  new PayApiException("0", 500, "URL : " + url + " access ERROR.");
            }

            if (status.equals("AUTH_FRAUD")){
                break;
            }

        } while (!status.equals("3DS_ENROLLED"));

        if (status.equals("3DS_ENROLLED")) {
            return body;
        }

        throw  new PayApiException("0", 500, " Can't get 3DSec values.");
    }

    private String getJsonValue(String json, String key){
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString(key);
    }

    private String getUrl(String response){

        final Document doc = Jsoup.parse(response);
        Elements scriptTags = doc.getElementsByTag("script");
        for (Element tag : scriptTags){
            for (DataNode node : tag.dataNodes()) {
                if(node.getWholeData().contains("check_url")){

                    return StringEscapeUtils.unescapeJava(node.getWholeData()
                            .replaceAll(".*\\(\'(.*)\'\\)\\.(.*\\r?\\n)*","$1"))
                            .replaceAll("&amp;", "&");
                }
            }
        }
        return null;
    }
}
