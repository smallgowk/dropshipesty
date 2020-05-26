/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.aliex.crawl;

import com.utils.AWSUtil;
import static com.utils.AWSUtil.checkContainKeyword;
import com.utils.StringUtils;

/**
 *
 * @author PhanDuy
 */
public class AliexScriptCrossLink {
    public String displayName;
    public String name;
    
    public String getRelatedWord() {
        if (displayName.contains("wholesale")) {
            displayName = displayName.replaceAll("wholesale", "");
        }

        if (displayName.contains("price")) {
            displayName = displayName.replaceAll("price", "");
        }

        if (displayName.contains("promotion")) {
            displayName = displayName.replaceAll("promotion", "");
        }

        if (!displayName.isEmpty()) {
            return StringUtils.getPrefixCapitalWord(displayName);
        } else {
            return null;
        }
    }
}
