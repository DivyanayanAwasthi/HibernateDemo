package com.example.demo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pojos.Badge;

@Repository
public class BadgeRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedjdbcTemplate;

	public int count() {
		return jdbcTemplate.queryForObject("SELECT count(*) FROM oneprofile.badge;", Integer.class);
	}

//    public List<Badge> getBadges() {
//
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("badgestatus", "%" + 1 + "%");
//        mapSqlParameterSource.addValue("price", 100);
//
//        return namedjdbcTemplate.query(
//                "select * from oneprofile.badge where badge_id like :badgestatus",
//                mapSqlParameterSource,
//                (rs, rowNum) ->
//                        new Badge(
//                                rs.getLong("badge_id"),
//                                rs.getString("badgename")
//                        )
//        );
//    }

//	public List<Badge> getAllEmployeesRowMapper() {
//		return namedjdbcTemplate.query("select * from oneprofile.badge", new RowMapper<Badge>() {
//			@Override
//			public Badge mapRow(ResultSet rs, int rownumber) throws SQLException {
//				Badge badge = new Badge();
//				badge.setBadgeId(rs.getLong("badge_id"));
//				badge.setBadgeName(rs.getString("badgename"));
//				return badge;
//			}
//		});
//
//	}
	
	 
	    public List<Badge> findEmployee() {
	        return namedjdbcTemplate.query("select * from oneprofile.badge where badge_id like :badgestatus", new MapSqlParameterSource(
	           "badgestatus", 1), new EmployeeMapper());
	    }
	    
	    private static final class EmployeeMapper implements RowMapper<Badge> {
	        public Badge mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Badge emp = new Badge();
	            emp.setBadgeId(rs.getLong("badge_id"));
	            emp.setBadgeName(rs.getString("badgename"));
	            return emp;
	        }
	    }
}
