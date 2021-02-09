package novedades_junit5;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Ejemplo {

	private String cadenaTexto;
	private Short numeroShort;
	private Integer numeroInteger;
	private Long numeroLong;
	private Double numeroDouble;
	private Float numeroFloat;
	private Boolean booleano;
	private BigDecimal bigDecimal;

	@Override
	public String toString() {
		return "cadenaTexto=" + cadenaTexto + "\nnumeroShort=" + numeroShort + "\nnumeroInteger=" + numeroInteger
		        + "\nnumeroLong=" + numeroLong + "\nnumeroDouble=" + numeroDouble + "\nnumeroFloat=" + numeroFloat
		        + "\nbooleano=" + booleano + "\nbigDecimal=" + bigDecimal + "]";
	}

}
