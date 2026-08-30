package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ================================================================
 *                            ITEM
 * ================================================================
 *
 * Sistema de itens de LAST CHANCE.
 *
 * Os itens podem:
 *
 * - alterar o jogo
 * - aumentar confiança de Alice
 * - diminuir medo
 * - revelar memórias
 * - interferir nas ações do Dealer
 * - proteger contra manipulações
 * - aumentar a instabilidade da máquina
 * - enfraquecer a influência do Dealer
 * - ajudar na redenção do Dealer
 *
 * IMPORTANTE:
 *
 * O Item NÃO decide sozinho o resultado da partida.
 * Ele apenas aplica modificadores ao GameState.
 *
 * ================================================================
 */

public class Item {

    // ============================================================
    // TIPOS
    // ============================================================

    public enum ItemType {

        CARD_ITEM,

        TRUST_ITEM,

        MEMORY_ITEM,

        DEALER_ITEM,

        PROTECTION_ITEM,

        REDEMPTION_ITEM,

        STORY_ITEM,

        GAMBLE_ITEM
    }

    // ============================================================
    // EFEITOS
    // ============================================================

    public enum EffectType {

        NONE,

        INCREASE_TRUST,

        DECREASE_FEAR,

        INCREASE_FEAR,

        INCREASE_SUSPICION,

        DECREASE_SUSPICION,

        INCREASE_CONFIDENCE,

        DECREASE_CONFIDENCE,

        REVEAL_MEMORY,

        REVEAL_DEALER,

        WEAKEN_DEALER,

        STABILIZE_MACHINE,

        DESTABILIZE_MACHINE,

        PROTECT_PLAYER,

        ALTER_BET,

        REVEAL_CARD,

        CHANGE_DIALOGUE,

        REDEMPTION,

        END_DEALER_INFLUENCE
    }

    // ============================================================
    // DADOS
    // ============================================================

    private final String id;

    private final String name;

    private final String description;

    private final ItemType type;

    private final EffectType effect;

    private final int power;

    private final boolean consumable;

    private boolean used;

    // ============================================================
    // CONSTRUTOR
    // ============================================================

    public Item(
        String id,
        String name,
        String description,
        ItemType type,
        EffectType effect,
        int power,
        boolean consumable
    ) {

        this.id = id;

        this.name = name;

        this.description = description;

        this.type = type;

        this.effect = effect;

        this.power = power;

        this.consumable = consumable;

        this.used = false;
    }

    // ============================================================
    // GETTERS
    // ============================================================

