package com.itplh.demo4.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 查询我在github中chongqing地区的排名
 *
 * @author: tanpenggood
 * @since: JDK1.8
 */
public class Demo {

    private static HashMap<String, String> headers = new HashMap<>();
    private static Long totalPages;
    private static String location;
    private static boolean stop;
    /**
     * junit测试多线程，需要加countDownLatch，否则只能看到main线程的日志
     */
    private static CountDownLatch countDownLatch;

    static {
        headers.put("Accept-Language", "zh-CN,zh;q=0.9");
        headers.put("Connection", "keep-alive");
        headers.put("Cookie", "_octo=GH1.1.1078431350.1597553477; _ga=GA1.2.794663729.1597553486; _device_id=42a018ebe586fcf588025f3a25baf176; user_session=9xjtxVCnR4xrzb6nYnURdOvPzrGZww9-Zo2BbCG_NKK5TTpF; __Host-user_session_same_site=9xjtxVCnR4xrzb6nYnURdOvPzrGZww9-Zo2BbCG_NKK5TTpF; logged_in=yes; dotcom_user=tanpenggood; has_recent_activity=1; _gat=1; tz=Asia%2FShanghai; _gh_sess=HnHuDpyMPdN8j7i%2BDjoh2fqgFYRzIvdLTRfCjbzwjVC2Bkwm8qda8rEPa4ilerURrbljLWsXSUVPCPXAKh6xYroR9aZctisqLoVRgA908NbGDym36NaRMsimNupbWCu20QKpDFHHU7adSbQ1w0eh1nGJES20zlAFlcPpFqCqrY76Wzbb%2BPIAMWC8eJGiIL1m--GIimUrbITnIkjFQu--uaHFCERmVfQgqXhQ1cPK2A%3D%3D");
        headers.put("Host", "github.com");
        headers.put("If-None-Match", "W/\"83a70ffeef4b4593de8500fec8ca6cbe\"");
        headers.put("Sec-Fetch-Dest", "document");
        headers.put("Sec-Fetch-Mode", "navigate");
        headers.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36");
        totalPages = 0L;
        location = "chongqing";
        stop = false;
    }

    @Test
    public void testMultithreadingSelectUserByPageRange() {
        initTotalPages();
        multithreadingSelectUserByPageRange(68, 70, 1);
    }

    @Test
    public void testSelectUserByPageRange() {
        initTotalPages();
        selectUserByPageRange(68, 70);
    }

    /**
     * 多线程查询页码范围内的User
     *
     * @param start 开始页(包含)
     * @param end   结束页(包含)
     * @param step  步长
     * @author: tanpenggood
     * @date: 2020/8/24 12:45
     */
    private void multithreadingSelectUserByPageRange(long start, long end, long step) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        logPrintln("处理器个数为" + availableProcessors);
        ExecutorService threadPool = Executors.newFixedThreadPool(availableProcessors);
        countDownLatch = new CountDownLatch((int) ((end - start) / step + 1));
        do {
            long startPageNum = start;
            long endPageNum = (start + step - 1) > end ? end : (start + step - 1);
            logPrintln(startPageNum + " " + endPageNum);
            threadPool.execute(() -> selectUserByPageRange(startPageNum, endPageNum));
            start += step;
        } while (start <= end);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }

    /**
     * 查询页码范围内的User
     *
     * @param startPageNum 开始页(包含)
     * @param endPageNum   结束页(包含)
     * @author: tanpenggood
     * @date: 2020/8/24 12:41
     */
    private void selectUserByPageRange(long startPageNum, long endPageNum) {
        endPageNum = endPageNum > totalPages ? totalPages : endPageNum;
        for (long pageNum = startPageNum < 1L ? 1L : startPageNum; pageNum <= endPageNum; pageNum++) {
            selectUserByPage(pageNum);
            if (stop) {
                break;
            }
        }
        countDownLatch.countDown();
    }

    /**
     * 查询指定页的User
     *
     * @author: tanpenggood
     * @date: 2020/8/24 12:41
     */
    private void selectUserByPage(long pageNum) {
        Document document = getDocument(pageNum);
        Optional.ofNullable(document)
                .map(doc -> doc.getElementsByClass("user-list"))
                .map(userListElements -> userListElements.first())
                .map(userListElement -> userListElement.children())
                .ifPresent(userList -> {
                    for (int i = 0; i < userList.size(); i++) {
                        Element user = userList.get(i);
                        String no = (pageNum - 1) * 10 + i + 1 + "";
                        final String[] userInfo = {no, "", "", ""};
                        Optional.ofNullable(user)
                                .map(u -> u.getElementsByClass("mr-1"))
                                .map(nameElements -> nameElements.first())
                                .ifPresent(nameElement -> userInfo[1] = nameElement.text());
                        Optional.ofNullable(user)
                                .map(u -> u.getElementsByClass("text-gray"))
                                .map(usernameElements -> usernameElements.first())
                                .ifPresent(usernameElement -> {
                                    userInfo[2] = usernameElement.text();
                                    userInfo[3] = "https://github.com" + usernameElement.attr("href");
                                });
                        String info = String.format("pageNo.%s\tNo.%s\tname:%s\tusername:%s\t%s",
                                pageNum,
                                userInfo[0],
                                userInfo[1],
                                userInfo[2],
                                userInfo[3]);
                        if (Objects.equals(userInfo[1], "tanpenggood")) {
                            logPrintln("------------------------------------------------------------");
                            logPrintln("------------------------------------------------------------");
                            logPrintln(info);
                            logPrintln("------------------------------------------------------------");
                            logPrintln("------------------------------------------------------------");
                            stop = true;
                        } else {
                            logPrintln(info);
                        }
                    }
                });
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送http请求获取Document
     * 失败自动重试一次
     *
     * @param pageNum 页码
     * @author: tanpenggood
     * @date: 2020/8/24 12:40
     */
    private Document getDocument(long pageNum) {
        location = Optional.ofNullable(location).orElse("chongqing");
        pageNum = pageNum < 1 ? 1 : pageNum;
        String url = String.format("https://github.com/search?p=%s&q=location:%s&type=Users", pageNum, location);
        Document document = null;
        try {
            document = Jsoup.connect(url).headers(headers).timeout(15000).get();
        } catch (Exception e) {
            try {
                logPrintln(pageNum + "页，超时重试一次...");
                document = Jsoup.connect(url).headers(headers).timeout(15000).get();
            } catch (Exception e1) {
                logPrintln(pageNum + "页，获取数据失败...");
            }
        }
        return document;
    }

    /**
     * 初始化总页数
     *
     * @author: tanpenggood
     * @date: 2020/8/24 12:39
     */
    private void initTotalPages() {
        Document document = getDocument(1);
        Optional.ofNullable(document)
                .map(doc -> doc.getElementsByClass("current").first())
                .map(current -> current.attr("data-total-pages"))
                .map(dataTotalPages -> Long.parseLong(dataTotalPages))
                .ifPresent(dataTotalPages -> {
                    totalPages = dataTotalPages;
                    logPrintln("总页数：" + totalPages);
                });
    }

    /**
     * 日志打印
     *
     * @author: tanpenggood
     * @date: 2020/8/24 12:39
     */
    private void logPrintln(String info) {
        System.out.println(Thread.currentThread().getName() + "\t" + info);
    }

}
