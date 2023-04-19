package fr.univavignon.pokedex.api;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;

public class IPokedexFactoryTest {

    @Mock
    private IPokedexFactory mockedPokedexFactory;
    @Mock
    private IPokemonFactory mockedPokemonFactory;

    @Mock
    private IPokemonMetadataProvider mockedProvider;

    @Mock
    private IPokedex mockedPokedex;

    @Before
    public void initTestEnvironment(){
        mockedPokedexFactory = Mockito.mock(IPokedexFactory.class);
        mockedPokemonFactory = Mockito.mock(IPokemonFactory.class);
        mockedProvider = Mockito.mock(IPokemonMetadataProvider.class);
        mockedPokedex = Mockito.mock(IPokedex.class);

        Mockito.when(mockedPokedexFactory.createPokedex(mockedProvider, mockedPokemonFactory)).thenReturn(mockedPokedex);
    }

    @Test
    public void testCreatePokedex(){
        Assert.assertEquals(mockedPokedexFactory.createPokedex(mockedProvider, mockedPokemonFactory), mockedPokedex);
    }
}
