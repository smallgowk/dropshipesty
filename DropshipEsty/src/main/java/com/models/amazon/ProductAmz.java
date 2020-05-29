/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.models.amazon;

import com.utils.MarketUtil;
import com.models.aliex.AliexOriginalInfo;
import com.models.aliex.AliexProductFull;
import com.models.aliex.store.AliexStoreCommon;
import com.models.aliex.PriceFull;
import com.models.aliex.PropertyFull;
import com.models.aliex.Variation;
import com.models.aliex.VariationProperty;
import com.models.aliex.crawl.ItemSpecifics;
import com.models.aliex.store.AliexStoreInfo;
import com.models.aliex.store.inputdata.SnakeBaseStoreOrderInfo;
import com.models.esty.EstyVariation;
import com.pong.control.ProcessTransformAliexToAmz;
import com.utils.AWSUtil;
import com.utils.StringUtils;
import com.utils.Utils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author duyuno
 */
public class ProductAmz {

//    public static final int TYPE_PARENT = 1;
//    public static final int TYPE_CHILD = 2;
//    public static final int TYPE_NORMAL = 0;
    public String aliexId;

    public String feed_product_type;
    public String item_sku;
    public String brand_name;
    public String external_product_id;
    public String external_product_id_type;
    public String item_name;
    public String manufacturer;
    public String part_number;
    public String item_type;
    public String standard_price;
    public String quantity;
    public String main_image_url;
    public String swatch_image_url;
    public String other_image_url1;
    public String other_image_url2;
    public String other_image_url3;
    public String other_image_url4;
    public String other_image_url5;
    public String other_image_url6;
    public String other_image_url7;
    public String other_image_url8;
    public String parent_child;
    public String parent_sku;
    public String relationship_type;
    public String variation_theme;
    public String update_delete;
    public String model;
    public String product_description;
    public String catalog_number;
    public String bullet_point1;
    public String bullet_point2;
    public String bullet_point3;
    public String bullet_point4;
    public String bullet_point5;
    public String specific_uses_keywords;
    public String target_audience_keywords;
    public String target_audience_keywords1;
    public String target_audience_keywords2;
    public String thesaurus_attribute_keywords;
    public String thesaurus_subject_keywords;
    public String generic_keywords;
    public String main_keywords;
    public String platinum_keywords1;
    public String platinum_keywords2;
    public String platinum_keywords3;
    public String platinum_keywords4;
    public String platinum_keywords5;
    public String wattage;
    public String is_portable;
    public String recommended_uses_for_product;
    public String target_audience_base;
    public String size_name;
    public String color_name;
    public String color_map;
    public String style_name;
    public String material_type;
    public String pattern_name;
    public String capacity_unit_of_measure;
    public String item_length;
    public String item_width;
    public String item_height;
    public String item_dimensions_unit_of_measure;
    public String item_shape;
    public String capacity;
    public String item_thickness_derived;
    public String item_thickness_unit_of_measure;
    public String product_grade;
    public String form_factor;
    public String maximum_pressure;
    public String measurement_system;
    public String item_diameter_derived;
    public String item_diameter_unit_of_measure;
    public String website_shipping_weight;
    public String website_shipping_weight_unit_of_measure;
    public String unit_count;
    public String unit_count_type;
    public String item_display_diameter;
    public String item_display_diameter_unit_of_measure;
    public String item_display_weight;
    public String item_display_weight_unit_of_measure;
    public String item_display_height;
    public String item_display_height_unit_of_measure;
    public String item_display_length;
    public String item_display_length_unit_of_measure;
    public String item_display_width;
    public String item_display_width_unit_of_measure;
    public String display_dimensions_unit_of_measure;
    public String size_map;
    public String package_height;
    public String fulfillment_center_id;
    public String package_width;
    public String package_length;
    public String package_weight;
    public String package_weight_unit_of_measure;
    public String package_dimensions_unit_of_measure;
    public String energy_efficiency_image_url;
    public String warranty_type;
    public String mfg_warranty_description_type;
    public String cpsia_cautionary_statement;
    public String cpsia_cautionary_description;
    public String item_weight;
    public String fabric_type;
    public String import_designation;
    public String country_of_origin;
    public String item_weight_unit_of_measure;
    public String legal_compliance_certification_metadata;
    public String legal_compliance_certification_expiration_date;
    public String specific_uses_for_product;
    public String battery_type1;
    public String battery_type2;
    public String battery_type3;
    public String number_of_batteries1;
    public String number_of_batteries2;
    public String number_of_batteries3;
    public String are_batteries_included;
    public String batteries_required;
    public String battery_cell_composition;
    public String lithium_battery_energy_content;
    public String lithium_battery_packaging;
    public String lithium_battery_weight;
    public String number_of_lithium_ion_cells;
    public String number_of_lithium_metal_cells;
    public String battery_weight;
    public String battery_weight_unit_of_measure;
    public String lithium_battery_energy_content_unit_of_measure;
    public String lithium_battery_weight_unit_of_measure;
    public String supplier_declared_dg_hz_regulation1;
    public String supplier_declared_dg_hz_regulation2;
    public String supplier_declared_dg_hz_regulation3;
    public String supplier_declared_dg_hz_regulation4;
    public String supplier_declared_dg_hz_regulation5;
    public String hazmat_united_nations_regulatory_id;
    public String safety_data_sheet_url;
    public String item_volume;
    public String item_volume_unit_of_measure;
    public String lighting_facts_image_url;
    public String flash_point;
    public String legal_compliance_certification_date_of_issue;
    public String external_testing_certification;
    public String ghs_classification_class1;
    public String ghs_classification_class2;
    public String ghs_classification_class3;
    public String california_proposition_65_compliance_type;
    public String california_proposition_65_chemical_names1;
    public String california_proposition_65_chemical_names2;
    public String california_proposition_65_chemical_names3;
    public String california_proposition_65_chemical_names4;
    public String california_proposition_65_chemical_names5;
    public String merchant_shipping_group_name;
    public String max_order_quantity;
    public String item_package_quantity;
    public String currency;
    public String list_price;
    public String map_price;
    public String product_site_launch_date;
    public String merchant_release_date;
    public String condition_type;
    public String fulfillment_latency;
    public String restock_date;
    public String max_aggregate_ship_quantity;
    public String product_tax_code;
    public String condition_note;
    public String sale_price;
    public String sale_from_date;
    public String sale_end_date;
    public String offering_can_be_gift_messaged;
    public String offering_can_be_giftwrapped;
    public String is_discontinued_by_manufacturer;
    public String delivery_schedule_group_id;
    public String offering_end_date;
    public String offering_start_date;
    public String horsepower;
    public String power_source_type;
    public String voltage;
    public String efficiency;
    public String included_components1;
    public String included_components2;
    public String included_components3;
    public String included_components4;
    public String energy_consumption;
    public String water_consumption;
    public String compatible_counter_depth;
    public String installation_type;
    public String compatible_devices;
    public String controller_type;
    public String noise_level;
    public String number_of_pieces;
    public String number_of_handles;
    public String specification_met;
    public String controls_type;
    public String brightness;
    public String minimum_efficiency_reporting_value;
    public String dryer_power_source;
    public String lighting_method;
    public String shelf_type;
    public String pore_size;
    public String item_torque;
    public String mfg_minimum;
    public String number_of_items;
    public String groupType;
    public String department_name;

    public ArrayList<Element> listDesParams;
    public ArrayList<String> listKeywords;
    public ArrayList<ItemSpecifics> listItemSpecificses;
    public ArrayList<ItemSpecifics> listItemPackaging;

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public void setType(int type, AliexStoreInfo aliexStoreInfo) {

        switch (type) {
            case ProductTypes.TYPE_NORMAL:
                parent_child = "";
                variation_theme = "";
                break;
            case ProductTypes.TYPE_CHILD_COLOR:
                parent_child = "Child";
                variation_theme = "ColorName";
                break;
            case ProductTypes.TYPE_CHILD_SIZE:
                parent_child = "Child";
                variation_theme = "SizeName";
                break;
            case ProductTypes.TYPE_PARENT_BOTH:
                parent_child = "Parent";
                variation_theme = aliexStoreInfo.variationThemeBoth;
                quantity = "";
                standard_price = "";
                color_name = "";
                size_name = "";

                break;
            case ProductTypes.TYPE_PARENT_COLOR:
                parent_child = "Parent";
                variation_theme = aliexStoreInfo.variationThemeColor;
                quantity = "";
                standard_price = "";
                color_name = "";
                size_name = "";
                break;
            case ProductTypes.TYPE_PARENT_SIZE:
                parent_child = "Parent";
                variation_theme = aliexStoreInfo.variationThemeSize;
                quantity = "";
                standard_price = "";
                color_name = "";
                size_name = "";
                break;
        }
    }

    public void setVariationTheme(String theme) {
        parent_child = "Parent";
        variation_theme = theme;
        quantity = "";
        standard_price = "";
        color_name = "";
        size_name = "";
    }

    public String getMain_keywords() {
        return main_keywords;
    }

    public void setMain_keywords(String main_keywords) {
        this.main_keywords = main_keywords;
    }

    public String getTarget_audience_keywords() {
        return target_audience_keywords;
    }

