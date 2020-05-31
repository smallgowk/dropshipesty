/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.utils.MarketUtil;
import com.models.aliex.AliexProductFull;
import com.models.aliex.PriceFull;
import com.models.aliex.ProductAttribute;
import com.models.amazon.ProductAmz;
import com.models.amazon.ProductTypes;
import com.models.aliex.crawl.ItemSpecifics;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.esty.EstyCrawlProductItem;
import com.models.esty.EstyVariation;
import com.utils.AWSUtil;
import com.utils.FuncUtil;
import com.utils.StringUtils;
import com.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author PhanDuy
 */
public class ProcessTransformEstyToAmz {

    public static HashMap<String, String> setBannedProduct = new HashMap<>();

    public static ArrayList<ProductAmz> transform(EstyCrawlProductItem estyCrawlProductItem, SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo) {
        ArrayList<ProductAmz> results = new ArrayList<>();

        ProductAmz productAmz = createBasicProductAmz(estyCrawlProductItem, snakeBaseStoreOrderInfo);

//        productAmz.setItem_name(AWSUtil.processTrademarkAndBrandname(productAmz.getItem_name()));

        productAmz.setGeneric_keywords(estyCrawlProductItem.getTitle());

//        productAmz.genBulletPoints();
        results.add(productAmz);

        productAmz.genDescriptions(snakeBaseStoreOrderInfo.getDescription());

//        ArrayList<ProductAmz> listChilds = createChilds(estyCrawlProductItem, snakeBaseStoreOrderInfo, productAmz);
//        results.addAll(listChilds);
        return results;
    }

//    public static ArrayList<ProductAmz> createChilds(EstyCrawlProductItem estyCrawlProductItem, SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo, ProductAmz productAmz) {
//        ArrayList<ProductAmz> results = new ArrayList<>();
//        for(int i = 0, size = snakeBaseStoreOrderInfo.listVariation.size(); i < size; i++) {
//            EstyVariation estyVariation = snakeBaseStoreOrderInfo.listVariation.get(i);
//            ProductAmz child = productAmz.createChild((i + 1) , estyVariation);
//            results.add(child);
//        }
//            
//        return results;
//    }

//    public static void genProductSearchTerm(AliexStoreInfo aliexStoreInfo, AliexProductFull aliexProductFull, ProductAmz productAmz) {
//
//        if (!StringUtils.isEmpty(aliexStoreInfo.getMain_key())) {
//            productAmz.setMain_keywords(aliexStoreInfo.getMain_key());
//        }
////        if (aliexStoreInfo.getListKeyWords() != null && !aliexStoreInfo.getListKeyWords().isEmpty()) {
////            productAmz.setGeneric_keywords(aliexStoreInfo.getListKeyWords());
////        } else {
////            productAmz.setGeneric_keywords(aliexProductFull.getListSearchTerms());
////        }
//        productAmz.genGeneric_keywords(aliexProductFull.getListSearchTerms());
//    }
//    public static void genBulletPoint(AliexProductFull aliexProductFull, ProductAmz productAmz) {
//        productAmz.genBulletPoints(aliexProductFull.getListSearchTerms());
//    }
    public static void genProductSpecifics(AliexStoreInfo aliexStoreInfo, AliexProductFull aliexProductFull, ProductAmz productAmz) {
        ArrayList<ItemSpecifics> listSpecifics = null;

        if (aliexProductFull.getAttributes() != null) {
            for (ProductAttribute productAttribute : aliexProductFull.getAttributes()) {
                if (listSpecifics == null) {
                    listSpecifics = new ArrayList<>();
                }
                listSpecifics.add(new ItemSpecifics(productAttribute.name, productAttribute.value));
            }
            productAmz.setItemSpecific(listSpecifics, aliexStoreInfo.getFullAudienceTarget());
        }
    }

