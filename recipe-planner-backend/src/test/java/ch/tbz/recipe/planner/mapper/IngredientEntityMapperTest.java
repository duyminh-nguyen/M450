package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

class IngredientEntityMapperTest {

    private final IngredientEntityMapper mapper = Mappers.getMapper(IngredientEntityMapper.class);

    @Test
    void entityToDomain_mapsAllFields() {
        UUID id = UUID.randomUUID();
        IngredientEntity entity = new IngredientEntity(id, "Tomato", "The big ones", Unit.PIECE, 5);

        Ingredient domain = mapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain).isNotNull();
        softly.assertThat(domain.getId()).isEqualTo(entity.getId());
        softly.assertThat(domain.getName()).isEqualTo(entity.getName());
        softly.assertThat(domain.getComment()).isEqualTo(entity.getComment());
        softly.assertThat(domain.getUnit()).isEqualTo(entity.getUnit());
        softly.assertThat(domain.getAmount()).isEqualTo(entity.getAmount());
        softly.assertAll();
    }

    @Test
    void domainToEntity_mapsAllFields() {
        UUID id = UUID.randomUUID();
        Ingredient domain = new Ingredient(id, "Salt", "Fine", Unit.GRAMM, 10);

        IngredientEntity entity = mapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity).isNotNull();
        softly.assertThat(entity.getId()).isEqualTo(domain.getId());
        softly.assertThat(entity.getName()).isEqualTo(domain.getName());
        softly.assertThat(entity.getComment()).isEqualTo(domain.getComment());
        softly.assertThat(entity.getUnit()).isEqualTo(domain.getUnit());
        softly.assertThat(entity.getAmount()).isEqualTo(domain.getAmount());
        softly.assertAll();
    }

    @Test
    void entitiesToDomains_mapsList() {
        IngredientEntity e1 = new IngredientEntity(UUID.randomUUID(), "Tomato", "Big", Unit.PIECE, 2);
        IngredientEntity e2 = new IngredientEntity(UUID.randomUUID(), "Flour", "White", Unit.GRAMM, 500);

        List<Ingredient> domains = mapper.entitiesToDomains(List.of(e1, e2));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domains).hasSize(2);

        softly.assertThat(domains.get(0).getId()).isEqualTo(e1.getId());
        softly.assertThat(domains.get(0).getName()).isEqualTo(e1.getName());
        softly.assertThat(domains.get(0).getComment()).isEqualTo(e1.getComment());
        softly.assertThat(domains.get(0).getUnit()).isEqualTo(e1.getUnit());
        softly.assertThat(domains.get(0).getAmount()).isEqualTo(e1.getAmount());

        softly.assertThat(domains.get(1).getId()).isEqualTo(e2.getId());
        softly.assertThat(domains.get(1).getName()).isEqualTo(e2.getName());
        softly.assertThat(domains.get(1).getComment()).isEqualTo(e2.getComment());
        softly.assertThat(domains.get(1).getUnit()).isEqualTo(e2.getUnit());
        softly.assertThat(domains.get(1).getAmount()).isEqualTo(e2.getAmount());

        softly.assertAll();
    }

    @Test
    void domainsToEntities_mapsList() {
        Ingredient d1 = new Ingredient(UUID.randomUUID(), "Milk", "Fresh", Unit.DECILITRE, 3);
        Ingredient d2 = new Ingredient(UUID.randomUUID(), "Rice", "Jasmine", Unit.KILOGRAMM, 1);

        List<IngredientEntity> entities = mapper.domainsToEntities(List.of(d1, d2));

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entities).hasSize(2);

        softly.assertThat(entities.get(0).getId()).isEqualTo(d1.getId());
        softly.assertThat(entities.get(0).getName()).isEqualTo(d1.getName());
        softly.assertThat(entities.get(0).getComment()).isEqualTo(d1.getComment());
        softly.assertThat(entities.get(0).getUnit()).isEqualTo(d1.getUnit());
        softly.assertThat(entities.get(0).getAmount()).isEqualTo(d1.getAmount());

        softly.assertThat(entities.get(1).getId()).isEqualTo(d2.getId());
        softly.assertThat(entities.get(1).getName()).isEqualTo(d2.getName());
        softly.assertThat(entities.get(1).getComment()).isEqualTo(d2.getComment());
        softly.assertThat(entities.get(1).getUnit()).isEqualTo(d2.getUnit());
        softly.assertThat(entities.get(1).getAmount()).isEqualTo(d2.getAmount());

        softly.assertAll();
    }
}
