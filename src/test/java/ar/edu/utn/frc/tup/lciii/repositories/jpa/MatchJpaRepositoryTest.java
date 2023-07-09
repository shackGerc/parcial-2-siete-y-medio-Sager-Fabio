package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.RoundEntity;
import ar.edu.utn.frc.tup.lciii.helpers.PlayerHelper;
import ar.edu.utn.frc.tup.lciii.helpers.RoundHelper;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
class MatchJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    void getAllByPlayerIdAndMatchStatusTest() {
        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(1L);
        matchEntity.setMatchStatus(MatchStatus.PLAYING);
        PlayerEntity player =
                playerJpaRepository.save(PlayerHelper.getPlayerEntity("email@email.com",
                        new BigDecimal(200)));
        matchEntity.setPlayer(player);
        matchEntity.setRounds(new ArrayList<>());
        matchEntity.getRounds().add(RoundHelper.getRoundEntity(matchEntity));
        // TODO: Completar el Test para validar que el metodo
        //  getAllByPlayerIdAndMatchStatus(Long playerId, MatchStatus matchStatus)
        //  de la clase MatchJpaRepository funciona.
        entityManager.merge(matchEntity);
        entityManager.flush();

        Optional<List<MatchEntity>> matchEntityListOptional = matchJpaRepository.getAllByPlayerIdAndMatchStatus(
                1L, MatchStatus.PLAYING
        );
        assertTrue(matchEntityListOptional.isPresent());
    }
}