    public static ArrayList<Element> genDescriptionHtml(String descriptionHtmlOrigin, String productId) {

        if (productId.equals("4000168195664")) {
            System.out.println("OK");
        }
//        if(StringUtils.isEmpty(descriptionHtmlOrigin)) {
//            return;
//        }
//        if (descriptionHtmlOrigin.contains("<a href=")) {
//            productAmz.setListDesParams(null);
//            return;
//        }
//        System.out.println("Des: " + descriptionHtmlOrigin);
        Pattern pt = Pattern.compile("[^!-~ ]");
        Matcher match = pt.matcher(descriptionHtmlOrigin);
        while (match.find()) {
            String s = match.group();
            descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("\\" + s, "");
        }
//        descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("\\<a href=.*?>", "");
//        descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("\\<img.*?>", "");
        descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("\\<span.*?>", "");
        descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("\\</span>", "");
        descriptionHtmlOrigin = descriptionHtmlOrigin.replaceAll("&nbsp;", "");

//        System.out.println("Des after: " + descriptionHtmlOrigin);
        ArrayList<Element> results = new ArrayList<>();
        Whitelist myCustomWhitelist = new Whitelist();
//            myCustomWhitelist.addTags("b", "br", "p", "ul", "div", "li", "strong", "border", "tbody", "span", "stype", "h2", "h1");
//        myCustomWhitelist.addTags("b", "br", "p", "ul", "div", "li", "strong", "border", "tbody", "span", "stype");
        myCustomWhitelist.addTags("b", "br", "p", "ul", "div", "li", "strong", "border", "span", "stype", "kse:widget");
        String clean = Jsoup.clean(descriptionHtmlOrigin, myCustomWhitelist);

//        clean = clean.replaceAll("\\<\\/div\\>", "\\<\\/p\\>");
//        clean = clean.replaceAll("\\<div\\>", "\\<p\\>");
//        clean = clean.replaceAll("\\<\\/b\\>", "\\<\\/strong\\>");
//        clean = clean.replaceAll("\\<b\\>", "\\<strong\\>");
//        clean = clean.replaceAll(Pattern.quote("●"), "-");
//        clean = clean.replaceAll(Pattern.quote(" ● "), "-");
//        clean = clean.replaceAll("\\<\\/span\\>", "");
//        clean = clean.replaceAll("\\<span\\>", "");
        clean = clean.replaceAll("\\<\\/h2", "\\<\\/h3");
        clean = clean.replaceAll("\\<h2\\>", "\\<h3\\>");

        clean = clean.replaceAll("\\<\\/h1", "\\<\\/h3");
        clean = clean.replaceAll("\\<h1\\>", "\\<h3\\>");

        clean = removeTagAndContent(clean, "kse:widget");

//        clean = clean.replaceAll("\\<kse:widget*?>", "");
//        clean = clean.replaceAll("\\</kse:widget>", "");
//        System.out.println("Clean: " + clean);
//                results.add(clean);
//                productAmz.setListDesParams(results);
        Document doc = Jsoup.parse(clean);

        List<Node> childs = doc.body().childNodes();

        Element preElement = null;

        for (int i = 0, size = childs.size(); i < size; i++) {
            Node childNodeLv1 = childs.get(i);
            parseElement(results, childNodeLv1, productId);
        }
        return results;
    }

