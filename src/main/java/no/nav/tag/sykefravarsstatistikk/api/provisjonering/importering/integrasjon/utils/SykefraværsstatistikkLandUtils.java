package no.nav.tag.sykefravarsstatistikk.api.provisjonering.importering.integrasjon.utils;

import no.nav.tag.sykefravarsstatistikk.api.domene.statistikk.SykefraværsstatistikkLand;
import no.nav.tag.sykefravarsstatistikk.api.domene.statistikk.ÅrstallOgKvartal;
import no.nav.tag.sykefravarsstatistikk.api.provisjonering.importering.integrasjon.CreateSykefraværsstatistikkFunction;
import no.nav.tag.sykefravarsstatistikk.api.provisjonering.importering.integrasjon.DeleteSykefraværsstatistikkFunction;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class SykefraværsstatistikkLandUtils extends SykefraværsstatistikkIntegrasjon
    implements SykefraværsstatistikkIntegrasjonUtils {

  public static final String ARSTALL = "ARSTALL";
  public static final String KVARTAL = "KVARTAL";
  public static final String TAPTE_DAGSVERK = "TAPTE_DAGSVERK";
  public static final String MULIGE_DAGSVERK = "MULIGE_DAGSVERK";

  public SykefraværsstatistikkLandUtils(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    super(namedParameterJdbcTemplate);
  }

  @Override
  public DeleteSykefraværsstatistikkFunction getDeleteFunction() {
    DeleteSykefraværsstatistikkFunction function =
        (ÅrstallOgKvartal årstallOgKvartal) -> {
          SqlParameterSource namedParameters =
              new MapSqlParameterSource()
                  .addValue(ARSTALL, årstallOgKvartal.getÅrstall())
                  .addValue(KVARTAL, årstallOgKvartal.getKvartal());

          int antallSlettet =
              namedParameterJdbcTemplate.update(
                  String.format(
                      "delete from SYKEFRAVAR_STATISTIKK_LAND where arstall = :%s and kvartal = :%s",
                      ARSTALL, KVARTAL),
                  namedParameters);
          return antallSlettet;
        };
    return function;
  }

  @Override
  public CreateSykefraværsstatistikkFunction getCreateFunction() {
    CreateSykefraværsstatistikkFunction<SykefraværsstatistikkLand> function =
        (SykefraværsstatistikkLand sykefraværsstatistikkLand) -> {
          SqlParameterSource namedParameters =
              new MapSqlParameterSource()
                  .addValue(ARSTALL, sykefraværsstatistikkLand.getÅr())
                  .addValue(KVARTAL, sykefraværsstatistikkLand.getKvartal())
                  .addValue(TAPTE_DAGSVERK, sykefraværsstatistikkLand.getTapteDagsverk())
                  .addValue(MULIGE_DAGSVERK, sykefraværsstatistikkLand.getMuligeDagsverk());

          return namedParameterJdbcTemplate.update(
              String.format(
                  "insert into SYKEFRAVAR_STATISTIKK_LAND (arstall, kvartal, tapte_dagsverk, mulige_dagsverk)  "
                      + "values (:ARSTALL, :KVARTAL, :TAPTE_DAGSVERK, :MULIGE_DAGSVERK)",
                  ARSTALL,
                  KVARTAL,
                  TAPTE_DAGSVERK,
                  MULIGE_DAGSVERK),
              namedParameters);
        };

    return function;
  }
}
