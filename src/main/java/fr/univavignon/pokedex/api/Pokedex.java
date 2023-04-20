package fr.univavignon.pokedex.api;

import java.util.*;

public class Pokedex implements IPokedex {

    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;
    private final LinkedHashMap<Integer, Pokemon> pokemons = new LinkedHashMap<>();

    Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory){
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }
    @Override
    public int size() {
        return pokemons.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        int index = pokemon.getIndex();
        pokemons.put(pokemons.size(), pokemon);
        return index;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        Pokemon pokemon = pokemons.get(id);
        if(pokemon == null){
            throw new PokedexException("Not found");
        }
        return pokemon;
    }

    @Override
    public List<Pokemon> getPokemons() {
        return new ArrayList<>(pokemons.values());
    }

    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
        return null;
    }

    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return metadataProvider.getPokemonMetadata(index);
    }
}
