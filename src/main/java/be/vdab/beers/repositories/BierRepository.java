package be.vdab.beers.repositories;

import be.vdab.beers.domain.Bier;
import be.vdab.beers.dto.BierNaamLijst;
import be.vdab.beers.exceptions.BierNietGevondenException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BierRepository {
    private final JdbcTemplate template;
    private final RowMapper<Bier> bierMapper = (result, rowNr) ->
            new Bier(result.getLong("id"), result.getString("naam"),
                    result.getLong("brouwerId"), result.getInt("alcohol"),
                    result.getBigDecimal("prijs"), result.getLong("besteld"));

    public BierRepository(JdbcTemplate template) {
        this.template = template;
    }

    public Optional<Bier> findById(long id) {
        try {
            var sql = """
                    select id,naam,brouwerId,alcohol,prijs,besteld
                    from bieren
                    where id = ?
                    """;
            return Optional.of(template.queryForObject(sql, bierMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    public long aantalBieren() {
        var sql = """
                select count(*)
                from bieren
                """;
        return template.queryForObject(sql, Long.class);
    }

    public List<BierNaamLijst> findByBrouwerId(long id) {
        RowMapper<BierNaamLijst> mapper = (result, rowNum) ->
                new BierNaamLijst(result.getLong("id"), result.getString("naam"));
        try {
            var sql = """
                    select id,naam
                    from bieren
                    where brouwerId = ?
                    order by naam
                    """;
            return template.query(sql, mapper, id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return List.of();
        }
    }

    public void update(Bier bier) {
        var sql = """
                update bieren
                set besteld =  besteld + 1
                where id = ?
                """;

        if (template.update(sql,bier.getId())==0){
            throw new BierNietGevondenException();
        }
    }
}
