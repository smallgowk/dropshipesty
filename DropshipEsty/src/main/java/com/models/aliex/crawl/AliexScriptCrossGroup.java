/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import com.utils.AWSUtil;
import java.util.ArrayList;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptCrossGroup {

    public String channel;
    public String name;
    public ArrayList<AliexScriptCrossLink> crossLinkList;

    public ArrayList<String> sumKeywords() {

        if (crossLinkList == null || crossLinkList.isEmpty()) {
            return null;
        }
        ArrayList<String> listKeywords = null;

        for (AliexScriptCrossLink aliexScriptCrossLink : crossLinkList) {
            String word = aliexScriptCrossLink.getRelatedWord();
            if (word != null) {
                if (listKeywords == null) {
                    listKeywords = new ArrayList<>();
                }
                
                listKeywords.add(word);
            }

            if (listKeywords != null && listKeywords.size() >= 100) {
                break;
            }
        }
        return listKeywords;
    }
}
