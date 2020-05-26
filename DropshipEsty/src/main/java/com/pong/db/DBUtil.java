/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.db;

import com.pong.authen.Account;
import com.pong.authen.ComputerLicense;
import com.config.SystemInfo;
import com.models.aliex.AliexOriginalInfo;
import com.models.aliex.store.AliexStoreCommon;
import com.models.aliex.store.AliexPageInfo;
import com.utils.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author PhanDuy
 */
public class DBUtil {

    private static Logger LOGGER = Logger.getLogger("DBUtil");

    public static boolean saveStoreInfo(AliexStoreCommon aliexStoreCommon, String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();

            ps = connection.prepareCall("SELECT count(id) as total FROM dropship_db.store_info where store_sign=? and create_by=?");
            ps.setString(1, aliexStoreCommon.getCacheFile());
            ps.setString(2, serial);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("total");
            }

            if (total == 0) {

                ps = connection.prepareCall("insert into dropship_db.store_info "
                        + "(link, product_type, brand_name, main_keyword,bullet_point1,bullet_point2,"
                        + "bullet_point3,bullet_point4,bullet_point5, "
                        + "tips, reasons, descriptions, category, acc_no, create_date, create_by, page_total, store_sign, "
                        + "variation_theme_both, variation_theme_color, variation_theme_size,color_map,size_map,list_keywords,list_audience) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,current_timestamp(), ?, ?, ?,?,?,?,?,?,?, ?)");
                ps.setString(1, aliexStoreCommon.getUrl());
                ps.setString(2, aliexStoreCommon.getProductType());
                ps.setString(3, aliexStoreCommon.getBrandName());
                ps.setString(4, aliexStoreCommon.getMainKey());
                for (int i = 1; i <= 5; i++) {
                    ArrayList<String> listBullets = aliexStoreCommon.getListBulletPoints();
                    if (listBullets == null || listBullets.isEmpty() || i >= listBullets.size()) {
                        ps.setString(4 + i, "");
                    } else {
                        ps.setString(4 + i, listBullets.get(i));
                    }

                }
                ps.setString(10, aliexStoreCommon.getTips());
                ps.setString(11, aliexStoreCommon.getReasons());
                ps.setString(12, aliexStoreCommon.getDescriptionForm());
                ps.setString(13, aliexStoreCommon.getCategoryPath());
                ps.setString(14, aliexStoreCommon.getAccNo());
                ps.setString(15, serial);
                ps.setInt(16, aliexStoreCommon.getTotalPage());
                ps.setString(17, aliexStoreCommon.getCacheFile());
                ps.setString(18, aliexStoreCommon.getVariationThemeBoth());
                ps.setString(19, aliexStoreCommon.getVariationThemeColor());
                ps.setString(20, aliexStoreCommon.getVariationThemeSize());
                ps.setString(21, aliexStoreCommon.getColorMap());
                ps.setString(22, aliexStoreCommon.getSizeMap());

                StringBuilder sb = new StringBuilder();
                for (String s : aliexStoreCommon.getListKeyWords()) {
                    if (sb.length() == 0) {
                        sb.append(s);
                    } else {
                        sb.append(" ").append(s);
                    }
                }

                ps.setString(23, sb.toString());

                StringBuilder sbAudience = new StringBuilder();
                if (aliexStoreCommon.getFullAudienceTarget() != null) {
                    for (String s : aliexStoreCommon.getFullAudienceTarget()) {
                        if (sbAudience.length() == 0) {
                            sbAudience.append(s);
                        } else {
                            sbAudience.append(" ").append(s);
                        }
                    }
                }

                ps.setString(24, sbAudience.toString());

            } else {
                ps = connection.prepareCall("update dropship_db.store_info "
                        + " set link=?, product_type=?, brand_name=?, main_keyword=?,bullet_point1=?,"
                        + " bullet_point2=?,bullet_point3=?,bullet_point4=?,bullet_point5=?, tips=?, reasons=?, descriptions=?,"
                        + " category=?, acc_no=?, update_date=current_timestamp(), page_total=?,list_keywords=?,list_audience=? "
                        + " where store_sign=? and create_by=?");

                ps.setString(1, aliexStoreCommon.getUrl());
                ps.setString(2, aliexStoreCommon.getProductType());
                ps.setString(3, aliexStoreCommon.getBrandName());
                ps.setString(4, aliexStoreCommon.getMainKey());
                for (int i = 1; i <= 5; i++) {
                    ArrayList<String> listBullets = aliexStoreCommon.getListBulletPoints();
                    if (listBullets == null || listBullets.isEmpty() || i >= listBullets.size()) {
                        ps.setString(4 + i, "");
                    } else {
                        ps.setString(4 + i, listBullets.get(i));
                    }

                }
                ps.setString(10, aliexStoreCommon.getTips());
                ps.setString(11, aliexStoreCommon.getReasons());
                ps.setString(12, aliexStoreCommon.getDescriptionForm());
                ps.setString(13, aliexStoreCommon.getCategoryPath());
                ps.setString(14, aliexStoreCommon.getAccNo());
                ps.setInt(15, aliexStoreCommon.getTotalPage());

                StringBuilder sb = new StringBuilder();
                for (String s : aliexStoreCommon.getListKeyWords()) {
                    if (sb.length() == 0) {
                        sb.append(s);
                    } else {
                        sb.append(" ").append(s);
                    }
                }
                ps.setString(16, sb.toString());

                StringBuilder sbAudience = new StringBuilder();
                if (aliexStoreCommon.getFullAudienceTarget() != null) {
                    for (String s : aliexStoreCommon.getFullAudienceTarget()) {
                        if (sbAudience.length() == 0) {
                            sbAudience.append(s);
                        } else {
                            sbAudience.append(" ").append(s);
                        }
                    }
                }

                ps.setString(17, sbAudience.toString());

                ps.setString(18, aliexStoreCommon.getCacheFile());
                ps.setString(19, serial);

            }
            ps.execute();

            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert store store to DB with info " + aliexStoreCommon.getStoreId());
            LOGGER.error("Exception when insert store store to DB with info " + aliexStoreCommon.getStoreId(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static AliexStoreCommon getAliexStoreCommon(String storeSign, String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("SELECT * FROM dropship_db.store_info where store_sign=? and create_by=?");
            ps.setString(1, storeSign);
            ps.setString(2, serial);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AliexStoreCommon aliexStoreCommon = new AliexStoreCommon();
                aliexStoreCommon.setUrl(rs.getString("link"));
                aliexStoreCommon.setProductType(rs.getString("product_type"));
                aliexStoreCommon.setBrandName(rs.getString("brand_name"));
                aliexStoreCommon.setMainKey(rs.getString("main_keyword"));
                aliexStoreCommon.setTips(rs.getString("tips"));
                aliexStoreCommon.setReasons(rs.getString("reasons"));
                aliexStoreCommon.setDescriptionForm(rs.getString("descriptions"));
                aliexStoreCommon.setCategoryPath(rs.getString("category"));
                aliexStoreCommon.setAccNo(rs.getString("acc_no"));
                aliexStoreCommon.setTotalPage(rs.getInt("page_total"));

                ArrayList<String> listBullets = null;

                for (int i = 1; i <= 4; i++) {
                    String bullet = rs.getString("bullet_point" + i);
                    if (!StringUtils.isEmpty(bullet)) {
                        if (listBullets == null) {
                            listBullets = new ArrayList<>();
                        }
                        listBullets.add(bullet);
                    }
                }

                aliexStoreCommon.setListBulletPoints(listBullets);

                aliexStoreCommon.setStoreSign(rs.getString("store_sign"));
                aliexStoreCommon.setVariationThemeBoth(rs.getString("variation_theme_both"));
                aliexStoreCommon.setVariationThemeColor(rs.getString("variation_theme_color"));
                aliexStoreCommon.setVariationThemeSize(rs.getString("variation_theme_size"));
                aliexStoreCommon.setColorMap(rs.getString("color_map"));
                aliexStoreCommon.setSizeMap(rs.getString("size_map"));

                String keywords = rs.getString("list_keywords");

                String[] listKeys = keywords.split(Pattern.quote(" "));
                aliexStoreCommon.setListKeyWords(new ArrayList<String>(Arrays.asList(listKeys)));

                return aliexStoreCommon;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Exception when insert store store to DB with info " + storeSign);
            LOGGER.error("Exception when insert store store to DB with info " + storeSign, ex);
            return null;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean createProduct(AliexOriginalInfo aliexOriginalInfo) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("insert into dropship_db.product (aliex_id,price) values(?, ?)");
            ps.setString(1, aliexOriginalInfo.getId());
            ps.setString(2, "" + aliexOriginalInfo.getProductPrice(2.5f));
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert aliexOriginalInfo store to DB with info " + aliexOriginalInfo.getId());
            LOGGER.error("Exception when insert aliexOriginalInfo store  to DB with info " + aliexOriginalInfo.getId(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean createEmailStore(String email, String client, AliexStoreCommon aliexStoreCommon) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();

            ps = connection.prepareCall("SELECT id FROM dropship_db.email_store where client=? and store_sign=? and email=? and flag=0");
            ps.setString(1, client);
            ps.setString(2, aliexStoreCommon.getStoreSign());
            ps.setString(3, email);
            ResultSet rs = ps.executeQuery();
//
            int id = 0;
            if (rs.next()) {
                id = rs.getInt("id");
            }
//
            if (id == 0) {
//                ps = connection.prepareCall("update dropship_db.email_store set total_product=0,flag=0,update_date=current_timestamp(),email_mode=? where id = ?");
//                ps.setInt(1, aliexStoreCommon.getSendEmailMode());
//                ps.setInt(2, id);
//            } else {
//                ps = connection.prepareCall("insert into dropship_db.email_store (email,url,store_sign,acc_no,total_page,flag, create_date,client,email_mode,market_name) values(?, ?, ?, ?, ?,0,current_timestamp(),?,?,?)");
//                ps.setString(1, email);
//                ps.setString(2, aliexStoreCommon.getUrl());
//                ps.setString(3, aliexStoreCommon.getCacheFile());
//                ps.setString(4, aliexStoreCommon.getAccNo());
//                ps.setInt(5, aliexStoreCommon.getTotalPage());
//                ps.setString(6, client);
//                ps.setInt(7, aliexStoreCommon.getSendEmailMode());
//                ps.setString(8, aliexStoreCommon.getMarketName());

                ps = connection.prepareCall("insert into dropship_db.email_store (email,url,store_sign,acc_no,total_page,flag, create_date,client,email_mode,market_name) values(?, ?, ?, ?, ?,0,current_timestamp(),?,?,?)");
                ps.setString(1, email);
                ps.setString(2, aliexStoreCommon.getUrl());
                ps.setString(3, aliexStoreCommon.getCacheFile());
                ps.setString(4, aliexStoreCommon.getAccNo());
                ps.setInt(5, aliexStoreCommon.getTotalPage());
                ps.setString(6, client);
                ps.setInt(7, aliexStoreCommon.getSendEmailMode());
                ps.setString(8, aliexStoreCommon.getMarketName());

                ps.execute();
            }

            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert email store to DB with info " + email + " " + aliexStoreCommon.getCacheFile());
            LOGGER.error("Exception when insert email store  to DB with info " + email + " " + aliexStoreCommon.getCacheFile(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean createEmailStorePage(String client, String email, AliexPageInfo aliexPageInfo) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("insert into dropship_db.email_store_page (store_sign,page_index, flag, create_date,total_product,client,email) values(?, ?,0,current_timestamp(),0,?,?)");
            ps.setString(1, aliexPageInfo.getStoreSign());
            ps.setInt(2, aliexPageInfo.getPageIndex());
            ps.setString(3, client);
            ps.setString(4, email);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert email store page to DB with info " + aliexPageInfo.getStoreSign() + " " + aliexPageInfo.getPageIndex());
            LOGGER.error("Exception when insert email store page to DB with info " + aliexPageInfo.getStoreSign() + " " + aliexPageInfo.getPageIndex(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean updateEmailStoreFinish(String email, String storeSign, int totalProduct) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("update dropship_db.email_store set total_product = ?,flag = 1, update_date=current_timestamp() where email = ? and store_sign=? and flag = 0");
            ps.setInt(1, totalProduct);
            ps.setString(2, email);
            ps.setString(3, storeSign);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert email store to DB with info " + email + " " + storeSign);
            LOGGER.error("Exception when insert email store  to DB with info " + email + " " + storeSign, ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean updateEmailStorePageFinish(EmailStorePage emailStorePage, int totalProduct) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("update dropship_db.email_store_page set total_product = ?,flag = 1, update_date=current_timestamp() where id=?");
            ps.setInt(1, totalProduct);
            ps.setInt(2, emailStorePage.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert email store to DB with info " + emailStorePage.getStoreSign() + " " + emailStorePage.getPageIndex() + " " + emailStorePage.getId());
            LOGGER.error("Exception when insert email store  to DB with info " + emailStorePage.getStoreSign() + " " + emailStorePage.getPageIndex() + " " + emailStorePage.getId(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean createUser(Account account) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("insert into dropship_db.user (username,password,email,create_date, active) values(?, ?, ?, current_timestamp(), 1)");
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert account to DB with username " + account.getUsername());
            LOGGER.error("Exception when insert account to DB with username " + account.getUsername(), ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean createComputer(String serial, String computerName) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("insert into dropship_db.computer (serial,computer_name, create_date) values(?, ?, current_timestamp())");
            ps.setString(1, serial);
            ps.setString(2, computerName);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception when insert computer to DB with serial " + serial);
            LOGGER.error("Exception when insert computer to DB with serial " + serial, ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static boolean mapLicenseToComputer(String license, String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();

            ps = connection.prepareCall("SELECT count(id) as total FROM dropship_db.license_com where computer_serial=?");
            ps.setString(1, serial);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("total");
            }
            if (total == 0) {
                ps = connection.prepareCall("insert into dropship_db.license_com (license_code, computer_serial,create_date, active) values (?,?,current_timestamp(),1)");
                ps.setString(1, license);
                ps.setString(2, serial);
                ps.execute();
            } else {
                ps = connection.prepareCall("update dropship_db.license_com set license_code=? where computer_serial=?");
                ps.setString(1, license);
                ps.setString(2, serial);
                ps.execute();
            }
            return true;

        } catch (SQLException ex) {
            System.out.println("Exception when select mapLicenseToComputer " + license);
            LOGGER.error("Exception when select mapLicenseToComputer " + license, ex);
            return false;
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error("Ignored", e);
                }
            }
        }
    }

    public static ComputerLicense getComputerLicenseInfo(String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select li.license_code,li.type,lc.create_date,lc.current_product,lt.period,lt.total_product,lt.price "
                    + "from dropship_db.computer com "
                    + "inner join dropship_db.license_com lc on com.serial = lc.computer_serial and lc.active = 1 "
                    + "inner join dropship_db.license li on lc.license_code = li.license_code "
                    + "inner join dropship_db.license_type lt on li.type = lt.type "
                    + "where com.serial = ?");
            ps.setString(1, serial);
            rs = ps.executeQuery();

            if (rs.next()) {
                ComputerLicense computerLicense = new ComputerLicense();
                computerLicense.setSerial(serial);
                computerLicense.setLicense(rs.getString("license_code"));
                computerLicense.setLicenseType(rs.getString("type"));
                computerLicense.setPeriod(rs.getInt("period"));
                computerLicense.setTotalProduct(rs.getInt("total_product"));
                computerLicense.setCurrentProduct(rs.getInt("current_product"));
                computerLicense.setPrice(rs.getInt("price"));
                computerLicense.setStartTime(rs.getTimestamp("create_date").getTime());
                computerLicense.initEndTime();

                return computerLicense;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

    public static SystemInfo getSystemInfo() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("SELECT lastest_version,force_version FROM dropship_db.system_info");
            rs = ps.executeQuery();

            if (rs.next()) {
                SystemInfo systemInfo = new SystemInfo();
                systemInfo.setLastestVersion(rs.getString("lastest_version"));
                systemInfo.setForceVersion(rs.getString("force_version"));
                return systemInfo;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

    public static Account getUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select username,email,password FROM dropship_db.user where active = 1 and username = ?");
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));

                return account;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }
    
    public static Account getUserBySerial(String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select username,email,license FROM dropship_db.user where active = 1 and serial = ?");
            ps.setString(1, serial);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setLicense(rs.getString("license"));

                return account;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

    public static Account getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select username,email,password FROM dropship_db.user where active = 1 and email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setEmail(rs.getString("email"));

                return account;
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

    public static boolean checkAccountInfoExisted(String username, String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select count(username) as total FROM dropship_db.user where active = 1 and username = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return false;
    }

    public static boolean checkComputerAvail(String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select count(id) as total FROM dropship_db.computer where serial = ?");
            ps.setString(1, serial);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
    }

    public static boolean checkLicenseAvail(String license) {

        if (StringUtils.isEmpty(license)) {
            return false;
        }

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select count(id) as total FROM dropship_db.license where license_code = ? and active = 1");
            ps.setString(1, license);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
    }

    public static boolean checkLicenseUsed(String license) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select count(id) as total FROM dropship_db.license_com where active = 1 and license_code = ?");
            ps.setString(1, license);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
            return false;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
    }

    public static boolean checkComputerRegisterLicense(String serial) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select count(id) as total FROM dropship_db.license_com where active = 1 and computer_serial = ?");
            ps.setString(1, serial);
            rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                return total > 0;
            }
        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return false;
    }

    public static ArrayList<EmailStore> getListEmailStore() {

        ArrayList<EmailStore> listEmailStore = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("SELECT id,email,market_name,url,store_sign,acc_no, total_page,total_product,email_mode,client FROM dropship_db.email_store where flag=0");
            rs = ps.executeQuery();
            while (rs.next()) {

                if (listEmailStore == null) {
                    listEmailStore = new ArrayList<>();
                }

                EmailStore emailStore = new EmailStore();
                emailStore.setId(rs.getInt("id"));
                emailStore.setEmail(rs.getString("email"));
                emailStore.setUrl(rs.getString("url"));
                emailStore.setStoreSign(rs.getString("store_sign"));
                emailStore.setAccNo(rs.getString("acc_no"));
                emailStore.setTotalPage(rs.getInt("total_page"));
                emailStore.setTotalProduct(rs.getInt("total_product"));
                emailStore.setEmailMode(rs.getInt("email_mode"));
                emailStore.setMarketName(rs.getString("market_name"));
                emailStore.setClient(rs.getString("client"));

                listEmailStore.add(emailStore);
            }

            return listEmailStore;

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

    public static ArrayList<EmailStorePage> getListEmailStorePage(EmailStore emailStore) {

        ArrayList<EmailStorePage> listEmailStore = null;

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("SELECT esp.id,esp.page_index,esp.store_sign FROM dropship_db.email_store_page esp "
                    + "inner join dropship_db.email_store es on es.client = esp.client and es.email=esp.email and es.store_sign=esp.store_sign "
                    + "where esp.client=? and esp.email=? and esp.store_sign=? and esp.flag = 0");
            ps.setString(1, emailStore.getClient());
            ps.setString(2, emailStore.getEmail());
            ps.setString(3, emailStore.getStoreSign());
            rs = ps.executeQuery();
            while (rs.next()) {

                if (listEmailStore == null) {
                    listEmailStore = new ArrayList<>();
                }

                EmailStorePage emailStorePage = new EmailStorePage();
                emailStorePage.setId(rs.getInt("id"));
                emailStorePage.setPageIndex(rs.getInt("page_index"));
                emailStorePage.setStoreSign(rs.getString("store_sign"));

                listEmailStore.add(emailStorePage);
            }

            return listEmailStore;

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.debug("Ignored", e);
                }
            }
        }
        return null;
    }

}
