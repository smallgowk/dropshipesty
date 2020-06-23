/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ping;

import com.google.common.base.CharMatcher;
import static com.utils.AWSUtil.removeWithKeyIndex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import static com.utils.AWSUtil.getListKeywordIndex;
import com.utils.DialogUtil;
import com.utils.StringUtils;
import com.utils.WindowsShortcut;
import java.io.File;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class Test {

    private static final RuntimeException NOT_VALID_EAN_EXCEPTION = new RuntimeException("NOT VALID EAN CODE");

    static final long MAX_POSSIBLE_UPC_CODE = 999999999999L;
    static final long MIN_POSSIBLE_UPC_CODE = 100000000000L;

    static String id = "32972271832";

    static int count = 0;
    static int size = 0;
    static long start = 0;
    static long end = 0;

    static long startOne = 0;
    static long endOne = 0;

    static long apiTimeTotal = 0;
    static long crawlTimeTotal = 0;

    public static void main(String[] str) throws Exception {
        
        WindowsShortcut windowsShortcut = new WindowsShortcut(new File("C:\\Users\\PhanDuy\\Desktop\\Profiles\\long (SnakeAccount) - Chrome.lnk"));
        System.out.println(windowsShortcut.getRealFilename());
        System.out.println(windowsShortcut.getCommandLineArguments());
        System.out.println(windowsShortcut.getDescription());
        System.out.println(windowsShortcut.getWorkingDirectory());
        
//        String strs = "https://i.etsystatic.com/18207802/d/il/71600d/2330731027/il_340x270.2330731027_s11y.jpg?version=0";
//        strs = StringUtils.convertEstyImageLink(strs);
//        
//        System.out.println("" + strs);
        
//        ArrayList<String> listString = new ArrayList<>();
//        listString.add("Red");
//        listString.add("White");
//        listString.add("Pink");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        listString.add("Green");
//        
//        Set<String> listPick = DialogUtil.showListViewValues(1, listString, null);
//        for(String s: listPick) {
//            System.out.println("" + s);
//        }
        
//        AWSUtil.init();
        
//        String s = "I have an APPLE";
//        String s1 = "Plate Serving Covers - Stainless Steel Cloche Food Cover Dome Dish Dining Dinner Domed Tray Metal Cat Set Kitchen Restaurant Tableware For Fruit Mirror Petkit Candle Porcelain Foldable Spoon Aqua Dog Bento Bowl Cake Pet Salt Shaker Korean";
//        String s2 = "I have an APPLE in the table";
//        String s3 = "I have an APPLEPINE in the table";
//        
//        String input = s1;
//        String inputLower = input.toLowerCase();
//        
////        String keyword = "apple".toLowerCase();
//        String keyword = "Korean".toLowerCase();
//        
////        String keyword1 = "Alternative Apparel".toLowerCase();
////        String keyword2 = "Altru".toLowerCase();
////        String keyword3 = "5.11".toLowerCase();
////        String keyword4 = "adam lippes".toLowerCase();
////        String keyword5 = "zimmer".toLowerCase();
//        
//        ArrayList<Integer> listKeyIndex = getListKeywordIndex(inputLower, keyword);
//            
//        if(listKeyIndex == null) {
//            System.out.println("Đéo có");
//            return;
//        }
//        
//        System.out.println("" + listKeyIndex.size());
//        
////        AWSUtil.processTrademarkAndBrandname(input);
//            
//        for(int size = listKeyIndex.size(), i = size - 1; i >= 0; i--) {
//                input = removeWithKeyIndex(input, listKeyIndex.get(i), keyword.length());
//                inputLower = removeWithKeyIndex(inputLower, listKeyIndex.get(i), keyword.length());
//        }
//        
//        System.out.println("" + input);
//        System.out.println("" + inputLower);

//        String regex = "[a-zA-Z-_]+";
//        System.out.println("" + Pattern.matches(regex, "10pcs"));
//        System.out.println("" + Pattern.matches(regex, "Car-styling"));
//        System.out.println("" + Pattern.matches(regex, "Car_styling"));
//        System.out.println("" + Pattern.matches(regex, "plating"));
        
//        String currentUrl = getCurrentUrl();
//        URI currentUri = URI.create(currentUrl);
//        currentUri.get
        
//        for (int i = 0; i < 200; i++) {
//            AliexProductFull aliexProductFull = AliexApiCall.getProductFullInfo("32828401081");
//            System.out.println("" + aliexProductFull.getTitle());
//        }
//        MavenXpp3Reader reader = new MavenXpp3Reader();
//        Model model = null;
//        if ((new File(System.getProperty("user.dir") + Configs.pathChar +  "pom.xml")).exists()) {
//            try {
//                model = reader.read(new FileReader(System.getProperty("user.dir") + Configs.pathChar +  "pom.xml"));
//                System.out.println("model: " + model.getVersion());
////                if(model != null && model.getVersion() != null) {
////                    appVersion = model.getVersion();
////                }
////                
////                return appVersion;
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException | XmlPullParserException ex) {
//                Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

//        String pathStr = null;
//        if (OSUtil.isWindows()) {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver.exe";
//        } else {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver";
//        }
//
//        if (pathStr != null) {
//            System.setProperty("webdriver.chrome.driver", pathStr);
//        }

//        WebDriver driver = new RemoteWebDriver(capabilities);
//        String version = driver.getCapabilities().getVersion();
//        System.out.println("" + version);
        
//        String pathStr = null;
//        if (OSUtil.isWindows()) {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver.exe";
//        } else {
//            pathStr = Configs.CONFIG_FOLDER_PATH + "chromedriver";
//        }
//
//        if (pathStr != null) {
//            System.setProperty("webdriver.chrome.driver", pathStr);
//        }
//        String productId = "4000411323626";
//
//        Document doc = AliexCrawlSvs.getInstance().processPage("https://www.aliexpress.com/store/all-wholesale-products/5432024.html?spm=a2g0o.detail.1000061.2.1caa5b09KycRrl");
//////
//        System.out.println("" + doc.toString());
//        String response = AliexApiCall.getProductShipping(productId, null);
//        System.out.println("" + response);

//        Elements skus = doc.select("div[class=ui-unusual ui-unusual-busy]");
//        
//        if(skus != null && !skus.isEmpty()) {   
//            System.out.println("Xe taiiii");
//        } else {
//            skus = doc.select("body");
//            String type = skus.first().attr("data-spm");
//            System.out.println("" + type);
//        }
//        Elements skus = doc.select("script");
//        if (skus != null && !skus.isEmpty()) {
//            for (Element elementSku : skus) {
//                String html = elementSku.html();
//                
//                if(html.startsWith("window.runParams")) {
//                    String s = html.substring("window.runParams = ".length(), html.indexOf("};") + 1);
//                    System.out.println("" + s);
//                    Gson gson = new Gson();
//                    AliexScriptDetail scriptDetail = gson.fromJson(s, AliexScriptDetail.class);
//                    break;
//                }
//            }
//        }
//        sb.append("Total crawl product time " + (System.currentTimeMillis() - start1));
//        sb.append("\n");
//        System.out.println("" + sb.toString());
//        if (crawlProductInfo.getBrandName() != null) {
//            Gson gson = new Gson();
//            String dataClean = gson.toJson(crawlProductInfo);
//            String encrytData = EncryptUtil.encrypt(dataClean);
//
//            File file = new File(Configs.CACHE_PATH + Configs.STORE_INFO_CACHE_DIR);
//            if (!file.exists()) {
//                file.mkdir();
//            }
//
//            file = new File(file.getAbsolutePath() + Configs.pathChar + aliexStoreInfo.getStoreSign());
//            if (!file.exists()) {
//                file.mkdir();
//            }
//
//            file = new File(Configs.CACHE_PATH + Configs.STORE_INFO_CACHE_DIR + Configs.pathChar + aliexStoreInfo.getStoreSign() + Configs.pathChar + crawlPageProductItem.getId() + ".txt");
//
//            try {
//                FileUtils.writeStringToFile(file, encrytData);
//            } catch (IOException ex) {
//                Logger.getLogger(AliexCrawlSvs.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        crawlProductInfo.setAliexProductFull(aliexProductFull);
//        System.out.println("" + System.getProperty("user.dir"));
        // Java 7
//        System.out.println(Paths.get("").toAbsolutePath().toString());
//        System.out.println("" + EncryptUtil.encrypt("nguyenvantrungaliex@gmail.com"));
//        System.out.println("" + EncryptUtil.encrypt("HIEUHIEU1"));
//        System.out.println("" + EncryptUtil.encrypt("SEHRTRFKOWVKHUST"));
//        String sc = "cmd java -jar service.jar";
//
//        Process p = Runtime.getRuntime().exec(sc);
//        p.waitFor();
    }

    private static String toString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            return stringBuilder.toString();
        }
    }

    private static String getComputerName() {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME")) {
            return env.get("COMPUTERNAME");
        } else if (env.containsKey("HOSTNAME")) {
            return env.get("HOSTNAME");
        } else {
            return "Unknown Computer";
        }
    }

    private static boolean validate(String code) {
        if (code == null || code.length() != 13) {
            throw NOT_VALID_EAN_EXCEPTION;
        }
        if (!CharMatcher.DIGIT.matchesAllOf(code)) {
            throw NOT_VALID_EAN_EXCEPTION;
        }
        String codeWithoutVd = code.substring(0, 12);
        int pretendVd = Integer.valueOf(code.substring(12, 13));
        int e = sumEven(codeWithoutVd);
        int o = sumOdd(codeWithoutVd);
        int me = o * 3;
        int s = me + e;
        int dv = getEanVd(s);
        if (!(pretendVd == dv)) {
            throw NOT_VALID_EAN_EXCEPTION;
        }

        return true;
    }

    private static int getEanVd(int s) {
        return 10 - (s % 10);
    }

    private static int sumEven(String code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            if (isEven(i)) {
                sum += Character.getNumericValue(code.charAt(i));
            }
        }
        return sum;
    }

    private static int sumOdd(String code) {
        int sum = 0;
        for (int i = 0; i < code.length(); i++) {
            if (!isEven(i)) {
                sum += Character.getNumericValue(code.charAt(i));
            }
        }
        return sum;
    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }

    public static void checkEmail() throws NoSuchProviderException, MessagingException, IOException {
        Session session = Session.getDefaultInstance(new Properties());
        Store store = session.getStore("imaps");
        store.connect("imap.googlemail.com", 993, "smallwolk1989@gmail.com", "tlsvdhbkhn");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        // Fetch unseen messages from inbox folder
        Message[] messages = inbox.search(
                new FlagTerm(new Flags(Flags.Flag.SEEN), false));

        // Sort messages from recent to oldest
        Arrays.sort(messages, (m1, m2) -> {
            try {
                return m2.getSentDate().compareTo(m1.getSentDate());
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        for (Message message : messages) {
//            message.getHeader("From");
            Address[] addresses = message.getFrom();
            for (Address address : addresses) {
                System.out.println("" + address.toString());
            }
//            String[] header = message.getHeader("From");
//            
//            for(String s : header) {
//                System.out.println("" + s);
//            }

//            message.getContent();
            System.out.println("");
            System.out.println("" + getTextFromMessage(message));

//            System.out.println("" + message.getHeader("From"));
//            System.out.println(
//                    "sendDate: " + message.getSentDate()
//                    + " subject:" + message.getSubject()
//                    + " subject:" + message.get
//            );
        }
    }

    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private static String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public static int countReload;

    public static void getDescription() {
//        AliexApiCall.getProductDescription("32823781390", new CallApiInterface() {
//            @Override
//            public void onSuccess(String response) {
//                AliexProductDescriptionRes aliexProductDetail = AliexParseUtil.parseProductDesResponse(response);
//
//                if (aliexProductDetail == null) {
//                    AliexErrorResponse aliexErrorResponse = AliexParseUtil.parseErrorResponse(response);
//                    if (aliexErrorResponse != null) {
//                        System.out.println("" + aliexErrorResponse.get(0).getCode());
//                    }
//                    if (countReload == 0) {
//                        AliexParameterFactory.changeKey();
//                        countReload++;
//                        getDescription();
//                    }
//                } else {
////                    System.out.println("" + aliexProductDetail.getDescription());
//
//                    System.out.println("original: ");
//                    System.out.println("" + aliexProductDetail.getDescription());
//
//                    System.out.println("====================");
//
//                    String html = MarketUtil.removeImgSrc(aliexProductDetail.getDescription(), null);
//                    System.out.println("" + html);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Exception ex) {
//
//            }
//        });
    }

    public static boolean checkIsUPC(long input) {

        long number = input;

        // determine whether number is a possible upc code	
        if ((input < MIN_POSSIBLE_UPC_CODE) || (input > MAX_POSSIBLE_UPC_CODE)) {
            // not a upc code	
//            System.out.println(input + " is an invalid UPC code");
            return false;
        } else {
            // might be a upc code	

            // determine individual digits	
            int d12 = (int) (number % 10);
            number /= 10;
            int d11 = (int) (number % 10);
            number /= 10;
            int d10 = (int) (number % 10);
            number /= 10;
            int d9 = (int) (number % 10);
            number /= 10;
            int d8 = (int) (number % 10);
            number /= 10;
            int d7 = (int) (number % 10);
            number /= 10;
            int d6 = (int) (number % 10);
            number /= 10;
            int d5 = (int) (number % 10);
            number /= 10;
            int d4 = (int) (number % 10);
            number /= 10;
            int d3 = (int) (number % 10);
            number /= 10;
            int d2 = (int) (number % 10);
            number /= 10;
            int d1 = (int) (number % 10);
            number /= 10;

            // compute sums of first 5 even digits and the odd digits	
            int m = d2 + d4 + d6 + d8 + d10;
            int n = d1 + d3 + d5 + d7 + d9 + d11;

            System.out.println("" + (m + 3 * n + d12));

            // use UPC formula to determine required value for d12	
//            int r = 10 - ((m + 3 * n) % 10);
            int r = (10 - ((m + 3 * n) % 10)) % 10;

            // based on r, can test whether number is a UPC code	
            if (r == d12) {
                // is a upc code	
//                System.out.println(input + " is a feasible UPC code");

                return true;
            } else {
                // not a upc code	
//                System.out.println(input + " is an invalid UPC code");

                return false;
            }
        }
    }

}
