/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

import java.net.URI;
import java.util.regex.Pattern;

/**
 *
 * @author PhanDuy
 */
public class AmzListingItem {

    private final String CUSTOM_LINK_FORMAT = "https://sellercentral.amazon.com/gestalt/managecustomization/index.html?sku=";

    public String sku;
    public String asin;
    public String customLink;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCustomLink() {
        return customLink;
    }

    public void setCustomLink(String customLink) {
        this.customLink = customLink;
        parseInfo();
    }

    private void parseInfo() {
        URI uri = URI.create(customLink);
        String query = uri.getQuery();

        if (query != null && !query.isEmpty()) {
            String[] queryParts = query.split(Pattern.quote("&"));
            for (String s : queryParts) {
                String[] paramsParts = s.split(Pattern.quote("="));
                if (paramsParts.length == 2) {
                    if (paramsParts[0].equals("sku")) {
                        sku = paramsParts[1].trim();
                    } else if(paramsParts[0].equals("asin")){
                        asin = paramsParts[1].trim();
                    }
                }

            }
        }
    }

    public String getAsin() {
        return asin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sku).append(", \n");
        sb.append(asin).append(", \n");
        sb.append(customLink).append(", \n");
        return sb.toString();
    }

}
