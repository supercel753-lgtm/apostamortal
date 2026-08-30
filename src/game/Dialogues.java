package game;

import java.util.Random;

/**
 * ================================================================
 *                         DIALOGUES
 * ================================================================
 *
 * Banco de diálogos de LAST CHANCE.
 *
 * As falas são propositalmente fragmentadas.
 * A história deve ser descoberta pelo jogador através de:
 *
 * - contradições
 * - lembranças
 * - provocações
 * - perguntas
 * - respostas incompletas
 * - silêncios
 * - mudanças de assunto
 *
 * PERSONAGENS:
 *
 * LEO      -> jogador
 * ALICE    -> adversária
 * DEALER   -> irmão de Leo / antigo namorado de Alice
 *
 * ================================================================
 */

public class Dialogues {

    private final Random random;

    public Dialogues() {

        random = new Random();
    }

    // ============================================================
    // INÍCIO DA PARTIDA
    // ============================================================

    public String intro01() {
        return "DEALER: Sentem-se.";
    }

    public String intro02() {
        return "ALICE: Eu não quero estar aqui.";
    }

    public String intro03() {
        return "DEALER: Ninguém quer.";
    }

    public String intro04() {
        return "LEO: Quem é você?";
    }

    public String intro05() {
        return "DEALER: Um funcionário muito mal pago.";
    }

    public String intro06() {
        return "ALICE: Você fala como se conhecesse a gente.";
    }

    public String intro07() {
        return "DEALER: Conhecer é uma palavra forte.";
    }

    public String intro08() {
        return "DEALER: Eu diria que tenho... familiaridade.";
    }

    public String intro09() {
        return "LEO: Familiaridade com o quê?";
    }

    public String intro10() {
        return "DEALER: Com pessoas tomando decisões ruins.";
    }

    // ============================================================
    // PRIMEIRAS PISTAS
    // ============================================================

    public String hint01() {
        return "DEALER: Você tem uma memória muito ruim, Leo.";
    }

    public String hint02() {
        return "LEO: Eu nunca te disse meu nome.";
    }

    public String hint03() {
        return "DEALER: Não precisava.";
    }

    public String hint04() {
        return "ALICE: Como ele sabe seu nome?";
    }

    public String hint05() {
        return "DEALER: Eu presto atenção.";
    }

    public String hint06() {
        return "LEO: Você me conhece?";
    }

    public String hint07() {
        return "DEALER: Mais do que deveria.";
    }

    public String hint08() {
        return "ALICE: Isso não faz sentido.";
    }

    public String hint09() {
        return "DEALER: Finalmente concordamos em alguma coisa.";
    }

    public String hint10() {
        return "LEO: Sua voz parece familiar.";
    }

    // ============================================================
    // O PASSADO COMEÇA A APARECER
    // ============================================================

    public String past01() {
        return "DEALER: Você costumava bater na porta antes de entrar.";
    }

    public String past02() {
        return "LEO: Eu ainda faço isso.";
    }

    public String past03() {
        return "DEALER: Não. Agora você só bate quando lembra.";
    }

    public String past04() {
        return "ALICE: Vocês eram amigos?";
    }

    public String past05() {
        return "DEALER: Mais ou menos.";
    }

    public String past06() {
        return "LEO: Você fala como alguém da minha família.";
    }

    public String past07() {
        return "DEALER: Família é uma palavra engraçada.";
    }

    public String past08() {
        return "DEALER: Às vezes significa amor.";
    }

    public String past09() {
        return "DEALER: Às vezes significa expulsão.";
    }

    public String past10() {
        return "LEO: O que você sabe sobre minha família?";
    }

    // ============================================================
    // O IRMÃO
    // ============================================================

    public String brother01() {
        return "DEALER: Você tinha um irmão.";
    }

    public String brother02() {
        return "LEO: Eu tenho.";
    }

    public String brother03() {
        return "DEALER: Tem certeza?";
    }

