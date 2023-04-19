package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

public class IPokemonFactoryTest {

    @Mock
    private IPokemonFactory mockedFactory;

    protected HashMap<Integer, Pokemon> validPokemons = new HashMap<>();

    @Before
    public void initTestEnvironment(){
        mockedFactory = Mockito.mock(IPokemonFactory.class);

        Pokemon bulbizarre = new Pokemon(0,  "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 613, 64, 4000, 4, 1.0);

        validPokemons.put(0, bulbizarre);
        validPokemons.put(133, aquali);

        Mockito.when(mockedFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(validPokemons.get(0));
        Mockito.when(mockedFactory.createPokemon(133, 2729, 202, 5000, 4)).thenReturn(validPokemons.get(133));

    }

    @Test
    public void testCreatePokemon(){
        Assert.assertEquals(mockedFactory.createPokemon(0, 613, 64, 4000, 4), validPokemons.get(0));
        Assert.assertEquals(mockedFactory.createPokemon(133, 2729, 202, 5000, 4), validPokemons.get(133));
    }
}
