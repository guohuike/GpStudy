package com.ghk.study.template.jdbc;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

/**
 * @Title: MemberDao
 * @Package: com.ghk.study.template.jdbc
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: huike.guo
 * @date: 2021/5/2 19:25
 * @version: V1.0
 * <p>Company: Leyou(China) Chain Store Co.,Ltd</p >
 * <p>版权所有: Copyright1999-2021 leyou.com. All Rights Reserved</p >
 */
public class MemberDao  extends JdbcTemplate{

    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List executeQueryAll() throws Exception{
        String sql = "select * from member";
        List<?> objects = super.executeQuery(sql, new RowMapper<MemberEntity>() {
            @Override
            public MemberEntity mapRow(ResultSet resultSet, int index) throws Exception{
                MemberEntity memberEntity = new MemberEntity();
                memberEntity.setName(resultSet.getString("name"));
                memberEntity.setSex(resultSet.getString("sex"));
                return memberEntity;
            }
        },new Object[]{});
        return objects;

    }
}