    public String brother04() {
        return "LEO: Não começa.";
    }

    public String brother05() {
        return "DEALER: Ele era mais velho?";
    }

    public String brother06() {
        return "LEO: Era.";
    }

    public String brother07() {
        return "DEALER: Engraçado.";
    }

    public String brother08() {
        return "LEO: O quê?";
    }

    public String brother09() {
        return "DEALER: Você fala dele no passado sem perceber.";
    }

    public String brother10() {
        return "ALICE: Você nunca falou dele comigo.";
    }

    public String brother11() {
        return "LEO: Eu não tinha motivo.";
    }

    public String brother12() {
        return "DEALER: Tinha vários.";
    }

    // ============================================================
    // SEGREDOS
    // ============================================================

    public String secret01() {
        return "DEALER: Ele tinha um lugar onde ninguém procurava.";
    }

    public String secret02() {
        return "LEO: Que lugar?";
    }

    public String secret03() {
        return "DEALER: O banco de uma praça.";
    }

    public String secret04() {
        return "LEO: Como você sabe disso?";
    }

    public String secret05() {
        return "DEALER: Ele gostava de ficar lá.";
    }

    public String secret06() {
        return "ALICE: Sozinho?";
    }

    public String secret07() {
        return "DEALER: Nem sempre.";
    }

    public String secret08() {
        return "LEO: Ele encontrava alguém?";
    }

    public String secret09() {
        return "DEALER: Finalmente.";
    }

    public String secret10() {
        return "DEALER: Uma pergunta interessante.";
    }

    // ============================================================
    // ALICE COMEÇA A RECONHECER DETALHES
    // ============================================================

    public String alice01() {
        return "ALICE: Ele gostava daquela praça.";
    }

    public String alice02() {
        return "LEO: Como você sabe?";
    }

    public String alice03() {
        return "ALICE: Eu... ouvi falar.";
    }

    public String alice04() {
        return "DEALER: Péssima mentira.";
    }

    public String alice05() {
        return "ALICE: Cala a boca.";
    }

    public String alice06() {
        return "DEALER: Aí está.";
    }

    public String alice07() {
        return "LEO: Alice...";
    }

    public String alice08() {
        return "ALICE: Não olha para mim desse jeito.";
    }

    public String alice09() {
        return "LEO: Você conhecia meu irmão?";
    }

    public String alice10() {
        return "ALICE: Eu não disse isso.";
    }

    public String alice11() {
        return "DEALER: Mas também não negou.";
    }

    public String alice12() {
        return "ALICE: Eu odeio quando você faz isso.";
    }

    // ============================================================
    // O RELACIONAMENTO
    // ============================================================

    public String relationship01() {
        return "LEO: Vocês estavam juntos?";
    }

    public String relationship02() {
        return "ALICE: ...";
    }

    public String relationship03() {
        return "DEALER: Estavam.";
    }

    public String relationship04() {
        return "LEO: Por quanto tempo?";
    }

    public String relationship05() {
        return "ALICE: Tempo suficiente.";
    }

    public String relationship06() {
        return "LEO: Minha mãe sabia?";
    }

    public String relationship07() {
        return "ALICE: Não.";
    }

    public String relationship08() {
        return "LEO: E eu?";
    }

    public String relationship09() {
        return "ALICE: Você descobriu.";
    }

    public String relationship10() {
        return "LEO: Como?";
    }

    public String relationship11() {
        return "ALICE: Você encontrou as mensagens.";
    }

    public String relationship12() {
        return "LEO: Eu lembro disso.";
    }

    // ============================================================
    // AS MENSAGENS
    // ============================================================

    public String messages01() {
        return "LEO: Eu achei que ele estivesse escondendo alguma coisa.";
    }

    public String messages02() {
        return "DEALER: Ele estava.";
    }

    public String messages03() {
        return "LEO: Eu achei que fosse algo pior.";
    }

    public String messages04() {
        return "ALICE: Para mim não era.";
    }

