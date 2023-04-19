package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.util.HashMap;
import java.util.Map;

public class IPokedexTest {

    @Mock
    private IPokedex mockedPockedex;

    protected HashMap<Integer, Pokemon> validPokemons = new HashMap<>();

    @Before
    public void initTestEnvironment() throws PokedexException {
        mockedPockedex = Mockito.mock(IPokedex.class);

        Pokemon bulbizarre = new Pokemon(0,  "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 613, 64, 4000, 4, 1.0);

        validPokemons.put(0, bulbizarre);
        validPokemons.put(133, aquali);

        Mockito.when(mockedPockedex.getPokemon(-1)).thenThrow(new PokedexException("Not found"));
        Mockito.when(mockedPockedex.getPokemon(0)).thenReturn(validPokemons.get(0));
        Mockito.when(mockedPockedex.getPokemon(133)).thenReturn(validPokemons.get(133));
        Mockito.when(mockedPockedex.addPokemon(validPokemons.get(0))).thenReturn(0);
        Mockito.when(mockedPockedex.addPokemon(validPokemons.get(133))).thenReturn(133);
    }

    @Test
    public void testGetPokemonWithMockito() throws PokedexException {
        for (Map.Entry<Integer, Pokemon> entry : validPokemons.entrySet()){
            Assert.assertEquals(mockedPockedex.getPokemon(entry.getKey()), entry.getValue());
        }
        Assert.assertThrows(PokedexException.class, () -> {
           mockedPockedex.getPokemon(-1);
        });
    }

    @Test
    public void testAddPokemonWithMockito(){
        for (Map.Entry<Integer, Pokemon> entry : validPokemons.entrySet()){
            Assert.assertEquals((int) entry.getKey(), mockedPockedex.addPokemon(entry.getValue()));
        }
    }
}
