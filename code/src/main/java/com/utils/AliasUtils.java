package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 兼容原生成前端里的拼音接口名、字段名和当前英文实体/库表名。
 */
public class AliasUtils {
    private static final Map<String, String> TABLE_ALIASES = new HashMap<String, String>();
    private static final Map<String, String> COLUMN_ALIASES = new HashMap<String, String>();

    static {
        TABLE_ALIASES.put("fangwuleixing", "house_type");
        TABLE_ALIASES.put("houseType", "house_type");
        TABLE_ALIASES.put("fangyuanxinxi", "property_info");
        TABLE_ALIASES.put("propertyInfo", "property_info");
        TABLE_ALIASES.put("kanfangyuyue", "viewing_reservation");
        TABLE_ALIASES.put("viewingReservation", "viewing_reservation");
        TABLE_ALIASES.put("yixiangjinzhifu", "intent_deposit_payment");
        TABLE_ALIASES.put("intentDepositPayment", "intent_deposit_payment");
        TABLE_ALIASES.put("hetongqianding", "contract_signing");
        TABLE_ALIASES.put("contractSigning", "contract_signing");
        TABLE_ALIASES.put("discussfangyuanxinxi", "property_comment");
        TABLE_ALIASES.put("propertyComment", "property_comment");
        TABLE_ALIASES.put("newstype", "news_type");
        TABLE_ALIASES.put("newsType", "news_type");
        TABLE_ALIASES.put("kaifashang", "developer");
        TABLE_ALIASES.put("yonghu", "user");

        COLUMN_ALIASES.put("fangwuleixing", "house_type");
        COLUMN_ALIASES.put("fangwumingcheng", "property_name");
        COLUMN_ALIASES.put("fangwutupian", "property_image");
        COLUMN_ALIASES.put("louceng", "floor");
        COLUMN_ALIASES.put("tingshi", "rooms");
        COLUMN_ALIASES.put("diduan", "district");
        COLUMN_ALIASES.put("chushoujiage", "sale_price");
        COLUMN_ALIASES.put("xiangqing", "details");
        COLUMN_ALIASES.put("weizhi", "address");
        COLUMN_ALIASES.put("mianji", "floor_area");
        COLUMN_ALIASES.put("kaifashanghao", "developer_code");
        COLUMN_ALIASES.put("kaifashangming", "developer_name");
        COLUMN_ALIASES.put("chushouhetong", "sale_contract");
        COLUMN_ALIASES.put("lianxidianhua", "contact_phone");
        COLUMN_ALIASES.put("dengjishijian", "register_time");
        COLUMN_ALIASES.put("yuyueshijian", "appointment_time");
        COLUMN_ALIASES.put("dingdanbianhao", "order_no");
        COLUMN_ALIASES.put("yixiangjinzhanbi", "intent_deposit_ratio");
        COLUMN_ALIASES.put("yixiangdingjin", "intent_deposit");
        COLUMN_ALIASES.put("weikuanjine", "remaining_amount");
        COLUMN_ALIASES.put("shenfenzheng", "id_card");
        COLUMN_ALIASES.put("zhanghao", "account");
        COLUMN_ALIASES.put("xingming", "full_name");
        COLUMN_ALIASES.put("shouji", "phone");
        COLUMN_ALIASES.put("touxiang", "avatar");
        COLUMN_ALIASES.put("sfsh", "audit_status");
        COLUMN_ALIASES.put("shhf", "audit_reply");
        COLUMN_ALIASES.put("ispay", "is_paid");
        COLUMN_ALIASES.put("hetong", "contract");
        COLUMN_ALIASES.put("yonghuhetong", "user_contract");

        COLUMN_ALIASES.put("houseType", "house_type");
        COLUMN_ALIASES.put("propertyName", "property_name");
        COLUMN_ALIASES.put("propertyImage", "property_image");
        COLUMN_ALIASES.put("floorArea", "floor_area");
        COLUMN_ALIASES.put("salePrice", "sale_price");
        COLUMN_ALIASES.put("developerCode", "developer_code");
        COLUMN_ALIASES.put("developerName", "developer_name");
        COLUMN_ALIASES.put("saleContract", "sale_contract");
        COLUMN_ALIASES.put("contactPhone", "contact_phone");
        COLUMN_ALIASES.put("registerTime", "register_time");
        COLUMN_ALIASES.put("appointmentTime", "appointment_time");
        COLUMN_ALIASES.put("orderNo", "order_no");
        COLUMN_ALIASES.put("intentDepositRatio", "intent_deposit_ratio");
        COLUMN_ALIASES.put("intentDeposit", "intent_deposit");
        COLUMN_ALIASES.put("remainingAmount", "remaining_amount");
        COLUMN_ALIASES.put("idCard", "id_card");
        COLUMN_ALIASES.put("auditStatus", "audit_status");
        COLUMN_ALIASES.put("auditReply", "audit_reply");
        COLUMN_ALIASES.put("isPaid", "is_paid");
        COLUMN_ALIASES.put("userContract", "user_contract");
    }

    public static String table(String tableName) {
        if (tableName == null) {
            return null;
        }
        String alias = TABLE_ALIASES.get(tableName);
        return alias == null ? tableName : alias;
    }

    public static String column(String columnName) {
        if (columnName == null) {
            return null;
        }
        String alias = COLUMN_ALIASES.get(columnName);
        return alias == null ? columnName : alias;
    }

    public static void putAlias(Map<String, Object> row, String requestColumn, String dbColumn) {
        if (row != null && requestColumn != null && dbColumn != null && !requestColumn.equals(dbColumn) && row.containsKey(dbColumn)) {
            row.put(requestColumn, row.get(dbColumn));
        }
    }
}
