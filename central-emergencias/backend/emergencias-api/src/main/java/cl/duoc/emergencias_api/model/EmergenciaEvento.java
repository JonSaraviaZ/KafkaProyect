
package cl.duoc.emergencias_api.model;

import java.time.Instant;

public record EmergenciaEvento(
        String id,
        String comuna,
        String tipo,
        String descripcion,
        Instant fecha
) {
}