    public String messages05() {
        return "LEO: Eu mostrei as mensagens para a mãe.";
    }

    public String messages06() {
        return "ALICE: Eu sei.";
    }

    public String messages07() {
        return "LEO: Eu não sabia quem você era.";
    }

    public String messages08() {
        return "ALICE: Eu sei.";
    }

    public String messages09() {
        return "LEO: Eu não queria destruir nada.";
    }

    public String messages10() {
        return "ALICE: Mas destruiu.";
    }

    public String messages11() {
        return "DEALER: Essa parte sempre foi minha favorita.";
    }

    public String messages12() {
        return "LEO: Você não tem o direito de falar disso.";
    }

    // ============================================================
    // A EXPULSÃO
    // ============================================================

    public String expelled01() {
        return "DEALER: Sua mãe ficou furiosa.";
    }

    public String expelled02() {
        return "LEO: Ela expulsou ele.";
    }

    public String expelled03() {
        return "DEALER: Sim.";
    }

    public String expelled04() {
        return "LEO: Eu tentei explicar.";
    }

    public String expelled05() {
        return "DEALER: Depois.";
    }

    public String expelled06() {
        return "LEO: Eu era criança.";
    }

    public String expelled07() {
        return "DEALER: Eu sei.";
    }

    public String expelled08() {
        return "LEO: Então por que você está me culpando?";
    }

    public String expelled09() {
        return "DEALER: Porque você quer ser culpado.";
    }

    public String expelled10() {
        return "LEO: Não quero.";
    }

    public String expelled11() {
        return "DEALER: Então pare de agir como se quisesse.";
    }

    public String expelled12() {
        return "ALICE: Ele não sabia o que ia acontecer.";
    }

    // ============================================================
    // O TÉRMINO
    // ============================================================

    public String breakup01() {
        return "LEO: O que aconteceu depois?";
    }

    public String breakup02() {
        return "ALICE: Eu terminei com ele.";
    }

    public String breakup03() {
        return "LEO: Por quê?";
    }

    public String breakup04() {
        return "ALICE: Porque tudo ficou complicado.";
    }

    public String breakup05() {
        return "DEALER: Mentira.";
    }

    public String breakup06() {
        return "ALICE: Não é mentira.";
    }

    public String breakup07() {
        return "DEALER: Você estava com medo.";
    }

    public String breakup08() {
        return "ALICE: Eu estava cansada.";
    }

    public String breakup09() {
        return "DEALER: Você chorou naquela noite.";
    }

    public String breakup10() {
        return "ALICE: Você não estava lá.";
    }

    public String breakup11() {
        return "DEALER: Eu estava.";
    }

    public String breakup12() {
        return "ALICE: Não depois.";
    }

    // ============================================================
    // O ACIDENTE
    // ============================================================

    public String accident01() {
        return "LEO: E então aconteceu o acidente.";
    }

    public String accident02() {
        return "ALICE: Sim.";
    }

    public String accident03() {
        return "LEO: Meu irmão morreu naquela noite.";
    }

    public String accident04() {
        return "ALICE: Sim.";
    }

    public String accident05() {
        return "LEO: E você sobreviveu.";
    }

    public String accident06() {
        return "ALICE: Sobrevivi.";
    }

    public String accident07() {
        return "LEO: Eu nunca soube que você estava lá.";
    }

    public String accident08() {
        return "ALICE: Quase ninguém soube.";
    }

    public String accident09() {
        return "DEALER: Algumas pessoas preferem histórias incompletas.";
    }

    public String accident10() {
        return "LEO: Você sabia de tudo.";
    }

    public String accident11() {
        return "DEALER: Eu sempre soube.";
    }

    public String accident12() {
        return "ALICE: Eu não quero falar daquela noite.";

    }

    // ============================================================
    // ALICE E O ESPELHO
    // ============================================================

    public String appearance01() {
        return "LEO: Alice...";
    }

