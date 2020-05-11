package com.itplh.bk.service;

import com.alibaba.fastjson.JSON;
import com.itplh.bk.mapper.HouseInfoMapper;
import com.itplh.bk.model.HouseInfo;
import com.itplh.bk.model.PageData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: tanpeng
 * @date: 2020-05-11 02:34
 * @version: v1.0.0
 */
@Service
public class BkService {

    @Autowired
    private Environment environment;

    @Autowired
    private HouseInfoMapper houseInfoMapper;

    /**
     * 抓取贝壳房源信息
     * @description:
     * @author: tanpeng
     * @date : 2020-05-11 03:09
     * @version: v1.0.0
     */
    public void simpleSpider(String indexUrl, String pageUrlTemplate) throws IOException {
        int totalPage = getPageData(indexUrl).getTotalPage();
        System.out.println("=====总页数: " + totalPage);

        String url;
        for (int i = 1; i <= totalPage; i++) {
            url =  i == 1 ? indexUrl : String.format(pageUrlTemplate, "pg" + i);
            singlePageSpider(url);
        }
    }

    /**
     * 抓取单页房源信息
     * @description:
     * @author: tanpeng
     * @date : 2020-05-11 02:58
     * @version: v1.0.0
     */
    private void singlePageSpider(String url) throws IOException {
        System.out.println("=====url: " + url);
        Document document = Jsoup.connect(url)
                .header("Host", "cq.ke.com")
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.129 Safari/537.36")
                .cookie("Cookie", "lianjia_uuid=007391ea-79a5-4f7f-8349-0ff589fca8bb; select_city=500000; Hm_lvt_9152f8221cb6243a53c83b956842be8a=1589076820; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22171fc5bdc9114e-096d742b97e507-30657c07-1296000-171fc5bdc922c2%22%2C%22%24device_id%22%3A%22171fc5bdc9114e-096d742b97e507-30657c07-1296000-171fc5bdc922c2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; login_ucid=2000000034267776; lianjia_token=2.00303679bb48b7f6d7219b508a37f39442; lianjia_token_secure=2.00303679bb48b7f6d7219b508a37f39442; security_ticket=LHTauvE3vfSq3hJda4yckxuHnD3ED8uFDhtzLnRQxZ2mrUebl3yf+M3O9hH9Eqrl3YNtVxLIm61HuLI7O9Zx3rE3tJS4k94I0k0rFY8g96IO7PHGIymCeOntVyb9hWBWmYuhP9WOppKdj0qFx+vxWPBc8Qk+WiwHTwsFxRUXvOk=; lianjia_ssid=f86e0cb6-aaf5-4cef-a5b8-2131a4394b04; srcid=eyJ0Ijoie1wiZGF0YVwiOlwiMDkxOGY3MWI4M2IwNGI3MzM0NDYxOGExMTI4N2RjNGNjMTJkYmQ5ZmQyNjQwZDI5OTQ4ZDcwNzA1Yjc3ZWRmZjM0OThjNTFlZDYyZDE0NWMxOTBiOWIwODMxNTk3NGIxZTM5MzU1Y2ZiN2I3YWNlNmFhOTlhMTAzMWEyYWE1YjE2OWJjZTU2OTAwYjc2ZDhhNGExYzBlODcxYmZjOWFiY2UzZGE1ZTNkZGI1MGQwMWJmMzBlMzQ5NmRjOTUwM2I1M2ZjMjZhYTVlMWY5NWE3Y2FmMmMzMjU1ZjE0ZTllNTY0NDg4YzNlMzU5ZWYyZjg3NWMxZmExY2EyODQxYzIyY2Q3MzJjMGU0ZDMzYjEzNzQ5NTk2M2I5NTNmZjVjNDViZmVmY2U0OTg2MWIwMzU2YWU2NGFlZGY1MzBjYWZmODk1MDU3N2U4YmZhOTc3YWY3ZWM5YjQ2ZTEwNzhkMTkzOVwiLFwia2V5X2lkXCI6XCIxXCIsXCJzaWduXCI6XCJkNTJmNDljOVwifSIsInIiOiJodHRwczovL2NxLmtlLmNvbS9lcnNob3VmYW5nL2Rhbnppc2hpL2EzYTQvIiwib3MiOiJ3ZWIiLCJ2IjoiMC4xIn0=; Hm_lpvt_9152f8221cb6243a53c83b956842be8a=1589130186")
                .timeout(10000)
                .get();
        Element sellListContentUl = document.getElementsByClass("sellListContent").get(0);
        List<Element> clearLis = sellListContentUl.children().stream()
                .filter(li -> li.hasClass("clear")).collect(Collectors.toList());
        System.out.println("=====本页房源数: " + clearLis.size());
        clearLis.stream().forEach(li -> {
            String maidian = li.child(0).attr("data-maidian");
            Element houseInfoDiv = li.child(1);
            HouseInfo houseInfo = getHouseInfo(maidian, houseInfoDiv);
            System.out.println(houseInfo.toString());
            houseInfoMapper.insert(houseInfo);
        });
    }

