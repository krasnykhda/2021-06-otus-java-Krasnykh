package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {
    private final EntityClassMetaData metaData;


    public EntitySQLMetaDataImpl(EntityClassMetaData metaData) {
        this.metaData = metaData;
    }

    @Override
    public String getSelectAllSql() {
        return "Select * from " + metaData.getName();

    }

    public EntityClassMetaData getMetaData() {
        return metaData;
    }

    @Override
    public String getSelectByIdSql() {
        return "Select * from " + metaData.getName() + " where " + metaData.getIdField().getName() + "=?";
    }

    @Override
    public String getInsertSql() {
        String queryBegin = "insert into " + metaData.getName() + "(";
        String queryEnd = "values(";
        int count = 1;
        List<Field> fields = metaData.getFieldsWithoutId();
        for (Field field : fields) {
            queryBegin = queryBegin + field.getName();
            queryEnd = queryEnd + "?";
            if (count < fields.size()) {
                queryBegin = queryBegin + ",";
                queryEnd = queryEnd + ",";
            } else {
                queryBegin = queryBegin + ")";
                queryEnd = queryEnd + ")";
            }
            count++;
        }
        return queryBegin + queryEnd;

    }

    @Override
    public String getUpdateSql() {
        String queryBegin = "update " + metaData.getName() + " set ";
        String queryEnd = "where ";
        int count = 1;
        List<Field> fields = metaData.getFieldsWithoutId();
        for (Field field : fields) {
            queryBegin = queryBegin + field.getName() + "= ? ";
            if (count < fields.size()) {
                queryBegin = queryBegin + ",";
            }
            count++;
        }
        var idFields = metaData.getIdField();
        queryEnd += idFields.getName() + "=?";
        return queryBegin + queryEnd;
    }
}
