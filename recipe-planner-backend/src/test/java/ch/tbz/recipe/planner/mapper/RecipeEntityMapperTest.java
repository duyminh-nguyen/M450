package ch.tbz.recipe.planner.mapper;

import ch.tbz.recipe.planner.domain.Ingredient;
import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.domain.Unit;
import ch.tbz.recipe.planner.entities.IngredientEntity;
import ch.tbz.recipe.planner.entities.RecipeEntity;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

class RecipeEntityMapperTest {

    private final RecipeEntityMapper mapper = Mappers.getMapper(RecipeEntityMapper.class);

    @Test
    void entityToDomain_mapsAllFields_includingIngredients() {
        UUID recipeId = UUID.randomUUID();
        IngredientEntity i1 = new IngredientEntity(UUID.randomUUID(), "Tomato", "Big", Unit.PIECE, 2);
        IngredientEntity i2 = new IngredientEntity(UUID.randomUUID(), "Salt", "Fine", Unit.GRAMM, 10);

        RecipeEntity entity = new RecipeEntity(
                recipeId,
                "Lasagne",
                "Nice",
                "https://example.com/img.jpg",
                List.of(i1, i2)
        );

        Recipe domain = mapper.entityToDomain(entity);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(domain).isNotNull();
        softly.assertThat(domain.getId()).isEqualTo(entity.getId());
        softly.assertThat(domain.getName()).isEqualTo(entity.getName());
        softly.assertThat(domain.getDescription()).isEqualTo(entity.getDescription());
        softly.assertThat(domain.getImageUrl()).isEqualTo(entity.getImageUrl());

        softly.assertThat(domain.getIngredients()).isNotNull();
        softly.assertThat(domain.getIngredients()).hasSize(2);

        Ingredient d1 = domain.getIngredients().get(0);
        softly.assertThat(d1.getId()).isEqualTo(i1.getId());
        softly.assertThat(d1.getName()).isEqualTo(i1.getName());
        softly.assertThat(d1.getComment()).isEqualTo(i1.getComment());
        softly.assertThat(d1.getUnit()).isEqualTo(i1.getUnit());
        softly.assertThat(d1.getAmount()).isEqualTo(i1.getAmount());

        Ingredient d2 = domain.getIngredients().get(1);
        softly.assertThat(d2.getId()).isEqualTo(i2.getId());
        softly.assertThat(d2.getName()).isEqualTo(i2.getName());
        softly.assertThat(d2.getComment()).isEqualTo(i2.getComment());
        softly.assertThat(d2.getUnit()).isEqualTo(i2.getUnit());
        softly.assertThat(d2.getAmount()).isEqualTo(i2.getAmount());

        softly.assertAll();
    }

    @Test
    void domainToEntity_mapsAllFields_includingIngredients() {
        UUID recipeId = UUID.randomUUID();
        Ingredient d1 = new Ingredient(UUID.randomUUID(), "Milk", "Fresh", Unit.LITRE, 1);
        Ingredient d2 = new Ingredient(UUID.randomUUID(), "Rice", "Jasmine", Unit.GRAMM, 200);

        Recipe domain = new Recipe(
                recipeId,
                "Fried Rice",
                "Tasty",
                "https://example.com/fried-rice.jpg",
                List.of(d1, d2)
        );

        RecipeEntity entity = mapper.domainToEntity(domain);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(entity).isNotNull();
        softly.assertThat(entity.getId()).isEqualTo(domain.getId());
        softly.assertThat(entity.getName()).isEqualTo(domain.getName());
        softly.assertThat(entity.getDescription()).isEqualTo(domain.getDescription());
        softly.assertThat(entity.getImageUrl()).isEqualTo(domain.getImageUrl());

        softly.assertThat(entity.getIngredients()).isNotNull();
        softly.assertThat(entity.getIngredients()).hasSize(2);

        IngredientEntity e1 = entity.getIngredients().get(0);
        softly.assertThat(e1.getId()).isEqualTo(d1.getId());
        softly.assertThat(e1.getName()).isEqualTo(d1.getName());
        softly.assertThat(e1.getComment()).isEqualTo(d1.getComment());
        softly.assertThat(e1.getUnit()).isEqualTo(d1.getUnit());
        softly.assertThat(e1.getAmount()).isEqualTo(d1.getAmount());

        IngredientEntity e2 = entity.getIngredients().get(1);
        softly.assertThat(e2.getId()).isEqualTo(d2.getId());
        softly.assertThat(e2.getName()).isEqualTo(d2.getName());
        softly.assertThat(e2.getComment()).isEqualTo(d2.getComment());
        softly.assertThat(e2.getUnit()).isEqualTo(d2.getUnit());
        softly.assertThat(e2.getAmount()).isEqualTo(d2.getAmount());

        softly.assertAll();
    }
}