    public void setTarget_audience_keywords(String target_audience_keywords) {
        this.target_audience_keywords = target_audience_keywords;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public ArrayList<Element> getListDesParams() {
        return listDesParams;
    }

    public void setListDesParams(ArrayList<Element> listDesParams) {
        this.listDesParams = listDesParams;
    }

    public ArrayList<String> getListKeywords() {
        return listKeywords;
    }

//    public void setListKeywords(ArrayList<String> listKeywords) {
//        this.listKeywords = listKeywords;
//    }
    public ArrayList<ItemSpecifics> getListItemPackaging() {
        return listItemPackaging;
    }

    public void setListItemPackaging(ArrayList<ItemSpecifics> listItemPackaging) {
        this.listItemPackaging = listItemPackaging;
    }

//    public void addDesParamsContent(String content) {
//        if (listDesParams == null) {
//            listDesParams = new ArrayList<>();
//        }
//
//        listDesParams.add(content);
//    }
    public String getMfg_minimum() {
        return mfg_minimum;
    }

    public void setMfg_minimum(String mfg_minimum) {
        this.mfg_minimum = mfg_minimum;
    }

    public String getNumber_of_items() {
        return number_of_items;
    }

    public void setNumber_of_items(String number_of_items) {
        this.number_of_items = number_of_items;
    }

    public String getFeed_product_type() {
        return feed_product_type;
    }

    public void setFeed_product_type(String feed_product_type) {
        this.feed_product_type = feed_product_type;
    }

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getExternal_product_id() {
        return external_product_id;
    }

    public void setExternal_product_id(String external_product_id) {
        this.external_product_id = external_product_id;
    }

    public String getExternal_product_id_type() {
        return external_product_id_type;
    }

    public void setExternal_product_id_type(String external_product_id_type) {
        this.external_product_id_type = external_product_id_type;
    }

    public void setOther_image_url4(String other_image_url4) {
        this.other_image_url4 = other_image_url4;
    }

    public void setOther_image_url5(String other_image_url5) {
        this.other_image_url5 = other_image_url5;
    }

    public void setOther_image_url6(String other_image_url6) {
        this.other_image_url6 = other_image_url6;
    }

    public void setOther_image_url7(String other_image_url7) {
        this.other_image_url7 = other_image_url7;
    }

    public void setOther_image_url8(String other_image_url8) {
        this.other_image_url8 = other_image_url8;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPart_number() {
        return part_number;
    }

    public void setPart_number(String part_number) {
        this.part_number = part_number;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getStandard_price() {
        return standard_price;
    }

    public void setStandard_price(String standard_price) {
        this.standard_price = standard_price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMain_image_url() {
        return main_image_url;
    }

    public void setMain_image_url(String main_image_url) {
        this.main_image_url = main_image_url;
    }

    public String getSwatch_image_url() {
        return swatch_image_url;
    }

    public void setSwatch_image_url(String swatch_image_url) {
        this.swatch_image_url = swatch_image_url;
    }

    public String getOther_image_url1() {
        return other_image_url1;
    }

    public void setOther_image_url1(String other_image_url1) {
        this.other_image_url1 = other_image_url1;
    }

    public String getOther_image_url2() {
        return other_image_url2;
    }

    public void setOther_image_url2(String other_image_url2) {
        this.other_image_url2 = other_image_url2;
    }

    public String getOther_image_url3() {
        return other_image_url3;
    }

    public void setOther_image_url3(String other_image_url3) {
        this.other_image_url3 = other_image_url3;
    }

    public String getParent_child() {
        return parent_child;
    }

    public void setParent_child(String parent_child) {
        this.parent_child = parent_child;
    }

    public String getParent_sku() {
        return parent_sku;
    }

    public void setParent_sku(String parent_sku) {
        this.parent_sku = parent_sku;
    }

    public String getRelationship_type() {
        return relationship_type;
    }

    public void setRelationship_type(String relationship_type) {
        this.relationship_type = relationship_type;
    }

    public String getVariation_theme() {
        return variation_theme;
    }

    public void setVariation_theme(String variation_theme) {
        this.variation_theme = variation_theme;
    }

    public String getUpdate_delete() {
        return update_delete;
    }

    public void setUpdate_delete(String update_delete) {
        this.update_delete = update_delete;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getCatalog_number() {
        return catalog_number;
    }

    public void setCatalog_number(String catalog_number) {
        this.catalog_number = catalog_number;
    }

    public String getBullet_point1() {
        return bullet_point1;
    }

    public void setBullet_point1(String bullet_point1) {
        this.bullet_point1 = bullet_point1;
    }

    public String getBullet_point2() {
        return bullet_point2;
    }

    public void setBullet_point2(String bullet_point2) {
        this.bullet_point2 = bullet_point2;
    }

    public String getBullet_point3() {
        return bullet_point3;
    }

    public void setBullet_point3(String bullet_point3) {
        this.bullet_point3 = bullet_point3;
    }

    public String getBullet_point4() {
        return bullet_point4;
    }

    public void setBullet_point4(String bullet_point4) {
        this.bullet_point4 = bullet_point4;
    }

    public String getBullet_point5() {
        return bullet_point5;
    }

    public void setBullet_point5(String bullet_point5) {
        this.bullet_point5 = bullet_point5;
    }

    public String getSpecific_uses_keywords() {
        return specific_uses_keywords;
    }

    public void setSpecific_uses_keywords(String specific_uses_keywords) {
        this.specific_uses_keywords = specific_uses_keywords;
    }

    public String getTarget_audience_keywords1() {
        return target_audience_keywords1;
    }

    public void setTarget_audience_keywords1(String target_audience_keywords1) {
        this.target_audience_keywords1 = target_audience_keywords1;
    }

    public String getTarget_audience_keywords2() {
        return target_audience_keywords2;
    }

    public void setTarget_audience_keywords2(String target_audience_keywords2) {
        this.target_audience_keywords2 = target_audience_keywords2;
    }

    public String getThesaurus_attribute_keywords() {
        return thesaurus_attribute_keywords;
    }

    public void setThesaurus_attribute_keywords(String thesaurus_attribute_keywords) {
        this.thesaurus_attribute_keywords = thesaurus_attribute_keywords;
    }

    public String getThesaurus_subject_keywords() {
        return thesaurus_subject_keywords;
    }

    public void setThesaurus_subject_keywords(String thesaurus_subject_keywords) {
        this.thesaurus_subject_keywords = thesaurus_subject_keywords;
    }

    public String getGeneric_keywords() {
        return generic_keywords;
    }

    public void genGeneric_keywords(ArrayList<String> listKeywords) {
//        System.out.println("============== id: " + aliexId);
//        System.out.println("setGeneric_keywords: " + listKeywords);
//        if (listKeywords == null || listKeywords.isEmpty()) {
//            listKeywords = new ArrayList<>();
//            String[] titleParts = item_name.split(" ");
//            
//            for (String s : titleParts) {
//                listKeywords.add(s);
//            }
//        }

//        for(int i = listKeywords.size() - 1; i >= 0; i--) {
//            String word = listKeywords.get(i).trim().toLowerCase();
//            
//            if(AWSUtil.containSpecialKeyword(word)) {
//                listKeywords.remove(i);
//                System.out.println("SpecialKey: " + word);
//                continue;
//            }
//            
//            if(!StringUtils.isTextOnly(word)) {
//                System.out.println("Not only text: " + word);
//                listKeywords.remove(i);
//            }
//        }
        this.generic_keywords = genKeywordsInfo(main_keywords, item_name, listKeywords);

        String bannedKey = AWSUtil.containBannedKeyword(this.generic_keywords);
        if (bannedKey != null) {
            ProcessTransformAliexToAmz.setBannedProduct.put(getAliexId(), bannedKey);
        } else {
            generic_keywords = AWSUtil.removeTrademark(generic_keywords);
            generic_keywords = AWSUtil.removeBrandname(generic_keywords);
        }

//        System.out.println("search_term: " + generic_keywords);
//        optimizeGenericKeywords();
//        genBulletPoints(listKeywords);
//       System.out.println("==================== ");
    }

    public String genKeywordsInfo(String mainKey, String title, ArrayList<String> listKeywords) {
        Set<String> hashMap = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isTextVisible(mainKey)) {
            String[] mainKeyParts = mainKey.split(Pattern.quote(" "));
            for (String s : mainKeyParts) {
                String lower = s.trim().toLowerCase();

                if (AWSUtil.isAvoidKeyword(lower) || !StringUtils.isTextOnly(lower)) {
                    continue;
                }

                if (!hashMap.contains(lower)) {
                    hashMap.add(lower);
                    if (sb.length() == 0) {
                        sb.append(s);
                    } else {
                        sb.append(" ").append(s);
                    }
                }

            }

        }
//        System.out.println("Title: " + item_name);
//        sb.append(listKeywords.get(0));
        String[] titlePart = item_name.split(Pattern.quote(" "));

//        if(!AWSUtil.containBannedKeyword(title)) {
//            sb.append(title);
//        }
        for (String s : titlePart) {
            String lower = s.trim().toLowerCase();
            if (lower.equals("for")) {
                break;
            }
            if (AWSUtil.isAvoidKeyword(lower) || !StringUtils.isTextOnly(lower)) {
                continue;
            }

            if (!hashMap.contains(lower)) {
                hashMap.add(lower);
                if (sb.length() == 0) {
                    sb.append(s);
                } else {
                    sb.append(" ").append(s);
                }
            }
        }

        if (sb.length() < 249 && listKeywords != null) {
            for (String key : listKeywords) {

                String[] subParts = key.split(" ");

                for (String s : subParts) {
                    String lower = s.trim().toLowerCase();

                    if (sb.length() + s.length() > 249) {
                        continue;
                    }

                    if (AWSUtil.isAvoidKeyword(lower) || !StringUtils.isTextOnly(lower)) {
                        continue;
                    }
                    if (!hashMap.contains(lower)) {
                        hashMap.add(lower);
                        if (sb.length() == 0) {
                            sb.append(s);
                        } else {
                            sb.append(" ").append(s);
                        }
                        if (sb.length() >= 248) {
                            break;
                        }
                    }
                }

//                if (sb.length() == 0) {
//                    sb.append(key);
//                } else {
//                    sb.append(" ").append(key);
//                }
                if (sb.length() >= 248) {
                    break;
                }
            }
        }

        return sb.toString();
    }

    public void setGeneric_keywords(String generic_keywords) {
        
        if(StringUtils.isEmpty(generic_keywords)) return;
        
        Set<String> hashMap = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        String[] titlePart = generic_keywords.split(Pattern.quote(" "));
        
        for (String s : titlePart) {
            String lower = s.trim().toLowerCase();
            
            if (AWSUtil.isAvoidKeyword(lower) || !StringUtils.isTextOnly(lower)) {
                continue;
            }

            if (!hashMap.contains(lower)) {
                hashMap.add(lower);
                if (sb.length() == 0) {
                    sb.append(s);
                } else {
                    sb.append(" ").append(s);
                }
            }
        }
        
        this.generic_keywords = sb.toString();
    }
    

//    public void optimizeGenericKeywords() {
//        if (generic_keywords == null || generic_keywords.isEmpty()) {
//            return;
//        }
//
//        String[] keyParts = generic_keywords.split(Pattern.quote(","));
//        StringBuilder sb = new StringBuilder();
//
//        int wordCount = 0;
//
//        for (String s : keyParts) {
//            s = s.trim();
//
//            String[] subParts = s.split(" ");
//
//            for (String s1 : subParts) {
//
//                if (!StringUtils.isValidWord(s1)) {
//                    continue;
//                }
//
//                if (AWSUtil.isAvoidKeyword(s1) || AWSUtil.containBannedKeyword(s1)) {
//                    continue;
//                }
//
//                String currentString = sb.toString();
//
//                if (currentString.contains(s1)) {
//                    continue;
//                }
//
//                sb.append(s1);
//                sb.append(" ");
//
//                wordCount++;
//            }
//        }
//
//        generic_keywords = sb.toString().trim();
//    }
    public String getPlatinum_keywords1() {
        return platinum_keywords1;
    }

    public void setPlatinum_keywords1(String platinum_keywords1) {
        this.platinum_keywords1 = platinum_keywords1;
    }

    public String getPlatinum_keywords2() {
        return platinum_keywords2;
    }

    public void setPlatinum_keywords2(String platinum_keywords2) {
        this.platinum_keywords2 = platinum_keywords2;
    }

    public String getPlatinum_keywords3() {
        return platinum_keywords3;
    }

    public void setPlatinum_keywords3(String platinum_keywords3) {
        this.platinum_keywords3 = platinum_keywords3;
    }

    public String getPlatinum_keywords4() {
        return platinum_keywords4;
    }

    public void setPlatinum_keywords4(String platinum_keywords4) {
        this.platinum_keywords4 = platinum_keywords4;
    }

    public String getPlatinum_keywords5() {
        return platinum_keywords5;
    }

    public void setPlatinum_keywords5(String platinum_keywords5) {
        this.platinum_keywords5 = platinum_keywords5;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
    }

    public String getIs_portable() {
        return is_portable;
    }

    public void setIs_portable(String is_portable) {
        this.is_portable = is_portable;
    }

    public String getRecommended_uses_for_product() {
        return recommended_uses_for_product;
    }

    public void setRecommended_uses_for_product(String recommended_uses_for_product) {
        this.recommended_uses_for_product = recommended_uses_for_product;
    }

    public String getTarget_audience_base() {
        return target_audience_base;
    }

    public void setTarget_audience_base(String target_audience_base) {
        this.target_audience_base = target_audience_base;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_map() {
        return color_map;
    }

    public void setColor_map(String color_map) {
        this.color_map = color_map;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

    public String getPattern_name() {
        return pattern_name;
    }

    public void setPattern_name(String pattern_name) {
        this.pattern_name = pattern_name;
    }

    public String getCapacity_unit_of_measure() {
        return capacity_unit_of_measure;
    }

    public void setCapacity_unit_of_measure(String capacity_unit_of_measure) {
        this.capacity_unit_of_measure = capacity_unit_of_measure;
    }

    public String getItem_length() {
        return item_length;
    }

    public void setItem_length(String item_length) {
        this.item_length = item_length;
    }

    public String getItem_width() {
        return item_width;
    }

    public void setItem_width(String item_width) {
        this.item_width = item_width;
    }

    public String getItem_height() {
        return item_height;
    }

    public void setItem_height(String item_height) {
        this.item_height = item_height;
    }

    public String getItem_dimensions_unit_of_measure() {
        return item_dimensions_unit_of_measure;
    }

    public void setItem_dimensions_unit_of_measure(String item_dimensions_unit_of_measure) {
        this.item_dimensions_unit_of_measure = item_dimensions_unit_of_measure;
    }

    public String getItem_shape() {
        return item_shape;
    }

    public void setItem_shape(String item_shape) {
        this.item_shape = item_shape;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getItem_thickness_derived() {
        return item_thickness_derived;
    }

    public void setItem_thickness_derived(String item_thickness_derived) {
        this.item_thickness_derived = item_thickness_derived;
    }

    public String getItem_thickness_unit_of_measure() {
        return item_thickness_unit_of_measure;
    }

    public void setItem_thickness_unit_of_measure(String item_thickness_unit_of_measure) {
        this.item_thickness_unit_of_measure = item_thickness_unit_of_measure;
    }

    public String getProduct_grade() {
        return product_grade;
    }

    public void setProduct_grade(String product_grade) {
        this.product_grade = product_grade;
    }

    public String getForm_factor() {
        return form_factor;
    }

    public void setForm_factor(String form_factor) {
        this.form_factor = form_factor;
    }

    public String getMaximum_pressure() {
        return maximum_pressure;
    }

    public void setMaximum_pressure(String maximum_pressure) {
        this.maximum_pressure = maximum_pressure;
    }

    public String getMeasurement_system() {
        return measurement_system;
    }

    public void setMeasurement_system(String measurement_system) {
        this.measurement_system = measurement_system;
    }

    public String getItem_diameter_derived() {
        return item_diameter_derived;
    }

    public void setItem_diameter_derived(String item_diameter_derived) {
        this.item_diameter_derived = item_diameter_derived;
    }

    public String getItem_diameter_unit_of_measure() {
        return item_diameter_unit_of_measure;
    }

    public void setItem_diameter_unit_of_measure(String item_diameter_unit_of_measure) {
        this.item_diameter_unit_of_measure = item_diameter_unit_of_measure;
    }

    public String getWebsite_shipping_weight() {
        return website_shipping_weight;
    }

    public void setWebsite_shipping_weight(String website_shipping_weight) {
        this.website_shipping_weight = website_shipping_weight;
    }

    public String getWebsite_shipping_weight_unit_of_measure() {
        return website_shipping_weight_unit_of_measure;
    }

    public void setWebsite_shipping_weight_unit_of_measure(String website_shipping_weight_unit_of_measure) {
        this.website_shipping_weight_unit_of_measure = website_shipping_weight_unit_of_measure;
    }

    public String getUnit_count() {
        return unit_count;
    }

    public void setUnit_count(String unit_count) {
        this.unit_count = unit_count;
    }

    public String getUnit_count_type() {
        return unit_count_type;
    }

    public void setUnit_count_type(String unit_count_type) {
        this.unit_count_type = unit_count_type;
    }

    public String getItem_display_diameter() {
        return item_display_diameter;
    }

    public void setItem_display_diameter(String item_display_diameter) {
        this.item_display_diameter = item_display_diameter;
    }

    public String getItem_display_diameter_unit_of_measure() {
        return item_display_diameter_unit_of_measure;
    }

    public void setItem_display_diameter_unit_of_measure(String item_display_diameter_unit_of_measure) {
        this.item_display_diameter_unit_of_measure = item_display_diameter_unit_of_measure;
    }

    public String getItem_display_weight() {
        return item_display_weight;
    }

    public void setItem_display_weight(String item_display_weight) {
        this.item_display_weight = item_display_weight;
    }

    public String getItem_display_weight_unit_of_measure() {
        return item_display_weight_unit_of_measure;
    }

    public void setItem_display_weight_unit_of_measure(String item_display_weight_unit_of_measure) {
        this.item_display_weight_unit_of_measure = item_display_weight_unit_of_measure;
    }

    public String getItem_display_height() {
        return item_display_height;
    }

    public void setItem_display_height(String item_display_height) {
        this.item_display_height = item_display_height;
    }

    public String getItem_display_height_unit_of_measure() {
        return item_display_height_unit_of_measure;
    }

    public void setItem_display_height_unit_of_measure(String item_display_height_unit_of_measure) {
        this.item_display_height_unit_of_measure = item_display_height_unit_of_measure;
    }

    public String getItem_display_length() {
        return item_display_length;
    }

    public void setItem_display_length(String item_display_length) {
        this.item_display_length = item_display_length;
    }

    public String getItem_display_length_unit_of_measure() {
        return item_display_length_unit_of_measure;
    }

    public void setItem_display_length_unit_of_measure(String item_display_length_unit_of_measure) {
        this.item_display_length_unit_of_measure = item_display_length_unit_of_measure;
    }

    public String getItem_display_width() {
        return item_display_width;
    }

    public void setItem_display_width(String item_display_width) {
        this.item_display_width = item_display_width;
    }

    public String getItem_display_width_unit_of_measure() {
        return item_display_width_unit_of_measure;
    }

    public void setItem_display_width_unit_of_measure(String item_display_width_unit_of_measure) {
        this.item_display_width_unit_of_measure = item_display_width_unit_of_measure;
    }

    public String getDisplay_dimensions_unit_of_measure() {
        return display_dimensions_unit_of_measure;
    }

    public void setDisplay_dimensions_unit_of_measure(String display_dimensions_unit_of_measure) {
        this.display_dimensions_unit_of_measure = display_dimensions_unit_of_measure;
    }

    public String getSize_map() {
        return size_map;
    }

    public void setSize_map(String size_map) {
        this.size_map = size_map;
    }

    public String getPackage_height() {
        return package_height;
    }

    public void setPackage_height(String package_height) {
        this.package_height = package_height;
    }

    public String getFulfillment_center_id() {
        return fulfillment_center_id;
    }

    public void setFulfillment_center_id(String fulfillment_center_id) {
        this.fulfillment_center_id = fulfillment_center_id;
    }

    public String getPackage_width() {
        return package_width;
    }

    public void setPackage_width(String package_width) {
        this.package_width = package_width;
    }

    public String getPackage_length() {
        return package_length;
    }

    public void setPackage_length(String package_length) {
        this.package_length = package_length;
    }

    public String getPackage_weight() {
        return package_weight;
    }

    public void setPackage_weight(String package_weight) {
        this.package_weight = package_weight;
    }

    public String getPackage_weight_unit_of_measure() {
        return package_weight_unit_of_measure;
    }

    public void setPackage_weight_unit_of_measure(String package_weight_unit_of_measure) {
        this.package_weight_unit_of_measure = package_weight_unit_of_measure;
    }

    public String getPackage_dimensions_unit_of_measure() {
        return package_dimensions_unit_of_measure;
    }

    public void setPackage_dimensions_unit_of_measure(String package_dimensions_unit_of_measure) {
        this.package_dimensions_unit_of_measure = package_dimensions_unit_of_measure;
    }

    public String getEnergy_efficiency_image_url() {
        return energy_efficiency_image_url;
    }

    public void setEnergy_efficiency_image_url(String energy_efficiency_image_url) {
        this.energy_efficiency_image_url = energy_efficiency_image_url;
    }

    public String getWarranty_type() {
        return warranty_type;
    }

    public void setWarranty_type(String warranty_type) {
        this.warranty_type = warranty_type;
    }

    public String getMfg_warranty_description_type() {
        return mfg_warranty_description_type;
    }

    public void setMfg_warranty_description_type(String mfg_warranty_description_type) {
        this.mfg_warranty_description_type = mfg_warranty_description_type;
    }

    public String getCpsia_cautionary_statement() {
        return cpsia_cautionary_statement;
    }

    public void setCpsia_cautionary_statement(String cpsia_cautionary_statement) {
        this.cpsia_cautionary_statement = cpsia_cautionary_statement;
    }

    public String getCpsia_cautionary_description() {
        return cpsia_cautionary_description;
    }

    public void setCpsia_cautionary_description(String cpsia_cautionary_description) {
        this.cpsia_cautionary_description = cpsia_cautionary_description;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }

    public String getFabric_type() {
        return fabric_type;
    }

    public void setFabric_type(String fabric_type) {
        this.fabric_type = fabric_type;
    }

    public String getImport_designation() {
        return import_designation;
    }

    public void setImport_designation(String import_designation) {
        this.import_designation = import_designation;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public String getItem_weight_unit_of_measure() {
        return item_weight_unit_of_measure;
    }

    public void setItem_weight_unit_of_measure(String item_weight_unit_of_measure) {
        this.item_weight_unit_of_measure = item_weight_unit_of_measure;
    }

    public String getLegal_compliance_certification_metadata() {
        return legal_compliance_certification_metadata;
    }

    public void setLegal_compliance_certification_metadata(String legal_compliance_certification_metadata) {
        this.legal_compliance_certification_metadata = legal_compliance_certification_metadata;
    }

    public String getLegal_compliance_certification_expiration_date() {
        return legal_compliance_certification_expiration_date;
    }

    public void setLegal_compliance_certification_expiration_date(String legal_compliance_certification_expiration_date) {
        this.legal_compliance_certification_expiration_date = legal_compliance_certification_expiration_date;
    }

    public String getSpecific_uses_for_product() {
        return specific_uses_for_product;
    }

    public void setSpecific_uses_for_product(String specific_uses_for_product) {
        this.specific_uses_for_product = specific_uses_for_product;
    }

    public String getBattery_type1() {
        return battery_type1;
    }

    public void setBattery_type1(String battery_type1) {
        this.battery_type1 = battery_type1;
    }

    public String getBattery_type2() {
        return battery_type2;
    }

    public void setBattery_type2(String battery_type2) {
        this.battery_type2 = battery_type2;
    }

    public String getBattery_type3() {
        return battery_type3;
    }

    public void setBattery_type3(String battery_type3) {
        this.battery_type3 = battery_type3;
    }

    public String getNumber_of_batteries1() {
        return number_of_batteries1;
    }

    public void setNumber_of_batteries1(String number_of_batteries1) {
        this.number_of_batteries1 = number_of_batteries1;
    }

    public String getNumber_of_batteries2() {
        return number_of_batteries2;
    }

    public void setNumber_of_batteries2(String number_of_batteries2) {
        this.number_of_batteries2 = number_of_batteries2;
    }

    public String getNumber_of_batteries3() {
        return number_of_batteries3;
    }

    public void setNumber_of_batteries3(String number_of_batteries3) {
        this.number_of_batteries3 = number_of_batteries3;
    }

    public String getAre_batteries_included() {
        return are_batteries_included;
    }

    public void setAre_batteries_included(String are_batteries_included) {
        this.are_batteries_included = are_batteries_included;
    }

    public String getBatteries_required() {
        return batteries_required;
    }

    public void setBatteries_required(String batteries_required) {
        this.batteries_required = batteries_required;
    }

    public String getBattery_cell_composition() {
        return battery_cell_composition;
    }

    public void setBattery_cell_composition(String battery_cell_composition) {
        this.battery_cell_composition = battery_cell_composition;
    }

    public String getLithium_battery_energy_content() {
        return lithium_battery_energy_content;
    }

    public void setLithium_battery_energy_content(String lithium_battery_energy_content) {
        this.lithium_battery_energy_content = lithium_battery_energy_content;
    }

    public String getLithium_battery_packaging() {
        return lithium_battery_packaging;
    }

    public void setLithium_battery_packaging(String lithium_battery_packaging) {
        this.lithium_battery_packaging = lithium_battery_packaging;
    }

    public String getLithium_battery_weight() {
        return lithium_battery_weight;
    }

    public void setLithium_battery_weight(String lithium_battery_weight) {
        this.lithium_battery_weight = lithium_battery_weight;
    }

    public String getNumber_of_lithium_ion_cells() {
        return number_of_lithium_ion_cells;
    }

    public void setNumber_of_lithium_ion_cells(String number_of_lithium_ion_cells) {
        this.number_of_lithium_ion_cells = number_of_lithium_ion_cells;
    }

    public String getNumber_of_lithium_metal_cells() {
        return number_of_lithium_metal_cells;
    }

    public void setNumber_of_lithium_metal_cells(String number_of_lithium_metal_cells) {
        this.number_of_lithium_metal_cells = number_of_lithium_metal_cells;
    }

    public String getBattery_weight() {
        return battery_weight;
    }

    public void setBattery_weight(String battery_weight) {
        this.battery_weight = battery_weight;
    }

    public String getBattery_weight_unit_of_measure() {
        return battery_weight_unit_of_measure;
    }

    public void setBattery_weight_unit_of_measure(String battery_weight_unit_of_measure) {
        this.battery_weight_unit_of_measure = battery_weight_unit_of_measure;
    }

    public String getLithium_battery_energy_content_unit_of_measure() {
        return lithium_battery_energy_content_unit_of_measure;
    }

    public void setLithium_battery_energy_content_unit_of_measure(String lithium_battery_energy_content_unit_of_measure) {
        this.lithium_battery_energy_content_unit_of_measure = lithium_battery_energy_content_unit_of_measure;
    }

    public String getLithium_battery_weight_unit_of_measure() {
        return lithium_battery_weight_unit_of_measure;
    }

    public void setLithium_battery_weight_unit_of_measure(String lithium_battery_weight_unit_of_measure) {
        this.lithium_battery_weight_unit_of_measure = lithium_battery_weight_unit_of_measure;
    }

    public String getSupplier_declared_dg_hz_regulation1() {
        return supplier_declared_dg_hz_regulation1;
    }

    public void setSupplier_declared_dg_hz_regulation1(String supplier_declared_dg_hz_regulation1) {
        this.supplier_declared_dg_hz_regulation1 = supplier_declared_dg_hz_regulation1;
    }

    public String getSupplier_declared_dg_hz_regulation2() {
        return supplier_declared_dg_hz_regulation2;
    }

    public void setSupplier_declared_dg_hz_regulation2(String supplier_declared_dg_hz_regulation2) {
        this.supplier_declared_dg_hz_regulation2 = supplier_declared_dg_hz_regulation2;
    }

    public String getSupplier_declared_dg_hz_regulation3() {
        return supplier_declared_dg_hz_regulation3;
    }

    public void setSupplier_declared_dg_hz_regulation3(String supplier_declared_dg_hz_regulation3) {
        this.supplier_declared_dg_hz_regulation3 = supplier_declared_dg_hz_regulation3;
    }

    public String getSupplier_declared_dg_hz_regulation4() {
        return supplier_declared_dg_hz_regulation4;
    }

    public void setSupplier_declared_dg_hz_regulation4(String supplier_declared_dg_hz_regulation4) {
        this.supplier_declared_dg_hz_regulation4 = supplier_declared_dg_hz_regulation4;
    }

    public String getSupplier_declared_dg_hz_regulation5() {
        return supplier_declared_dg_hz_regulation5;
    }

    public void setSupplier_declared_dg_hz_regulation5(String supplier_declared_dg_hz_regulation5) {
        this.supplier_declared_dg_hz_regulation5 = supplier_declared_dg_hz_regulation5;
    }

    public String getHazmat_united_nations_regulatory_id() {
        return hazmat_united_nations_regulatory_id;
    }

    public void setHazmat_united_nations_regulatory_id(String hazmat_united_nations_regulatory_id) {
        this.hazmat_united_nations_regulatory_id = hazmat_united_nations_regulatory_id;
    }

    public String getSafety_data_sheet_url() {
        return safety_data_sheet_url;
    }

    public void setSafety_data_sheet_url(String safety_data_sheet_url) {
        this.safety_data_sheet_url = safety_data_sheet_url;
    }

    public String getItem_volume() {
        return item_volume;
    }

    public void setItem_volume(String item_volume) {
        this.item_volume = item_volume;
    }

    public String getItem_volume_unit_of_measure() {
        return item_volume_unit_of_measure;
    }

    public void setItem_volume_unit_of_measure(String item_volume_unit_of_measure) {
        this.item_volume_unit_of_measure = item_volume_unit_of_measure;
    }

    public String getLighting_facts_image_url() {
        return lighting_facts_image_url;
    }

    public void setLighting_facts_image_url(String lighting_facts_image_url) {
        this.lighting_facts_image_url = lighting_facts_image_url;
    }

    public String getFlash_point() {
        return flash_point;
    }

    public void setFlash_point(String flash_point) {
        this.flash_point = flash_point;
    }

    public String getLegal_compliance_certification_date_of_issue() {
        return legal_compliance_certification_date_of_issue;
    }

    public void setLegal_compliance_certification_date_of_issue(String legal_compliance_certification_date_of_issue) {
        this.legal_compliance_certification_date_of_issue = legal_compliance_certification_date_of_issue;
    }

    public String getExternal_testing_certification() {
        return external_testing_certification;
    }

    public void setExternal_testing_certification(String external_testing_certification) {
        this.external_testing_certification = external_testing_certification;
    }

    public String getGhs_classification_class1() {
        return ghs_classification_class1;
    }

    public void setGhs_classification_class1(String ghs_classification_class1) {
        this.ghs_classification_class1 = ghs_classification_class1;
    }

    public String getGhs_classification_class2() {
        return ghs_classification_class2;
    }

    public void setGhs_classification_class2(String ghs_classification_class2) {
        this.ghs_classification_class2 = ghs_classification_class2;
    }

    public String getGhs_classification_class3() {
        return ghs_classification_class3;
    }

    public void setGhs_classification_class3(String ghs_classification_class3) {
        this.ghs_classification_class3 = ghs_classification_class3;
    }

    public String getCalifornia_proposition_65_compliance_type() {
        return california_proposition_65_compliance_type;
    }

    public void setCalifornia_proposition_65_compliance_type(String california_proposition_65_compliance_type) {
        this.california_proposition_65_compliance_type = california_proposition_65_compliance_type;
    }

    public String getCalifornia_proposition_65_chemical_names1() {
        return california_proposition_65_chemical_names1;
    }

    public void setCalifornia_proposition_65_chemical_names1(String california_proposition_65_chemical_names1) {
        this.california_proposition_65_chemical_names1 = california_proposition_65_chemical_names1;
    }

    public String getCalifornia_proposition_65_chemical_names2() {
        return california_proposition_65_chemical_names2;
    }

    public void setCalifornia_proposition_65_chemical_names2(String california_proposition_65_chemical_names2) {
        this.california_proposition_65_chemical_names2 = california_proposition_65_chemical_names2;
    }

    public String getCalifornia_proposition_65_chemical_names3() {
        return california_proposition_65_chemical_names3;
    }

    public void setCalifornia_proposition_65_chemical_names3(String california_proposition_65_chemical_names3) {
        this.california_proposition_65_chemical_names3 = california_proposition_65_chemical_names3;
    }

    public String getCalifornia_proposition_65_chemical_names4() {
        return california_proposition_65_chemical_names4;
    }

    public void setCalifornia_proposition_65_chemical_names4(String california_proposition_65_chemical_names4) {
        this.california_proposition_65_chemical_names4 = california_proposition_65_chemical_names4;
    }

    public String getCalifornia_proposition_65_chemical_names5() {
        return california_proposition_65_chemical_names5;
    }

    public void setCalifornia_proposition_65_chemical_names5(String california_proposition_65_chemical_names5) {
        this.california_proposition_65_chemical_names5 = california_proposition_65_chemical_names5;
    }

    public String getMerchant_shipping_group_name() {
        return merchant_shipping_group_name;
    }

    public void setMerchant_shipping_group_name(String merchant_shipping_group_name) {
        this.merchant_shipping_group_name = merchant_shipping_group_name;
    }

    public String getMax_order_quantity() {
        return max_order_quantity;
    }

    public void setMax_order_quantity(String max_order_quantity) {
        this.max_order_quantity = max_order_quantity;
    }

    public String getItem_package_quantity() {
        return item_package_quantity;
    }

    public void setItem_package_quantity(String item_package_quantity) {
        this.item_package_quantity = item_package_quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getList_price() {
        return list_price;
    }

    public void setList_price(String list_price) {
        this.list_price = list_price;
    }

    public String getMap_price() {
        return map_price;
    }

    public void setMap_price(String map_price) {
        this.map_price = map_price;
    }

    public String getProduct_site_launch_date() {
        return product_site_launch_date;
    }

    public void setProduct_site_launch_date(String product_site_launch_date) {
        this.product_site_launch_date = product_site_launch_date;
    }

    public String getMerchant_release_date() {
        return merchant_release_date;
    }

    public void setMerchant_release_date(String merchant_release_date) {
        this.merchant_release_date = merchant_release_date;
    }

    public String getCondition_type() {
        return condition_type;
    }

    public void setCondition_type(String condition_type) {
        this.condition_type = condition_type;
    }

    public String getFulfillment_latency() {
        return fulfillment_latency;
    }

    public void setFulfillment_latency(String fulfillment_latency) {
        this.fulfillment_latency = fulfillment_latency;
    }

    public String getRestock_date() {
        return restock_date;
    }

    public void setRestock_date(String restock_date) {
        this.restock_date = restock_date;
    }

    public String getMax_aggregate_ship_quantity() {
        return max_aggregate_ship_quantity;
    }

    public void setMax_aggregate_ship_quantity(String max_aggregate_ship_quantity) {
        this.max_aggregate_ship_quantity = max_aggregate_ship_quantity;
    }

    public String getProduct_tax_code() {
        return product_tax_code;
    }

    public void setProduct_tax_code(String product_tax_code) {
        this.product_tax_code = product_tax_code;
    }

    public String getCondition_note() {
        return condition_note;
    }

    public void setCondition_note(String condition_note) {
        this.condition_note = condition_note;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getSale_from_date() {
        return sale_from_date;
    }

    public void setSale_from_date(String sale_from_date) {
        this.sale_from_date = sale_from_date;
    }

    public String getSale_end_date() {
        return sale_end_date;
    }

    public void setSale_end_date(String sale_end_date) {
        this.sale_end_date = sale_end_date;
    }

    public String getOffering_can_be_gift_messaged() {
        return offering_can_be_gift_messaged;
    }

    public void setOffering_can_be_gift_messaged(String offering_can_be_gift_messaged) {
        this.offering_can_be_gift_messaged = offering_can_be_gift_messaged;
    }

    public String getOffering_can_be_giftwrapped() {
        return offering_can_be_giftwrapped;
    }

    public void setOffering_can_be_giftwrapped(String offering_can_be_giftwrapped) {
        this.offering_can_be_giftwrapped = offering_can_be_giftwrapped;
    }

    public String getIs_discontinued_by_manufacturer() {
        return is_discontinued_by_manufacturer;
    }

    public void setIs_discontinued_by_manufacturer(String is_discontinued_by_manufacturer) {
        this.is_discontinued_by_manufacturer = is_discontinued_by_manufacturer;
    }

    public String getDelivery_schedule_group_id() {
        return delivery_schedule_group_id;
    }

    public void setDelivery_schedule_group_id(String delivery_schedule_group_id) {
        this.delivery_schedule_group_id = delivery_schedule_group_id;
    }

    public String getOffering_end_date() {
        return offering_end_date;
    }

    public void setOffering_end_date(String offering_end_date) {
        this.offering_end_date = offering_end_date;
    }

    public String getOffering_start_date() {
        return offering_start_date;
    }

    public void setOffering_start_date(String offering_start_date) {
        this.offering_start_date = offering_start_date;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public String getPower_source_type() {
        return power_source_type;
    }

    public void setPower_source_type(String power_source_type) {
        this.power_source_type = power_source_type;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getIncluded_components1() {
        return included_components1;
    }

    public void setIncluded_components1(String included_components1) {
        this.included_components1 = included_components1;
    }

    public String getIncluded_components2() {
        return included_components2;
    }

    public void setIncluded_components2(String included_components2) {
        this.included_components2 = included_components2;
    }

    public String getIncluded_components3() {
        return included_components3;
    }

    public void setIncluded_components3(String included_components3) {
        this.included_components3 = included_components3;
    }

    public String getIncluded_components4() {
        return included_components4;
    }

    public void setIncluded_components4(String included_components4) {
        this.included_components4 = included_components4;
    }

    public String getEnergy_consumption() {
        return energy_consumption;
    }

    public void setEnergy_consumption(String energy_consumption) {
        this.energy_consumption = energy_consumption;
    }

    public String getWater_consumption() {
        return water_consumption;
    }

    public void setWater_consumption(String water_consumption) {
        this.water_consumption = water_consumption;
    }

    public String getCompatible_counter_depth() {
        return compatible_counter_depth;
    }

    public void setCompatible_counter_depth(String compatible_counter_depth) {
        this.compatible_counter_depth = compatible_counter_depth;
    }

    public String getInstallation_type() {
        return installation_type;
    }

    public void setInstallation_type(String installation_type) {
        this.installation_type = installation_type;
    }

    public String getCompatible_devices() {
        return compatible_devices;
    }

    public void setCompatible_devices(String compatible_devices) {
        this.compatible_devices = compatible_devices;
    }

    public String getController_type() {
        return controller_type;
    }

    public void setController_type(String controller_type) {
        this.controller_type = controller_type;
    }

    public String getNoise_level() {
        return noise_level;
    }

    public void setNoise_level(String noise_level) {
        this.noise_level = noise_level;
    }

    public String getNumber_of_pieces() {
        return number_of_pieces;
    }

    public void setNumber_of_pieces(String number_of_pieces) {
        this.number_of_pieces = number_of_pieces;
    }

    public String getNumber_of_handles() {
        return number_of_handles;
    }

    public void setNumber_of_handles(String number_of_handles) {
        this.number_of_handles = number_of_handles;
    }

    public String getSpecification_met() {
        return specification_met;
    }

    public void setSpecification_met(String specification_met) {
        this.specification_met = specification_met;
    }

    public String getControls_type() {
        return controls_type;
    }

    public void setControls_type(String controls_type) {
        this.controls_type = controls_type;
    }

    public String getBrightness() {
        return brightness;
    }

    public void setBrightness(String brightness) {
        this.brightness = brightness;
    }

    public String getMinimum_efficiency_reporting_value() {
        return minimum_efficiency_reporting_value;
    }

    public void setMinimum_efficiency_reporting_value(String minimum_efficiency_reporting_value) {
        this.minimum_efficiency_reporting_value = minimum_efficiency_reporting_value;
    }

    public String getDryer_power_source() {
        return dryer_power_source;
    }

    public void setDryer_power_source(String dryer_power_source) {
        this.dryer_power_source = dryer_power_source;
    }

    public String getLighting_method() {
        return lighting_method;
    }

    public void setLighting_method(String lighting_method) {
        this.lighting_method = lighting_method;
    }

    public String getShelf_type() {
        return shelf_type;
    }

    public void setShelf_type(String shelf_type) {
        this.shelf_type = shelf_type;
    }

    public String getPore_size() {
        return pore_size;
    }

    public void setPore_size(String pore_size) {
        this.pore_size = pore_size;
    }

    public String getItem_torque() {
        return item_torque;
    }

    public void setItem_torque(String item_torque) {
        this.item_torque = item_torque;
    }

    public String getAliexId() {
        return aliexId;
    }

    public void setAliexId(String aliexId) {
        this.aliexId = aliexId;
    }

    public String getOther_image_url4() {
        return other_image_url4;
    }

    public String getOther_image_url5() {
        return other_image_url5;
    }

    public String getOther_image_url6() {
        return other_image_url6;
    }

    public String getOther_image_url7() {
        return other_image_url7;
    }

    public String getOther_image_url8() {
        return other_image_url8;
    }

    public String getValueForCell(String column) {

        Class aClass = ProductAmz.class;

        try {
            Field field = aClass.getField(column);
            return (String) field.get(this);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
//            Logger.getLogger(ProductAmz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";
    }

    public void copyProduct(ProductAmz productAmz) {
        Class aClass = ProductAmz.class;

        try {
            Field[] fields = aClass.getFields();
            if (fields != null) {
                for (Field field : fields) {
                    field.set(this, field.get(productAmz));
                }
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
//            Logger.getLogger(ProductAmz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void validateData() {
//        Class aClass = ProductAmz.class;
//
//        try {
//            Field[] fields = aClass.getFields();
//            if (fields != null) {
//                for (Field field : fields) {
//                    String name = field.getName();
//
//                    if (AWSUtil.hashMapValidValues.containsKey(name)) {
//
//                        Object valObj = field.get(this);
//                        if (valObj == null || !(valObj instanceof String)) {
//                            continue;
//                        }
//                        String value = (String) valObj;
//
//                        if (value.isEmpty()) {
//                            continue;
//                        }
//
//                        System.out.println("" + name + " | " + value);
//
//                        ArrayList<String> listValues = AWSUtil.hashMapValidValues.get(name);
////                        if (!listValues.contains(value.trim())) {
////                            field.set(this, "");
////                        }
//                        boolean isAvai = false;
//                        for (String s : listValues) {
//                            if (s.equals(value.trim())) {
//
//                                isAvai = true;
//                                break;
//                            }
//                        }
//
//                        if (isAvai) {
//                            field.set(this, "");
//                        }
//
//                    }
//                }
//            }
//        } catch (SecurityException | IllegalArgumentException | IllegalAccessException ex) {
////            Logger.getLogger(ProductAmz.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void setItemSpecific(ArrayList<ItemSpecifics> listSpecifics, ArrayList<String> listAudiencValids) {
        if (listSpecifics == null || listSpecifics.isEmpty()) {
            return;
        }

        this.listItemSpecificses = listSpecifics;

//        ArrayList<String> listAudiencValids = AWSUtil.hashMapValidValues.get("target_audience_keywords");
//        if (listAudiencValids == null) {
//            listAudiencValids = AWSUtil.hashMapValidValues.get("target_audience_keywords1");
//        }
        String audienKey = "";
        StringBuilder audienBuilder = new StringBuilder();
        String fullKey = "";

        for (ItemSpecifics itemSpecifics : listSpecifics) {

            if (itemSpecifics.getName().toLowerCase().contains("material")) {
                setMaterial_type(itemSpecifics.getValue());
                continue;
            }
//            else if (itemSpecifics.getName().toLowerCase().contains("size:")) {
//                setSize_name(itemSpecifics.getValue());
//                continue;
//            }

            if (listAudiencValids != null) {
                for (String key : listAudiencValids) {

                    if (itemSpecifics.getValue() != null && itemSpecifics.getValue().trim().toLowerCase().contains(key)) {
                        if (!audienKey.toLowerCase().contains(key.toLowerCase()) && audienKey.length() + key.length() < 49) {
                            if (audienKey.length() == 0) {
                                audienKey = key;
                            } else {
                                audienKey += " " + key;
                            }

                        }

                    }

                    if (!fullKey.toLowerCase().contains(key.toLowerCase()) && (fullKey.length() + key.length() < 49)) {
                        if (fullKey.length() == 0) {
                            fullKey = key;
                        } else {
                            fullKey += " " + key;
                        }
                    }

                }
            }
        }

//        if (listAudiencValids != null) {
//            for (String key : listAudiencValids) {
//                for (ItemSpecifics itemSpecifics : listSpecifics) {
//                    if (itemSpecifics.getValue() != null && itemSpecifics.getValue().trim().toLowerCase().contains(key)) {
//                        audienKey += " " + key;
//                    }
//                }
//
//                fullKey += " " + key;
//            }
//        }
        if (audienKey.isEmpty()) {
            audienKey = fullKey.trim();
        }

        setTarget_audience_keywords(audienKey);
        setTarget_audience_keywords1(audienKey);
    }

    public void setListItemSpecificses(ArrayList<ItemSpecifics> listItemSpecificses) {
        this.listItemSpecificses = listItemSpecificses;
    }

//    public ProductAmz createChild(int type, String imageUrl, String skuId, String value) {
//        ProductAmz productAmz = new ProductAmz();
//
//        productAmz.copyProduct(this);
////        productAmz.setExternal_product_id(CodeUtils.genRandomProductId());
////        productAmz.setParent_child("Child");
//        productAmz.setType(type);
//        productAmz.setParent_sku(item_sku);
//        productAmz.setItem_sku(item_sku + "_" + skuId);
//        if (imageUrl != null) {
//            productAmz.setMain_image_url(imageUrl);
//        }
//
//        if (type == ProductTypes.TYPE_CHILD_COLOR) {
////            productAmz.setColor_name(color);
////            productAmz.setColor_map("white");
//
//            productAmz.setColor_name(value);
//            productAmz.setSize_name("");
//            productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_COLOR);
//            productAmz.setColor_map(AWSUtil.COLOR_MAP);
//        } else if (type == ProductTypes.TYPE_CHILD_SIZE) {
//            productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_SIZE);
//            productAmz.setSize_name(value);
//            productAmz.setColor_name("");
//            productAmz.setSize_map(AWSUtil.SIZE_MAP);
//        }
//
//        productAmz.setRelationship_type("variation");
//
//        return productAmz;
//    }
    public ProductAmz createChild(int index, Variation variation, AliexOriginalInfo aliexOriginalInfo, AliexStoreCommon aliexStoreCommon) {

        if (variation.getPropertyIdentifiers() == null || variation.getPropertyIdentifiers().isEmpty()) {
            return null;
        }

        ProductAmz productAmz = new ProductAmz();
        productAmz.copyProduct(this);
        productAmz.setParent_child("Child");
        productAmz.setParent_sku(item_sku);
        productAmz.setMain_image_url(null);

//        if (variation.getImageUrl() != null && !variation.getImageUrl().trim().isEmpty()) {
//            productAmz.setMain_image_url(MarketUtil.processImgUrl(variation.getImageUrl().trim()));
//        }
//        String imageUrl = MarketUtil.processImgUrl(variation.getImageUrl().trim());
//        
//        String hash = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
//        if(hash.length() >= 29) {
//            productAmz.setMain_image_url(imageUrl);
//        }
        StringBuilder sb = new StringBuilder();
        sb.append(item_sku).append("_").append(index);

        StringBuilder sbColorName = null;
        StringBuilder sbSizeName = null;

//        boolean isHasShipFrom = false;
        for (VariationProperty variationProperty : variation.getPropertyIdentifiers()) {
//            sb.append("_").append(variationProperty.getPropertyValueId());
//            sb.append("_").append(v);

            int variId = variationProperty.getPropertyId();
            int variValueId = variationProperty.getPropertyValueId();
            String variationName = aliexOriginalInfo.getDetail().getVariationName(variId, variValueId);

            if (variationName == null || variationName.isEmpty()) {
                continue;
            }

            if (aliexOriginalInfo.getDetail().isShipFrom(variationProperty.getPropertyId())) {
//                isHasShipFrom = true;
//                if (!variationName.toLowerCase().contains("united states") || !variationName.toLowerCase().contains("us")) {
//                    return null;
//                }
                continue;
            }

            if (variationName.length() > 50) {
                String[] variparts = variationName.split(Pattern.quote(";"));
                if (variparts.length > 1) {
                    StringBuilder sbVariation = new StringBuilder();
                    for (String s : variparts) {
                        if (sbVariation.length() + s.length() <= 49) {
                            if (sbVariation.length() != 0) {
                                sbVariation.append(" ");
                            }
                            sbVariation.append(s);
                        }
                    }

                    if (sbVariation.length() == 0) {
                        variationName = variationName.substring(0, 49);
                    } else {
                        variationName = sbVariation.toString();
                    }
                } else {
                    variationName = variationName.substring(0, 49);
                }

            }

            if (aliexOriginalInfo.getDetail().isColor(variationProperty.getPropertyId())) {
//                productAmz.setColor_map(AWSUtil.COLOR_MAP);
//                productAmz.setColor_name(variationName);
                if (sbColorName == null) {
                    sbColorName = new StringBuilder();
                    sbColorName.append(variationName);
                } else {
                    sbColorName.append(" ").append(variationName);
                }

            } else {
//                productAmz.setSize_map(AWSUtil.SIZE_MAP);
//                productAmz.setSize_name(variationName);

                if (sbSizeName == null) {
                    sbSizeName = new StringBuilder();
                    sbSizeName.append(variationName);
                } else {
                    sbSizeName.append(" ").append(variationName);
                }
            }
        }

        if (sbColorName != null) {
            productAmz.setColor_map(aliexStoreCommon.colorMap);
            productAmz.setColor_name(sbColorName.toString());
        }

        if (sbSizeName != null) {
            productAmz.setSize_map(aliexStoreCommon.sizeMap);
            productAmz.setSize_name(sbSizeName.toString());
        }

        if (sbColorName != null && sbSizeName != null) {
            productAmz.setVariation_theme(aliexStoreCommon.variationThemeBoth);
        } else if (sbColorName != null && sbSizeName == null) {

            productAmz.setVariation_theme(aliexStoreCommon.variationThemeColor);
//            
//            VariationProperty variationProperty = variation.getPropertyIdentifiers().get(0);
//
//            int variId = variationProperty.getPropertyId();
//            int type = aliexProductDetail.isColor(variId) ? ProductTypes.TYPE_CHILD_COLOR : ProductTypes.TYPE_CHILD_SIZE;
//            if (type == ProductTypes.TYPE_CHILD_COLOR) {
//                productAmz.setColor_map(AWSUtil.COLOR_MAP);
//                productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_COLOR);
//            } else {
//                productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_SIZE);
//            }
        } else if (sbColorName == null && sbSizeName != null) {
            productAmz.setVariation_theme(aliexStoreCommon.variationThemeSize);
        } else {
            return null;
        }

        float variationPrice = variation.getPriceValue();
        float promotionRate = aliexOriginalInfo.getPromotionRate();

        float productPrice = 0;

        boolean isOnUS = aliexStoreCommon.isOnlyUS;

//        if (isOnUS) {
//            productPrice = (variationPrice * (1 - promotionRate) + aliexOriginalInfo.getUPSShippringPrice()) * 2;
//        } else {
//            float priceRate = aliexStoreCommon.getPriceRate();
//            productPrice = (variationPrice * (1 - promotionRate) + aliexOriginalInfo.getShippingPrice()) * (priceRate - promotionRate);
//        }
        float priceRate = aliexStoreCommon.getPriceRate();
        productPrice = (variationPrice * (1 - promotionRate) + aliexOriginalInfo.getShippingPrice()) * (priceRate);

        float priceLimit = aliexStoreCommon.getPriceLimit();

        if (productPrice > priceLimit) {
            return null;
        }
        productAmz.setStandard_price("" + Utils.getCEOPrice(productPrice));

        productAmz.setItem_sku(sb.toString());
        productAmz.setRelationship_type("variation");

        return productAmz;
    }

    public ProductAmz createChild(int index, PriceFull priceFull, AliexProductFull aliexProductFull, AliexStoreInfo aliexStoreInfo) {

        ProductAmz productAmz = new ProductAmz();
        productAmz.copyProduct(this);
        productAmz.setParent_child("Child");
        productAmz.setParent_sku(item_sku);
        productAmz.setMain_image_url(null);

//        if (variation.getImageUrl() != null && !variation.getImageUrl().trim().isEmpty()) {
//            productAmz.setMain_image_url(MarketUtil.processImgUrl(variation.getImageUrl().trim()));
//        }
//        String imageUrl = MarketUtil.processImgUrl(variation.getImageUrl().trim());
//        
//        String hash = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
//        if(hash.length() >= 29) {
//            productAmz.setMain_image_url(imageUrl);
//        }
        StringBuilder sb = new StringBuilder();
        sb.append(item_sku).append("_").append(index);

        boolean isHasColor = false;
        boolean isHasSize = false;

        StringBuilder sbColor = new StringBuilder();
        StringBuilder sbSize = new StringBuilder();

        for (PropertyFull propertyFull : priceFull.properties) {

            if (StringUtils.isEmpty(propertyFull.getValueDisplayName())) {
                continue;
            }

            String displayName = StringUtils.getFirstCapitalWord(propertyFull.getValueDisplayName());

            if (propertyFull.isColor()) {
                productAmz.setColor_map(aliexStoreInfo.colorMap);

                if (sbColor.length() == 0) {
                    sbColor.append(displayName);
                } else {
                    if (sbColor.length() + displayName.length() + 3 < 50) {
                        sbColor.append(" - ").append(displayName);
                    }
                }

                isHasColor = true;
            } else if (propertyFull.isSize()) {
                productAmz.setSize_map(aliexStoreInfo.sizeMap);

                if (sbSize.length() == 0) {
                    sbSize.append(displayName);
                } else {
                    if (sbSize.length() + displayName.length() + 3 < 50) {
                        sbSize.append(" - ").append(displayName);
                    }
                }

                isHasSize = true;
            }
        }

        if (isHasColor) {
            productAmz.setColor_name(sbColor.toString());
        }

        if (isHasSize) {
            productAmz.setSize_name(sbSize.toString());
        }

        if (isHasColor && isHasSize) {
            productAmz.setVariation_theme(aliexStoreInfo.variationThemeBoth);
            productAmz.setItem_name(item_name + " (" + productAmz.getColor_name() + ", " + productAmz.getSize_name() + ")");
        } else if (isHasColor && !isHasSize) {
            productAmz.setVariation_theme(aliexStoreInfo.variationThemeColor);
            productAmz.setItem_name(item_name + " (" + productAmz.getColor_name() + ")");
        } else if (!isHasColor && isHasSize) {
            productAmz.setVariation_theme(aliexStoreInfo.variationThemeSize);
            productAmz.setItem_name(item_name + " (" + productAmz.getSize_name() + ")");
        } else {
            return null;
        }

        float variationPrice = priceFull.getPriceStandard();
        float promotionRate = aliexProductFull.getPromotionRate();

        float productPrice = 0;

        float priceRate = aliexStoreInfo.getPriceRate();
        if (aliexStoreInfo.isOnlyUS) {
            productPrice = (variationPrice * (1 - promotionRate) + 5) * priceRate;
        } else {
            productPrice = (variationPrice * (1 - promotionRate) + aliexProductFull.getShippingPrice()) * priceRate;
        }
//        productPrice = (variationPrice * (1 - promotionRate) + aliexProductFull.getShippingPrice()) * priceRate;

        float priceLimit = aliexStoreInfo.getPriceLimit();

        if (productPrice > priceLimit) {
            return null;
        }
        productAmz.setStandard_price("" + Utils.getCEOPrice(productPrice));

        productAmz.setItem_sku(sb.toString());
        productAmz.setRelationship_type("variation");

        return productAmz;
    }
    
    public ProductAmz createChild(int index, EstyVariation estyVariation) {

        ProductAmz productAmz = new ProductAmz();
        productAmz.copyProduct(this);
        productAmz.setParent_child("Child");
        productAmz.setParent_sku(item_sku);
//        productAmz.setMain_image_url(null);

//        if (variation.getImageUrl() != null && !variation.getImageUrl().trim().isEmpty()) {
//            productAmz.setMain_image_url(MarketUtil.processImgUrl(variation.getImageUrl().trim()));
//        }
//        String imageUrl = MarketUtil.processImgUrl(variation.getImageUrl().trim());
//        
//        String hash = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.lastIndexOf("."));
//        if(hash.length() >= 29) {
//            productAmz.setMain_image_url(imageUrl);
//        }
        StringBuilder sb = new StringBuilder();
        sb.append(item_sku).append("_").append(index);
        productAmz.setItem_sku(sb.toString());

        productAmz.setStandard_price("" + Utils.getCEOPrice(estyVariation.getPrice()));

        productAmz.setColor_map("White");
        productAmz.setColor_name(estyVariation.getColor());
        productAmz.setSize_map("Large");
        productAmz.setSize_name(estyVariation.getSize());


        
        productAmz.setRelationship_type("variation");

        return productAmz;
    }

//    public ProductAmz createChild(SkuValue colorSku, SkuValue sizeSku, AliexStoreCommon aliexStoreCommon) {
//
//        if (colorSku == null && sizeSku == null) {
//            return null;
//        }
//
//        ProductAmz productAmz = new ProductAmz();
//
//        productAmz.copyProduct(this);
//        productAmz.setParent_child("Child");
//        productAmz.setParent_sku(item_sku);
//
//        if (colorSku != null && sizeSku != null) {
//            productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_BOTH);
//        } else if (colorSku != null) {
//            productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_COLOR);
//        } else {
//            productAmz.setVariation_theme(AWSUtil.VARIATION_THEME_SIZE);
//        }
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(item_sku);
//        if (colorSku != null) {
//            sb.append("_").append(colorSku.getPropertyValueId());
//            productAmz.setMain_image_url(colorSku.getImageUrl());
//            productAmz.setColor_name(colorSku.getPropertyValueName());
//            productAmz.setColor_map(AWSUtil.COLOR_MAP);
//        } else {
//            productAmz.setColor_name("");
//        }
//
//        if (productAmz.getMain_image_url() == null || productAmz.getMain_image_url().isEmpty()) {
//            productAmz.setMain_image_url(main_image_url);
//        }
//
//        if (sizeSku != null) {
//            sb.append("_").append(sizeSku.getPropertyValueId());
//            productAmz.setSize_name(sizeSku.getPropertyValueName());
//            productAmz.setSize_map(AWSUtil.SIZE_MAP);
//        } else {
//            productAmz.setSize_name("");
//        }
//
//        productAmz.setItem_sku(sb.toString());
//
//        productAmz.setRelationship_type("variation");
//
//        return productAmz;
//    }
    public boolean checkAndFillValues(AliexStoreInfo storePageInfo, AliexStoreCommon aliexStoreCache) {

        if (ProductCache.hashMapProduct.containsKey(aliexId)) {
            ArrayList<String> listValues = ProductCache.hashMapProduct.get(aliexId);
            item_name = listValues.get(0);
            generic_keywords = listValues.get(1);
            product_description = listValues.get(2);
            return true;
        }

        if (item_name == null || item_name.isEmpty()) {
            return false;
        }

        ArrayList<String> listValues = new ArrayList<>();

        if (AWSUtil.containBannedKeyword(item_name) != null) {
            return false;
        }

//        String name = StringUtils.removeTradeMark(item_name, null);
//
//        if (name == null || name.isEmpty()) {
//            return false;
//        }
        if (!StringUtils.isEmpty(main_keywords)) {
            item_name = StringUtils.getFirstCapitalWord(main_keywords) + " - " + item_name;
        }
//        else {
//            item_name = name;
//        }

        listValues.add(item_name);

        if (AWSUtil.containBannedKeyword(generic_keywords) != null) {
            return false;
        }

//        String keyword = StringUtils.removeTradeMark(generic_keywords, null);
//        generic_keywords = keyword == null ? "" : keyword;
        listValues.add(generic_keywords);

//        validateData();
//        if (storePageInfo != null) {
//            genDescriptions(null, storePageInfo);
//        } else if (aliexStoreCache != null) {
//            genDescriptions(null, storePageInfo);
//        }
        genDescriptions(null, storePageInfo);

        listValues.add(product_description);

        ProductCache.hashMapProduct.put(aliexId, listValues);

        return true;
    }

    public void setBulletPoints(ArrayList<String> listBulletPoints) {
        if (listBulletPoints != null && !listBulletPoints.isEmpty()) {
            if (listBulletPoints.size() > 0) {
                setBullet_point1(listBulletPoints.get(0));
                if (listBulletPoints.size() > 1) {
                    setBullet_point2(listBulletPoints.get(1));
                    if (listBulletPoints.size() > 2) {
                        setBullet_point3(listBulletPoints.get(2));
                        if (listBulletPoints.size() > 3) {
                            setBullet_point4(listBulletPoints.get(3));
                            if (listBulletPoints.size() > 4) {
                                setBullet_point5(listBulletPoints.get(4));
                            }
                        }
                    }
                }
            }
        }
    }

    public void genBulletPoints() {

//        if (StringUtils.isEmpty(main_keywords) && (listKeywords == null || listKeywords.isEmpty())) {
//            for (int i = 1; i <= 5; i++) {
//                changeBulletPoint(i, "");
//            }
//            changeBulletPoint(AmzContentFormat.BRANDNAME_KEY, brand_name != null ? brand_name : "");
//            changeBulletPoint(AmzContentFormat.MAIN_KEYWORD_KEY, "");
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
        String[] keyParts = generic_keywords.split(Pattern.quote(" "));
        int size = keyParts.length;
        int one = size / 5;

//        changeBulletPoint(1, (ArrayList<String>) listKeywords.subList(0, one - 1));
//        changeBulletPoint(2, (ArrayList<String>) listKeywords.subList(one, one * 2 - 1));
//        changeBulletPoint(3, (ArrayList<String>) listKeywords.subList(one * 2, one * 3 - 1));
//        changeBulletPoint(4, (ArrayList<String>) listKeywords.subList(one * 3, one * 4 - 1));
//        changeBulletPoint(5, (ArrayList<String>) listKeywords.subList(one * 4, one * 5 - 1));
//        System.out.println("Bullet: " + size + " | " + one);
        ArrayList<String> listWords = new ArrayList<>();
        for (int i = 0; i < size; i++) {
//            if (sb.length() == 0) {
//                sb.append(listKeywords.get(i));
//            } else {
//                sb.append(", ").append(listKeywords.get(i));
//            }
//            sb.append(listKeywords.get(i)).append(" ");
//            if (size < 20) {
//                if (i == size - 1) {
//                    String bullet = sb.toString().trim();
//                    bullet = StringUtils.getPrefixCapitalWord(bullet);
//                    changeBulletPoint(1, bullet);
//                    changeBulletPoint(2, bullet);
//                    changeBulletPoint(3, bullet);
//                    changeBulletPoint(4, bullet);
//                    changeBulletPoint(5, bullet);
//                }
//            } else {
//                
//            }

            listWords.add(keyParts[i]);

            if (size < 5) {
                if (i == size - 1) {
                    changeBulletPoint(1, listWords);
                    changeBulletPoint(2, listWords);
                    changeBulletPoint(3, listWords);
                    changeBulletPoint(4, listWords);
                    changeBulletPoint(5, listWords);

                    return;
                }
            }

            if (i == one) {
//                changeBulletPoint(1, StringUtils.getPrefixCapitalWord(sb.toString().trim()));
//                sb = new StringBuilder();
                changeBulletPoint(1, listWords);
                listWords = new ArrayList<>();
            } else if (i == one * 2) {
//                changeBulletPoint(2, StringUtils.getPrefixCapitalWord(sb.toString().trim()));
//                sb = new StringBuilder();

                changeBulletPoint(2, listWords);
                listWords = new ArrayList<>();
            } else if (i == one * 3) {
//                changeBulletPoint(3, StringUtils.getPrefixCapitalWord(sb.toString().trim()));
//                sb = new StringBuilder();

                changeBulletPoint(3, listWords);
                listWords = new ArrayList<>();
            } else if (i == one * 4) {
//                changeBulletPoint(4, StringUtils.getPrefixCapitalWord(sb.toString().trim()));
//                sb = new StringBuilder();

                changeBulletPoint(4, listWords);
                listWords = new ArrayList<>();
            } else if (i == size - 1) {
//                changeBulletPoint(5, StringUtils.getPrefixCapitalWord(sb.toString().trim()));

                changeBulletPoint(5, listWords);
//                listWords = new ArrayList<>();
            }
        }

        changeBulletPoint(AmzContentFormat.BRANDNAME_KEY, brand_name != null ? brand_name : "");
        changeBulletPoint(AmzContentFormat.MAIN_KEYWORD_KEY, main_keywords != null ? main_keywords : "");

    }

    public void changeBulletPoint(String key, String value) {
        if (bullet_point1 != null) {
            bullet_point1 = bullet_point1.replaceAll(Pattern.quote(key), value);
        }

        if (bullet_point2 != null) {
            bullet_point2 = bullet_point2.replaceAll(Pattern.quote(key), value);
        }

        if (bullet_point3 != null) {
            bullet_point3 = bullet_point3.replaceAll(Pattern.quote(key), value);
        }

        if (bullet_point4 != null) {
            bullet_point4 = bullet_point4.replaceAll(Pattern.quote(key), value);
        }

        if (bullet_point5 != null) {
            bullet_point5 = bullet_point5.replaceAll(Pattern.quote(key), value);
        }
    }

    public void changeBulletPoint(int number, ArrayList<String> keywords) {

        String pattern = null;
        switch (number) {
            case 1:
                pattern = AmzContentFormat.SEARCH_TERM_1;
                if (bullet_point1 != null) {
                    String bulletKeys = genListKeyForBullet(bullet_point1.length(), keywords);
                    bullet_point1 = bullet_point1.replaceAll(Pattern.quote(pattern), bulletKeys);
                }

                break;
            case 2:
                pattern = AmzContentFormat.SEARCH_TERM_2;
                if (bullet_point2 != null) {
                    String bulletKeys = genListKeyForBullet(bullet_point2.length(), keywords);
                    bullet_point2 = bullet_point2.replaceAll(Pattern.quote(pattern), bulletKeys);
                }
                break;
            case 3:
                pattern = AmzContentFormat.SEARCH_TERM_3;
                if (bullet_point3 != null) {
                    String bulletKeys = genListKeyForBullet(bullet_point3.length(), keywords);
                    bullet_point3 = bullet_point3.replaceAll(Pattern.quote(pattern), bulletKeys);
                }
                break;
            case 4:
                pattern = AmzContentFormat.SEARCH_TERM_4;
                if (bullet_point4 != null) {
                    String bulletKeys = genListKeyForBullet(bullet_point4.length(), keywords);
                    bullet_point4 = bullet_point4.replaceAll(Pattern.quote(pattern), bulletKeys);
                }
                break;
            case 5:
                pattern = AmzContentFormat.SEARCH_TERM_5;
                if (bullet_point5 != null) {
                    String bulletKeys = genListKeyForBullet(bullet_point5.length(), keywords);
                    bullet_point5 = bullet_point5.replaceAll(Pattern.quote(pattern), bulletKeys);
                }
                break;
        }
    }

    public String genListKeyForBullet(int currentLength, ArrayList<String> listKeys) {

        if (listKeys == null || listKeys.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        HashMap<String, String> hashMap = new HashMap<>();

        for (String key : listKeys) {

            if (hashMap.containsKey(key.trim().toLowerCase())) {
                continue;
            }

            hashMap.put(key.trim().toLowerCase(), "");

            if (currentLength + sb.length() + key.length() > 400) {
                break;
            }

            if (sb.length() == 0) {
                sb.append(key);
            } else {
                sb.append(", ").append(key);
            }

        }

        String result = sb.toString();
        return AWSUtil.processTrademarkAndBrandname(result);
    }

    public void changeBulletPoint(int number, String bullet) {
        String pattern = null;
        switch (number) {
            case 1:
                pattern = AmzContentFormat.SEARCH_TERM_1;
                break;
            case 2:
                pattern = AmzContentFormat.SEARCH_TERM_2;
                break;
            case 3:
                pattern = AmzContentFormat.SEARCH_TERM_3;
                break;
            case 4:
                pattern = AmzContentFormat.SEARCH_TERM_4;
                break;
            case 5:
                pattern = AmzContentFormat.SEARCH_TERM_5;
                break;
        }

        if (bullet_point1 != null) {
            bullet_point1 = bullet_point1.replaceAll(Pattern.quote(pattern), bullet);
        }

        if (bullet_point2 != null) {
            bullet_point2 = bullet_point2.replaceAll(Pattern.quote(pattern), bullet);
        }

        if (bullet_point3 != null) {
            bullet_point3 = bullet_point3.replaceAll(Pattern.quote(pattern), bullet);
        }

        if (bullet_point4 != null) {
            bullet_point4 = bullet_point4.replaceAll(Pattern.quote(pattern), bullet);
        }

        if (bullet_point5 != null) {
            bullet_point5 = bullet_point5.replaceAll(Pattern.quote(pattern), bullet);
        }

    }

    public void genDescriptions(String description) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p><b>").append(item_name).append("</b></p></br>\n");
        sb.append("<p>");
        sb.append(description);
        sb.append("</p>");
        product_description = sb.toString();
    }

    public void genDescriptions(HashMap<String, Boolean> hashMap, AliexStoreInfo storePageInfo) {

        String descriptionForm = storePageInfo.getDescription();
        String tips = storePageInfo.getTip();
        String reasons = storePageInfo.getReasons();

        genDescriptions(hashMap, descriptionForm, tips, reasons);

    }

//    public void genDescriptions(HashMap<String, Boolean> hashMap, AliexStoreInfo aliexStoreInfo) {
//
//        String descriptionForm = null;
//        String tips = null;
//        String reasons = null;
//
//        descriptionForm = aliexStoreCache.getD;
//        tips = aliexStoreCache.getTips();
//        reasons = aliexStoreCache.getReasons();
//
//        genDescriptions(hashMap, descriptionForm, tips, reasons);
//
//    }
    public void genDescriptions(HashMap<String, Boolean> hashMap, String descriptionForm, String tips, String reasons) {

        if (StringUtils.isEmpty(descriptionForm)) {
            descriptionForm = getItemDescriptionHtml(listDesParams, hashMap, 2000, false);
            if (StringUtils.isEmpty(descriptionForm)) {
                product_description = genAutoDescription(hashMap, tips, reasons);
                return;
            }
            descriptionForm = descriptionForm.replaceAll("\\<\\/div\\>", "\\<\\/p\\>");
            descriptionForm = descriptionForm.replaceAll("\\<div\\>", "\\<p\\>");

            product_description = descriptionForm;
            return;
        }

        descriptionForm = replacePattern(descriptionForm, AmzContentFormat.TITLE_KEY, item_name, false);
        descriptionForm = replaceAllPattern(descriptionForm, AmzContentFormat.MAIN_KEYWORD_KEY, main_keywords, true);
        descriptionForm = replaceAllPattern(descriptionForm, AmzContentFormat.BRANDNAME_KEY, brand_name, false);

//        descriptionForm = descriptionForm.replace(AmzContentFormat.TITLE_KEY, item_name);
//        descriptionForm = descriptionForm.replaceAll(Pattern.quote(AmzContentFormat.MAIN_KEYWORD_KEY), StringUtils.getPrefixCapitalWord(main_keywords));
//        descriptionForm = descriptionForm.replaceAll(Pattern.quote(AmzContentFormat.BRANDNAME_KEY), brand_name);
        if (tips != null) {
            tips = replaceAllPattern(tips, AmzContentFormat.MAIN_KEYWORD_KEY, main_keywords, true);
            tips = replaceAllPattern(tips, AmzContentFormat.BRANDNAME_KEY, brand_name, false);
//            tips = tips.replaceAll(Pattern.quote(AmzContentFormat.MAIN_KEYWORD_KEY), StringUtils.getPrefixCapitalWord(main_keywords));
//            tips = tips.replaceAll(Pattern.quote(AmzContentFormat.BRANDNAME_KEY), brand_name);
        }

        if (reasons != null) {
            reasons = replaceAllPattern(reasons, AmzContentFormat.MAIN_KEYWORD_KEY, main_keywords, true);
            reasons = replaceAllPattern(reasons, AmzContentFormat.BRANDNAME_KEY, brand_name, false);
//            reasons = reasons.replaceAll(Pattern.quote(AmzContentFormat.MAIN_KEYWORD_KEY), StringUtils.getPrefixCapitalWord(main_keywords));
//            reasons = reasons.replaceAll(Pattern.quote(AmzContentFormat.BRANDNAME_KEY), brand_name);
        }

        String specifics = getItemSpecificsHtml(hashMap);
//        if (specifics != null) {
//            
//            descriptionForm = descriptionForm.replace(AmzContentFormat.SPECIFIC_KEY, specifics);
//        } else {
//            descriptionForm = descriptionForm.replace(AmzContentFormat.SPECIFIC_KEY, "");
//        }
        descriptionForm = replacePattern(descriptionForm, AmzContentFormat.SPECIFIC_KEY, specifics, false);

//        descriptionForm = descriptionForm.replace(AmzContentFormat.BULLET_KEY, "★★★ ");
        descriptionForm = replacePattern(descriptionForm, AmzContentFormat.BULLET_KEY, "★★★ ", false);

        int tipsLength = !StringUtils.isEmpty(tips) ? tips.length() : 0;

        int reasonLength = !StringUtils.isEmpty(reasons) ? reasons.length() : 0;

        String descriptionContent = getItemDescriptionHtml(listDesParams, hashMap, 10000, true);

        int deslength = descriptionContent != null ? descriptionContent.length() : 0;
        if (deslength == 0) {

            descriptionForm = replacePattern(descriptionForm, AmzContentFormat.DESCRIPTION_KEY, "", false);
            descriptionForm = replacePattern(descriptionForm, AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "", false);
            descriptionForm = replacePattern(descriptionForm, AmzContentFormat.REASON_KEY, !StringUtils.isEmpty(reasons) ? reasons : "", false);

//            descriptionForm = descriptionForm.replace(AmzContentFormat.DESCRIPTION_KEY, "");
//            descriptionForm = descriptionForm.replace(AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "");
//            descriptionForm = descriptionForm.replace(AmzContentFormat.REASON_KEY, !StringUtils.isEmpty(reasons) ? reasons : "");
        } else {
            int currentLengh = descriptionForm.length();

            if (currentLengh + tipsLength + reasonLength + deslength <= 2000) {

                descriptionForm = replacePattern(descriptionForm, AmzContentFormat.DESCRIPTION_KEY, descriptionContent, false);
                descriptionForm = replacePattern(descriptionForm, AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "", false);
                descriptionForm = replacePattern(descriptionForm, AmzContentFormat.REASON_KEY, !StringUtils.isEmpty(reasons) ? reasons : "", false);

//                descriptionForm = descriptionForm.replace(AmzContentFormat.DESCRIPTION_KEY, descriptionContent);
//                descriptionForm = descriptionForm.replace(AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "");
//                descriptionForm = descriptionForm.replace(AmzContentFormat.REASON_KEY, !StringUtils.isEmpty(reasons) ? reasons : "");
            } else {
                descriptionForm = replacePattern(descriptionForm, AmzContentFormat.REASON_KEY, "", false);
//                descriptionForm = descriptionForm.replace(AmzContentFormat.REASON_KEY, "");
                if (currentLengh + tipsLength + deslength <= 2000) {
                    descriptionForm = replacePattern(descriptionForm, AmzContentFormat.DESCRIPTION_KEY, descriptionContent, false);
                    descriptionForm = replacePattern(descriptionForm, AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "", false);

//                    descriptionForm = descriptionForm.replace(AmzContentFormat.DESCRIPTION_KEY, descriptionContent);
//                    descriptionForm = descriptionForm.replace(AmzContentFormat.TIPS_KEY, !StringUtils.isEmpty(tips) ? tips : "");
                } else {
                    descriptionForm = replacePattern(descriptionForm, AmzContentFormat.TIPS_KEY, "", false);
//                    descriptionForm = descriptionForm.replace(AmzContentFormat.TIPS_KEY, "");
                    if (currentLengh + deslength <= 2000) {
                        descriptionForm = replacePattern(descriptionForm, AmzContentFormat.DESCRIPTION_KEY, !StringUtils.isEmpty(descriptionContent) ? descriptionContent : "", false);
//                        descriptionForm = descriptionForm.replace(AmzContentFormat.DESCRIPTION_KEY, descriptionContent != null ? descriptionContent : "");
                    } else {
                        int remain = 2000 - currentLengh;
                        descriptionContent = getItemDescriptionHtml(listDesParams, hashMap, remain, true);

                        descriptionForm = replacePattern(descriptionForm, AmzContentFormat.DESCRIPTION_KEY, descriptionContent != null ? descriptionContent : "", false);
//                        descriptionForm = descriptionForm.replace(AmzContentFormat.DESCRIPTION_KEY, descriptionContent != null ? descriptionContent : "");
                    }
                }
            }
        }

        descriptionForm = descriptionForm.replaceAll("\\<\\/div\\>", "\\<\\/p\\>");
        descriptionForm = descriptionForm.replaceAll("\\<div\\>", "\\<p\\>");

        product_description = descriptionForm;

    }

    private String genAutoDescription(HashMap<String, Boolean> hashMap, String tips, String reasons) {
        StringBuilder sb = new StringBuilder();
        sb.append("<p><b>").append(item_name).append("</b></p></br>\n");
        String specifics = getItemSpecificsHtml(hashMap);
        sb.append(specifics);
        if (!StringUtils.isEmpty(tips)) {
            sb.append(tips).append("</br>\n");
        }
        if (!StringUtils.isEmpty(reasons)) {
            sb.append(reasons);
        }
        return sb.toString();
    }

    public String replacePattern(String form, String key, String content, boolean isNeedUpperPrefix) {
        if (content == null || content.isEmpty()) {
            return form.replace(key, "");
        }

        return form.replace(key, isNeedUpperPrefix ? StringUtils.getPrefixCapitalWord(content) : content);
    }

    public String replaceAllPattern(String form, String key, String content, boolean isNeedUpperPrefix) {
        if (content == null || content.isEmpty()) {
            return form.replaceAll(Pattern.quote(key), "");
        }

        return form.replaceAll(Pattern.quote(key), isNeedUpperPrefix ? StringUtils.getPrefixCapitalWord(content) : content);
    }

    public String getItemDescriptionHtml(ArrayList<Element> list, HashMap<String, Boolean> hashMap, int maxLength, boolean isForm) {
        String header = null;
        String tail = null;
        String breakLine = "\n";
        if (isForm) {
            header = "<p><b>Product description:</b></p>\n<ul>";
            tail = "</ul>";
            maxLength -= (header.length() + tail.length());
        }

        StringBuilder resultBuilder = new StringBuilder();

        if (list != null && !list.isEmpty()) {

            StringBuilder sb = new StringBuilder();

            int length = 0;

            Element preElement = null;

            for (Element elementLevel1 : list) {
                if (length + elementLevel1.outerHtml().length() + breakLine.length() <= maxLength) {
                    String text = elementLevel1.text().trim();
                    String textLower = text.toLowerCase().trim();
                    if (textLower.contains("shipment") || textLower.contains("payment")
                            || textLower.contains("china") || textLower.contains("aliexpress")) {
                        return sb.toString();
                    }

                    if (!StringUtils.isTextVisible(text)) {
                        if (elementLevel1.tagName().equals("br")) {
                            if (preElement != null && preElement.tagName().equals("br")) {
                                preElement = elementLevel1;
                                continue;
                            } else {
                                if (sb.length() > 0) {
                                    sb.append("</br>").append(breakLine);
                                    length = sb.length();
                                }
                                preElement = elementLevel1;
                                continue;
                            }
                        } else {
                            preElement = elementLevel1;
                            continue;
                        }
                    }

                    if (text.startsWith("[xlmodel]")) {
                        continue;
                    }

                    sb.append(elementLevel1.outerHtml()).append(breakLine);
                    preElement = elementLevel1;
                    length = sb.length();
//                    System.out.println("=======");
//                    System.out.println("" + element.outerHtml());
                } else {

//                    System.out.println("======= Limit");
//                    System.out.println("" + element.outerHtml());
                    Elements listElementLevel1Childs = elementLevel1.children();

//                    for(Element element1 : elements) {
//                        System.out.println("======= child limit");
//                        System.out.println("" + element1.outerHtml());
//                    }
                    Element headerElement = null;
                    String headerHtml = "";
                    ArrayList<String> listHeader = null;
                    int headerIndex = 0;

                    if (listElementLevel1Childs != null && !listElementLevel1Childs.isEmpty()) {

                        for (int i = 0, size = listElementLevel1Childs.size(); i < size; i++) {
                            Element elementLevel2 = listElementLevel1Childs.get(i);
                            String tagName = elementLevel2.tagName();

                            String txt = elementLevel2.text().trim();

                            String textLower = txt.toLowerCase().trim();
                            if (textLower.contains("shipment") || textLower.contains("payment")
                                    || textLower.contains("china") || textLower.contains("aliexpress")) {
                                return sb.toString();
                            }

                            if (txt.startsWith("[xlmodel]")) {
                                continue;
                            }

                            if (!StringUtils.isTextVisible(txt)) {
                                if (elementLevel2.tagName().equals("br")) {
                                    if (preElement != null && preElement.tagName().equals("br")) {
                                        preElement = elementLevel2;
                                        continue;
                                    } else {
                                        if (sb.length() > 0) {
                                            sb.append("</br>").append(breakLine);
                                            length = sb.length();
                                        }
                                        preElement = elementLevel2;
                                        continue;
                                    }
                                } else {
                                    preElement = elementLevel2;
                                    continue;
                                }
                            }

                            if (tagName.equals("strong") || tagName.equals("b")) {
                                headerIndex = i;

                                headerElement = elementLevel2;
                                headerHtml += headerElement.outerHtml() + breakLine.length();

                                if (listHeader == null) {
                                    listHeader = new ArrayList<>();
                                } else {
                                    listHeader.clear();
                                }
                                listHeader.add(headerHtml);

//                                System.out.println("======= Header");
//                                System.out.println("" + element.outerHtml());
                            } else {
                                if (headerElement != null && headerHtml != null) {
                                    if (length + headerHtml.length() + elementLevel2.outerHtml().length() + breakLine.length() <= maxLength) {
                                        if (i == headerIndex + 1) {
                                            String text = elementLevel2.text();
                                            if (text.startsWith("[xlmodel]")) {
                                                continue;
                                            }

                                            if (!text.trim().isEmpty()) {
                                                if (StringUtils.isTextVisible(text)) {
                                                    for (String s : listHeader) {
                                                        sb.append(s).append(breakLine);
                                                        length = sb.length();
                                                    }

                                                    listHeader.clear();

                                                    headerElement = null;
                                                    headerHtml = "";
                                                } else {
                                                    headerIndex++;
                                                }
                                            } else {
                                                listHeader.add(elementLevel2.outerHtml());
                                                headerHtml += elementLevel2.outerHtml();
                                                headerIndex++;
                                            }
                                        }
                                        String elementHtml = elementLevel2.outerHtml();
                                        sb.append(elementHtml).append(breakLine);
                                        preElement = elementLevel2;
                                        length = sb.length();
                                    } else {
                                        break;
                                    }
                                } else {
                                    if (length + elementLevel2.outerHtml().length() + breakLine.length() <= maxLength) {
                                        sb.append(elementLevel2.outerHtml()).append(breakLine);
                                        length = sb.length();
                                    } else {
                                        break;
                                    }

                                }
                            }
                        }
                    }
                    break;
                }

            }
            if (sb.length() > 0) {
                if (isForm) {
                    resultBuilder.append(header);
                }

                resultBuilder.append(sb.toString());
                if (isForm) {
                    resultBuilder.append(tail);
                }
                return resultBuilder.toString();
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    public boolean addDescontent(StringBuilder sb, Element element, int maxLength) {
        Elements childs = element.children();

        if (childs == null || childs.isEmpty()) {
            return false;
        }

        for (Element child : childs) {
            if (!addDescontent(sb, child, maxLength)) {
                return false;
            }
        }

        return true;
    }

    public String getItemTempHtml(ArrayList<String> list, HashMap<String, Boolean> hashMap) {
        if (list != null && !list.isEmpty()) {

            StringBuilder sb = new StringBuilder();
            sb.append("<p><strong>Product Specifics:</strong></p>\n").append("<ul>");

            for (String s : list) {
                addContentWithTag(sb, "li", s, "\n");
            }
            sb.append("</ul>");
            return sb.toString();
        } else {
            return null;
        }
    }

    public String getItemSpecificsHtml(HashMap<String, Boolean> hashMap) {
        StringBuilder sb = null;
        if (listItemSpecificses != null && !listItemSpecificses.isEmpty()) {

            sb = new StringBuilder();
            sb.append("<p><strong>Product Specifics:</strong></p>\n").append("<ul>");

            for (ItemSpecifics itemSpecifics : listItemSpecificses) {
                if (itemSpecifics.isAvailable()) {
                    addContentWithTag(sb, "li", StringUtils.getPrefixCapitalWord(itemSpecifics.getName()), " ", itemSpecifics.getValue(), "\n");
                }
            }
        }

        if (listItemPackaging != null && !listItemPackaging.isEmpty()) {

            if (sb == null) {
                sb = new StringBuilder();
            }

            StringBuilder packageSb = new StringBuilder();
            packageSb.append("Packaging Details: </br><ul>");
            for (ItemSpecifics itemSpecifics : listItemPackaging) {
                addContentWithTag(packageSb, "li", itemSpecifics.getName(), " ", itemSpecifics.getValue(), "\n");
            }
            packageSb.append("</ul>");

            addContentWithTag(sb, "li", packageSb.toString(), "\n");
        }

        if (sb != null && sb.length() > 0) {
            sb.append("</ul></br>");
            return sb.toString();
        } else {
            return null;
        }

    }

    public void addContentWithTag(StringBuilder sb, String tag, String... content) {

        if (content == null || content.length == 0) {
            return;
        }

        if (sb == null) {
            sb = new StringBuilder();
        }
        sb.append("<").append(tag).append(">");
        for (String s : content) {
            sb.append(s);
        }
        sb.append("</").append(tag).append(">");
    }

    public String getContentWithTag(String tag, String... content) {

        if (content == null || content.length == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<").append(tag).append(">");
        for (String s : content) {
            sb.append(s);
        }
        sb.append("</").append(tag).append(">");
        return sb.toString();
    }

    public void setImageUrl(String[] productImages) {

        main_image_url = MarketUtil.processImgUrl(productImages[0]);

        if (productImages.length <= 1) {
            return;
        }

        Class aClass = ProductAmz.class;

        for (int i = 1, size = productImages.length; i < size; i++) {
            String url = productImages[i];
            if (url == null || url.isEmpty()) {
                continue;
            }

            url = MarketUtil.processImgUrl(url);

            try {
                Field field = aClass.getField("other_image_url" + i);
                field.set(this, url);
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
//                Logger.getLogger(ProductAmz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setValueForAFiled(String fieldName, String value) {
        Class aClass = ProductAmz.class;

        try {
            Field field = aClass.getField(fieldName);
            field.set(this, value);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
//            Logger.getLogger(ProductAmz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getDescription() {

    }

    public void removeBrandInfo(ArrayList<String> listBrandName) {

        if (listBrandName == null || listBrandName.isEmpty()) {
            return;
        }

        for (String brandName : listBrandName) {
            if (!StringUtils.isEmpty(brandName)) {
                removeBrandNameInfo(brandName.trim());
            }
        }

    }

    public void removeBrandNameInfo(String brandName) {
        item_name = StringUtils.removeWord(item_name, brandName);
        generic_keywords = StringUtils.removeWord(generic_keywords, brandName);
        product_description = StringUtils.removeWord(product_description, brandName);
        bullet_point1 = StringUtils.removeWord(bullet_point1, brandName);
        bullet_point2 = StringUtils.removeWord(bullet_point2, brandName);
        bullet_point3 = StringUtils.removeWord(bullet_point3, brandName);
        bullet_point4 = StringUtils.removeWord(bullet_point4, brandName);
        bullet_point5 = StringUtils.removeWord(bullet_point5, brandName);
        material_type = StringUtils.removeWord(material_type, brandName);
    }

//    public String getParentChild(int type) {
//        switch (type) {
//            case TYPE_NORMAL:
//                return "";
//            case TYPE_CHILD:
//                return "Child";
//            case TYPE_PARENT:
//                return "Parent";
//        }
//
//        return "";
//    }
}
