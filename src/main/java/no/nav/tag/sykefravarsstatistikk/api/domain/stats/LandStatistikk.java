package no.nav.tag.sykefravarsstatistikk.api.domain.stats;

import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@ToString
@Builder
public class LandStatistikk {
    private final int arstall;
    private final int kvartal;
    private final BigDecimal tapteDagsverk;
    private final BigDecimal muligeDagsverk;

    public BigDecimal beregnSykkefravarProsent() {
        return (tapteDagsverk.multiply(new BigDecimal(100)).divide(muligeDagsverk, 1, RoundingMode.HALF_UP));
    }

}
