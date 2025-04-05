package com.storemanagement.config;

import org.hibernate.MappingException;
import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.env.spi.NameQualifierSupport;
import org.hibernate.service.ServiceRegistry;

import java.sql.Types;

public class SQLiteDialect extends Dialect {

    public SQLiteDialect(DialectResolutionInfo info) {
        super(info);
    }

    @Override
    protected void registerColumnTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.registerColumnTypes(typeContributions, serviceRegistry);
    }

    @Override
    protected String columnType(int jdbcTypeCode) {
        return switch (jdbcTypeCode) {
            case Types.BIT, Types.INTEGER -> "integer";
            case Types.TINYINT -> "tinyint";
            case Types.SMALLINT -> "smallint";
            case Types.BIGINT -> "bigint";
            case Types.FLOAT -> "float";
            case Types.DOUBLE -> "double";
            case Types.NUMERIC -> "numeric";
            case Types.DECIMAL -> "decimal";
            case Types.CHAR -> "char";
            case Types.VARCHAR -> "varchar";
            case Types.LONGVARCHAR -> "longvarchar";
            case Types.DATE -> "date";
            case Types.TIME -> "time";
            case Types.TIMESTAMP -> "datetime";
            case Types.BINARY, Types.VARBINARY, Types.LONGVARBINARY -> "blob";
            default -> super.columnType(jdbcTypeCode);
        };
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl() {
            @Override
            public boolean supportsIdentityColumns() {
                return true;
            }

            @Override
            public String getIdentitySelectString(String table, String column, int type) throws MappingException {
                return "select last_insert_rowid()";
            }

            @Override
            public String getIdentityColumnString(int type) {
                return "primary key autoincrement";
            }
        };
    }

    @Override
    public boolean hasAlterTable() {
        return false;
    }

    @Override
    public NameQualifierSupport getNameQualifierSupport() {
        return NameQualifierSupport.NONE;
    }
}
