package novedades_junit5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RandomMaQuiParametersExtension implements ParameterResolver {

	private final Random randomGenerator;

	public RandomMaQuiParametersExtension() {
		super();
		this.randomGenerator = new Random();
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return parameterContext.isAnnotated(RandomMaquiParameter.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		Object value = null;
		try {
			value = this.setValuesToObject(parameterContext.getParameter(), extensionContext);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
		        | IllegalArgumentException | InvocationTargetException e) {
			log.error(e.getMessage());
		}
		return value;
	}

	private Object setValuesToObject(Parameter parameter, ExtensionContext extensionContext)
	        throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException,
	        IllegalArgumentException, InvocationTargetException {
		Class<?> type = parameter.getType();

		List<Object> randomValues = new ArrayList<>();

		Constructor<?> constructor = type.getDeclaredConstructors()[0];

		Stream.of(constructor.getParameterTypes())
		        .forEach(this.generateRandomFromClass(randomValues));

		return constructor.newInstance(randomValues.toArray());
	}

	/**
	 * Crea un {@link Consumer} que genera un valor aleatorio dependiendo de la
	 * clase que sea su tipo y los mete a la lista pasada por parámetro:
	 * <p>
	 * <li>Class</td>
	 * <li>Short</td>
	 * <li>Integer</td>
	 * <li>Long</td>
	 * <li>BigDecimal</td>
	 * <li>Double</td>
	 * <li>Float</td>
	 * <li>Boolean</td>
	 * <p>
	 * 
	 * @param randomValues {@link List}< Object > a ser rellenada por los valores
	 *                     generados
	 * 
	 * @return {@link Consumer} < ? super Class<?> >
	 */
	private Consumer<? super Class<?>> generateRandomFromClass(List<Object> randomValues) {
		return e -> {
			if (e.equals(String.class)) {
				randomValues.add(UUID.randomUUID()
				        .toString());
			} else if (e.equals(Short.class)) {
				randomValues.add((short) this.randomGenerator.nextInt(Short.MAX_VALUE + 1));
			} else if (e.equals(Integer.class)) {
				randomValues.add(this.randomGenerator.nextInt());
			} else if (e.equals(Long.class)) {
				randomValues.add(this.randomGenerator.nextLong());
			} else if (e.equals(BigDecimal.class)) {
				randomValues.add(new BigDecimal(this.randomGenerator.nextDouble()));
			} else if (e.equals(Double.class)) {
				randomValues.add(this.randomGenerator.nextDouble());
			} else if (e.equals(Float.class)) {
				randomValues.add(this.randomGenerator.nextFloat());
			} else if (e.equals(Boolean.class)) {
				randomValues.add(this.randomGenerator.nextBoolean());
			}

		};
	}

}
