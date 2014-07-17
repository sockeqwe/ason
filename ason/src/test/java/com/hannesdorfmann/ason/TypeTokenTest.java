package com.hannesdorfmann.ason;

import com.hannesdorfmann.ason.model.User;
import com.hannesdorfmann.ason.reflect.TypeToken;
import com.hannesdorfmann.ason.typeadapter.TypeAdapter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Hannes Dorfmann
 */
public class TypeTokenTest {

  @Test
  public void test(){

    final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache =
        Collections.synchronizedMap(new HashMap<TypeToken<?>, TypeAdapter<?>>());


    TypeToken<User> typeToken1 = TypeToken.get(User.class);

    TypeToken<User> typeToken2 = TypeToken.get(User.class);

    Assert.assertEquals(typeToken1.hashCode(), typeToken2.hashCode());
    Assert.assertEquals(typeToken1, typeToken2);

  }
}
