package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.HashMap;

public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider mockedProvider;

    protected HashMap<Integer, PokemonMetadata> pokemonsMetadata = new HashMap<>();
    protected ArrayList<Integer> errors = new ArrayList<>();

    @Before
    public void initTestEnvironment() throws PokedexException {
        mockedProvider = Mockito.mock(IPokemonMetadataProvider.class);

        errors.add(500);
        errors.add(-1);

        pokemonsMetadata.put(0, new PokemonMetadata(0,"Bulbizarre",126,126,90));
        pokemonsMetadata.put(133, new PokemonMetadata(133,"Aquali",186,168,260));

        Mockito.when(mockedProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException(""));
        Mockito.when(mockedProvider.getPokemonMetadata(500)).thenThrow(new PokedexException(""));
        Mockito.when(mockedProvider.getPokemonMetadata(0)).thenReturn(pokemonsMetadata.get(0));
        Mockito.when(mockedProvider.getPokemonMetadata(133)).thenReturn(pokemonsMetadata.get(133));
    }

    @Test
    public void testPokemonNotFoundException(){
        for(Integer index : errors){
            Assert.assertThrows(PokedexException.class, () -> {
                mockedProvider.getPokemonMetadata(index);
            });
        }
    }

    @Test
    public void testPokemonFound() throws PokedexException {
        for(PokemonMetadata pokemonMetadata : pokemonsMetadata.values()){
            Assert.assertEquals(mockedProvider.getPokemonMetadata(pokemonMetadata.getIndex()).getIndex(), pokemonMetadata.getIndex());
            Assert.assertEquals(mockedProvider.getPokemonMetadata(pokemonMetadata.getIndex()).getName(), pokemonMetadata.getName());
            Assert.assertEquals(mockedProvider.getPokemonMetadata(pokemonMetadata.getIndex()).getAttack(), pokemonMetadata.getAttack());
            Assert.assertEquals(mockedProvider.getPokemonMetadata(pokemonMetadata.getIndex()).getDefense(), pokemonMetadata.getDefense());
            Assert.assertEquals(mockedProvider.getPokemonMetadata(pokemonMetadata.getIndex()).getStamina(), pokemonMetadata.getStamina());
        }
    }
}
