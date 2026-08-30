```javascript
"use strict";

/*
===============================================================
                       LAST CHANCE
                       GAME.JS
===============================================================

Motor principal da versão web.

Responsável por:
- Leo
- Alice
- Dealer
- Relógio
- Baralho
- Truco
- Apostas
- Confiança
- Medo
- Suspeita
- Influência do Dealer
- Descobertas
- Diálogos
- Finais

A interface será controlada posteriormente pelo HTML + CSS.
===============================================================
*/


// =============================================================
// CONFIGURAÇÕES
// =============================================================

const CONFIG = {

    START_TIME: {
        hour: 23,
        minute: 47,
        second: 0
    },

    MAX_STAT: 100,

    CARDS_PER_PLAYER: 3,

    MAX_TRUCO_VALUE: 12,

    TIME_PER_ACTION: 10,

    TIME_PER_DIALOGUE: 20

};


// =============================================================
// ESTADO GLOBAL
// =============================================================

const Game = {

    running: false,

    state: "INTRO",

    round: 1,

    playerScore: 0,

    aliceScore: 0,

    tableValue: 1,

    instantVictory: false,

    gameFinished: false,

    ending: null,

    clock: {

        hour: CONFIG.START_TIME.hour,

        minute: CONFIG.START_TIME.minute,

        second: CONFIG.START_TIME.second,

        running: true

    },

    discoveries: {

        dealerIdentity: false,

        relationship: false,

        familyConflict: false,

        accident: false,

        truth: false

    }

};


// =============================================================
// LEO
// =============================================================

const Player = {

    name: "Leo",

    health: 100,

    maxHealth: 100,

    fear: 20,

    confidence: 50,

    mentalStability: 100,

    dealerResistance: 0,

    chips: 100,

    hand: [],

    roundsWon: 0,

    roundsLost: 0,

    trucoCalls: 0,

    bluffs: 0,

    compassionateChoices: 0,

    cruelChoices: 0,


    damageMental(amount) {

        this.mentalStability -= amount;

        clampPlayer();

    },


    recoverMental(amount) {

        this.mentalStability += amount;

        clampPlayer();

    },


    increaseFear(amount) {

        this.fear += amount;

        clampPlayer();

    },


    decreaseFear(amount) {

        this.fear -= amount;

        clampPlayer();

    },


    increaseConfidence(amount) {

        this.confidence += amount;

        clampPlayer();

    },


    decreaseConfidence(amount) {

        this.confidence -= amount;

        clampPlayer();

    },


    resistDealer(amount) {

        this.dealerResistance += amount;

        clampPlayer();

    },


    loseChips(amount) {

        this.chips -= amount;

        if (this.chips < 0) {

            this.chips = 0;

        }

    },


    gainChips(amount) {

        this.chips += amount;

    }

};


// =============================================================
// ALICE
// =============================================================

const Alice = {

    name: "Alice",

    trust: 20,

    fear: 35,

    suspicion: 10,

    anger: 5,

    hope: 40,

    score: 0,

    hand: [],

    turn: false,

    remembersBrother: false,

    knowsDealerIdentity: false,


    increaseTrust(amount) {

        this.trust += amount;

        clampAlice();

    },


    decreaseTrust(amount) {

        this.trust -= amount;

        clampAlice();

    },


    increaseFear(amount) {

        this.fear += amount;

        clampAlice();

    },


    decreaseFear(amount) {

        this.fear -= amount;

        clampAlice();

    },


    increaseSuspicion(amount) {

        this.suspicion += amount;

        clampAlice();

    },


    decreaseSuspicion(amount) {

        this.suspicion -= amount;

        clampAlice();

    },


    increaseHope(amount) {

        this.hope += amount;

        clampAlice();

    },


    decreaseHope(amount) {

        this.hope -= amount;

        clampAlice();

    },


    increaseAnger(amount) {

        this.anger += amount;

        clampAlice();

    },


    decreaseAnger(amount) {

        this.anger -= amount;

        clampAlice();

    },


    getMood() {

        if (this.fear >= 80) {

            return "APAVORADA";

        }

        if (this.anger >= 75) {

            return "FURIOSA";

        }

        if (this.suspicion >= 70) {

            return "DESCONFIADA";

        }

        if (this.trust >= 75) {

            return "CONFIANTE";

        }

        if (this.hope >= 70) {

            return "ESPERANÇOSA";

        }

        return "TENSIONADA";

    }

};


// =============================================================
// DEALER
// =============================================================

const Dealer = {

    name: "Dealer",

    influence: 30,

    redemption: 0,

    patience: 100,

    irritation: 0,

    identityRevealed: false,

    active: true,


    lines: [

        "Boa noite, Leo.",

        "Vamos jogar.",

        "Você está tremendo.",

        "Não precisa ficar nervoso.",

        "Alice, você confia nele?",

        "Isso está ficando divertido.",

        "Não parem agora.",

        "Eu esperava mais de vocês.",

        "Vocês realmente não lembram?",

        "Que memória conveniente.",

        "Ah, essa expressão.",

        "Não olha para mim assim.",

        "Eu estou apenas ajudando.",

        "Vocês dois têm muito o que conversar.",

        "Essa partida vai durar o quanto eu quiser.",

        "Não estraguem minha diversão.",

        "Você sempre foi curioso, Leo.",

        "Alice sabe mais do que imagina.",

        "Talvez vocês devessem confiar em mim.",

        "Ou talvez não."

    ],


    speak() {

        if (!this.active) {

            return;

        }

        const index =
            Math.floor(
                Math.random() *
                this.lines.length
            );

        dialogue(
            this.name,
            this.lines[index]
        );

    },


    increaseInfluence(amount) {

        this.influence += amount;

        clampDealer();

    },


    decreaseInfluence(amount) {

        this.influence -= amount;

        clampDealer();

    },


    increaseRedemption(amount) {

        this.redemption += amount;

        clampDealer();

    },


    increaseIrritation(amount) {

        this.irritation += amount;

        clampDealer();

    }

};


// =============================================================
// BARALHO
// =============================================================

const Deck = {

    cards: [

        "4♣",
        "5♣",
        "6♣",
        "7♣",

        "Q♥",
        "J♥",
        "K♥",

        "A♠",
        "2♠",
        "3♠",

        "Q♦",
        "J♦",
        "K♦",

        "A♣",
        "2♣",
        "3♣"

    ],


    create() {

        return [
            ...this.cards
        ];

    },


    shuffle(deck) {

        for (
            let i = deck.length - 1;
            i > 0;
            i--
        ) {

            const j =
                Math.floor(
                    Math.random() *
                    (i + 1)
                );

            [
                deck[i],
                deck[j]
            ] =
            [
                deck[j],
                deck[i]
            ];

        }

        return deck;

    }

};


let CurrentDeck = [];


// =============================================================
// INVENTÁRIO
// =============================================================

const Inventory = {

    items: [],


    add(item) {

        if (
            !this.items.includes(item)
        ) {

            this.items.push(item);

        }

    },


    remove(item) {

        const index =
            this.items.indexOf(item);

        if (index !== -1) {

            this.items.splice(
                index,
                1
            );

        }

    },


    has(item) {

        return this.items.includes(item);

    }

};


// =============================================================
// INICIAR JOGO
// =============================================================

function startGame() {

    Game.running = true;

    Game.state = "INTRO";

    Game.gameFinished = false;

    Game.ending = null;

    showDialogueSequence();

}


// =============================================================
// INTRO
// =============================================================

function showDialogueSequence() {

    const sequence = [

        {
            speaker: "Dealer",
            text: "Boa noite, Leo."
        },

        {
            speaker: "Leo",
            text: "Onde eu estou?"
        },

        {
            speaker: "Alice",
            text: "Eu... também não sei."
        },

        {
            speaker: "Dealer",
            text: "Não importa."
        },

        {
            speaker: "Dealer",
            text: "Sentem-se."
        },

        {
            speaker: "Alice",
            text: "Você conhece ele?"
        },

        {
            speaker: "Dealer",
            text: "Conheço."
        },

        {
            speaker: "Leo",
            text: "De onde?"
        },

        {
            speaker: "Dealer",
            text: "Isso vocês vão descobrir."

        }

    ];


    let delay = 0;


    sequence.forEach(
        line => {

            setTimeout(
                () => {

                    dialogue(
                        line.speaker,
                        line.text
                    );

                },
                delay
            );

            delay += 1700;

        }
    );


    setTimeout(
        () => {

            Game.state = "WAITING";

            render();

        },
        delay + 500
    );

}


// =============================================================
// INICIAR RODADA
// =============================================================

function startRound() {

    if (
        Game.gameFinished
    ) {

        return;

    }


    resetRound();

    CurrentDeck =
        Deck.shuffle(
            Deck.create()
        );


    dealCards();

    Game.state =
        "PLAYER_TURN";


    advanceTime(5);

    render();

}


// =============================================================
// RESET DA RODADA
// =============================================================

function resetRound() {

    Player.hand = [];

    Alice.hand = [];

    Game.tableValue = 1;

    Game.instantVictory = false;

}


// =============================================================
// DISTRIBUIR CARTAS
// =============================================================

function dealCards() {

    for (
        let i = 0;
        i < CONFIG.CARDS_PER_PLAYER;
        i++
    ) {

        Player.hand.push(
            CurrentDeck.pop()
        );

        Alice.hand.push(
            CurrentDeck.pop()
        );

    }

}


// =============================================================
// TURNO DO JOGADOR
// =============================================================

function playerPlayCard(index) {

    if (
        Game.state !== "PLAYER_TURN"
    ) {

        return;

    }


    if (
        index < 0 ||
        index >= Player.hand.length
    ) {

        return;

    }


    const card =
        Player.hand.splice(
            index,
            1
        )[0];


    dialogue(
        "Leo",
        `Eu jogo ${card}.`
    );


    advanceTime(
        CONFIG.TIME_PER_ACTION
    );


    Game.state =
        "ALICE_TURN";


    render();


    setTimeout(
        alicePlayCard,
        1000
    );

}


// =============================================================
// TURNO DA ALICE
// =============================================================

function alicePlayCard() {

    if (
        Alice.hand.length === 0
    ) {

        return;

    }


    const index =
        Math.floor(
            Math.random() *
            Alice.hand.length
        );


    const card =
        Alice.hand.splice(
            index,
            1
        )[0];


    dialogue(
        "Alice",
        "Minha vez."
    );


    advanceTime(
        CONFIG.TIME_PER_ACTION
    );


    Game.state =
        "DEALER_TURN";


    render();


    setTimeout(
        dealerTurn,
        1000
    );

}


// =============================================================
// DEALER
// =============================================================

function dealerTurn() {

    Dealer.speak();

    checkInstantVictory();

    dealerManipulation();

    advanceTime(10);


    Game.state =
        "WAITING";


    render();

}


// =============================================================
// MANIPULAÇÃO DO DEALER
// =============================================================

function dealerManipulation() {

    if (
        Dealer.influence >= 70
    ) {

        Alice.increaseSuspicion(3);

        Player.increaseFear(2);

    }


    if (
        Dealer.influence >= 90
    ) {

        Player.damageMental(2);

    }


    if (
        Dealer.irritation >= 70
    ) {

        Alice.increaseAnger(2);

    }


    clampEverything();

}


// =============================================================
// VITÓRIA INSTANTÂNEA
// =============================================================

function checkInstantVictory() {

    const playerDanger =
        Player.hand.length >= 3 &&
        Game.playerScore >= 10;


    const aliceDanger =
        Alice.hand.length >= 3 &&
        Game.aliceScore >= 10;


    if (
        playerDanger ||
        aliceDanger
    ) {

        Game.instantVictory = true;


        if (playerDanger) {

            dialogue(
                "Dealer",
                "Leo tem uma mão que pode acabar com isso."
            );

        }


        if (aliceDanger) {

            dialogue(
                "Dealer",
                "Alice também."
            );

        }

    }

}


// =============================================================
// TRUCO
// =============================================================

function callTruco() {

    if (
        Game.state !== "PLAYER_TURN"
    ) {

        return;

    }


    Player.trucoCalls++;


    Game.tableValue =
        Math.min(
            Game.tableValue * 2,
            CONFIG.MAX_TRUCO_VALUE
        );


    dialogue(
        "Leo",
        "TRUCO!"
    );


    Dealer.increaseIrritation(5);


    setTimeout(
        () => {

            dialogue(
                "Dealer",
                "Agora sim."

            );

        },
        700
    );


    advanceTime(15);


    render();

}


// =============================================================
// ESCOLHAS
// =============================================================

function chooseCompassion() {

    Player.compassionateChoices++;

    Player.increaseConfidence(2);

    Player.resistDealer(2);

    Alice.increaseTrust(7);

    Alice.increaseHope(5);

    Dealer.decreaseInfluence(2);

    Dealer.increaseRedemption(2);


    advanceTime(
        CONFIG.TIME_PER_DIALOGUE
    );


    updateRevelations();

    render();

}


function chooseSuspicion() {

    Alice.increaseSuspicion(5);

    Player.resistDealer(3);

    Dealer.increaseIrritation(2);

    Game.suspicion =
        (Game.suspicion || 0) + 8;


    advanceTime(
        CONFIG.TIME_PER_DIALOGUE
    );


    updateRevelations();

    render();

}


function chooseCruelty() {

    Player.cruelChoices++;

    Alice.decreaseTrust(7);

    Alice.increaseFear(5);

    Player.increaseFear(4);

    Dealer.increaseInfluence(4);

    Dealer.increaseIrritation(5);


    advanceTime(
        CONFIG.TIME_PER_DIALOGUE
    );


    updateRevelations();

    render();

}


function chooseSilence() {

    Player.increaseFear(2);

    Player.damageMental(1);

    advanceTime(10);

    render();

}


// =============================================================
// DESCOBERTAS
// =============================================================

function updateRevelations() {

    const suspicion =
        Game.suspicion || 0;


    if (
        suspicion >= 50 &&
        !Game.discoveries.dealerIdentity
    ) {

        Game.discoveries.dealerIdentity =
            true;

        Dealer.identityRevealed =
            true;

        Alice.knowsDealerIdentity =
            true;


        dialogue(
            "Dealer",
            "Finalmente."
        );

    }


    if (
        Alice.trust >= 65 &&
        !Game.discoveries.relationship
    ) {

        Game.discoveries.relationship =
            true;

        Alice.remembersBrother =
            true;


        dialogue(
            "Alice",
            "Eu acho que conhecia alguém parecido com você."
        );

    }


    if (
        suspicion >= 75 &&
        !Game.discoveries.truth
    ) {

        Game.discoveries.truth =
            true;

        Game.discoveries.familyConflict =
            true;

        Game.discoveries.accident =
            true;


        dialogue(
            "Dealer",
            "Vocês finalmente juntaram as peças."
        );

    }

}


// =============================================================
// FINAIS
// =============================================================

function calculateEnding() {

    let ending;


    // FINAL 6

    if (

        Alice.trust >= 85 &&

        Dealer.redemption >= 80 &&

        Game.discoveries.truth &&

        Game.discoveries.dealerIdentity

    ) {

        ending = 6;

    }


    // FINAL 5

    else if (

        Alice.trust >= 75 &&

        Dealer.redemption >= 60 &&

        Game.discoveries.truth

    ) {

        ending = 5;

    }


    // FINAL 4

    else if (

        Dealer.influence >= 80

    ) {

        ending = 4;

    }


    // FINAL 3

    else if (

        Dealer.redemption < 30 &&

        Alice.trust < 40

    ) {

        ending = 3;

    }


    // FINAL 2

    else if (

        Game.aliceScore >= 12

    ) {

        ending = 2;

    }


    // FINAL 1

    else {

        ending = 1;

    }


    Game.ending =
        ending;

    Game.gameFinished =
        true;

    Game.running =
        false;

    Game.clock.running =
        false;


    showEnding(
        ending
    );

}


// =============================================================
// FINAIS
// =============================================================

function showEnding(ending) {

    const endings = {

        1: {

            title: "A ÚLTIMA MÃO",

            text:
                "A partida terminou."

        },


        2: {

            title: "NÃO ERA PARA ELA",

            text:
                "Alice venceu. Mas a vitória não trouxe alívio."

        },


        3: {

            title: "A MESA VAZIA",

            text:
                "Ninguém realmente conseguiu sair daquela mesa."

        },


        4: {

            title: "O DEALER VENCE",

            text:
                "Ele conseguiu colocar os dois exatamente onde queria."

        },


        5: {

            title: "ÚLTIMA CHANCE",

            text:
                "Pela primeira vez, existe uma possibilidade de escapar."

        },


        6: {

            title: "ADEUS",

            text:
                "O Dealer finalmente deixa a mesa."

        }

    };


    const result =
        endings[ending];


    document.dispatchEvent(

        new CustomEvent(
            "lastChanceEnding",
            {

                detail: {

                    ending,

                    title:
                        result.title,

                    text:
                        result.text

                }

            }

        )

    );


    console.log(
        `FINAL ${ending}: ${result.title}`
    );

}


// =============================================================
// RELÓGIO
// =============================================================

function advanceTime(seconds) {

    if (
        !Game.clock.running
    ) {

        return;

    }


    Game.clock.second += seconds;


    while (
        Game.clock.second >= 60
    ) {

        Game.clock.second -= 60;

        Game.clock.minute++;

    }


    while (
        Game.clock.minute >= 60
    ) {

        Game.clock.minute -= 60;

        Game.clock.hour++;

    }


    while (
        Game.clock.hour >= 24
    ) {

        Game.clock.hour -= 24;

    }


    checkClockEvents();

}


function getTime() {

    return (

        String(
            Game.clock.hour
        ).padStart(2, "0") +

        ":" +

        String(
            Game.clock.minute
        ).padStart(2, "0") +

        ":" +

        String(
            Game.clock.second
        ).padStart(2, "0")

    );

}


// =============================================================
// EVENTOS DO RELÓGIO
// =============================================================

function checkClockEvents() {

    const hour =
        Game.clock.hour;

    const minute =
        Game.clock.minute;


    /*
       Eventos especiais podem ser
       adicionados aqui posteriormente.
    */


    if (
        hour === 0 &&
        minute === 0
    ) {

        Dealer.increaseInfluence(5);

    }

}


// =============================================================
// DIÁLOGO
// =============================================================

function dialogue(
    speaker,
    text
) {

    document.dispatchEvent(

        new CustomEvent(
            "lastChanceDialogue",
            {

                detail: {

                    speaker,

                    text

                }

            }

        )

    );


    console.log(
        `[${speaker}] ${text}`
    );

}


// =============================================================
// LIMITADORES
// =============================================================

function clamp(
    value,
    min = 0,
    max = 100
) {

    return Math.max(
        min,
        Math.min(
            max,
            value
        )
    );

}


function clampPlayer() {

    Player.health =
        clamp(
            Player.health
        );

    Player.fear =
        clamp(
            Player.fear
        );

    Player.confidence =
        clamp(
            Player.confidence
        );

    Player.mentalStability =
        clamp(
            Player.mentalStability
        );

    Player.dealerResistance =
        clamp(
            Player.dealerResistance
        );

}


function clampAlice() {

    Alice.trust =
        clamp(
            Alice.trust
        );

    Alice.fear =
        clamp(
            Alice.fear
        );

    Alice.suspicion =
        clamp(
            Alice.suspicion
        );

    Alice.anger =
        clamp(
            Alice.anger
        );

    Alice.hope =
        clamp(
            Alice.hope
        );

}


function clampDealer() {

    Dealer.influence =
        clamp(
            Dealer.influence
        );

    Dealer.redemption =
        clamp(
            Dealer.redemption
        );

    Dealer.patience =
        clamp(
            Dealer.patience
        );

    Dealer.irritation =
        clamp(
            Dealer.irritation
        );

}


function clampEverything() {

    clampPlayer();

    clampAlice();

    clampDealer();

}


// =============================================================
// RENDER
// =============================================================

function render() {

    renderClock();

    renderPlayer();

    renderAlice();

    renderDealer();

}


function renderClock() {

    document.dispatchEvent(

        new CustomEvent(
            "lastChanceClock",
            {

                detail: {

                    time:
                        getTime()

                }

            }

        )

    );

}


function renderPlayer() {

    document.dispatchEvent(

        new CustomEvent(
            "lastChancePlayer",
            {

                detail: {

                    health:
                        Player.health,

                    fear:
                        Player.fear,

                    confidence:
                        Player.confidence,

                    stability:
                        Player.mentalStability,

                    resistance:
                        Player.dealerResistance,

                    chips:
                        Player.chips,

                    hand:
                        [...Player.hand]

                }

            }

        )

    );

}


function renderAlice() {

    document.dispatchEvent(

        new CustomEvent(
            "lastChanceAlice",
            {

                detail: {

                    trust:
                        Alice.trust,

                    fear:
                        Alice.fear,

                    suspicion:
                        Alice.suspicion,

                    hope:
                        Alice.hope,

                    mood:
                        Alice.getMood(),

                    cards:
                        Alice.hand.length

                }

            }

        )

    );

}


function renderDealer() {

    document.dispatchEvent(

        new CustomEvent(
            "lastChanceDealer",
            {

                detail: {

                    influence:
                        Dealer.influence,

                    redemption:
                        Dealer.redemption,

                    irritation:
                        Dealer.irritation,

                    revealed:
                        Dealer.identityRevealed

                }

            }

        )

    );

}


// =============================================================
// LOOP
// =============================================================

function gameLoop() {

    if (
        !Game.running ||
        Game.gameFinished
    ) {

        return;

    }


    if (
        Game.state === "FINAL"
    ) {

        calculateEnding();

        return;

    }


    render();

}


// =============================================================
// API PÚBLICA
// =============================================================

window.LastChance = {

    Game,

    Player,

    Alice,

    Dealer,

    Inventory,

    Deck,

    startGame,

    startRound,

    playerPlayCard,

    callTruco,

    chooseCompassion,

    chooseSuspicion,

    chooseCruelty,

    chooseSilence,

    calculateEnding,

    advanceTime,

    getTime,

    dialogue

};


// =============================================================
// CARREGAMENTO
// =============================================================

document.addEventListener(
    "DOMContentLoaded",
    () => {

        console.log(
            "LAST CHANCE: game.js carregado."
        );

    }
);
```
