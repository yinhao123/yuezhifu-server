package com.linln.common.mysql;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 重写数据库方言，设置默认字符集为utf8
 * @author Eric
 * @date 2019/05/09
 */
public class MySQLDialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
