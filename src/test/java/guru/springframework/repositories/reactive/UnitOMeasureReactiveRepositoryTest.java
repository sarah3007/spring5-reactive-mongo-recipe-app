package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
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
public class UnitOMeasureReactiveRepositoryTest {

  @Autowired
  UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

  @Before
  public void setUp() throws Exception {
    unitOfMeasureReactiveRepository.deleteAll().block();
  }

  @Test
  public void testSave() throws Exception {
    UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setDescription("Foo");

    unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

    Long count = unitOfMeasureReactiveRepository.count().block();

    assertEquals(Long.valueOf(1L), count);
  }

  @Test
  public void testFindByDescription() throws Exception {
    UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
    unitOfMeasure.setDescription("Foo");

    unitOfMeasureReactiveRepository.save(unitOfMeasure).then().block();

    UnitOfMeasure fetchedCat = unitOfMeasureReactiveRepository.findByDescription("Foo").block();

    assertNotNull(fetchedCat.getId());
  }
}