    public String appearance02() {
        return "ALICE: Não.";
    }

    public String appearance03() {
        return "LEO: Eu só ia perguntar.";
    }

    public String appearance04() {
        return "ALICE: Eu sei o que você ia perguntar.";
    }

    public String appearance05() {
        return "DEALER: Ela não era assim antes.";
    }

    public String appearance06() {
        return "ALICE: Obrigada pela delicadeza.";
    }

    public String appearance07() {
        return "DEALER: Estou apenas sendo honesto.";
    }

    public String appearance08() {
        return "ALICE: Você nunca foi delicado.";
    }

    public String appearance09() {
        return "LEO: Eu não reconheci você.";
    }

    public String appearance10() {
        return "ALICE: Eu também demorei.";
    }

    public String appearance11() {
        return "LEO: Você mudou muito.";
    }

    public String appearance12() {
        return "ALICE: Todo mundo muda.";

    }

    // ============================================================
    // CULPA
    // ============================================================

    public String guilt01() {
        return "LEO: Se eu não tivesse contado...";
    }

    public String guilt02() {
        return "ALICE: Não.";
    }

    public String guilt03() {
        return "LEO: Mas se eu não tivesse...";
    }

    public String guilt04() {
        return "ALICE: Você não sabia.";
    }

    public String guilt05() {
        return "DEALER: Ele sabia menos ainda.";
    }

    public String guilt06() {
        return "LEO: Para.";
    }

    public String guilt07() {
        return "DEALER: Você queria uma resposta.";
    }

    public String guilt08() {
        return "LEO: Eu queria esquecer.";
    }

    public String guilt09() {
        return "DEALER: Péssima estratégia.";
    }

    public String guilt10() {
        return "ALICE: Não foi sua culpa.";
    }

    public String guilt11() {
        return "LEO: Então por que parece que foi?";
    }

    public String guilt12() {
        return "ALICE: Porque algumas coisas não precisam ser culpa de ninguém.";

    }

    // ============================================================
    // DEALER REVELA QUEM É
    // ============================================================

    public String reveal01() {
        return "DEALER: Vocês demoraram bastante.";
    }

    public String reveal02() {
        return "LEO: Para quê?";
    }

    public String reveal03() {
        return "DEALER: Para perceber.";
    }

    public String reveal04() {
        return "LEO: Perceber o quê?";
    }

    public String reveal05() {
        return "DEALER: Que eu não sou um estranho.";
    }

    public String reveal06() {
        return "ALICE: Não...";
    }

    public String reveal07() {
        return "DEALER: Oi, Alice.";
    }

    public String reveal08() {
        return "ALICE: Você morreu.";
    }

    public String reveal09() {
        return "DEALER: Eu sei.";
    }

    public String reveal10() {
        return "LEO: ...";
    }

    public String reveal11() {
        return "DEALER: Sentiu minha falta, irmãozinho?";
    }

    public String reveal12() {
        return "LEO: Você é ele.";

    }

    // ============================================================
    // CONFIRMAÇÃO
    // ============================================================

    public String confirmation01() {
        return "DEALER: Finalmente.";
    }

    public String confirmation02() {
        return "LEO: Meu irmão.";
    }

    public String confirmation03() {
        return "DEALER: Seu irmão.";
    }

    public String confirmation04() {
        return "ALICE: Meu namorado.";
    }

    public String confirmation05() {
        return "DEALER: Ex-namorado.";
    }

    public String confirmation06() {
        return "ALICE: Você continua sendo insuportável.";
    }

    public String confirmation07() {
        return "DEALER: Algumas coisas não mudam.";
    }

    public String confirmation08() {
        return "LEO: Por que você voltou?";
    }

    public String confirmation09() {
        return "DEALER: Porque aparentemente eu tinha negócios inacabados.";
    }

    public String confirmation10() {
        return "LEO: Isso é algum tipo de punição?";
    }

    public String confirmation11() {
        return "DEALER: Não.";
    }

