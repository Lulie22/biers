package be.vdab.beers.repositories;

import be.vdab.beers.domain.Brouwer;
import be.vdab.beers.dto.BrouwerMetGemeente;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BrouwerRepository {
    private final JdbcTemplate template;
    private final RowMapper<Brouwer> brouwerMapper = (result,rowNr) ->
            new Brouwer(result.getLong("id"),result.getString("naam"),
                    result.getString("straat"),result.getString("huisNr"),
                    result.getInt("postcode"),result.getString("gemeente"),
                    result.getInt("omzet"));
    public BrouwerRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<BrouwerMetGemeente> findBrouwersMetNaamEnGemeente(){
        RowMapper<BrouwerMetGemeente> mapper = (result,rowNr) ->
                new BrouwerMetGemeente(result.getLong("id"),
                        result.getString("naam"),
                        result.getString("gemeente"));
        try {
            var sql = """
                  select id,naam,gemeente
                  from brouwers
                  order by naam;
                  """;
        return template.query(sql,mapper);
        }catch (IncorrectResultSizeDataAccessException ex){
            return List.of();
        }
    }
    public Optional<Brouwer> findById(long id){
        try {
            var sql = """
                  select id,naam,straat,huisNr,postcode,gemeente,omzet
                  from brouwers
                  where id = ?
                  order by naam;
                  """;
        return Optional.of(template.queryForObject(sql,brouwerMapper,id));
        }catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

}
