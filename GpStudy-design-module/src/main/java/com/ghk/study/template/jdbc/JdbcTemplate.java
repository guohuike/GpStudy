package com.ghk.study.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: JdbcTemplate
 * @Package: com.ghk.study.template.jdbc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 18:54
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected  List<?> executeQuery(String sql, RowMapper rowMapper, Object[]paramArray){
        List<?> resultList = null;
        try{
            //加载驱动
            loadDrive("com.mysql.....");
            //获得连接
            Connection connection = this.getConnection();
            //获得prepareStatement
            PreparedStatement preparedStatement = this.getPreparedStatement(connection,sql);
            //执行sql,获得返回值
            ResultSet resultSet = this.doExecute(preparedStatement,sql,paramArray);
            //得到resultSet获取处理返回值
            resultList = this.handlerResult(resultSet,rowMapper);
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;

    }

    protected  List<?> handlerResult(ResultSet resultSet,RowMapper rowMapper) throws Exception{
        ArrayList<Object> objects = new ArrayList<>();
        int i = 0;
        while (resultSet.next()){
            objects.add(rowMapper.mapRow(resultSet,i++));
        }
        return objects;
    }

    protected ResultSet doExecute(PreparedStatement preparedStatement, String sql, Object[] paramArray) throws Exception{
        for (int i = 0; i < paramArray.length; i++) {
            preparedStatement.setObject(i,paramArray[i]);
        }
        return preparedStatement.executeQuery(sql);
    }

    protected PreparedStatement getPreparedStatement(Connection connection,String sql)throws Exception{
        return connection.prepareStatement(sql);
    }

    protected Connection getConnection()throws Exception{
        return this.dataSource.getConnection();
    }

    private void loadDrive(String derveName)throws Exception{
        Class.forName(derveName);
    }
}