    /**
     * 构建房源信息对象
     * @description:
     * @author: tanpeng
     * @date : 2020-05-11 00:18
     * @version: v1.0.0
     */
    private HouseInfo getHouseInfo(String maidian, Element houseInfoDiv) {
        Element titleDiv = houseInfoDiv.child(0);
        Elements goodHouseSpan = titleDiv.getElementsByClass("goodhouse_tag");
        String goodHouse = goodHouseSpan.isEmpty() ? "" : goodHouseSpan.text();
        Elements newUpSpan = titleDiv.getElementsByClass("new");
        String newUp = newUpSpan.isEmpty() ? "" : newUpSpan.text();
        Element titleA = titleDiv.child(0);
        String title = titleA.attr("title");
        String href = titleA.attr("href");

        Element addressDiv = houseInfoDiv.child(1);
        String neighbourhood = addressDiv.child(0).child(0).child(1).html();
        List<String> infoList = Arrays.asList(StringUtils.delimitedListToStringArray(addressDiv.child(1).text(), "|")).stream()
                .map(info -> info.trim()).collect(Collectors.toList());
        String floor = "";
        String buildYear = "";
        String type = "";
        String size = "";
        String faceToward = "";
        if (infoList.size() == 5) {
            floor = infoList.get(0);
            buildYear = infoList.get(1);
            type = infoList.get(2);
            size = infoList.get(3);
            faceToward = infoList.get(4);
        } else if (infoList.size() == 3) {
            String floorAndType = infoList.get(0);
            int bracketsIndex = floorAndType.lastIndexOf(")");
            floor = floorAndType.substring(0, bracketsIndex + 1);
            type = floorAndType.substring(bracketsIndex + 1);
            size = infoList.get(1);
            faceToward = infoList.get(2);
        }

        List<String> followInfo = Arrays.asList(StringUtils.delimitedListToStringArray(addressDiv.child(2).text(), "/")).stream()
                .map(info -> info.trim()).collect(Collectors.toList());
        String focus = followInfo.get(0);
        String publishTime = followInfo.get(1);
        Element priceInfoDiv = addressDiv.child(4);
        String totalPrice = priceInfoDiv.child(0).child(0).text();
        String unitPrice = priceInfoDiv.child(1).attr("data-price");

        HouseInfo houseInfo = HouseInfo.builder()
                .maidian(maidian)
                .title(title)
                .href(href)
                .neighbourhood(neighbourhood)
                .floor(floor)
                .buildYear(buildYear)
                .type(type)
                .size(size)
                .faceToward(faceToward)
                .focus(focus)
                .publishTime(publishTime)
                .totalPrice(totalPrice)
                .unitPrice(unitPrice)
                .goodHouse(goodHouse)
                .newUp(newUp)
                .build();
        return houseInfo;
    }

    /**
     * 获取分页数据
     * dev环境只抓取首页数据
     * @description:
     * @author: tanpeng
     * @date : 2020-05-11 02:26
     * @version: v1.0.0
     */
    private PageData getPageData(String indexUrl) throws IOException {
        boolean isDevEnvironment = Arrays.asList(environment.getActiveProfiles()).stream().anyMatch(env -> "dev".equals(env));
        PageData pageData;
        if (isDevEnvironment) {
            pageData = PageData.builder().curPage(1).totalPage(1).build();
        } else {
            Document document = Jsoup.parse(new URL(indexUrl), 5000);
            String pageDataString = document.getElementsByClass("house-lst-page-box").get(0).attr("page-data");
            pageData = JSON.parseObject(pageDataString, PageData.class);
        }
        return pageData;
    }

}
