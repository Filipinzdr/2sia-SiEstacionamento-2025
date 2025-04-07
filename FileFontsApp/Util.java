import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Util {
    private Bilhete[] bilhete = new Bilhete[4];
    private int index = 0;

    public void menuPrincipal() {
        int op;
        String menu = "1. Administrador\n2. Usúario\n3. Finalizar\n";
        do {
            op = parseInt(showInputDialog(menu));
            if (op < 1 || op > 3) {
                showMessageDialog(null, "Opção inválida");
            }
            if (op == 1) {
                menuADM();

            }
            if (op == 2) {
                menuUsuario();
            }
        } while (op != 3);
    }

    private void menuADM() {
        int op;
        String opcoes = "1. Emitir bilhete\n2.Listar bilhetes\n3.Remover bilhete\n4.Sair\n";
        do {
            op = parseInt(showInputDialog(opcoes));
            if (op < 1 || op > 4) {
                showMessageDialog(null, "Opção inválida");
            }
            if (op == 1) {
                emitirBilhete();

            }
            if (op == 2) {
                listarBilhetes();
            }
            if (op == 3) {
                removerBilhete();
            }
        } while (op != 4);
    }

    private void menuUsuario() {
        int opcao;
        String menu = "1. Consultar saldo\n2. Carregar bilhete\n3. Passar na catraca\n4. Sair";
        do {
            opcao = parseInt(showInputDialog(menu));
            if(opcao < 1 || opcao > 4){
                showMessageDialog(null, "Opção inválida");
            }
            if (opcao == 1) {
                consultarSaldo();
            }
            if (opcao == 2) {
                carregarBilhete();
            }
            if (opcao == 3){
                passarCatraca();
            }
        }while(opcao != 4);

    }

    private void passarCatraca() {
        int posicao = pesquisar();
        double valor;
        if (posicao != -1){
            showMessageDialog(null, bilhete[posicao].passarCatraca());
        }
    }

    private void carregarBilhete() {
        int posicao = pesquisar();
        double valor;
        if (posicao != -1){
            valor = parseDouble(showInputDialog("Valor para carregar o bilhete"));
            bilhete[posicao].carregarBilhete(valor);
        }
    }

    private void consultarSaldo() {
        int posicao = pesquisar();
        double valor;
        if (posicao != -1){
            showMessageDialog(null, "Saldo R$: " + bilhete[posicao].verSaldo());

        }
    }

    private void removerBilhete() {
        int i = pesquisar();
        if (i == -1){return;};
        for (; i < index-1; i++) {
            bilhete[i] = bilhete[(i+1)];
        }
        bilhete[index-1] = null;
        index--;
    }

    private void emitirBilhete() {
        String nome, perfil;
        long cpf;
        if (bilheteEmpty()) {
            showMessageDialog(null, "Procure a administração");
            return;
        }
        nome = showInputDialog(("Nome"));
        cpf = parseLong(showInputDialog("CPF"));
        perfil = showInputDialog("Perfil --> Estudante, Professor ou comum");
        Usuario u = new Usuario(cpf, nome, perfil);
        bilhete[index] = new Bilhete(new Usuario(cpf, nome, perfil));
        index++;

    }

    private boolean bilheteEmpty() {
        if (index >= bilhete.length) {
            return true;
        } else {
            return false;
        }
    }

    private void listarBilhetes() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "";
        for (int i = 0; i < index; i++) {
            aux += "ID do bilhete: " + bilhete[i].numero + "\n";
            aux += "Nome: " + bilhete[i].usuario.nome + "\n";
            aux += "CPF: " + bilhete[i].usuario.cpf + "\n";
            aux += "Saldo: R$" + df.format(bilhete[i].saldo) + "\n";
            aux += "Perfil (tipo de tarifa)" + bilhete[i].usuario.perfil + "\n";

            showMessageDialog(null, aux);

        }
    }

    private int pesquisar() {
        long cpf = parseLong(showInputDialog("Digite o cpf"));
        for (int i = 0; i < index; i++) {
            if(bilhete[i].usuario.cpf == cpf){
                return i;
            }
        }
        showMessageDialog(null, cpf + "não encontrado");
        return -1;
    }





}
