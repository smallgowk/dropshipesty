/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.esty;

import com.utils.StringUtils;

/**
 *
 * @author PhanDuy
 */
public class EstyCrawlDataStoreBase extends EstyCrawlDataPageBase{
    
    public static final String PAGE_INDEX_PATTERN = "{pageIndex}";
    
    public int pageTotal;
    public String pageRuleUrl;
    public String storeName;
    
//    EstyScriptCrawl estyScriptCrawl;
    
    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getPageRuleUrl() {
        return pageRuleUrl;
    }

    public void setPageRuleUrl(String pageRuleUrl) {
        this.pageRuleUrl = pageRuleUrl;
    }
    
    public String genPageUrl(int pageIndex) {
        return pageRuleUrl.replace(PAGE_INDEX_PATTERN, "" + pageIndex);
    }
    
    public boolean isHasData() {
        return pageTotal > 0 && !StringUtils.isEmpty(pageRuleUrl);
    }

    public String getStoreName() {
        return storeName.replaceAll(" ", "_");
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
    
    

//    public EstyScriptCrawl getEstyScriptCrawl() {
//        return estyScriptCrawl;
//    }
//
//    public void setEstyScriptCrawl(EstyScriptCrawl estyScriptCrawl) {
//        this.estyScriptCrawl = estyScriptCrawl;
//    }
    
    public String getPageLink(int pageCount) {
        if (pageTotal == 1) {
            return pageRuleUrl;
        }
                
        return pageRuleUrl + "&page=" + pageCount + "#items";
    }
    
}