    public String confirmation12() {
        return "DEALER: É ressentimento.";

    }

    // ============================================================
    // DEALER SENDO UM DESGRAÇADO
    // ============================================================

    public String asshole01() {
        return "DEALER: Vamos falar de coisas felizes.";
    }

    public String asshole02() {
        return "ALICE: Você acabou de falar da pior época da minha vida.";
    }

    public String asshole03() {
        return "DEALER: Eu disse felizes.";
    }

    public String asshole04() {
        return "LEO: Você é sempre assim?";
    }

    public String asshole05() {
        return "DEALER: Morto?";
    }

    public String asshole06() {
        return "LEO: Irritante.";
    }

    public String asshole07() {
        return "DEALER: Também.";
    }

    public String asshole08() {
        return "ALICE: Ele era assim vivo.";
    }

    public String asshole09() {
        return "LEO: Então nada mudou.";
    }

    public String asshole10() {
        return "DEALER: Eu diria que melhorei.";
    }

    public String asshole11() {
        return "ALICE: Você ficou pior.";
    }

    public String asshole12() {
        return "DEALER: Obrigado.";

    }

    // ============================================================
    // PROVOCAÇÕES
    // ============================================================

    public String provocation01() {
        return "DEALER: Vamos ver quem conhece quem.";
    }

    public String provocation02() {
        return "DEALER: Você acha que conhece Alice?";
    }

    public String provocation03() {
        return "LEO: Não.";
    }

    public String provocation04() {
        return "DEALER: Ótimo.";
    }

    public String provocation05() {
        return "DEALER: Ela também não conhece você.";
    }

    public String provocation06() {
        return "ALICE: Eu conheço o suficiente.";
    }

    public String provocation07() {
        return "DEALER: Isso foi exatamente o que você disse antes.";
    }

    public String provocation08() {
        return "ALICE: Eu me arrependo.";
    }

    public String provocation09() {
        return "DEALER: De muita coisa.";
    }

    public String provocation10() {
        return "LEO: Você quer que a gente brigue.";
    }

    public String provocation11() {
        return "DEALER: Eu quero que vocês joguem.";
    }

    public String provocation12() {
        return "DEALER: Se brigarem no processo, melhor ainda.";

    }

    // ============================================================
    // MOMENTOS DE SILÊNCIO
    // ============================================================

    public String silence01() {
        return "...";
    }

    public String silence02() {
        return "O Dealer não responde.";
    }

    public String silence03() {
        return "Alice olha para as próprias cartas.";
    }

    public String silence04() {
        return "Leo percebe que ninguém está sorrindo.";
    }

    public String silence05() {
        return "O relógio continua funcionando.";
    }

    public String silence06() {
        return "Ninguém fala por alguns segundos.";
    }

    public String silence07() {
        return "O Dealer finalmente quebra o silêncio.";
    }

    public String silence08() {
        return "DEALER: Bom... isso ficou estranho.";

    }

    // ============================================================
    // FALAS SOBRE O JOGO
    // ============================================================

    public String game01() {
        return "DEALER: Truco.";
    }

    public String game02() {
        return "DEALER: Seis.";
    }

    public String game03() {
        return "DEALER: Nove.";
    }

    public String game04() {
        return "DEALER: Doze.";
    }

    public String game05() {
        return "DEALER: Não faça isso.";
    }

    public String game06() {
        return "DEALER: Faça isso.";
    }

    public String game07() {
        return "DEALER: Eu não disse nada.";
    }

    public String game08() {
        return "DEALER: Você está pensando demais.";
    }

    public String game09() {
        return "DEALER: Ela está blefando.";
    }

    public String game10() {
        return "DEALER: Talvez.";
    }

    public String game11() {
        return "DEALER: Você vai se arrepender.";
    }

    public String game12() {
        return "DEALER: Ou não.";

    }

    // ============================================================
    // RELÓGIO
    // ============================================================

    public String clock01() {
        return "DEALER: Olha a hora.";
    }