    public String getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }

    public ItemType getType() {

        return type;
    }

    public EffectType getEffect() {

        return effect;
    }

    public int getPower() {

        return power;
    }

    public boolean isConsumable() {

        return consumable;
    }

    public boolean isUsed() {

        return used;
    }

    // ============================================================
    // USAR ITEM
    // ============================================================

    public String use(
        GameState state,
        Girl alice
    ) {

        if (state == null) {

            return "ERRO: GameState inexistente.";
        }

        if (used && consumable) {

            return
                "O item já foi utilizado.";
        }

        String result;

        switch (effect) {

            // ----------------------------------------------------
            // CONFIANÇA
            // ----------------------------------------------------

            case INCREASE_TRUST:

                state.registerCompassionateChoice();

                alice.gainTrust(power);

                state.decreaseSuspicion(
                    Math.max(
                        1,
                        power / 2
                    )
                );

                result =
                    "Alice parece confiar um pouco mais em você.";

                break;

            // ----------------------------------------------------
            // MEDO
            // ----------------------------------------------------

            case DECREASE_FEAR:

                alice.decreaseFear(power);

                result =
                    "Alice parece respirar um pouco mais tranquila.";

                break;

            case INCREASE_FEAR:

                alice.increaseFear(power);

                result =
                    "Alice parece ficar mais nervosa.";

                break;

            // ----------------------------------------------------
            // SUSPEITA
            // ----------------------------------------------------

            case INCREASE_SUSPICION:

                state.increaseSuspicion(power);

                result =
                    "A desconfiança aumenta.";

                break;

            case DECREASE_SUSPICION:

                state.increaseSuspicion(
                    -power
                );

                result =
                    "A tensão parece diminuir.";

                break;

            // ----------------------------------------------------
            // CONFIANÇA DO JOGADOR
            // ----------------------------------------------------

            case INCREASE_CONFIDENCE:

                state.increasePlayerConfidence(
                    power
                );

                result =
                    "Leo recupera um pouco da confiança.";

                break;

            case DECREASE_CONFIDENCE:

                state.increasePlayerFear(
                    power
                );

                result =
                    "Leo começa a duvidar das próprias decisões.";

                break;

            // ----------------------------------------------------
            // MEMÓRIA
            // ----------------------------------------------------

            case REVEAL_MEMORY:

                revealMemory(
                    state
                );

                result =
                    "Uma memória esquecida começa a fazer sentido.";

                break;

            // ----------------------------------------------------
            // DEALER
            // ----------------------------------------------------

            case REVEAL_DEALER:

                state.discoverDealersIdentity();

                result =
                    "A verdadeira identidade do Dealer fica mais clara.";

                break;

            case WEAKEN_DEALER:

                state.setDealerManipulating(
                    false
                );

                state.increaseSuspicion(
                    power
                );

                result =
                    "A influência do Dealer sobre a mesa diminui.";

                break;

            // ----------------------------------------------------
            // MÁQUINA
            // ----------------------------------------------------

            case STABILIZE_MACHINE:

                result =
                    "A máquina parece funcionar de maneira mais estável.";

                break;

            case DESTABILIZE_MACHINE:

                result =
                    "A máquina começa a apresentar comportamento irregular.";

                break;

            // ----------------------------------------------------
            // PROTEÇÃO
            // ----------------------------------------------------

            case PROTECT_PLAYER:

                state.decreaseSuspicion(
                    power
                );

                state.decreasePlayerFear(
                    power
                );

                result =
                    "O item cria uma pequena proteção contra a influência externa.";

                break;

            // ----------------------------------------------------
            // APOSTA
            // ----------------------------------------------------

            case ALTER_BET:

                state.setCurrentBet(
                    power
                );

                result =
                    "A aposta foi alterada.";

                break;

            // ----------------------------------------------------
            // CARTA
            // ----------------------------------------------------

            case REVEAL_CARD:

                result =
                    "Uma informação sobre as cartas é revelada.";

                break;

            // ----------------------------------------------------
            // DIÁLOGO
            // ----------------------------------------------------

            case CHANGE_DIALOGUE:

                state.advanceDialogue();

                result =
                    "O item altera a conversa.";

                break;

            // ----------------------------------------------------
            // REDENÇÃO
            // ----------------------------------------------------

            case REDEMPTION:

                state.increaseSuspicion(
                    power
                );

                state.increaseRevelation();

                result =
                    "Algo dentro do Dealer parece enfraquecer.";

                break;

            // ----------------------------------------------------
            // FIM DA INFLUÊNCIA
            // ----------------------------------------------------

            case END_DEALER_INFLUENCE:

                state.discoverTruth();

                state.increaseRevelation();

                result =
                    "A presença do Dealer começa a perder força.";

                break;

            // ----------------------------------------------------
            // NENHUM
            // ----------------------------------------------------

            default:

                result =
                    "Nada parece acontecer.";

                break;
        }

        if (consumable) {

            used = true;
        }

        return result;
    }

    // ============================================================
    // MEMÓRIA
    // ============================================================

    private void revealMemory(
        GameState state
    ) {

        int revelation =
            state.getRevelationLevel();

        if (revelation < 10) {

            state.discoverBrother();

        } else if (revelation < 20) {

            state.discoverAlice();

        } else if (revelation < 30) {

            state.discoverRelationship();

        } else if (revelation < 40) {

            state.discoverMessages();

        } else if (revelation < 50) {

            state.discoverExpulsion();

        } else if (revelation < 60) {

            state.discoverBreakup();

        } else if (revelation < 70) {

            state.discoverAccident();

        } else {

            state.discoverTruth();
        }
    }

    // ============================================================
    // ITENS PRÉ-CONFIGURADOS
    // ============================================================

    public static Item oldPhoto() {

        return new Item(

            "old_photo",

            "Fotografia Antiga",

            "Uma fotografia que parece pertencer a uma época esquecida.",

            ItemType.MEMORY_ITEM,

            EffectType.REVEAL_MEMORY,

            10,

            true
        );
    }

    // ============================================================

    public static Item bracelet() {

        return new Item(

            "bracelet",

            "Pulseira",

            "Um objeto pequeno que Alice parece reconhecer.",

            ItemType.TRUST_ITEM,

            EffectType.INCREASE_TRUST,

            12,

            true
        );
    }

    // ============================================================

    public static Item letter() {

        return new Item(

            "letter",

            "Carta Amassada",

            "Uma carta que nunca chegou ao destino.",

            ItemType.STORY_ITEM,

            EffectType.REVEAL_MEMORY,

            15,

            true
        );
    }

    // ============================================================

    public static Item familyPhoto() {

        return new Item(

            "family_photo",

            "Fotografia de Família",

            "Uma fotografia antiga da família de Leo.",

            ItemType.MEMORY_ITEM,

            EffectType.REVEAL_MEMORY,

            20,

            true
        );
    }

    // ============================================================

    public static Item coffee() {

        return new Item(

            "coffee",

            "Café",

            "Uma coisa banal em uma situação que deixou de ser banal.",

            ItemType.TRUST_ITEM,

            EffectType.DECREASE_FEAR,

            8,

            true
        );
    }

    // ============================================================

    public static Item luckyCard() {

        return new Item(

            "lucky_card",

            "Carta Marcada",

            "Uma carta que parece ter sido preparada para momentos específicos.",

            ItemType.CARD_ITEM,

            EffectType.REVEAL_CARD,

            1,

            true
        );
    }

    // ============================================================

    public static Item redToken() {

        return new Item(

            "red_token",

            "Ficha Vermelha",

            "O Dealer parece não gostar dessa ficha.",

            ItemType.DEALER_ITEM,

            EffectType.WEAKEN_DEALER,

            10,

            true
        );
    }

    // ============================================================

    public static Item mirror() {

        return new Item(

            "mirror",

            "Espelho Trincado",

            "O reflexo parece mostrar coisas que a sala não deveria conter.",

            ItemType.MEMORY_ITEM,

            EffectType.REVEAL_DEALER,

            20,

            true
        );
    }

    // ============================================================

    public static Item photographOfAlice() {

        return new Item(

            "alice_photo",

            "Fotografia de Alice",

            "Uma fotografia de antes de tudo mudar.",

            ItemType.TRUST_ITEM,

            EffectType.INCREASE_TRUST,

            20,

            true
        );
    }

    // ============================================================

    public static Item brothersKey() {

        return new Item(

            "brothers_key",

            "Chave Antiga",

            "Uma chave que Leo reconhece, embora não saiba mais de onde.",

            ItemType.MEMORY_ITEM,

            EffectType.REVEAL_MEMORY,

            25,

            true
        );
    }

    // ============================================================

    public static Item brokenWatch() {

        return new Item(

            "broken_watch",

            "Relógio Quebrado",

            "Os ponteiros estão parados em um horário específico.",

            ItemType.STORY_ITEM,

            EffectType.REVEAL_MEMORY,

            30,

            true
        );
    }

    // ============================================================
    // ITENS CONTRA O DEALER
    // ============================================================

    public static Item oldCoin() {

        return new Item(

            "old_coin",

            "Moeda Antiga",

            "O Dealer parece reconhecer a moeda imediatamente.",

            ItemType.DEALER_ITEM,

            EffectType.WEAKEN_DEALER,

            15,

            true
        );
    }

    // ============================================================

    public static Item emptyFrame() {

        return new Item(

            "empty_frame",

            "Moldura Vazia",

            "Uma moldura sem fotografia. O Dealer evita olhar para ela.",

            ItemType.DEALER_ITEM,

            EffectType.WEAKEN_DEALER,

            20,

            true
        );
    }

    // ============================================================

    public static Item oldKey() {

        return new Item(

            "old_key",

            "Chave do Passado",

            "Uma chave que parece representar algo que o Dealer perdeu.",

            ItemType.REDEMPTION_ITEM,

            EffectType.REDEMPTION,

            15,

            true
        );
    }

    // ============================================================

    public static Item finalLetter() {

        return new Item(

            "final_letter",

            "Carta Nunca Entregue",

            "Uma última mensagem que poderia ter mudado muita coisa.",

            ItemType.REDEMPTION_ITEM,

            EffectType.REDEMPTION,

            30,

            true
        );
    }

    // ============================================================

    public static Item brothersMemory() {

        return new Item(

            "brothers_memory",

            "Memória Perdida",

            "Uma lembrança que o Dealer tentou esquecer.",

            ItemType.REDEMPTION_ITEM,

            EffectType.REDEMPTION,

            25,

            true
        );
    }

    // ============================================================

    public static Item salvationToken() {

        return new Item(

            "salvation_token",

            "Ficha da Última Chance",

            "Uma ficha que representa a possibilidade de abandonar o ciclo.",

            ItemType.REDEMPTION_ITEM,

            EffectType.END_DEALER_INFLUENCE,

            50,

            true
        );
    }

    // ============================================================
    // INVENTÁRIO
    // ============================================================

    public static class Inventory {

        private final List<Item> items;

        public Inventory() {

            items =
                new ArrayList<>();
        }

        // --------------------------------------------------------
        // ADICIONAR
        // --------------------------------------------------------

        public void add(
            Item item
        ) {

            if (item == null) {

                return;
            }

            items.add(item);
        }

        // --------------------------------------------------------
        // REMOVER
        // --------------------------------------------------------

        public boolean remove(
            String id
        ) {

            for (int i = 0; i < items.size(); i++) {

                if (
                    items.get(i)
                        .getId()
                        .equals(id)
                ) {

                    items.remove(i);

                    return true;
                }
            }

            return false;
        }

        // --------------------------------------------------------
        // BUSCAR
        // --------------------------------------------------------

        public Item find(
            String id
        ) {

            for (Item item : items) {

                if (
                    item.getId()
                       .equals(id)
                ) {

                    return item;
                }
            }

            return null;
        }

        // --------------------------------------------------------
        // USAR
        // --------------------------------------------------------

        public String use(
            String id,
            GameState state,
            Girl alice
        ) {

            Item item =
                find(id);

            if (item == null) {

                return
                    "Item não encontrado.";
            }

            return item.use(
                state,
                alice
            );
        }

        // --------------------------------------------------------
        // TAMANHO
        // --------------------------------------------------------

        public int size() {

            return items.size();
        }

        // --------------------------------------------------------
        // LISTAR
        // --------------------------------------------------------

        public String list() {

            if (items.isEmpty()) {

                return
                    "INVENTÁRIO VAZIO.";
            }

            StringBuilder result =
                new StringBuilder();

            result.append(
                "==============================\n"
            );

            result.append(
                "          INVENTÁRIO\n"
            );

            result.append(
                "==============================\n"
            );

            for (Item item : items) {

                result.append(
                    "- "
                );

                result.append(
                    item.getName()
                );

                if (
                    item.isUsed()
                ) {

                    result.append(
                        " [USADO]"
                    );
                }

                result.append("\n");
            }

            result.append(
                "=============================="
            );

            return result.toString();
        }

        // --------------------------------------------------------
        // ITENS DISPONÍVEIS
        // --------------------------------------------------------

        public List<Item> getItems() {

            return new ArrayList<>(
                items
            );
        }
    }

    // ============================================================
    // LOOT INICIAL
    // ============================================================

    public static Inventory createStartingInventory() {

        Inventory inventory =
            new Inventory();

        inventory.add(
            coffee()
        );

        inventory.add(
            oldPhoto()
        );

        return inventory;
    }

    // ============================================================
    // ITEM ALEATÓRIO
    // ============================================================

    public static Item randomItem() {

        Random random =
            new Random();

        int choice =
            random.nextInt(12);

        switch (choice) {

            case 0:
                return oldPhoto();

            case 1:
                return bracelet();

            case 2:
                return letter();

            case 3:
                return familyPhoto();

            case 4:
                return coffee();

            case 5:
                return luckyCard();

            case 6:
                return redToken();

            case 7:
                return mirror();

            case 8:
                return oldCoin();

            case 9:
                return oldKey();

            case 10:
                return brokenWatch();

            default:
                return brothersKey();
        }
    }
}
