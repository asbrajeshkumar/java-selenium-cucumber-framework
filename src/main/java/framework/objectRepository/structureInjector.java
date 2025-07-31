package framework.objectRepository;

import java.lang.reflect.Field;

public class structureInjector {
	
	public static void inject(Object page, OrLoader loader) {
        Class<?> clazz = page.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Structure.class)) {
                Structure structure = field.getAnnotation(Structure.class);
                String locatorName = structure.name();

                orStruct locator = loader.get(locatorName);
                if (locator != null) {
                    try {
                        field.setAccessible(true);
                        field.set(page, locator);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to inject locator: " + locatorName, e);
                    }
                }
            }
        }
    }
}