    public String clock02() {
        return "DEALER: O tempo está acabando.";
    }

    public String clock03() {
        return "ALICE: Quanto falta?";
    }

    public String clock04() {
        return "DEALER: Menos do que você gostaria.";
    }

    public String clock05() {
        return "LEO: O que acontece quando chegar ao fim?";
    }

    public String clock06() {
        return "DEALER: Você descobre.";
    }

    public String clock07() {
        return "LEO: Essa resposta está ficando irritante.";
    }

    public String clock08() {
        return "DEALER: Essa é a intenção.";

    }

    // ============================================================
    // ÚLTIMAS PISTAS
    // ============================================================

    public String finalHint01() {
        return "DEALER: Você contou para sua mãe.";
    }

    public String finalHint02() {
        return "LEO: Eu sei.";
    }

    public String finalHint03() {
        return "DEALER: Ela expulsou seu irmão.";
    }

    public String finalHint04() {
        return "LEO: Eu sei.";
    }

    public String finalHint05() {
        return "DEALER: Alice terminou com ele.";
    }

    public String finalHint06() {
        return "ALICE: Eu sei.";
    }

    public String finalHint07() {
        return "DEALER: Depois veio o acidente.";
    }

    public String finalHint08() {
        return "LEO: Para.";
    }

    public String finalHint09() {
        return "DEALER: Agora você lembra.";
    }

    public String finalHint10() {
        return "LEO: Eu queria não lembrar.";
    }

    public String finalHint11() {
        return "ALICE: Eu também.";
    }

    public String finalHint12() {
        return "DEALER: E mesmo assim vocês continuam jogando.";

    }

    // ============================================================
    // DIÁLOGOS ALEATÓRIOS
    // ============================================================

    public String randomDealerLine() {

        int choice =
            random.nextInt(30);

        switch (choice) {

            case 0:
                return "DEALER: Você está demorando.";

            case 1:
                return "DEALER: Interessante.";

            case 2:
                return "DEALER: Eu não faria isso.";

            case 3:
                return "DEALER: Eu faria isso.";

            case 4:
                return "DEALER: Talvez eu esteja mentindo.";

            case 5:
                return "DEALER: Talvez não.";

            case 6:
                return "DEALER: Não olha para mim.";

            case 7:
                return "DEALER: Continua.";

            case 8:
                return "DEALER: Você lembra daquela noite?";

            case 9:
                return "DEALER: Esquece que eu falei.";

            case 10:
                return "DEALER: Alice, sua vez.";

            case 11:
                return "DEALER: Leo, sua vez.";

            case 12:
                return "DEALER: Vocês são muito previsíveis.";

            case 13:
                return "DEALER: Isso foi quase inteligente.";

            case 14:
                return "DEALER: Quase.";

            case 15:
                return "DEALER: Eu estou impressionado.";

            case 16:
                return "DEALER: Não estou.";

            case 17:
                return "DEALER: Isso vai dar errado.";

            case 18:
                return "DEALER: Você sabe disso.";

            case 19:
                return "DEALER: Não confie em mim.";

            case 20:
                return "DEALER: Finalmente alguém disse algo sensato.";

            case 21:
                return "DEALER: Eu deveria ganhar comissão por isso.";

            case 22:
                return "DEALER: Ser fantasma não paga bem.";

            case 23:
                return "DEALER: Eu já tive empregos melhores.";

            case 24:
                return "DEALER: Eu sinto falta de dormir.";

            case 25:
                return "DEALER: Tecnicamente eu não durmo mais.";

            case 26:
                return "DEALER: Que situação maravilhosa.";

            case 27:
                return "DEALER: Estou começando a me arrepender.";

            case 28:
                return "DEALER: Mentira. Não estou.";

            default:
                return "DEALER: Continuem.";

        }
    }

    // ============================================================
    // DIÁLOGO QUANDO UMA MÃO MUITO FORTE APARECE
    // ============================================================

