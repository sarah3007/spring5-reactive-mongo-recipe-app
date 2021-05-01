package guru.springframework.repositories.reactive;

import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryTest {

  @Autowired
  RecipeReactiveRepository recipeReactiveRepository;

  @Before
  public void setUp() throws Exception {
    recipeReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSave() throws Exception {
    Long count = recipeReactiveRepository.count().block();

    assertEquals(Long.valueOf(0L), count);

    Recipe recipe = new Recipe();
    recipe.setDescription("Foo");

    recipeReactiveRepository.save(recipe).block();

    count = recipeReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }

}
