
package cl.duoc.emergencias_api.DTO;

import jakarta.validation.constraints.NotBlank;

public record CrearEmergenciaRequest(
        @NotBlank String comuna,
        @NotBlank String tipo,
        @NotBlank String descripcion
) {
}
