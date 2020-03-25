package com.endava.weather.repository;

import com.endava.weather.report.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class ReportRepository {

    private static final RowMapper<Report> REPORT_ROW_MAPPER = (rs, rowNum) -> Report.builder()
            .id(rs.getLong("id"))
            .creationTimestamp(rs.getTimestamp("creation_timestamp").toLocalDateTime())
            .content(rs.getBytes("content"))
            .fileName(rs.getString("file_name"))
            .build();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Report findById(long reportId) {
        String sql = "select id, creation_timestamp, content, file_name from reports.report r where r.id = :id";
        return jdbcTemplate.queryForObject(sql, Map.of("id", reportId), REPORT_ROW_MAPPER);
    }

    public Report save(Report report) {
        String sql = "insert into reports.report(creation_timestamp, content, file_name) values " +
                "(:timestamp, :content, :file_name)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        parameterSource.addValue("timestamp", timestamp);
        parameterSource.addValue("content", report.getContent());
        parameterSource.addValue("file_name", report.getFileName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, parameterSource, keyHolder);
        report.setId((Long) keyHolder.getKeys().get("id"));
        report.setCreationTimestamp(timestamp.toLocalDateTime());
        return report;
    }

    public List<Report> findAll() {
        String sql = "select id, creation_timestamp, content, file_name from reports.report";
        return jdbcTemplate.query(sql, REPORT_ROW_MAPPER);
    }

    public void deleteById(long reportId) {
        String sql = "delete from reports.report r where r.id = :id";
        jdbcTemplate.update(sql, Map.of("id", reportId));
    }
}