    public String instantWinReaction(
        boolean player
    ) {

        if (player) {

            return
                "DEALER: Leo...\n" +
                "DEALER: olha suas cartas.\n" +
                "DEALER: Você realmente recebeu isso.";
        }

        return
            "DEALER: Alice...\n" +
            "DEALER: eu acho que você acabou de virar o jogo.";
    }

    // ============================================================
    // DIÁLOGO DE FINAL
    // ============================================================

    public String ending01() {
        return "DEALER: Então é isso.";
    }

    public String ending02() {
        return "LEO: Acabou?";
    }

    public String ending03() {
        return "DEALER: A partida acabou.";
    }

    public String ending04() {
        return "ALICE: E agora?";
    }

    public String ending05() {
        return "DEALER: Agora vocês descobrem o que ganharam.";
    }

    public String ending06() {
        return "LEO: Você disse que haveria uma recompensa.";
    }

    public String ending07() {
        return "DEALER: Eu disse que haveria uma recompensa.";
    }

    public String ending08() {
        return "ALICE: Isso não é a mesma coisa.";
    }

    public String ending09() {
        return "DEALER: Agora você está aprendendo.";
    }

    public String ending10() {
        return "DEALER: Nunca confie em um Dealer.";
    }

    // ============================================================
    // MÉTODO PARA BUSCAR UMA FALA POR ID
    // ============================================================

    public String getDialogue(
        String id
    ) {

        switch (id) {

            case "intro01":
                return intro01();

            case "intro02":
                return intro02();

            case "intro03":
                return intro03();

            case "intro04":
                return intro04();

            case "intro05":
                return intro05();

            case "intro06":
                return intro06();

            case "intro07":
                return intro07();

            case "intro08":
                return intro08();

            case "intro09":
                return intro09();

            case "intro10":
                return intro10();

            case "past01":
                return past01();

            case "past02":
                return past02();

            case "past03":
                return past03();

            case "past04":
                return past04();

            case "past05":
                return past05();

            case "past06":
                return past06();

            case "past07":
                return past07();

            case "past08":
                return past08();

            case "past09":
                return past09();

            case "past10":
                return past10();

            case "brother01":
                return brother01();

            case "brother02":
                return brother02();

            case "brother03":
                return brother03();

            case "brother04":
                return brother04();

            case "brother05":
                return brother05();

            case "brother06":
                return brother06();

            case "brother07":
                return brother07();

            case "brother08":
                return brother08();

            case "brother09":
                return brother09();

            case "brother10":
                return brother10();

            case "brother11":
                return brother11();

            case "brother12":
                return brother12();

            case "secret01":
                return secret01();

            case "secret02":
                return secret02();

            case "secret03":
                return secret03();

            case "secret04":
                return secret04();

            case "secret05":
                return secret05();

            case "secret06":
                return secret06();

            case "secret07":
                return secret07();

            case "secret08":
                return secret08();

            case "secret09":
                return secret09();

            case "secret10":
                return secret10();

            case "reveal01":
                return reveal01();

            case "reveal02":
                return reveal02();

            case "reveal03":
                return reveal03();

            case "reveal04":
                return reveal04();

            case "reveal05":
                return reveal05();

            case "reveal06":
                return reveal06();

            case "reveal07":
                return reveal07();

            case "reveal08":
                return reveal08();

            case "reveal09":
                return reveal09();

            case "reveal10":
                return reveal10();

            case "reveal11":
                return reveal11();

            case "reveal12":
                return reveal12();

            case "ending01":
                return ending01();

            case "ending02":
                return ending02();

            case "ending03":
                return ending03();

            case "ending04":
                return ending04();

            case "ending05":
                return ending05();

            case "ending06":
                return ending06();

            case "ending07":
                return ending07();

            case "ending08":
                return ending08();

            case "ending09":
                return ending09();

            case "ending10":
                return ending10();

            default:
                return "...";
        }
    }
}