    public static void parseElement(ArrayList<Element> results, Node node, String productId) {

        if (setBannedProduct.containsKey(productId)) {
            return;
        }

        List<Node> listNode = node.childNodes();
        if (!isHasElementNode(listNode)) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (!isElementNode(element)) {
                    results.add(element);
//                    System.out.println("" + element.outerHtml());
                } else if (element.tagName().equals("kse:widget")) {
                    return;
                } else {
                    if (listNode != null && !listNode.isEmpty()) {
                        for (Node no : listNode) {

                            if (no instanceof TextNode) {
                                String txt = ((TextNode) no).text();
                                if (StringUtils.isTextVisible(txt)) {
                                    String bannedKey = AWSUtil.containBannedKeyword(txt);
                                    if (bannedKey != null) {
                                        setBannedProduct.put(productId, bannedKey);
                                        return;
                                    }

                                    txt = AWSUtil.processTrademarkAndBrandname(txt);

                                    Element newElement = new Element(Tag.valueOf("p"), "");
                                    newElement.append(txt);

                                    results.add(newElement);
                                }
                            }
                        }
                    }

//                    String txt = element.text();
//
//                    if (StringUtils.isTextVisible(txt)) {
//                        String elementHtml = element.html();
//
//                        if (productId.equals("4000168195664")) {
//                            elementHtml = element.html();
//                            System.out.println("" + elementHtml);
//                        }
//
//                        String bannedKey = AWSUtil.containBannedKeyword(elementHtml);
//                        if (bannedKey != null) {
//                            setBannedProduct.put(productId, bannedKey);
//                            return;
//                        }
//
//                        Tag tag = element.tag();
//
//                        elementHtml = AWSUtil.processTrademarkAndBrandname(elementHtml);
////                        element.text(txt);
//
//                        Element newElement = new Element(Tag.valueOf("p"), "");
//                        newElement.append(elementHtml);
//
//                        results.add(newElement);
//
//                    }
                }
            } else {
                String nodeText = node.toString().trim();
                if (StringUtils.isTextVisible(nodeText)) {

                    return;
                }
                Element element = new Element(Tag.valueOf("p"), "");
                element.appendText(nodeText);
                results.add(element);
//                System.out.println("" + element.outerHtml());
            }

        } else {
            for (Node no : listNode) {
                parseElement(results, no, productId);
            }
        }

    }

    public static boolean isHasElementNode(List<Node> listNode) {
        if (listNode == null || listNode.isEmpty()) {
            return false;
        }

        for (Node no : listNode) {
            if (isElementNode(no)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isElementNode(Node node) {

        if (!(node instanceof Element)) {
            return false;
        }
        Element element = (Element) node;
        String tag = element.tagName();
        return !tag.equals("br") && !tag.equals("b") && !tag.equals("strong");
    }

    public static String removeTagAndContent(String html, String tag) {
        String startTag = "<" + tag + ">";
        int start = html.indexOf(startTag);
        while (start > 0) {
            String endTag = "</" + tag + ">";
            int end = html.indexOf(endTag);
            html = html.substring(0, start) + html.substring(end + endTag.length(), html.length());
            start = html.indexOf("<" + tag + ">");
        }

        return html;
    }

    public static ProductAmz createBasicProductAmz(EstyCrawlProductItem estyCrawlProductItem, SnakeBaseStoreOrderInfo snakeBaseStoreOrderInfo) {
        ProductAmz productAmz = new ProductAmz();
        productAmz.setExternal_product_id_type("UPC");
        productAmz.setFeed_product_type("shirt");
        productAmz.setQuantity("200");
        productAmz.setFulfillment_latency("" + snakeBaseStoreOrderInfo.getHandlingTime());
        productAmz.setMfg_minimum("10");
        productAmz.setUnit_count("1");
        productAmz.setUnit_count_type("PC");
        productAmz.setItem_package_quantity("1");
        productAmz.setNumber_of_items("1");
        productAmz.setMaterial_type("other");
        productAmz.setBrand_name(snakeBaseStoreOrderInfo.getBrand_name());
        productAmz.setImageUrl(snakeBaseStoreOrderInfo.getImagesUrl(estyCrawlProductItem.getImageUrl()));
        productAmz.setItem_type(snakeBaseStoreOrderInfo.getItemType());
//        productAmz.setVariation_theme(snakeBaseStoreOrderInfo.getVariationType());
        
        productAmz.setBulletPoints(snakeBaseStoreOrderInfo.getListBullets());
        productAmz.setItem_sku(snakeBaseStoreOrderInfo.getPrefix().toUpperCase() + FuncUtil.createSaltNumber(5) + "_" + estyCrawlProductItem.getId());
        productAmz.setPart_number(productAmz.getItem_sku().substring(0, productAmz.getItem_sku().length() - 2));
        productAmz.setOuter_material_type1(snakeBaseStoreOrderInfo.getOuterMaterialType());
        productAmz.setMaterial_composition1(snakeBaseStoreOrderInfo.getMaterialComposition());
        productAmz.setIs_adult_product("TRUE");
        productAmz.setColor_map("White");
        productAmz.setColor_name("Black");
        productAmz.setSize_map("Large");
        productAmz.setSize_name("Large");

        String title = estyCrawlProductItem.getTitle();

//        if(title == null) return null;
        productAmz.setItem_name(title);

        productAmz.setItem_type(snakeBaseStoreOrderInfo.getItemType());
//        productAmz.setTarget_audience_keywords(aliexStoreInfo.getAudienceKeyword());
//        productAmz.setTarget_audience_keywords1(aliexStoreInfo.getAudienceKeyword());
        productAmz.setDepartment_name(snakeBaseStoreOrderInfo.getDepartment());
//        productAmz.setTarget_audience_keywords(aliexStoreInfo.getAudienceKeyword());
//        productAmz.setTarget_audience_keywords1(aliexStoreInfo.getAudienceKeyword());

        productAmz.setUnit_count_type("PC");
        productAmz.setMaterial_type("other");
        productAmz.setManufacturer(productAmz.getBrand_name());
        productAmz.setStandard_price("" + Utils.getCEOPrice(snakeBaseStoreOrderInfo.getBasePrice()));
        
//        productAmz.setVariationTheme(snakeBaseStoreOrderInfo.getVariationType());

        return productAmz;
    }
}
