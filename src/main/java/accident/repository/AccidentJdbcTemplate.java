package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AccidentJdbcTemplate implements accident.repository.Repository {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // ACCIDENT

    @Override
    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(
                            "INSERT INTO accident (name, text, address, accident_type_id) "
                                    + "VALUES (?, ?, ?, ?)",
                            new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    public Accident update(Accident accident) {
        jdbc.update(
                "UPDATE accident SET name = ?, text = ?, address = ?, accident_type_id = ? "
                        + "WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("DELETE FROM accident_rules WHERE accident_id = ?", accident.getId());
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rules (accident_id, rule_id) VALUES (?, ?)",
                    accident.getId(),
                    rule.getId());
        }
        return accident;
    }

    @Override
    public List<Accident> getAll() {
        return jdbc.query("select * from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("accident_type_id")));
                    accident.setRules(getRulesByAccidentId(accident.getId()));
                    return accident;
                });
    }

    @Override
    public Accident findAccidentById(int id) {
        return jdbc.query("SELECT * FROM accident WHERE id=?",
                new BeanPropertyRowMapper<>(Accident.class), id)
                .stream().findAny().orElse(null);
    }

    // ACCIDENTTYPE
    @Override
    public List<AccidentType> getAccidentTypes() {
        return jdbc.query("SELECT * FROM accident_type",
                (resultSet, i) -> AccidentType.builder()
                        .name(resultSet.getString("name"))
                        .id(resultSet.getInt("id"))
                        .build());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return jdbc.query("SELECT * FROM accident_type WHERE id=?",
                new BeanPropertyRowMapper<>(AccidentType.class), id)
                .stream().findAny().orElse(null);
    }

    // RULE
    @Override
    public Rule findRuleById(int id) {
        return jdbc.query("SELECT * FROM rule WHERE id=?",
                new BeanPropertyRowMapper<>(Rule.class), id)
                .stream().findAny().orElse(null);
    }

    public List<Rule> getRulesByAccidentId(int accidentId) {
        return new ArrayList<>(
                jdbc.query("SELECT * FROM rule JOIN accident_rules "
                                + "ON (rule.id = accident_rules.rule_id) "
                                + "WHERE accident_rules.accident_id = ?",
                        new BeanPropertyRowMapper<>(Rule.class), accidentId));
    }

    @Override
    public List<Rule> getRules() {
        return jdbc.query("SELECT * FROM rule",
                (resultSet, i) -> Rule.builder()
                        .name(resultSet.getString("name"))
                        .id(resultSet.getInt("id"))
                        .build());
    }
}
