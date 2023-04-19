package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

public class IPokemonTrainerFactoryTest {

    @Mock
    private IPokemonTrainerFactory mockedFactory;

    protected PokemonTrainer trainer;

    protected IPokedexFactory mockedPokedexFactory;

    @Before
    public void initTestEnvironment(){
        mockedFactory = Mockito.mock(IPokemonTrainerFactory.class);
        mockedPokedexFactory = Mockito.mock(IPokedexFactory.class);
        IPokedex mockedPokedex = Mockito.mock(IPokedex.class);
        trainer = new PokemonTrainer("Nom", Team.MYSTIC, mockedPokedex);
        Mockito.when(mockedFactory.createTrainer("Nom", Team.MYSTIC, mockedPokedexFactory)).thenReturn(trainer);
    }


    @Test
    public void testCreateTrainer(){
        Assert.assertEquals(trainer, mockedFactory.createTrainer("Nom", Team.MYSTIC, mockedPokedexFactory));
    }
}
