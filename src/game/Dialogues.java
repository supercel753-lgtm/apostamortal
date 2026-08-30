```java
package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ================================================================
 *                         DIALOGUES
 * ================================================================
 *
 * Banco de diálogos de LAST CHANCE.
 *
 * 360 falas divididas em:
 *
 * 01 - Primeiro contato
 * 02 - Mesa
 * 03 - Alice
 * 04 - Leo
 * 05 - Dealer
 * 06 - Truco
 * 07 - Desconfiança
 * 08 - Memórias
 * 09 - Irmão
 * 10 - Alice e o irmão
 * 11 - Família
 * 12 - Acidente
 * 13 - Dealer manipulando
 * 14 - Dealer provocando
 * 15 - Confiança
 * 16 - Confronto
 * 17 - Redenção
 * 18 - Relógio
 * 19 - Finais ruins
 * 20 - Finais bons
 *
 * Cada grupo possui 18 falas.
 *
 * TOTAL: 20 x 18 = 360
 *
 * ================================================================
 */

public class Dialogues {

    private final Random random;

    public Dialogues() {

        random = new Random();
    }

    // ============================================================
    // ESTRUTURA DE DIÁLOGO
    // ============================================================

    public static class Dialogue {

        private final String speaker;
        private final String text;

        public Dialogue(
            String speaker,
            String text
        ) {

            this.speaker = speaker;
            this.text = text;
        }

        public String getSpeaker() {

            return speaker;
        }

        public String getText() {

            return text;
        }

        @Override
        public String toString() {

            return speaker + ": " + text;
        }
    }

    // ============================================================
    // 01 - PRIMEIRO CONTATO
    // ============================================================

    private Dialogue[] firstContact() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Você também não sabe onde estamos, sabe?"),

            new Dialogue("Leo",
                "Não."),

            new Dialogue("Alice",
                "Ótimo. Eu estava começando a achar que era só comigo."),

            new Dialogue("Dealer",
                "Que bonito. Dois perdidos na mesma mesa."),

            new Dialogue("Alice",
                "Eu não gosto da voz dele."),

            new Dialogue("Dealer",
                "E eu adoro quando alguém já começa me odiando."),

            new Dialogue("Leo",
                "Quem é você?"),

            new Dialogue("Dealer",
                "Hoje? O sujeito que distribui as cartas."),

            new Dialogue("Alice",
                "Hoje?"),

            new Dialogue("Dealer",
                "Você vai aprender a fazer perguntas melhores."),

            new Dialogue("Leo",
                "Alice, certo?"),

            new Dialogue("Alice",
                "Como você sabe meu nome?"),

            new Dialogue("Dealer",
                "Eu sei muitas coisas."),

            new Dialogue("Alice",
                "Isso não responde."),

            new Dialogue("Dealer",
                "Respostas são caras."),

            new Dialogue("Leo",
                "E qual é o preço?"),

            new Dialogue("Dealer",
                "Uma partida."),

            new Dialogue("Alice",
                "Então vamos jogar."
        };
    }

    // ============================================================
    // 02 - MESA
    // ============================================================

    private Dialogue[] table() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "Cartas na mesa. Olhos na mesa. Problemas fora da mesa."),

            new Dialogue("Alice",
                "Você fala demais."),

            new Dialogue("Dealer",
                "E você pensa alto demais."),

            new Dialogue("Leo",
                "Ele sempre foi assim?"),

            new Dialogue("Alice",
                "Sempre?"),

            new Dialogue("Dealer",
                "Interessante escolha de palavras."),

            new Dialogue("Alice",
                "Eu não escolhi nada."),

            new Dialogue("Dealer",
                "Ainda."),

            new Dialogue("Leo",
                "Qual é a aposta?"),

            new Dialogue("Dealer",
                "A que você tiver coragem de aceitar."),

            new Dialogue("Alice",
                "Isso não parece Truco."),

            new Dialogue("Dealer",
                "É Truco. Só não é um jogo comum."),

            new Dialogue("Leo",
                "Você está tentando nos assustar."),

            new Dialogue("Dealer",
                "Não preciso tentar."),

            new Dialogue("Alice",
                "Não olha para ele."),

            new Dialogue("Leo",
                "Por quê?"),

            new Dialogue("Alice",
                "Porque ele fica feliz quando alguém olha."),

            new Dialogue("Dealer",
                "Ela me conhece tão bem."
        };
    }

    // ============================================================
    // 03 - ALICE
    // ============================================================

    private Dialogue[] alice() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Eu não sou boa em conhecer pessoas."),

            new Dialogue("Leo",
                "Você parece estar indo bem."),

            new Dialogue("Alice",
                "Estou fingindo."),

            new Dialogue("Leo",
                "Por que está aqui?"),

            new Dialogue("Alice",
                "Se eu soubesse, já teria ido embora."),

            new Dialogue("Leo",
                "Você parece cansada."),

            new Dialogue("Alice",
                "Estou cansada há muito tempo."),

            new Dialogue("Dealer",
                "Que frase dramática."),

            new Dialogue("Alice",
                "Cala a boca."),

            new Dialogue("Dealer",
                "Não."),

            new Dialogue("Leo",
                "Você conhece esse lugar?"),

            new Dialogue("Alice",
                "Conheço a sensação."),

            new Dialogue("Leo",
                "Que sensação?"),

            new Dialogue("Alice",
                "De estar esperando alguma coisa terminar."),

            new Dialogue("Dealer",
                "Agora estamos chegando a algum lugar."),

            new Dialogue("Alice",
                "Não começa."),

            new Dialogue("Dealer",
                "Eu nem comecei."),

            new Dialogue("Alice",
                "Esse é exatamente o problema."
        };
    }

    // ============================================================
    // 04 - LEO
    // ============================================================

    private Dialogue[] leo() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Você está tremendo."),

            new Dialogue("Leo",
                "Estou bem."),

            new Dialogue("Alice",
                "Mentira."),

            new Dialogue("Leo",
                "Talvez."),

            new Dialogue("Alice",
                "Você parece conhecer essa voz."),

            new Dialogue("Leo",
                "Não conheço."),

            new Dialogue("Alice",
                "Você hesitou."),

            new Dialogue("Leo",
                "Eu só estou cansado."),

            new Dialogue("Dealer",
                "Ele mente mal."),

            new Dialogue("Leo",
                "Ninguém perguntou."),

            new Dialogue("Dealer",
                "Eu sei."),

            new Dialogue("Alice",
                "Leo."),

            new Dialogue("Leo",
                "O quê?"),

            new Dialogue("Alice",
                "Seu nome me parece familiar."),

            new Dialogue("Leo",
                "Talvez você tenha ouvido em algum lugar."),

            new Dialogue("Alice",
                "Talvez."),

            new Dialogue("Dealer",
                "Ou talvez não."),

            new Dialogue("Leo",
                "Você pode parar de falar?"
        };
    }

    // ============================================================
    // 05 - DEALER
    // ============================================================

    private Dialogue[] dealer() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "Senhoras e senhores, bem-vindos ao pior encontro do mundo."),

            new Dialogue("Dealer",
                "Eu faria uma piada, mas vocês dois já são a piada."),

            new Dialogue("Dealer",
                "Não façam essa cara. Eu preparei tudo com carinho."),

            new Dialogue("Dealer",
                "Carinho é uma palavra engraçada."),

            new Dialogue("Dealer",
                "Vocês deveriam confiar mais em mim."),

            new Dialogue("Alice",
                "Não."),

            new Dialogue("Dealer",
                "Resposta rápida. Gostei."),

            new Dialogue("Leo",
                "Você parece se divertir."),

            new Dialogue("Dealer",
                "Finalmente alguém percebeu."),

            new Dialogue("Dealer",
                "Eu estava entediado."),

            new Dialogue("Dealer",
                "Então pensei: por que não arruinar duas noites de uma vez?"),

            new Dialogue("Alice",
                "Você é insuportável."),

            new Dialogue("Dealer",
                "Obrigado."),

            new Dialogue("Dealer",
                "Não foi um elogio."),

            new Dialogue("Dealer",
                "Eu sei."),

            new Dialogue("Dealer",
                "Esse é o meu charme."),

            new Dialogue("Alice",
                "Você não tem charme."),

            new Dialogue("Dealer",
                "E mesmo assim vocês continuam ouvindo."
        };
    }

    // ============================================================
    // 06 - TRUCO
    // ============================================================

    private Dialogue[] truco() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "Truco."),

            new Dialogue("Alice",
                "Truco."),

            new Dialogue("Leo",
                "Seis."),

            new Dialogue("Dealer",
                "Olha só. Coragem."),

            new Dialogue("Alice",
                "Você tem certeza?"),

            new Dialogue("Leo",
                "Não."),

            new Dialogue("Alice",
                "Então por que aumentou?"),

            new Dialogue("Leo",
                "Porque ele queria que eu hesitasse."),

            new Dialogue("Dealer",
                "Eu? Jamais."),

            new Dialogue("Alice",
                "Você está sorrindo."),

            new Dialogue("Dealer",
                "Eu sempre sorrio."),

            new Dialogue("Alice",
                "Isso é preocupante."),

            new Dialogue("Dealer",
                "Só quando tenho uma boa mão."),

            new Dialogue("Leo",
                "Você nem está jogando."),

            new Dialogue("Dealer",
                "Detalhes."),

            new Dialogue("Alice",
                "Nove."),

            new Dialogue("Leo",
                "Você está blefando."),

            new Dialogue("Alice",
                "Talvez."
        };
    }

    // ============================================================
    // 07 - DESCONFIANÇA
    // ============================================================

    private Dialogue[] suspicion() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Ele sabe demais."),

            new Dialogue("Leo",
                "Também achei."),

            new Dialogue("Alice",
                "Você percebeu antes de mim."),

            new Dialogue("Leo",
                "Talvez eu já tenha ouvido essa voz."),

            new Dialogue("Alice",
                "Onde?"),

            new Dialogue("Leo",
                "Não sei."),

            new Dialogue("Dealer",
                "Que memória conveniente."),

            new Dialogue("Alice",
                "Você está escondendo alguma coisa."),

            new Dialogue("Dealer",
                "Todos estão."),

            new Dialogue("Leo",
                "Inclusive você."),

            new Dialogue("Dealer",
                "Principalmente eu."),

            new Dialogue("Alice",
                "Isso deveria me tranquilizar?"),

            new Dialogue("Dealer",
                "Não."),

            new Dialogue("Leo",
                "Por que você sabe tanto sobre nós?"),

            new Dialogue("Dealer",
                "Porque vocês deixam pistas por toda parte."),

            new Dialogue("Alice",
                "Nós nem conhecemos você."),

            new Dialogue("Dealer",
                "Esse é o detalhe mais engraçado."),

            new Dialogue("Alice",
                "Não tem graça nenhuma."
        };
    }

    // ============================================================
    // 08 - MEMÓRIAS
    // ============================================================

    private Dialogue[] memories() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Eu lembro de uma janela."),

            new Dialogue("Leo",
                "Que janela?"),

            new Dialogue("Alice",
                "Não sei."),

            new Dialogue("Alice",
                "Tinha alguém do outro lado."),

            new Dialogue("Dealer",
                "Memórias são péssimas testemunhas."),

            new Dialogue("Leo",
                "Você fala como se soubesse."),

            new Dialogue("Dealer",
                "Eu sei."),

            new Dialogue("Alice",
                "Eu lembro de rir."),

            new Dialogue("Leo",
                "Com quem?"),

            new Dialogue("Alice",
                "Com alguém que eu amava."),

            new Dialogue("Dealer",
                "Próxima carta."),

            new Dialogue("Alice",
                "Não muda de assunto."),

            new Dialogue("Dealer",
                "Eu faço o que quero."),

            new Dialogue("Leo",
                "Eu também lembro de alguém."),

            new Dialogue("Alice",
                "Quem?"),

            new Dialogue("Leo",
                "Meu irmão."),

            new Dialogue("Alice",
                "Você tinha um irmão?"),

            new Dialogue("Leo",
                "Tenho uma lembrança dele."
        };
    }

    // ============================================================
    // 09 - IRMÃO
    // ============================================================

    private Dialogue[] brother() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Como ele era?"),

            new Dialogue("Leo",
                "Irritante."),

            new Dialogue("Alice",
                "Engraçado."),

            new Dialogue("Leo",
                "Também."),

            new Dialogue("Alice",
                "Teimoso?"),

            new Dialogue("Leo",
                "Muito."),

            new Dialogue("Alice",
                "Ele gostava de música?"),

            new Dialogue("Leo",
                "Como você sabe disso?"),

            new Dialogue("Alice",
                "Eu não sei."),

            new Dialogue("Dealer",
                "Ela tem boa memória."),

            new Dialogue("Leo",
                "Você conhecia meu irmão?"),

            new Dialogue("Dealer",
                "Talvez."),

            new Dialogue("Leo",
                "Isso não é resposta."),

            new Dialogue("Dealer",
                "Eu adoro essa frase."),

            new Dialogue("Alice",
                "Ele tinha uma cicatriz na sobrancelha."),

            new Dialogue("Leo",
                "Tinha."),

            new Dialogue("Alice",
                "Então era ele."),

            new Dialogue("Leo",
                "Quem era ele para você?"
        };
    }

    // ============================================================
    // 10 - ALICE E O IRMÃO
    // ============================================================

    private Dialogue[] aliceAndBrother() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Ele dizia que tinha um irmão."),

            new Dialogue("Leo",
                "Ele nunca falava de você."),

            new Dialogue("Alice",
                "Talvez tivesse medo."),

            new Dialogue("Leo",
                "De mim?"),

            new Dialogue("Alice",
                "Da sua família."),

            new Dialogue("Dealer",
                "Ah."),

            new Dialogue("Leo",
                "Você sabia disso?"),

            new Dialogue("Dealer",
                "Eu sabia de muita coisa."),

            new Dialogue("Alice",
                "Ele aparecia escondido."),

            new Dialogue("Leo",
                "Na nossa casa?"),

            new Dialogue("Alice",
                "Não. Na minha."),

            new Dialogue("Leo",
                "Por quê?"),

            new Dialogue("Alice",
                "Porque ninguém podia saber."),

            new Dialogue("Dealer",
                "E alguém acabou sabendo."),

            new Dialogue("Leo",
                "Minha mãe."),

            new Dialogue("Alice",
                "Foi aí que tudo mudou."),

            new Dialogue("Leo",
                "O que ela fez?"),

            new Dialogue("Alice",
                "Mandou ele escolher."
        };
    }

    // ============================================================
    // 11 - FAMÍLIA
    // ============================================================

    private Dialogue[] family() {

        return new Dialogue[] {

            new Dialogue("Leo",
                "Minha mãe nunca gostou de segredos."),

            new Dialogue("Alice",
                "A minha também não."),

            new Dialogue("Leo",
                "Ela descobriu vocês."),

            new Dialogue("Alice",
                "Descobriu nós dois."),

            new Dialogue("Leo",
                "E expulsou meu irmão."),

            new Dialogue("Alice",
                "Sim."),

            new Dialogue("Leo",
                "Eu não sabia."),

            new Dialogue("Alice",
                "Ele não queria que você soubesse."),

            new Dialogue("Dealer",
                "Que família adorável."),

            new Dialogue("Leo",
                "Você não fala da minha família."),

            new Dialogue("Dealer",
                "Por quê?"),

            new Dialogue("Leo",
                "Porque você não conhece eles."),

            new Dialogue("Dealer",
                "Conheço mais do que você imagina."),

            new Dialogue("Alice",
                "Ele está fazendo isso de propósito."),

            new Dialogue("Leo",
                "Eu sei."),

            new Dialogue("Dealer",
                "Finalmente estamos aprendendo."),

            new Dialogue("Alice",
                "Ele queria que vocês brigassem."),

            new Dialogue("Leo",
                "E conseguiu."
        };
    }

    // ============================================================
    // 12 - ACIDENTE
    // ============================================================

    private Dialogue[] accident() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Depois disso, ele foi embora."),

            new Dialogue("Leo",
                "Eu lembro."),

            new Dialogue("Alice",
                "Você lembra de verdade?"),

            new Dialogue("Leo",
                "Lembro do telefone."),

            new Dialogue("Alice",
                "Eu lembro da estrada."),

            new Dialogue("Dealer",
                "Não precisamos falar disso."),

            new Dialogue("Alice",
                "Por quê?"),

            new Dialogue("Dealer",
                "Porque algumas coisas não precisam ser repetidas."),

            new Dialogue("Leo",
                "Você parece nervoso."),

            new Dialogue("Dealer",
                "Eu não fico nervoso."),

            new Dialogue("Alice",
                "Você acabou de ficar."),

            new Dialogue("Dealer",
                "Parabéns."),

            new Dialogue("Leo",
                "Ele morreu naquele acidente."),

            new Dialogue("Alice",
                "Sim."),

            new Dialogue("Leo",
                "E você estava lá."),

            new Dialogue("Alice",
                "Sim."),

            new Dialogue("Dealer",
                "Próxima rodada."),

            new Dialogue("Leo",
                "Você não consegue esconder para sempre."
        };
    }

    // ============================================================
    // 13 - DEALER MANIPULANDO
    // ============================================================

    private Dialogue[] dealerManipulation() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "Leo, ela não confia em você."),

            new Dialogue("Alice",
                "Não escuta ele."),

            new Dialogue("Dealer",
                "Alice, ele sabia."),

            new Dialogue("Leo",
                "Sabia o quê?"),

            new Dialogue("Dealer",
                "Você vai descobrir."),

            new Dialogue("Alice",
                "Ele está tentando nos separar."),

            new Dialogue("Dealer",
                "Não preciso tentar."),

            new Dialogue("Leo",
                "Por que você quer isso?"),

            new Dialogue("Dealer",
                "Porque juntos vocês são inconvenientes."),

            new Dialogue("Alice",
                "Para quem?"),

            new Dialogue("Dealer",
                "Para mim."),

            new Dialogue("Leo",
                "Finalmente uma resposta honesta."),

            new Dialogue("Dealer",
                "Não se acostume."),

            new Dialogue("Alice",
                "Ele quer que eu desconfie de você."),

            new Dialogue("Leo",
                "Então não dê esse prazer a ele."),

            new Dialogue("Dealer",
                "Vocês estão ficando amigos."),

            new Dialogue("Alice",
                "Talvez."),

            new Dialogue("Dealer",
                "Isso vai ser um problema."
        };
    }

    // ============================================================
    // 14 - DEALER PROVOCANDO
    // ============================================================

    private Dialogue[] dealerProvoking() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "Vamos lá, Leo. Você pode fazer melhor."),

            new Dialogue("Dealer",
                "Ou talvez não."),

            new Dialogue("Dealer",
                "Alice, olha a cara dele."),

            new Dialogue("Alice",
                "Para."),

            new Dialogue("Dealer",
                "Você está defendendo ele agora?"),

            new Dialogue("Alice",
                "Estou defendendo a minha paciência."),

            new Dialogue("Dealer",
                "Que pena. Eu queria destruir as duas."),

            new Dialogue("Leo",
                "Você é sempre assim?"),

            new Dialogue("Dealer",
                "Pior quando estou feliz."),

            new Dialogue("Alice",
                "Então você está feliz demais."),

            new Dialogue("Dealer",
                "Eu estou me divertindo."),

            new Dialogue("Leo",
                "Isso vai acabar."),

            new Dialogue("Dealer",
                "Tudo acaba."),

            new Dialogue("Alice",
                "Você fala como se estivesse esperando isso."),

            new Dialogue("Dealer",
                "Talvez eu esteja."),

            new Dialogue("Leo",
                "Você tem medo do fim."),

            new Dialogue("Dealer",
                "Eu tenho medo de ficar sozinho."),

            new Dialogue("Alice",
                "Finalmente algo verdadeiro."
        };
    }

    // ============================================================
    // 15 - CONFIANÇA
    // ============================================================

    private Dialogue[] trust() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Eu acho que posso confiar em você."),

            new Dialogue("Leo",
                "Isso é bom?"),

            new Dialogue("Alice",
                "Não sei."),

            new Dialogue("Leo",
                "Eu também não."),

            new Dialogue("Alice",
                "Você poderia ter mentido."),

            new Dialogue("Leo",
                "Eu poderia."),

            new Dialogue("Alice",
                "Mas não mentiu."),

            new Dialogue("Dealer",
                "Que emocionante."),

            new Dialogue("Alice",
                "Você está com ciúmes?"),

            new Dialogue("Dealer",
                "Eu? Nunca."),

            new Dialogue("Leo",
                "Ele está."),

            new Dialogue("Dealer",
                "Continuem. Estou adorando."),

            new Dialogue("Alice",
                "Eu lembro dele agora."),

            new Dialogue("Leo",
                "Do meu irmão?"),

            new Dialogue("Alice",
                "Sim."),

            new Dialogue("Leo",
                "Sinto muito."),

            new Dialogue("Alice",
                "Eu também."),

            new Dialogue("Dealer",
                "Não deveriam."
        };
    }

    // ============================================================
    // 16 - CONFRONTO
    // ============================================================

    private Dialogue[] confrontation() {

        return new Dialogue[] {

            new Dialogue("Leo",
                "Você é meu irmão."),

            new Dialogue("Dealer",
                "Finalmente."),

            new Dialogue("Alice",
                "Eu sabia."),

            new Dialogue("Dealer",
                "Parabéns para os dois."),

            new Dialogue("Leo",
                "Você morreu."),

            new Dialogue("Dealer",
                "Eu sei."),

            new Dialogue("Leo",
                "Então por que está aqui?"),

            new Dialogue("Dealer",
                "Porque eu não fui embora."),

            new Dialogue("Alice",
                "Você me odeia?"),

            new Dialogue("Dealer",
                "Não."),

            new Dialogue("Alice",
                "Então por quê?"),

            new Dialogue("Dealer",
                "Porque eu não sabia como dizer adeus."),

            new Dialogue("Leo",
                "E decidiu prender todo mundo com você."),

            new Dialogue("Dealer",
                "Foi uma péssima decisão."),

            new Dialogue("Alice",
                "Pelo menos você admite."),

            new Dialogue("Dealer",
                "Não significa que vou parar."),

            new Dialogue("Leo",
                "Nós vamos parar você."),

            new Dialogue("Dealer",
                "Tentem."
        };
    }

    // ============================================================
    // 17 - REDENÇÃO
    // ============================================================

    private Dialogue[] redemption() {

        return new Dialogue[] {

            new Dialogue("Leo",
                "Você não precisa continuar."),

            new Dialogue("Dealer",
                "Preciso."),

            new Dialogue("Leo",
                "Não."),

            new Dialogue("Dealer",
                "Você não entende."),

            new Dialogue("Alice",
                "Então explica."),

            new Dialogue("Dealer",
                "Eu não lembro como voltar."),

            new Dialogue("Leo",
                "Talvez não exista volta."),

            new Dialogue("Dealer",
                "Então o que existe?"),

            new Dialogue("Alice",
                "Fim."),

            new Dialogue("Dealer",
                "Essa palavra me assusta."),

            new Dialogue("Leo",
                "Por quê?"),

            new Dialogue("Dealer",
                "Porque eu passei tempo demais fugindo dela."),

            new Dialogue("Alice",
                "Você pode deixar a gente ir."),

            new Dialogue("Dealer",
                "E ficar sozinho?"),

            new Dialogue("Leo",
                "Dessa vez você não vai estar sozinho."),

            new Dialogue("Dealer",
                "Irmão..."),

            new Dialogue("Leo",
                "Estou aqui."),

            new Dialogue("Dealer",
                "Eu sinto muito."
        };
    }

    // ============================================================
    // 18 - RELÓGIO
    // ============================================================

    private Dialogue[] clock() {

        return new Dialogue[] {

            new Dialogue("Alice",
                "Que horas são?"),

            new Dialogue("Leo",
                "Não sei."),

            new Dialogue("Alice",
                "O relógio está andando."),

            new Dialogue("Dealer",
                "Claro que está."),

            new Dialogue("Leo",
                "Quanto tempo temos?"),

            new Dialogue("Dealer",
                "Menos do que vocês gostariam."),

            new Dialogue("Alice",
                "Isso não ajuda."),

            new Dialogue("Dealer",
                "Não era para ajudar."),

            new Dialogue("Leo",
                "O ponteiro pulou."),

            new Dialogue("Alice",
                "Ele está acelerando."),

            new Dialogue("Dealer",
                "O tempo fica impaciente."),

            new Dialogue("Leo",
                "Você também."),

            new Dialogue("Dealer",
                "Eu estou esperando há anos."),

            new Dialogue("Alice",
                "Esperando por quê?"),

            new Dialogue("Dealer",
                "Vocês."),

            new Dialogue("Leo",
                "Por que nós?"),

            new Dialogue("Dealer",
                "Porque vocês são as únicas pessoas que ainda lembram de mim."),

            new Dialogue("Alice",
                "Então talvez seja hora de lembrar direito."
        };
    }

    // ============================================================
    // 19 - FINAIS RUINS
    // ============================================================

    private Dialogue[] badEndings() {

        return new Dialogue[] {

            new Dialogue("Dealer",
                "A última carta."),

            new Dialogue("Alice",
                "Não."),

            new Dialogue("Leo",
                "Acabou."),

            new Dialogue("Dealer",
                "Acabou para alguém."),

            new Dialogue("Alice",
                "Eu não queria isso."),

            new Dialogue("Dealer",
                "Querer nunca foi suficiente."),

            new Dialogue("Leo",
                "Você conseguiu."),

            new Dialogue("Dealer",
                "Consegui o quê?"),

            new Dialogue("Leo",
                "Nos separar."),

            new Dialogue("Dealer",
                "Eu avisei que seria divertido."),

            new Dialogue("Alice",
                "A mesa está vazia."),

            new Dialogue("Dealer",
                "Então começamos outra."),

            new Dialogue("Leo",
                "Não existe outra."),

            new Dialogue("Dealer",
                "Existe enquanto eu lembrar."),

            new Dialogue("Alice",
                "Você ganhou."),

            new Dialogue("Dealer",
                "Não."),

            new Dialogue("Dealer",
                "Eu só consegui adiar a derrota."),

            new Dialogue("Dealer",
                "E esse é o pior tipo de vitória."
        };
    }

    // ============================================================
    // 20 - FINAIS BONS
    // ============================================================

    private Dialogue[] goodEndings() {

        return new Dialogue[] {

            new Dialogue("Leo",
                "Chega."),

            new Dialogue("Alice",
                "Chega."),

            new Dialogue("Dealer",
                "Vocês dois finalmente concordam."),

            new Dialogue("Leo",
                "Solta a gente."),

            new Dialogue("Dealer",
                "Eu não sei como."),

            new Dialogue("Alice",
                "Então aprende."),

            new Dialogue("Dealer",
                "Eu estou com medo."),

            new Dialogue("Leo",
                "Eu também."),

            new Dialogue("Dealer",
                "Você ainda me chama de irmão."),

            new Dialogue("Leo",
                "Porque você ainda é."),

            new Dialogue("Alice",
                "E você ainda é importante para mim."),

            new Dialogue("Dealer",
                "Depois de tudo?"),

            new Dialogue("Alice",
                "Depois de tudo."),

            new Dialogue("Dealer",
                "Então acabou."),

            new Dialogue("Leo",
                "Acabou."),

            new Dialogue("Dealer",
                "Obrigado por lembrarem de mim."),

            new Dialogue("Alice",
                "Adeus."),

            new Dialogue("Dealer",
                "Adeus."
        };
    }

    // ============================================================
    // TODAS AS FALAS
    // ============================================================

    public List<Dialogue> getAllDialogues() {

        List<Dialogue> all =
            new ArrayList<>();

        addGroup(
            all,
            firstContact()
        );

        addGroup(
            all,
            table()
        );

        addGroup(
            all,
            alice()
        );

        addGroup(
            all,
            leo()
        );

        addGroup(
            all,
            dealer()
        );

        addGroup(
            all,
            truco()
        );

        addGroup(
            all,
            suspicion()
        );

        addGroup(
            all,
            memories()
        );

        addGroup(
            all,
            brother()
        );

        addGroup(
            all,
            aliceAndBrother()
        );

        addGroup(
            all,
            family()
        );

        addGroup(
            all,
            accident()
        );

        addGroup(
            all,
            dealerManipulation()
        );

        addGroup(
            all,
            dealerProvoking()
        );

        addGroup(
            all,
            trust()
        );

        addGroup(
            all,
            confrontation()
        );

        addGroup(
            all,
            redemption()
        );

        addGroup(
            all,
            clock()
        );

        addGroup(
            all,
            badEndings()
        );

        addGroup(
            all,
            goodEndings()
        );

        return all;
    }

    // ============================================================
    // ADICIONAR GRUPO
    // ============================================================

    private void addGroup(
        List<Dialogue> target,
        Dialogue[] group
    ) {

        for (Dialogue dialogue : group) {

            target.add(dialogue);
        }
    }

    // ============================================================
    // BUSCAR POR FALA
    // ============================================================

    public Dialogue getRandomDialogue(
        String speaker
    ) {

        List<Dialogue> matching =
            new ArrayList<>();

        for (
            Dialogue dialogue :
            getAllDialogues()
        ) {

            if (
                dialogue
                    .getSpeaker()
                    .equalsIgnoreCase(
                        speaker
                    )
            ) {

                matching.add(
                    dialogue
                );
            }
        }

        if (matching.isEmpty()) {

            return new Dialogue(
                "???",
                "..."
            );
        }

        return matching.get(
            random.nextInt(
                matching.size()
            )
        );
    }

    // ============================================================
    // DIÁLOGOS POR ESTADO
    // ============================================================

    public Dialogue[] getForState(
        GameState.State state
    ) {

        if (state == null) {

            return firstContact();
        }

        switch (state) {

            case INTRO:

                return firstContact();

            case WAITING:

                return table();

            case PLAYER_TURN:

                return truco();

            case ALICE_TURN:

                return alice();

            case DEALER_TURN:

                return dealer();

            case TRUCO:

                return truco();

            case DIALOGUE:

                return memories();

            case REVELATION:

                return confrontation();

            case FINAL:

                return goodEndings();

            case GAME_OVER:

                return badEndings();

            default:

                return table();
        }
    }

    // ============================================================
    // DIÁLOGO CONDICIONAL
    // ============================================================

    public Dialogue[] getConditionalDialogues(
        GameState state,
        Girl alice
    ) {

        List<Dialogue> result =
            new ArrayList<>();

        if (
            state == null ||
            alice == null
        ) {

            return firstContact();
        }

        /*
         * Pouca confiança:
         * Alice mantém distância.
         */

        if (
            state.getAliceTrust() < 30
        ) {

            result.addAll(
                List.of(
                    alice()[0],
                    alice()[2],
                    alice()[4],
                    alice()[6],
                    alice()[10]
                )
            );
        }

        /*
         * Confiança média:
         */

        else if (
            state.getAliceTrust() < 70
        ) {

            result.addAll(
                List.of(
                    trust()[0],
                    trust()[4],
                    trust()[6],
                    trust()[12],
                    trust()[16]
                )
            );
        }

        /*
         * Confiança alta:
         */

        else {

            result.addAll(
                List.of(
                    trust()[0],
                    trust()[6],
                    trust()[12],
                    trust()[14],
                    redemption()[12]
                )
            );
        }

        /*
         * Suspeita alta:
         */

        if (
            state.getSuspicionLevel() >= 50
        ) {

            result.addAll(
                List.of(
                    suspicion()[0],
                    suspicion()[6],
                    suspicion()[12],
                    dealerManipulation()[8],
                    dealerManipulation()[13]
                )
            );
        }

        /*
         * Identidade descoberta:
         */

        if (
            state.discoveredDealersIdentity()
        ) {

            result.addAll(
                List.of(
                    confrontation()[0],
                    confrontation()[4],
                    confrontation()[6],
                    confrontation()[12],
                    confrontation()[16]
                )
            );
        }

        /*
         * Verdade descoberta:
         */

        if (
            state.discoveredTruth()
        ) {

            result.addAll(
                List.of(
                    redemption()[0],
                    redemption()[4],
                    redemption()[8],
                    redemption()[12],
                    redemption()[16]
                )
            );
        }

        if (result.isEmpty()) {

            return firstContact();
        }

        return result.toArray(
            new Dialogue[0]
        );
    }

    // ============================================================
    // CONTAGEM
    // ============================================================

    public int getDialogueCount() {

        return getAllDialogues().size();
    }

    // ============================================================
    // VERIFICAÇÃO
    // ============================================================

    public boolean hasExactly360Dialogues() {

        return getDialogueCount() == 360;
    }

    // ============================================================
    // DEBUG
    // ============================================================

    public void printDialogueCount() {

        System.out.println(
            "Diálogos carregados: "
            + getDialogueCount()
        );

        if (
            hasExactly360Dialogues()
        ) {

            System.out.println(
                "Banco de diálogos: OK"
            );

        } else {

            System.out.println(
                "ERRO: quantidade diferente de 360."
            );
        }
    }
}
```
