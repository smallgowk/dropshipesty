/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong.control;

import com.google.gson.Gson;
import com.pong.authen.Account;
import com.pong.authen.ComputerLicense;
import com.config.SystemInfo;
import com.pong.db.DBUtil;
import com.pong.db.DropshipConnectionPool;
import com.pong.server.obj.income.BaseIncomeRequestObject;
import com.pong.server.obj.outcome.BaseOutComeObj;
import com.utils.Constants;
import com.utils.StringUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PhanDuy
 */
public class ProcessAuthenSvs {

    public static boolean checkAuthen(String diskSerial, String email) {
        return true;
    }

    public static void processAuthenData(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
        switch (bio.getAction()) {
            case Constants.TCPAction.SIGNUP:
                processSignUp(bio, processInputLister);
                break;
            case Constants.TCPAction.GET_ACCOUNT:
                processGetAccount(bio, processInputLister);
                break;
            case Constants.TCPAction.UPDATE_LICENSE:
                processUpdateLicense(bio, processInputLister);
                break;
            case Constants.TCPAction.CHECK_LICENSE:
                processCheckLicenseInfo(bio, processInputLister);
                break;
            case Constants.TCPAction.CHECK_SERIAL:
                processCheckSerialInfo(bio, processInputLister);
                break;
        }
    }

    public static void processSignUp(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {

        Account account = bio.getAccount();

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("insert into dropship_db.user (username,password,email,create_date, active, serial) values(?, ?, ?, current_timestamp(), 1, ?)");
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, bio.getDiskSerialNumber());
            ps.execute();

            processInputLister.responseToClient(Constants.ResultCode.SUCCESS);
        } catch (SQLException ex) {
            System.out.println("Exception when insert account to DB with username " + account.getUsername());
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public static void processGetAccount(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = DropshipConnectionPool.getInstance().getConnection();
            ps = connection.prepareCall("select u.username,u.email, lc.license_code as license, si.api_key  "
                    + "FROM dropship_db.user u "
                    + "left join dropship_db.license_com lc on u.serial = lc.computer_serial and lc.active = 1 "
                    + "left join system_info si on si.active = 1 "
                    + "where u.active = 1 and u.serial = ?");
            ps.setString(1, bio.getDiskSerialNumber());
            rs = ps.executeQuery();

            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                account.setLicense(rs.getString("license"));
                account.setApiKey(rs.getString("api_key"));

                BaseOutComeObj baseOutComeObj = new BaseOutComeObj();
                baseOutComeObj.setResultCode(Constants.ResultCode.SUCCESS);
                Gson gson = new Gson();
                baseOutComeObj.setData(gson.toJson(account));

                processInputLister.responseToClient(baseOutComeObj);
            } else {
                processInputLister.responseToClient(Constants.ResultCode.FAILURE);
            }

        } catch (SQLException ex) {
            System.out.println("" + ex.toString());
            processInputLister.responseToClient(Constants.ResultCode.FAILURE);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public static void processUpdateLicense(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
//        if (bio.getAccount() == null) {
//            reponseResultToClient(Constants.ResultCode.AUTHEN_FAIL, trailer, ctx);
//            return;
//        }
        BaseOutComeObj baseOutComeObj = new BaseOutComeObj();

        String serial = bio.getDiskSerialNumber();
        if (!DBUtil.checkComputerAvail(serial)) {
            if (!DBUtil.createComputer(serial, bio.getComputerName())) {
                baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
                baseOutComeObj.setMessage("System error!!!");
                processInputLister.responseToClient(baseOutComeObj);
                return;
            }
        }

        String license = bio.getLicense();

        if (!DBUtil.checkLicenseAvail(license)) {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("License code is not existed!!!");
            processInputLister.responseToClient(baseOutComeObj);
            return;
        }

//        boolean checkLicenseUsed = DBUtil.checkLicenseUsed(license);
//        if (checkLicenseUsed) {
//            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE_LICENSE_USED);
//            baseOutComeObj.setMessage("License is used!!!");
//            reponseResultToClient(baseOutComeObj, trailer, ctx);
//            return;
//        }
        if (DBUtil.mapLicenseToComputer(license, serial)) {
            processInputLister.responseToClient(Constants.ResultCode.SUCCESS);
        } else {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("Update license fail!!!");
            processInputLister.responseToClient(baseOutComeObj);
        }
    }

    public static void processCheckLicenseInfo(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
        BaseOutComeObj baseOutComeObj = checkAuthen(bio);
        processInputLister.responseToClient(baseOutComeObj);
    }

    public static void processCheckSerialInfo(BaseIncomeRequestObject bio, ProcessInputLister processInputLister) {
        ComputerLicense computerLicense = DBUtil.getComputerLicenseInfo(bio.getDiskSerialNumber());
        BaseOutComeObj baseOutComeObj = new BaseOutComeObj();
        if (computerLicense != null && !StringUtils.isEmpty(computerLicense.getLicense())) {
            baseOutComeObj.setResultCode(Constants.ResultCode.SUCCESS);
            baseOutComeObj.setData(computerLicense.getLicense());
            processInputLister.responseToClient(baseOutComeObj);
        } else {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            processInputLister.responseToClient(baseOutComeObj);
        }

    }

    public static BaseOutComeObj checkAuthen(BaseIncomeRequestObject bio) {

        BaseOutComeObj baseOutComeObj = new BaseOutComeObj();

        String serial = bio.getDiskSerialNumber();

        ComputerLicense computerLicense = DBUtil.getComputerLicenseInfo(serial);

        if (computerLicense == null) {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("Not registed license yet!!!");
            return baseOutComeObj;
        }

        if (computerLicense.getEndTime() < System.currentTimeMillis()) {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("License is expired!");
            return baseOutComeObj;
        }

        if (computerLicense.getCurrentProduct() >= computerLicense.getTotalProduct()) {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("Over the product limit of license! The limit is " + computerLicense.getTotalProduct());
            return baseOutComeObj;
        }

        if (!SystemInfo.getInstance().isValidVersion(bio.getVersion())) {
            baseOutComeObj.setResultCode(Constants.ResultCode.FAILURE);
            baseOutComeObj.setMessage("Application version is old! Please update the lastest version!");
            return baseOutComeObj;
        }

        baseOutComeObj.setResultCode(Constants.ResultCode.SUCCESS);
        return baseOutComeObj;
    }
}
