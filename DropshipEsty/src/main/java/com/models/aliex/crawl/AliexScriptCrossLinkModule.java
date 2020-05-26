/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import java.util.ArrayList;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptCrossLinkModule {

    public ArrayList<AliexScriptCrossGroup> crossLinkGroupList;

    public ArrayList<String> getRelatedSearch() {

        if (crossLinkGroupList == null || crossLinkGroupList.isEmpty()) {
            return null;
        }

        ArrayList<String> result = null;

        for (AliexScriptCrossGroup aliexScriptCrossGroup : crossLinkGroupList) {
            ArrayList<String> sumKeys = aliexScriptCrossGroup.sumKeywords();
            if(sumKeys != null && !sumKeys.isEmpty()) {
                if(result == null) {
                    result = new ArrayList<>();
                }
                
                result.addAll(sumKeys);
            }
            
            if (result != null && result.size() >= 100) {
                break;
            }
            
        }

        return result;
    }
}
