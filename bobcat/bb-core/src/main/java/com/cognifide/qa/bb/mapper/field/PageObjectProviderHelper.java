package com.cognifide.qa.bb.mapper.field;

/*-
 * #%L
 * Bobcat Parent
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import com.cognifide.qa.bb.qualifier.PageObject;

/**
 * Helper class for Page Objects
 */
public class PageObjectProviderHelper {

  private PageObjectProviderHelper() {
    // Empty for helper class
  }

  /**
   * Gets selector from {@link PageObject} if class annoted by this annotation is used in list
   * 
   * @param field
   * @return selector
   */
  public static By getSelectorFromGenericPageObject(Field field) {
    return retrieveSelectorFromPageObject(field, true);
  }

  /**
   * Gets selector from {@link PageObject}
   * 
   * @param field
   * @return selector
   */
  public static By getSelectorFromPageObject(Field field) {
    return retrieveSelectorFromPageObject(field, false);
  }

  /**
   * Gets generic type from field (if field type is {@link ParameterizedType})
   * @param field
   * @return
   */
  public static Class<?> getGenericType(Field field) {
    Type type = field.getGenericType();
    if (type instanceof ParameterizedType) {
      Type firstParameter = ((ParameterizedType) type).getActualTypeArguments()[0];
      if (!(firstParameter instanceof WildcardType)) {
        return (Class<?>) firstParameter;
      }
    }
    return null;
  }

  private static By retrieveSelectorFromPageObject(Field field, boolean useGeneric) {
    By toReturn = null;
    String cssValue = useGeneric
        ? PageObjectProviderHelper.getGenericType(field).getAnnotation(PageObject.class).css()
        : field.getType().getAnnotation(PageObject.class).css();
    String xpathValue = useGeneric
        ? PageObjectProviderHelper.getGenericType(field).getAnnotation(PageObject.class).xpath()
        : field.getType().getAnnotation(PageObject.class).xpath();
    if (StringUtils.isNotEmpty(cssValue)) {
      toReturn = By.cssSelector(cssValue);
    } else if (StringUtils.isNoneEmpty(xpathValue)) {
      toReturn = By.xpath(xpathValue);
    }
    if (toReturn == null) {
      throw new IllegalArgumentException(
          "PageObject has to have defined selector when used with FindPageObject annotation");
    }
    return toReturn;
  }
}
