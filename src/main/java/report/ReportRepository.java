package report;

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
            .creationTimestamp(rs.getTimestamp(2).toLocalDateTime())
            .content(rs.getBytes(3))
            .fileName(rs.getString(4))
            .build();

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Report findById(long reportId) {
        String sql = "select id, creation_timestamp, content, file_name from reports.report r where r.id = :id";
        return jdbcTemplate.queryForObject(sql, Map.of("id", reportId), REPORT_ROW_MAPPER);
    }

    public Report save(Report report) {
        String sql = "insert into reports.report(creation_timestamp, content, file_name) " +
                "values (:creation_timestamp, :content, :file_name)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Timestamp timestamp = new Timestamp(new Date().getTime());
        report.setCreationTimestamp(timestamp.toLocalDateTime());
        parameterSource.addValue("creation_timestamp", timestamp);
        parameterSource.addValue("content", report.getContent());
        parameterSource.addValue("file_name", report.getFileName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, parameterSource, keyHolder);
        report.setId((Long) keyHolder.getKeys().get("id"));
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
