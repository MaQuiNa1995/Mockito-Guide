package novedades_junit5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Parameter;
import java.util.Random;
import java.util.UUID;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class RandomMaQuiParametersExtension implements ParameterResolver {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	public @interface RandomMaquiParameter {
	}

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return parameterContext.isAnnotated(RandomMaquiParameter.class);
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
		return getRandomValue(parameterContext.getParameter(), extensionContext);
	}

	private Object getRandomValue(Parameter parameter, ExtensionContext extensionContext) {
		Class<?> type = parameter.getType();
		Random random = extensionContext.getRoot()
		        .getStore(Namespace.GLOBAL)
		        .getOrComputeIfAbsent(Random.class);
		if (int.class.equals(type)) {
			return random.nextInt();
		}
		if (double.class.equals(type)) {
			return random.nextDouble();
		}
		if (String.class.equals(type)) {
			return UUID.randomUUID()
			        .toString();
		}

		throw new ParameterResolutionException("No has metido ni integer " + type);
	}

}
